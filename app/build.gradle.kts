import com.example.util.simpletimetracker.Base
import com.example.util.simpletimetracker.Deps
import com.example.util.simpletimetracker.applyAndroidLibrary

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

applyAndroidLibrary()

android {
    defaultConfig {
        applicationId = "com.razeeman.util.simpletimetracker"
        versionCode = Base.versionCode
        versionName = Base.versionName

        testInstrumentationRunner = "com.example.util.simpletimetracker.utils.CustomTestRunner"
    }

    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".debug"
            isMinifyEnabled = true
            isCrunchPngs = false
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                "proguard-debug-rules.pro"
            )
            testProguardFile("proguard-test-rules.pro")
        }
        getByName("release") {
            isMinifyEnabled = true
            isCrunchPngs = false
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    testOptions {
        animationsDisabled = true
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":data_local"))
    implementation(project(":feature_main"))
    implementation(project(":feature_running_records"))
    implementation(project(":feature_change_record_type"))
    implementation(project(":feature_records"))
    implementation(project(":feature_records_all"))
    implementation(project(":feature_change_record"))
    implementation(project(":feature_change_running_record"))
    implementation(project(":feature_statistics"))
    implementation(project(":feature_statistics_detail"))
    implementation(project(":feature_settings"))
    implementation(project(":feature_dialogs"))
    implementation(project(":feature_widget"))
    implementation(project(":feature_notification"))
    implementation(project(":feature_categories"))
    implementation(project(":feature_change_category"))
    implementation(project(":feature_change_record_tag"))
    implementation(project(":feature_change_activity_filter"))
    implementation(project(":feature_archive"))
    implementation(project(":feature_tag_selection"))
    implementation(project(":feature_data_edit"))
    implementation(project(":feature_records_filter"))

    implementation(Deps.Androidx.room)
    implementation(Deps.Ktx.navigationFragment)
    implementation(Deps.Ktx.navigationUi)
    implementation(Deps.Google.dagger)

    kapt(Deps.Kapt.dagger)
    kaptAndroidTest(Deps.Kapt.dagger)

    androidTestImplementation(Deps.UiTest.junit)
    androidTestImplementation(Deps.UiTest.espresso)
    androidTestImplementation(Deps.UiTest.espressoContrib)
    androidTestImplementation(Deps.UiTest.dagger)
}
