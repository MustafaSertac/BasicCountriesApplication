package ViewModel

import Model.Country
import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import kotlinx.coroutines.launch
import service.CountryAPIService

import service.CountryDatabase
import util.CustomSharedPrefences

class FeedViewModel (application: Application) : BaseViewModel(application) {
    private val countryApiService=CountryAPIService()
    private val disposable=CompositeDisposable()
    private var customPrefences=CustomSharedPrefences(getApplication())
    private var refreshTime=10*60*1000*1000*1000

    val countries = MutableLiveData<List<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

    fun refreshData() {
        val updateTimer=customPrefences.getTime()
if(updateTimer!=null && updateTimer!=0L&& System.nanoTime()-updateTimer<refreshTime){


    getDataFromSQlite()
}
        else {
    getDataFromAPI()
        }

        /*
        val country = Country("Turkey","Asia","Ankara","TRY","Turkish","www.ss.com")
        val country2 = Country("France","Europe","Paris","EUR","French","www.ss.com")
        val country3 = Country("Germany","Europe","Berlin","EUR","German","www.ss.com")

        val countryList = arrayListOf<Country>(country,country2,country3)

        countries.value = countryList
        countryError.value = false
        countryLoading.value = false*/

    }
    fun refResshFromAPI(){
        getDataFromAPI()
    }
    private fun getDataFromSQlite(){
        countryLoading.value=true
        launch { val countries=CountryDatabase(getApplication()).countryDao().getAllCountries()
        showCountries(countries)
        Toast.makeText(getApplication(),"Countreis get fron sql",Toast.LENGTH_LONG).show()}
    }
    private fun getDataFromAPI(){
        countryLoading.value=true

        disposable.add(
            countryApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Country>>(){
                    override fun onSuccess(t: List<Country>) {
                    storeInSQlLite(t)
                    }

                    override fun onError(e: Throwable) {
                        countryLoading.value = false
                        countryError.value = true
                        e.printStackTrace()
                    }
                }))
}
    private fun showCountries(countrtlist:List<Country>){
        countries.value=countrtlist
        countryLoading.value = false
        countryError.value = false

    }
    private fun storeInSQlLite(list:List<Country>){
        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
            dao.deleteAllCountries()
            val listLong = dao.insertAll(*list.toTypedArray()) // -> list -> individual
            var i = 0
            while (i < list.size) {
                list[i].uuid = listLong[i].toInt()
                i = i + 1
                i = i + 1
            }

            showCountries(list)
        }
        customPrefences.savetime(System.nanoTime())
    }

    override fun onCleared() {
        super.onCleared()

        disposable.clear()
    }
}
