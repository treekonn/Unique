// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(Plugins.application) version Versions.application apply false
    id(Plugins.androidLibrary) version Versions.application apply false
    id(Plugins.kotlinAndroid) version Versions.kotlin apply false
    id(Plugins.safeargs) version Versions.safeargs apply false
    id(Plugins.hiltGoogle) version Versions.hiltPlugin apply false
}


tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}