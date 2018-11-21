package com.dertyp7214.libraries.fragments

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import androidx.fragment.app.Fragment
import com.dertyp7214.libraries.screens.FragmentScreen

@SuppressLint("ValidFragment")
open class BaseFragment(c: FragmentScreen) : Fragment() {
    var name: String = ""
        protected set(value) {
            field = value
        }
    var icon: Drawable? = null
        protected set(value) {
            field = value
        }
}