plugins {
    id(Plugins.application)
    kotlin("android")
    kotlin("kapt")
    id(Plugins.safeargs)
    id(Plugins.hilt)
}

android {
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = AppConfig.id
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName
        testInstrumentationRunner = AppConfig.testInstrumentationRunner
        signingConfig = signingConfigs.getByName("debug")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions.jvmTarget = Versions.jvmVersion

    viewBinding.enable = true

    namespace = AppConfig.id
}

dependencies {
    testImplementation(AppDependencies.JUnit.junit)
    androidTestImplementation(AppDependencies.JUnit.ext)

    androidTestImplementation(AppDependencies.Espresso.core)

    implementation(AppDependencies.AppCompat.appcompat)
    implementation(AppDependencies.Core.ktx)
    implementation(AppDependencies.Material.material)

    implementation(AppDependencies.Constraint.constraintLayout)

    implementation(AppDependencies.Jsoup.jsoup)

    implementation(AppDependencies.Navigation.ktx)
    implementation(AppDependencies.Navigation.uiKtx)

    implementation(AppDependencies.Coroutines.core)
    implementation(AppDependencies.Coroutines.android)

    implementation(AppDependencies.Lifecycle.ktx)
    implementation(AppDependencies.Lifecycle.runtime)

    implementation(AppDependencies.Hilt.hilt)
    annotationProcessor(AppDependencies.Hilt.compiler)
    kapt(AppDependencies.Hilt.compiler)

    implementation(AppDependencies.AnimatedBottomBar.library)

    implementation(AppDependencies.Room.runtime)
    implementation(AppDependencies.Room.ktx)
    kapt(AppDependencies.Room.compiler)

    implementation(AppDependencies.LiveData.ktx)

    implementation(AppDependencies.PowerSpinner.powerSpinner)

    implementation(AppDependencies.ToggleButtonGroup.toggleButtonGroup)

    implementation(AppDependencies.SurroundCardView.surroundCardView)

    implementation(AppDependencies.RoundCornerProgressBar.roundCornerProgressBar)

    implementation(AppDependencies.RecyclerViewAnimators.recyclerViewAnimators)

    implementation(AppDependencies.ViewBindingPropertyDelegate.viewBindingPropertyDelegate)

    implementation(AppDependencies.DataStore.preferences)
}