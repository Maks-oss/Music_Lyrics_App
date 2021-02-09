package com.example.musiclyricsapp

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T : Any> toMain(block: suspend CoroutineScope.() -> T) =
    withContext(Dispatchers.Main, block)