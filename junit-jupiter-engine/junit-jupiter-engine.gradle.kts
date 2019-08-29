plugins {
	`kotlin-library-conventions`
	groovy
}

apply(from = "$rootDir/gradle/testing.gradle.kts")

description = "JUnit Jupiter Engine"

val testArtifacts by configurations.creating {
	extendsFrom(configurations.testRuntime.get())
}

val testJar by tasks.creating(Jar::class) {
	archiveClassifier.set("test")
	from(sourceSets.test.get().output)
}

artifacts {
	add(testArtifacts.name, testJar)
}

dependencies {
	api(apiGuardian())

	api(project(":junit-platform-engine"))
	api(project(":junit-jupiter-api"))

	testImplementation(project(":junit-platform-launcher"))
	testImplementation(project(":junit-platform-runner"))
	testImplementation(project(":junit-platform-testkit"))
	testImplementation(kotlinStdlib())
	testImplementation(groovy())
}
