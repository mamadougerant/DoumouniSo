import org.gradle.kotlin.dsl.DependencyHandlerScope

object Versions{
    const val core = "1.12.0"
    const val junit = "4.13.2"
    const val junitExt = "1.1.5"
    const val espressoCore = "3.5.1"
    const val activity = "1.8.2"
    const val lifecycle = "2.7.0"
    const val composeBom = "2023.10.01"
    const val compose = "1.5.4"
    // retrofit
    const val retrofit = "2.9.0"
    const val okHttp = "5.0.0-alpha.11"
    // dagger hilt
    const val hilt = "2.50"
    const val compiler = "1.1.0"

    // navigation
    const val navigation = "2.7.6"

    // room
    const val room = "2.6.1"
    // gson
    const val gson = "2.10.1"

}
object Libs{
    const val core = ("androidx.core:core-ktx:1.12.0")
    const val lifecycle = ("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    const val activity = ("androidx.activity:activity-compose:1.8.2")
    const val composeBom = (("androidx.compose:compose-bom:2024.02.00"))
    const val composeUi = ("androidx.compose.ui:ui")
    const val composeUiGraphics = ("androidx.compose.ui:ui-graphics")
    const val composeUiTooling = ("androidx.compose.ui:ui-tooling-preview")
    const val material3 = ("androidx.compose.material3:material3")
    const val junit = ("junit:junit:4.13.2")
    const val extJunit = ("androidx.test.ext:junit:1.1.5")
    const val espresso = ("androidx.test.espresso:espresso-core:3.5.1")
    const val composeBomTest = (("androidx.compose:compose-bom:2024.02.00"))
    const val composeUiTestJ4 = ("androidx.compose.ui:ui-test-junit4")
    const val composeUiToolingTest = ("androidx.compose.ui:ui-tooling")
    const val composeManifest = ("androidx.compose.ui:ui-test-manifest")
}

fun DependencyHandlerScope.composeCommon(){
    val list = listOf(
        Libs.core, Libs.lifecycle, Libs.activity,
        Libs.composeBom, Libs.composeUi, Libs.composeUiGraphics,
        Libs.composeUiTooling, Libs.material3
    )
    val testList = listOf(
        Libs.extJunit, Libs.espresso,
        Libs.composeBomTest, Libs.composeUiTestJ4
    )
    val listDebug = listOf(
        Libs.composeUiToolingTest, Libs.composeManifest
    )
    list.forEach { add("implementation", it) }
    testList.forEach { add("androidTestImplementation", it) }
    add("testImplementation", Libs.junit)
    listDebug.forEach { add("debugImplementation", it) }
}


object Retrofit{
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val logging = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"

}

fun DependencyHandlerScope.retrofit() {
    val list = listOf(
        Retrofit.retrofit, Retrofit.converter,
        Retrofit.logging
    )
    list.forEach { add("implementation", it) }
}



object Room{
    const val room = "androidx.room:room-ktx:${Versions.room}"
    const val compiler = "androidx.room:room-compiler:${Versions.room}"
    const val paging = "androidx.room:room-paging:${Versions.room}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
}

fun DependencyHandlerScope.room() {
    val list = listOf(
        Room.room, Room.paging, Room.gson
    )
    list.forEach { add("implementation", it) }
    add("kapt", Room.compiler)
}

object DaggerHilt{
    const val hilt =("com.google.dagger:hilt-android:2.50")
    const val hiltAndCompiler =("com.google.dagger:hilt-android-compiler:2.50")
    const val hiltCompiler =("androidx.hilt:hilt-compiler:1.1.0")
    const val hiltNavigation =("androidx.hilt:hilt-navigation-compose:1.1.0")

    const val hiltTesting = "com.google.dagger:hilt-android-testing:${Versions.hilt}"
}

fun DependencyHandlerScope.daggerHilt() {
    val list = listOf(
        DaggerHilt.hilt, DaggerHilt.hiltNavigation
    )
    list.forEach { add("implementation", it) }
    add("kapt", DaggerHilt.hiltAndCompiler)
    add("kapt", DaggerHilt.hiltCompiler)
}