package org.work.processor;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import org.work.Helper;
import org.work.annotation.DataBing;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;

@SupportedAnnotationTypes("org.work.annotation.Logger")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class DataBingProcessor extends AbstractProcessor {
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        HashSet<String> set = new HashSet<>();
        set.add(DataBing.class.getCanonicalName());
        return set;
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {


        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(DataBing.class);
        for (Element element : elements) {
            if (element.getKind() != ElementKind.CLASS) {
                return true;
            }

            onBuildDataBing(element, (TypeElement) element);
        }
        return true;
    }

    private void onBuildDataBing(final Element element, final TypeElement typeElement) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "=====>DataBing APT");
        final String className = element.getSimpleName().toString();
        final String pack = processingEnv.getElementUtils().getPackageOf(element).getQualifiedName().toString();
        final String fileName = className + "ViewModelInit";
     /*   processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, pack);
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, fileName);*/
        //泛型指定在父类上
        final TypeMirror superclassType = typeElement.getSuperclass();
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, superclassType.toString());
        try {
            final List<? extends TypeMirror> typeArguments = ((DeclaredType) superclassType).getTypeArguments();

            //如果没有泛型参数，则不生成代码
            if (typeArguments.isEmpty()) return;


            final String viewModelName = typeArguments.get(0).toString();
            final String viewBindingName = typeArguments.get(1).toString();

            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, viewModelName);
            final TypeSpec typeSpec = TypeSpec.classBuilder(fileName) // 生成主类
                    .addModifiers(Modifier.PUBLIC)
                    //provideViewModel 方法生成
                    .addMethod(MethodSpec.methodBuilder("provideViewModel")
                            .addModifiers(Modifier.PUBLIC)
                            .addParameter(
                                    Helper.T("androidx.lifecycle", "ViewModelStoreOwner"),
                                    "owner",
                                    Modifier.FINAL)
                            .returns(Helper.T(pack, viewModelName))
                            .addStatement("return new $T(owner).get($T.class)",
                                    Helper.T("androidx.lifecycle", "ViewModelProvider"),
                                    Helper.T(pack, viewModelName))
                            .build())
                    //provideViewBinding 方法生成
                    //import android.view.ViewGroup;
                    //import android.view.LayoutInflater;
                    .addMethod(MethodSpec.methodBuilder("provideViewBinding")
                            .addModifiers(Modifier.PUBLIC)
                            .addParameter(
                                    Helper.T("android.view", "LayoutInflater"),
                                    "inflater",Modifier.FINAL
                                    )
                            .addParameter(
                                    Helper.T("android.view", "ViewGroup"),
                                    "container",Modifier.FINAL

                            )
                            .returns(Helper.T("", viewBindingName))
                            .addStatement("return $T.inflate(inflater,container,false)",
                                    Helper.T("", viewBindingName)) // 具体的方法
                            .build())
                    .build();


            JavaFile javaFile = JavaFile.builder(pack, typeSpec)
                    .build();
            javaFile.writeTo(processingEnv.getFiler());


        } catch (Exception exception) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, exception.toString());
        }
    }
}
