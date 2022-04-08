package com.hamzaaktay.kotlincountries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.hamzaaktay.kotlincountries.R
import com.hamzaaktay.kotlincountries.databinding.FragmentCountryBinding
import com.hamzaaktay.kotlincountries.databinding.FragmentFeedBinding
import com.hamzaaktay.kotlincountries.util.downloadFromUrl
import com.hamzaaktay.kotlincountries.util.placeholderProgressBar
import com.hamzaaktay.kotlincountries.viewmodel.CountryViewModel


class CountryFragment : Fragment() {

    private lateinit var viewModel : CountryViewModel
    private lateinit var binding : FragmentCountryBinding
    private var countryUuid = 0
    private lateinit var databinding: FragmentCountryBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        databinding = DataBindingUtil.inflate(inflater,R.layout.fragment_country,container,false)
        return databinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            countryUuid = CountryFragmentArgs.fromBundle(it).countryUuid
        }


        viewModel = ViewModelProviders.of(this).get(CountryViewModel::class.java)
        viewModel.getDataFromRoom(countryUuid)


        observeLiveData ()


    }

    private fun observeLiveData () {

        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country ->

            country?.let {
                databinding.selectedCountry = country


            }

        })

    }


}