import java.util.Properties

plugins {
    id("com.android.application") version "9.2.1"
    id("mmaarij.axycord.plugin")
}

val pluginPackage = "mmaarij.axycord.permissionsviewer"

axycordPlugin {
    id.set("axycord.permissionsviewer")
    entrypoint.set("$pluginPackage.PermissionsViewerPlugin")
    description.set("Inspect server roles, member permissions, and channel overwrites.")
    axycordVersionMinimum.set(3)
    axycordVersionMaximumExclusive.set(4)
    usesResources.set(false)
    async()
}

android {
    namespace = pluginPackage
    compileSdk { version = release(37) }

    defaultConfig {
        applicationId = pluginPackage
        minSdk = 23
        targetSdk = 36
        versionCode = 1
        versionName = "1.0.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    lint {
        checkOnly += setOf("NewApi")
        fatal += "NewApi"
    }

    val signingFile = rootProject.file("keystore.properties")
    if (signingFile.isFile) {
        val keys = Properties().apply { signingFile.inputStream().use(::load) }
        val releaseKey = signingConfigs.create("release") {
            storeFile = rootProject.file(keys.getProperty("storeFile"))
            storePassword = keys.getProperty("storePassword")
            keyAlias = keys.getProperty("keyAlias")
            keyPassword = keys.getProperty("keyPassword")
        }
        buildTypes.named("release") { signingConfig = releaseKey }
    }
}

dependencies {
    val checkout = providers.gradleProperty("axycordCheckout").orNull
    if (checkout == null) {
        compileOnly("mmaarij.axycord:plugin-api:1.0.6")
    } else {
        val sdk = file(checkout).resolve("build/plugin-sdk/axycord-plugin-api-1.0.6.jar")
        require(sdk.isFile) { "Generate the local SDK first: scripts/build.sh pluginSdkJar" }
        compileOnly(files(sdk))
    }
    compileOnly("io.reactivex:rxjava:1.3.8")
    compileOnly("androidx.core:core:1.16.0") { isTransitive = false }
}
