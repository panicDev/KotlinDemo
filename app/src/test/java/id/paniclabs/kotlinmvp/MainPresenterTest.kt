//package id.paniclabs.kotlinmvp
//
//import id.paniclabs.kotlinmvp.api.ApiInteractor
//import id.paniclabs.kotlinmvp.rx.Schedulers
//import io.reactivex.Scheduler
//import io.reactivex.Single
//import io.reactivex.schedulers.Schedulers.trampoline
//import org.junit.After
//import org.junit.Before
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.mockito.Mock
//import org.mockito.Mockito.*
//import org.mockito.junit.MockitoJUnitRunner
//
//@RunWith(MockitoJUnitRunner::class)
//class MainPresenterTest() {
//
//    val urls: String = "https://sites.google.com/macros/exec?service=AKfycbxoZDvBSaC2QRdzvoRlFzr6EzDLoimdqewnpeMoGoMFAT2sD3cB&tahun=2017&bulan=5"
//
//    @Mock
//    lateinit var interactor: ApiInteractor
//
//    lateinit var presenter: MainPresenter
//
//
//    @Before
//    fun setUp() {
//        presenter = MainPresenter(interactor, TestSchedulers)
//
//    }
//
//    @Test
//    fun load_list_of_repos_and_hide_errors_When_attached_to_view() {
//        val view = mock(MainView::class.java)
//        val list = emptyList<DataItem>()
//        `when`(interactor.ambilDataHarga(urls, year)).thenReturn(Single.just(list))
//        presenter.attachView(view)
//        verify(view).onResultView(list)
//    }
//
//    @Test
//    fun show_error_message_When_attached_to_view_and_got_error() {
//        var view = mock(MainView::class.java)
//        `when`(interactor.ambilDataHarga(urls, year)).thenReturn(Single.error(Throwable("Error")))
//        presenter.attachView(view)
//        verify(view).onErrorView("Error")
//    }
//
//    @After
//    fun tearDown() {
//        presenter.detachView()
//    }
//}
//
//object TestSchedulers : Schedulers {
//    override fun io(): Scheduler = trampoline()
//    override fun ui(): Scheduler = trampoline()
//}
