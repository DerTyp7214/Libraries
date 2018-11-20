package com.dertyp7214.libraries.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import androidx.fragment.app.Fragment

@SuppressLint("ValidFragment")
open class BaseFragment(c: Context) : Fragment() {
    var name: String = ""
        protected set(value) {
            field = value
        }
    var icon: Drawable? = null
        protected set(value) {
            field = value
        }
}