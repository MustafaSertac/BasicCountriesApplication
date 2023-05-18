package service

import Model.Country
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
@Dao
interface CountryDao {

    @Insert
    suspend fun insertAll(vararg countries: Country) : List<Long>
    //Data access Object=DAO
    @Query("SELECT * FROM country")
    suspend fun getAllCountries() : List<Country>

    @Query("SELECT * FROM country WHERE uuid = :countryId")
    suspend fun getCountry(countryId : Int) : Country

    @Query("DELETE FROM country")
    suspend fun deleteAllCountries()
}