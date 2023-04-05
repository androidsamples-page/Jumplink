package co.icanteach.jumplink

import org.gradle.api.Project
import java.io.FileInputStream
import java.util.Properties

fun Project.getMinSdkVersion(): Int {
    val propertiesFile = rootProject.file("sdk-versions.properties")

    val versionProperties = Properties().apply {
        load(FileInputStream(propertiesFile))
    }

    return versionProperties["MIN"].toString().toInt()
}

fun Project.getTargetSdkVersion(): Int {
    val propertiesFile = rootProject.file("sdk-versions.properties")

    val versionProperties = Properties().apply {
        load(FileInputStream(propertiesFile))
    }

    return versionProperties["TARGET"].toString().toInt()
}

fun Project.getCompileSdkVersion(): Int {
    val propertiesFile = rootProject.file("sdk-versions.properties")

    val versionProperties = Properties().apply {
        load(FileInputStream(propertiesFile))
    }

    return versionProperties["COMPILE"].toString().toInt()
}