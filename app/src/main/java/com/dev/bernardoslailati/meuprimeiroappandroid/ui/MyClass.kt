package com.dev.bernardoslailati.meuprimeiroappandroid.ui

import android.content.Context
import java.lang.ref.WeakReference

class MyClass(val context: Context) {
    // ...
    val contextWeakReference = WeakReference(context)

    fun doSomething() {
        contextWeakReference.get()
        // Use the context here
    }
}