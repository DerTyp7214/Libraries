package com.dertyp7214.libraries.style

import android.os.Build
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.dertyp7214.libraries.R

class SlidePageTransformer : ViewPager.PageTransformer {

    override fun transformPage(view: View, position: Float) {
        view.apply {
            if (position <= 0) {
                translationX = (width * -position) / 3 * 2
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    val drawable = context.getDrawable(R.drawable.fg_shadow)
                    drawable!!.alpha = (255 * Math.min(1F, Math.abs(position) * 1.5F)).toInt()
                    foreground = drawable
                }
            }
            z = position
        }
    }
}