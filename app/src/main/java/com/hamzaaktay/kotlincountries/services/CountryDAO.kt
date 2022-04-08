package com.hamzaaktay.kotlincountries.services

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.hamzaaktay.kotlincountries.model.Country

@Dao
interface CountryDAO {

    //Data Access Object


    @Insert
    suspend fun insertAll (vararg countries : Country) : List<Long>

    //Insert -> INSERT INTO
    // suspend -> Coroutine, pause & resume
    // vararg -> Tekil objeyi çoğul olarak göstermek için. Bir listeyi tek tek vermek.
    //vararg -> multiple country objects
    // List <Long> -> Primary Keys


    @Query("SELECT * FROM country")
    suspend fun getAllCountries () : List<Country>

    @Query ("SELECT * FROM country WHERE uuid= :countryId")
    suspend fun getCountry (countryId : Int) : Country

    @Query("DELETE FROM country")
    suspend fun deleteAllCountries()


}