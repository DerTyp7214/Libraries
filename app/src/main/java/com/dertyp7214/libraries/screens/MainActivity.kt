package com.dertyp7214.libraries.screens

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.core.view.forEachIndexed
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.dertyp7214.libraries.R
import com.dertyp7214.libraries.Root
import com.dertyp7214.libraries.fragments.BaseFragment
import com.dertyp7214.libraries.fragments.HomeFragment
import com.dertyp7214.libraries.fragments.SettingsFragment
import com.dertyp7214.themeablecomponents.utils.ThemeManager
import com.google.android.material.bottomnavigation.BottomNavigationView

@SuppressLint("ValidFragment")
class MainActivity(root: Root) : FragmentScreen(root) {

    constructor(root: Root, color: Int) : this(root) {
        super.color = color
    }

    lateinit var viewPager: ViewPager
    private lateinit var navView: BottomNavigationView
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var activity: Activity

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        navView.menu.forEachIndexed { index, item ->
            if (item == it && viewPagerAdapter.count >= index)
                viewPager.setCurrentItem(index, true)
        }
        true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.main, container, false)

        activity = getActivity()!!

        navView = view.findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        viewPager = view.findViewById(R.id.vp)

        viewPagerAdapter = ViewPagerAdapter(fragmentManager!!)
        viewPager.adapter = viewPagerAdapter

        viewPagerAdapter.addFragment(HomeFragment(this))
        viewPagerAdapter.addFragment(SettingsFragment(this))
        viewPagerAdapter.addFragment(SettingsFragment(this))

        setUpBottomNavView()

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                navView.selectedItemId = navView.menu[position].itemId
            }
        })

        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            viewPagerAdapter.fragmentList.forEach {
                it.onAttach(context)
            }
        } catch (e: Exception) {
        }
    }

    private fun setUpBottomNavView() {
        viewPagerAdapter.fragmentList.forEachIndexed { index, it ->
            if (index != 0)
                navView.menu.add(Menu.NONE, index, Menu.NONE, it.name).icon = it.icon
        }
    }

    private inner class ViewPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        val fragmentList = ArrayList<BaseFragment>()

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return fragmentList[position].name
        }

        fun addFragment(fragment: BaseFragment) {
            fragmentList.add(fragment)
            notifyDataSetChanged()
        }
    }
}
