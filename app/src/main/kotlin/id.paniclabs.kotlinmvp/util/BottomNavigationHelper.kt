package id.paniclabs.kotlinmvp.util

import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.util.Log

/**
 * @author ali@pergikuliner
 * @created 5/23/17.
 * @project BelajarKotlinMVP.
 */
object BottomNavigationHelper {
    fun disableShiftMode(view: BottomNavigationView) {
        val menuView = view.getChildAt(0) as BottomNavigationMenuView
        try {
            val shiftingMode = menuView.javaClass.getDeclaredField("mShiftingMode")
            shiftingMode.isAccessible = true
            shiftingMode.setBoolean(menuView, false)
            shiftingMode.isAccessible = false
            for (i in 0..menuView.childCount - 1) {
                val item = menuView.getChildAt(i) as BottomNavigationItemView
                item.setShiftingMode(false)
                item.setChecked(item.itemData.isChecked)
            }
        } catch (e: NoSuchFieldException) {
            Log.e("BottomNavigationHelper", "Unable to get shift mode field", e)
        } catch (e: IllegalAccessException) {
            Log.e("BottomNavigationHelper", "Unable to change value of shift mode", e)
        }
    }

    fun activateMenu(bottomNavigationView: BottomNavigationView, position: Int) {
        bottomNavigationView.menu.getItem(position).isChecked = true
    }

    fun findPositionById(id: Int): BottomNavigationPosition = when (id) {
        BottomNavigationPosition.HOME.id -> BottomNavigationPosition.HOME
        BottomNavigationPosition.DASHBOARD.id -> BottomNavigationPosition.DASHBOARD
        BottomNavigationPosition.OLD.id -> BottomNavigationPosition.OLD
        else -> BottomNavigationPosition.HOME
    }
}