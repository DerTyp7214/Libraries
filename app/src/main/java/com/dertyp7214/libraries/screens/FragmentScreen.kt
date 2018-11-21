@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.dertyp7214.libraries.screens

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import androidx.fragment.app.Fragment
import com.dertyp7214.libraries.Root

@SuppressLint("ValidFragment")
open class FragmentScreen(val root: Root) : Fragment() {

    constructor(root: Root, color: Int) : this(root) {
        this.color = color
    }

    protected fun openScreen(screen: FragmentScreen) {
        root.openScreen(screen)
    }

    protected fun close(close: FragmentScreen) {
        root.close(close)
    }

    fun getDrawable(id: Int): Drawable {
        return root.getDrawable(id)
    }

    var color: Int? = null
        protected set(value) {
            field = value
        }
}