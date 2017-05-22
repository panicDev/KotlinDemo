package id.paniclabs.kotlinmvp.feature.main

import id.paniclabs.kotlinmvp.api.ApiInteractor
import id.paniclabs.kotlinmvp.api.response.DataHargaResponse
import id.paniclabs.kotlinmvp.api.response.DataItem
import id.paniclabs.kotlinmvp.rx.Schedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


/**
 * @author      paniclabs.
 * @created     on 5/23/17.
 * @email       panic.inc.dev@gmail.com
 * @projectName Belajarkotlin-android
 */
class MainPresenter
@Inject constructor(val interactor: ApiInteractor,
                    val rxSchedule: Schedulers) {

    private var view: MainView? = null
    private val composite = CompositeDisposable()

    /*onAttach*/
    fun attachView(view: MainView){
        this.view = view
    }

    /**
     * Method untuk load data di presenter
     * @return url
     */
    fun loadData(month: Int, year: Int) {
        composite.add(
                interactor.ambilDataHarga(month,year)
                        .observeOn(rxSchedule.ui())
                        .doOnSubscribe { view?.onLoadingView(true) }
                        .subscribe (
                        {onNext(it) }, {onError(it)}
                        )
        )
    }

    /**
     * API sukses direquest,
     * tampilkan data.
     * @return list
     */
    private fun onNext(response: DataHargaResponse) {
        view?.onLoadingView(false)
        view?.onResultView(response.data as List<DataItem>)
    }

    /**
    * API gagal di request.
    * tampilkan message error.
    * @return throwable
    */
    private fun onError(throwable: Throwable) {
        view?.onLoadingView(false)
        view?.onErrorView(throwable.localizedMessage)
    }

    /*onDetach*/
    fun detachView() {
        composite.clear()
        this.view = null
    }

}