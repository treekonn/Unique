object AppDependencies {

    // test libraries

    object JUnit {
        const val junit = "junit:junit:${Versions.junit}"
        const val ext = "androidx.test.ext:junit:${Versions.extJunit}"
    }

    object Espresso {
        const val core = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    }


    // android ui libraries

    object AppCompat {
        const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    }

    object Core {
        const val ktx = "androidx.core:core-ktx:${Versions.coreKtx}"
    }

    object Material {
        const val material = "com.google.android.material:material:${Versions.material}"
    }

    object Constraint {
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraint}"
    }

    object AnimatedBottomBar {
        const val library =
            "nl.joery.animatedbottombar:library:${Versions.animatedBottomBar}"
    }

    object PowerSpinner {
        const val powerSpinner = "com.github.skydoves:powerspinner:${Versions.powerSpinner}"
    }

    object ToggleButtonGroup {
        const val toggleButtonGroup =
            "com.nex3z:toggle-button-group:${Versions.toggleButtonGroup}"
    }

    object SurroundCardView {
        const val surroundCardView =
            "com.furkanakdemir:surroundcardview:${Versions.surroundCardView}"
    }

    object RoundCornerProgressBar {
        const val roundCornerProgressBar =
            "com.akexorcist:round-corner-progress-bar:${Versions.roundCornerProgressBar}"
    }

    object RecyclerViewAnimators {
        val recyclerViewAnimators =
            "jp.wasabeef:recyclerview-animators:${Versions.recyclerViewAnimators}"
    }


    // external libraries

    object Jsoup {
        const val jsoup = "org.jsoup:jsoup:${Versions.jsoup}"
    }

    object ViewBindingPropertyDelegate {
        const val viewBindingPropertyDelegate =
            "com.github.kirich1409:viewbindingpropertydelegate-noreflection:${Versions.viewBindingPropertyDelegate}"
    }

    object Navigation {
        const val ktx =
            "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        const val uiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    }

    object Coroutines {
        const val core =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val android =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    }

    object Lifecycle {
        const val ktx =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    }

    object Hilt {
        const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val compiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
    }

    object Room {
        const val runtime = "androidx.room:room-runtime:${Versions.room}"
        const val ktx = "androidx.room:room-ktx:${Versions.room}"
        const val compiler = "androidx.room:room-compiler:${Versions.room}"
    }

    object LiveData {
        const val ktx =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    }

    object DataStore {
        const val preferences =
            "androidx.datastore:datastore-preferences:${Versions.dataStore}"
    }

}





