package com.hamzaaktay.kotlincountries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.hamzaaktay.kotlincountries.R
import com.hamzaaktay.kotlincountries.adapter.CountryAdapter
import com.hamzaaktay.kotlincountries.databinding.FragmentFeedBinding
import com.hamzaaktay.kotlincountries.viewmodel.FeedViewModel


class FeedFragment : Fragment(R.layout.fragment_feed) {

    private lateinit var binding : FragmentFeedBinding
    private lateinit var viewModel : FeedViewModel
    private val countryAdapter = CountryAdapter(arrayListOf())



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
        viewModel.refreshData()

        binding.countryList.layoutManager = LinearLayoutManager(context)
        binding.countryList.adapter = countryAdapter


        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.countryList.visibility = View.GONE
            binding.countryError.visibility = View.GONE
            binding.countryLoading.visibility = View.VISIBLE
            viewModel.refreshFromAPI()
            binding.swipeRefreshLayout.isRefreshing = false


        }


        observeLiveData ()

    }


    private fun observeLiveData () {
        viewModel.countries.observe(viewLifecycleOwner, Observer { countries ->

            countries?.let {
                binding.countryList.visibility = View.VISIBLE
                countryAdapter.updateCountryList(countries)
            }

        })

        viewModel.countryError.observe(viewLifecycleOwner, Observer { error ->

            error?.let {
                if (it) {
                    binding.countryError.visibility = View.VISIBLE
                    binding.countryList.visibility = View.GONE

                } else {
                    binding.countryError.visibility = View.GONE
                }
            }

            viewModel.countryLoading.observe(viewLifecycleOwner, Observer { loading ->

                loading?.let {
                    if (it) {
                        binding.countryLoading.visibility = View.VISIBLE
                        binding.countryList.visibility = View.GONE
                        binding.countryError.visibility = View.GONE
                    } else {
                        binding.countryLoading.visibility = View.GONE
                    }
                }

            } )


        })



    }


}