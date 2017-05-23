package id.paniclabs.kotlinmvp.util

import id.paniclabs.kotlinmvp.R

/**
 * @author ali@pergikuliner
 * @created 5/23/17.
 * @project BelajarKotlinMVP.
 */
enum class BottomNavigationPosition(val position: Int, val id: Int) {
    HOME(0, R.id.home),
    DASHBOARD(1, R.id.dashboard),
    OLD(2, R.id.oldest)
}