package xyz.zolbooo.hetevch.base.coroutines

import kotlinx.coroutines.CoroutineDispatcher

expect class MainDispatcher {
    val dispatcher: CoroutineDispatcher
}
