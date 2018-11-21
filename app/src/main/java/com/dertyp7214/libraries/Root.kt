package com.dertyp7214.libraries

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import androidx.core.graphics.ColorUtils
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.dertyp7214.libraries.components.ViewPagerCustomDuration
import com.dertyp7214.libraries.logs.Logger
import com.dertyp7214.libraries.screens.FragmentScreen
import com.dertyp7214.libraries.screens.MainActivity
import com.dertyp7214.libraries.style.SlidePageTransformer
import com.dertyp7214.themeablecomponents.utils.ThemeManager
import kotlinx.android.synthetic.main.activity_root.*




class Root : FragmentActivity() {

    private lateinit var viewPager: ViewPagerCustomDuration
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var logger: Logger
    private lateinit var themeManager: ThemeManager
    private var width: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)

        themeManager = ThemeManager.getInstance(this)
        themeManager.enableStatusAndNavBar(this)

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        width = displayMetrics.widthPixels

        logger = Logger(this)
        viewPager = findViewById(R.id.viewPager)

        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPager.setPageTransformer(true, SlidePageTransformer())
        viewPager.isSaveFromParentEnabled = false
        viewPager.adapter = viewPagerAdapter
        viewPager.setScrollDurationFactor(2.toDouble())

        viewPagerAdapter.addFragment(MainActivity(this, Color.parseColor("#88FF88")))

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            var lastPos = -1F

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                if (positionOffset != 0F) {
                    val color = if (viewPagerAdapter.fragmentList.size - 1 >= position + 1) {
                        val color1 = viewPagerAdapter.fragmentList[position].color
                        val color2 = viewPagerAdapter.fragmentList[position + 1].color
                        if (color1 != null && color2 != null) ColorUtils.blendARGB(
                            color1,
                            color2,
                            positionOffset
                        ) else null
                    } else null
                    if (color != null) themeManager.changeColor(toolbar, color)
                } else if (viewPagerAdapter.fragmentList.size - 1 >= position) {
                    val color = viewPagerAdapter.fragmentList[position].color
                    themeManager.getComponents(this@Root).forEach {
                        if (it.type == ThemeManager.Component.TYPE.TOOLBAR && color != null) {
                            it.changeColor(color, true)
                        }
                    }
                }
                if (position == viewPagerAdapter.fragmentList.lastIndex - 1 && positionOffset == 0F && positionOffset < lastPos && lastPos < 0.5F) {
                    close(viewPagerAdapter.fragmentList.last())
                }
                lastPos = positionOffset
            }

            override fun onPageSelected(position: Int) {
            }
        })
    }

    fun openScreen(open: FragmentScreen) {
        viewPagerAdapter.addFragment(open)
        viewPager.currentItem = viewPagerAdapter.count - 1
    }

    fun close(close: FragmentScreen) {
        viewPagerAdapter.fragmentList.forEachIndexed { index, fragmentScreen ->
            if (fragmentScreen == close) {
                viewPager.currentItem = index - 1
                Handler().postDelayed({
                    viewPagerAdapter.remove(fragmentScreen)
                }, 300)
            }
        }
    }

    private inner class ViewPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        val fragmentList = ArrayList<FragmentScreen>()

        override fun getItem(position: Int): FragmentScreen {
            return fragmentList[position]
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

        override fun getItemPosition(obj: Any): Int {
            return if (fragmentList.contains(obj))
                fragmentList.indexOf(obj)
            else POSITION_NONE
        }

        fun remove(fragment: FragmentScreen) {
            fragmentList.remove(fragment)
            notifyDataSetChanged()
        }

        fun addFragment(fragment: FragmentScreen) {
            fragmentList.add(fragment)
            notifyDataSetChanged()
        }
    }

    override fun onBackPressed() {
        if (viewPagerAdapter.fragmentList.size > 1)
            close(viewPagerAdapter.fragmentList.last())
        else
            super.onBackPressed()
    }
}