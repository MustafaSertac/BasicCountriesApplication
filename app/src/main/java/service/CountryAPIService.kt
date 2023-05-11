package service

import Model.Country
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CountryAPIService {

    private val BASE_URL="https://raw.githubusercontent.com/atilsamancioglu/"

    private val     api=Retrofit.Builder()
        .baseUrl(BASE_URL).
    addConverterFactory(GsonConverterFactory.create()).
    addCallAdapterFactory((RxJava3CallAdapterFactory.create()))
        .build()
        .create(CountryApı::class.java)




 fun getData():Single<List<Country>>{
     return api.GetCountries()
 }
}