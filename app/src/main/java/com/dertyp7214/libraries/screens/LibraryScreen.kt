package com.dertyp7214.libraries.screens

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.dertyp7214.libraries.R
import com.dertyp7214.libraries.Root

@SuppressLint("ValidFragment")
class LibraryScreen(root: Root): FragmentScreen(root) {

    constructor(root: Root, color: Int) : this(root) {
        super.color = color
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.library_screen, container, false)

        view.findViewById<Button>(R.id.button2).setOnClickListener {
            root.openScreen(MainActivity(root, Color.BLUE))
        }

        view.findViewById<Button>(R.id.button3).setOnClickListener {
            root.openScreen(LibraryScreen(root, Color.GREEN))
        }

        return view
    }
}