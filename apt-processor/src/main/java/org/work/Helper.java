package org.work;

import com.squareup.javapoet.ClassName;

public class Helper {
    static public ClassName T(final String packageName, final String className) {
        return ClassName.get(packageName, className);
    }
}
