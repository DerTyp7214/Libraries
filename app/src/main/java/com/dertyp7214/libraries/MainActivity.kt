package com.dertyp7214.libraries

import android.os.Bundle
import android.view.Menu
import androidx.core.view.forEachIndexed
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.dertyp7214.libraries.fragments.BaseFragment
import com.dertyp7214.libraries.fragments.HomeFragment
import com.dertyp7214.libraries.fragments.SettingsFragment
import com.dertyp7214.libraries.style.SlidePageTransformer
import com.dertyp7214.themeablecomponents.utils.ThemeManager
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : FragmentActivity() {

    lateinit var viewPager: ViewPager
    private lateinit var navView: BottomNavigationView
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        navView.menu.forEachIndexed { index, item ->
            if (item == it && viewPagerAdapter.count >= index)
                viewPager.setCurrentItem(index, true)
        }
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        viewPager = findViewById(R.id.viewPager)

        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPager.adapter = viewPagerAdapter
        viewPager.setPageTransformer(true, SlidePageTransformer())

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

        val themeManager = ThemeManager.getInstance(this)

        themeManager.enableStatusAndNavBar(this)
    }

    private fun setUpBottomNavView() {
        viewPagerAdapter.fragmentList.forEachIndexed { index, it->
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
