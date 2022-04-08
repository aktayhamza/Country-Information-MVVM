package com.hamzaaktay.kotlincountries.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hamzaaktay.kotlincountries.model.Country
import com.hamzaaktay.kotlincountries.services.CountryDatabase
import kotlinx.coroutines.launch

class CountryViewModel (application: Application) : BaseViewModel (application) {

    val countryLiveData = MutableLiveData <Country> ()


    fun getDataFromRoom (uuid: Int) {
        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
            val country = dao.getCountry(uuid)
            countryLiveData.value = country
        }

    }
}