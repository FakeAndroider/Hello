package org.work.processor;

import com.google.auto.service.AutoService;

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

          /*  onBuildDataBing(element, (TypeElement) element);*/
        }
        return true;
    }

    private void onBuildDataBing(final Element element, final TypeElement typeElement) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "=====>DataBing APT");
        final String className = element.getSimpleName().toString();
        final String pack = processingEnv.getElementUtils().getPackageElement(element.getSimpleName()).toString();
        final String fileName = className + "ViewModelInit";
        //泛型指定在父类上
        final TypeMirror superclassType = typeElement.getSuperclass();

        try {
            final List<? extends TypeMirror> typeArguments = ((DeclaredType) superclassType).getTypeArguments();

            //如果没有泛型参数，则不生成代码
            if (typeArguments.isEmpty()) return;
            final  String viewModelName = typeArguments.get(0).toString();

        } catch (Exception exception) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, exception.toString());
        }
    }
}
