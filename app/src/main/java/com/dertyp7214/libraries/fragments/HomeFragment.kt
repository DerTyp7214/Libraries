package com.dertyp7214.libraries.fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.dertyp7214.libraries.R
import com.dertyp7214.libraries.screens.FragmentScreen
import com.dertyp7214.libraries.screens.LibraryScreen

@SuppressLint("ValidFragment")
class HomeFragment(private val c: FragmentScreen) : BaseFragment(c) {

    init {
        name = c.getString(R.string.title_home)
        icon = c.getDrawable(R.drawable.ic_home_black_24dp)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.home, container, false)

        view.findViewById<Button>(R.id.button).setOnClickListener {
            c.root.openScreen(LibraryScreen(c.root, Color.RED))
        }

        return view
    }
}
