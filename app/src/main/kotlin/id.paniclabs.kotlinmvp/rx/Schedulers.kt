package id.paniclabs.kotlinmvp.rx

import io.reactivex.Scheduler


/**
 * @author      paniclabs.
 * @created     on 5/23/17.
 * @email       panic.inc.dev@gmail.com
 * @projectName Belajarkotlin-android
 */
interface Schedulers {
    fun io() : Scheduler
    fun ui() : Scheduler
}