plugins {
  id("org.jetbrains.intellij") version "1.17.4"
  id("java")
}

group = "com.github.intellij.gno"
version = "0.0.1"

repositories {
  mavenCentral()
}

intellij {
  version.set("2024.3.2")
  type.set("IU")
  pluginName.set("Gno")

  plugins.set(listOf("java"))

}

//dependencies {
//  implementation("com.jetbrains.intellij.platform:lsp-api:2024.3.2")
//}

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
