package id.paniclabs.kotlinmvp.feature.oldest

import id.paniclabs.kotlinmvp.api.ApiInteractor
import id.paniclabs.kotlinmvp.feature.popular.PopularView
import id.paniclabs.kotlinmvp.model.Photo
import id.paniclabs.kotlinmvp.rx.Schedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * @author ali@pergikuliner
 * @created 5/23/17.
 * @project BelajarKotlinMVP.
 */
class OldestPresenters
@Inject constructor(val interactor: ApiInteractor, val schedulers: Schedulers){

    private var view: OldestView? = null
    private val composite = CompositeDisposable()

    fun attachView(view: OldestView){
        this.view = view
    }


    fun detachView(){
        composite.clear()
        this.view = null
    }

    fun loadPopularPhotos(){
        composite.add(
                interactor.getOldestCuratedPhotos()
                        .observeOn(schedulers.ui())
                        .doOnSubscribe { view?.isLoading(true) }
                        .doOnDispose { view?.isLoading(false) }
                        .subscribe(this::onNext,this::onError)
        )
    }

    private fun onNext(list: List<Photo>){
        view?.isLoading(false)
        view?.showData(list)
    }

    private fun onError(throwable: Throwable){
        view?.isLoading(false)
        view?.showError(throwable.localizedMessage)
    }

}