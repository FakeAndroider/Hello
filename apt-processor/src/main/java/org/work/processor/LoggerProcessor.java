package org.work.processor;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import org.work.annotation.Logger;
import org.work.Helper;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

import javax.lang.model.element.Element;

@SupportedAnnotationTypes("org.work.annotation.Logger")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class LoggerProcessor extends AbstractProcessor {

    private boolean isProcess = false;
    final private String packageName = "com.orhanobut.logger";

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        HashSet<String> set = new HashSet<>();
        set.add(Logger.class.getCanonicalName());
        return set;
    }


    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (!isProcess) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "=====>Logger APT");

            Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(Logger.class);
            if (!elements.isEmpty()) {
                Element e = (Element) elements.toArray()[0];
                if (e.getKind() == ElementKind.METHOD) {
                    isProcess = true;
                    onBuildLogger(e);
                }

            }

        }

        return true;
    }

    private void onBuildLogger(final Element e) {

        final TypeSpec loggerClass = TypeSpec.classBuilder("log")
                .addModifiers(Modifier.PUBLIC)
                .addMethod(MethodSpec.methodBuilder("onInit")
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                        .returns(void.class)
                        .addStatement(
                                "  final $T formatStrategy = $T.newBuilder()\n" +
                                        "                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true\n" +
                                        "                .methodCount(0)         // (Optional) How many method line to show. Default 2\n" +
                                        "                .methodOffset(0)        // (Optional) Hides internal method calls up to offset. Default 5\n" +
                                        "                .tag(\"Worker\")   // (Optional) Global tag for every log. Default PRETTY_LOGGER\n" +
                                        "                .build();\n" +
                                        "\n" +
                                        "$T.addLogAdapter(new $T(formatStrategy));",
                                Helper.T(packageName, "FormatStrategy"),
                                Helper.T(packageName, "PrettyFormatStrategy"),
                                Helper.T(packageName, "Logger"),
                                Helper.T(packageName, "AndroidLogAdapter")
                        )
                        .build())
                .addMethod(MethodSpec.methodBuilder("d")
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                        .addParameter(Object.class, "val")
                        .addStatement("$T.d(val)", Helper.T(packageName, "Logger"))
                        .build())
                .addMethod(MethodSpec.methodBuilder("e")
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                        .addParameter(String.class, "val")
                        .addStatement("$T.e(val)", Helper.T(packageName, "Logger"))
                        .build())
                .addMethod(MethodSpec.methodBuilder("json")
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                        .addParameter(String.class, "val")
                        .addStatement("$T.json(val)", Helper.T(packageName, "Logger"))
                        .build())
                .build();

        ExecutableElement method = (ExecutableElement) e;
        final TypeElement classElement = (TypeElement) method.getEnclosingElement();

        final String packageName = processingEnv.getElementUtils().getPackageOf(classElement).getQualifiedName().toString();
        JavaFile javaFile = JavaFile.builder(packageName, loggerClass)
                .build();

        try {
            javaFile.writeTo(processingEnv.getFiler());
        } catch (IOException exception) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, exception.toString());
        }
    }


}
