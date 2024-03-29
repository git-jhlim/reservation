//import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
//
//plugins {
//    id("org.springframework.boot") version "3.0.4"
//    id("io.spring.dependency-management") version "1.1.0"
//    kotlin("jvm") version "1.7.22"
//    kotlin("plugin.spring") version "1.7.22"
//    kotlin("plugin.jpa") version "1.7.22"
//    kotlin("kapt") version "1.7.21"
//    idea
//}
//
//group = "com.exam"
//version = "0.0.1-SNAPSHOT"
//java.sourceCompatibility = JavaVersion.VERSION_17
//
//repositories {
//    mavenCentral()
//}
//
//dependencies {
//    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
//    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
//    implementation("org.springframework.boot:spring-boot-starter-webflux")
//    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
//    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
//    implementation("org.jetbrains.kotlin:kotlin-reflect")
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
//    runtimeOnly("com.h2database:h2:2.1.214")
//
//    testImplementation("org.springframework.boot:spring-boot-starter-test")
//    testImplementation("io.projectreactor:reactor-test")
//}
//
//tasks.withType<KotlinCompile> {
//    kotlinOptions {
//        freeCompilerArgs = listOf("-Xjsr305=strict")
//        jvmTarget = "17"
//    }
//}
//
//tasks.withType<Test> {
//    useJUnitPlatform()
//}
//
//idea {
//    module {
//        val kaptMain = file("build/generated/source/kapt/main")
//        sourceDirs.add(kaptMain)
//        generatedSourceDirs.add(kaptMain)
//    }
//}