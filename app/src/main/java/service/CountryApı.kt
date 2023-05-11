package service

import Model.Country
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface CountryApı {
//https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json
    @GET("IA19-DataSetCountries/master/countrydataset.json")
    fun GetCountries(): Single<List<Country>>
}