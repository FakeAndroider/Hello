package org.work.processor;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;

import org.work.annotation.MyAnnotation;

import java.io.IOException;
import java.io.Writer;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

@AutoService(Processor.class)
public class MyProcessor extends AbstractProcessor {


    @Override
    public Set<String> getSupportedAnnotationTypes() {
        HashSet<String> set = new HashSet<>();
//        set.add(MyAnnotation.class.getCanonicalName());
        return set;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return processingEnv.getSourceVersion();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "=====>PROCESS APT");
        try {
            Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(MyAnnotation.class);
            Filer filer = processingEnv.getFiler();
            final String fileName = "WorkPrinterUtil";
            FileObject fileObject = filer.getResource(StandardLocation.SOURCE_OUTPUT, "", fileName);

            final URI res = filer.getResource(StandardLocation.SOURCE_OUTPUT, "", fileName).toUri();
            if (res != null) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "=====>APT Del" + res);
                fileObject.delete();
            }

            fileObject = processingEnv.getFiler().createSourceFile("WorkPrinterUtil");

            Writer writer = fileObject.openWriter();
            writer.write("package org.work.annotation;\n");
            writer.write("\n");
            writer.write("public class WorkPrinterUtil{\n");
            for (Element e : elements) {
                Name simpleName = e.getSimpleName();
                writer.write("// 输出" + simpleName + "\n");
                writer.write("public static void print$$" + simpleName + "(){\n");
                writer.write("System.out.println(\"hello " + simpleName + "\" );\n }\n\n");
            }
            writer.write("}");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}