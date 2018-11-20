package com.dertyp7214.libraries.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dertyp7214.libraries.R

@SuppressLint("ValidFragment")
class SettingsFragment(c: Context) : BaseFragment(c) {

    init {
        name = c.getString(R.string.title_settings)
        icon = c.getDrawable(R.drawable.ic_settings_black_24dp)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.settings, container, false)

        return view
    }
}
