plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version(Versions.agp).apply(false)
    id("com.android.library").version(Versions.agp).apply(false)
    kotlin("android").version(Versions.kotlin).apply(false)
    kotlin("multiplatform").version(Versions.kotlin).apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
