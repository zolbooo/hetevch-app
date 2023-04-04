package xyz.zolbooo.hetevch

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform