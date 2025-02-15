plugins {
  id("org.jetbrains.kotlin.jvm") version "1.9.25"
  id("org.jetbrains.intellij") version "1.17.4"
  id("java")
}

sourceSets["main"].java.srcDirs("src/main/java")

group = "com.github.intellij.gno"
version = "0.0.1-SNAPSHOT"

repositories {
  mavenCentral()
  gradlePluginPortal()
}


intellij {
  version.set(providers.gradleProperty("platformVersion").orNull ?: "2023.2")
  type.set(providers.gradleProperty("platformType").orNull ?: "IC")
  pluginName.set("Gno")

  plugins.set(
    providers.gradleProperty("platformPlugins")
      .map { it.split(',').map(String::trim).filter(String::isNotEmpty)}
  )
}
dependencies {
  implementation("org.jetbrains.kotlin:kotlin-stdlib")
}

tasks {
  withType<JavaCompile> {
    sourceCompatibility = "17"
    targetCompatibility = "17"
  }

  patchPluginXml {
    sinceBuild.set("241.0")
    untilBuild.set("243.*")
  }

  signPlugin {
    certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
    privateKey.set(System.getenv("PRIVATE_KEY"))
    password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
  }

  publishPlugin {
    token.set(System.getenv("PUBLISH_TOKEN"))
  }
}
