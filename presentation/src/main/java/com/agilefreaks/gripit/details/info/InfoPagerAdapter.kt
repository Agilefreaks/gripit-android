package com.agilefreaks.gripit.details.info

import android.app.Fragment
import android.app.FragmentManager
import android.support.v13.app.FragmentStatePagerAdapter

class InfoPagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {
    val PAGE_COUNT = 3
    val fragments = listOf(RouteInfoFragment(), RouteMeFragment(), RouteOthersFragment())

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return PAGE_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence {
        when (position) {
            0 -> return "Info"
            1 -> return "Route Me"
            2 -> return "Route Others"
        }
        return ""
    }
}