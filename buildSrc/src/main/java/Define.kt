/**
 * 相关定义
 * https://developer.android.google.cn/studio/projects/android-library?hl=zh-cn
 * https://blog.csdn.net/Welcome_Word/article/details/131968778
 * */
object Define {
    const val TARGET_SDK = 33
    const val MIN_SDK = 21
    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0.0"
    const val APPLICATION_ID = "org.work"
}


object Version {
    const val app_constraintlayout = "2.0.4"
    const val app_compat = "1.2.0"
    const val kotlin_core = "1.3.0"
    const val kotlin = "1.3.72"


    const  val java_poet = "1.13.0"
    const val auto_service = "1.1.1"
    const val logger = "2.2.0"
}

object Deps {

    const val kotlin_core = "androidx.core:core-ktx:${Version.kotlin_core}"
    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Version.kotlin}"
    const val kotlin_gradle_plugin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}"

    const val app_compat = "androidx.appcompat:appcompat:${Version.app_compat}"
    const val app_constraintlayout =
        "androidx.constraintlayout:constraintlayout:${Version.app_constraintlayout}"

    /*log 组件*/
    const val logger = "com.orhanobut:logger:${Version.logger}"

    /*auto-service 组件*/
    const val auto_service = "com.google.auto.service:auto-service:${Version.auto_service}"

    const val java_poet = "com.squareup:javapoet:${Version.java_poet}"
}