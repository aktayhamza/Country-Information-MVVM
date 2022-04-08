package com.hamzaaktay.kotlincountries.adapter

import android.content.ClipData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.hamzaaktay.kotlincountries.R
import com.hamzaaktay.kotlincountries.databinding.ItemCountryBinding
import com.hamzaaktay.kotlincountries.model.Country
import com.hamzaaktay.kotlincountries.util.downloadFromUrl
import com.hamzaaktay.kotlincountries.util.placeholderProgressBar
import com.hamzaaktay.kotlincountries.view.FeedFragmentDirections

class CountryAdapter (val countryList : ArrayList<Country>) : RecyclerView.Adapter <CountryAdapter.CountryViewHolder> () {


    class CountryViewHolder (val view: ItemCountryBinding) : RecyclerView.ViewHolder (view.root)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        //val itemCountryBinding =ItemCountryBinding.inflate(layoutInflater, parent,false)
        val view = DataBindingUtil.inflate<ItemCountryBinding>(layoutInflater,R.layout.item_country,parent,false)
        return CountryViewHolder(view)


    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {

        holder.view.country = countryList[position]

        holder.view.itemCountry.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(countryList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }



        /*
        holder.itemCountryBinding.apply {
        nameText.text = countryList[position].countryName
            regionText.text = countryList[position].countryRegion}



        holder.itemCountryBinding.itemCountry.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(countryList[position].uuid)
            Navigation.findNavController(it).navigate(action)


        }

        holder.itemCountryBinding.countryImage.downloadFromUrl(countryList[position].imageUrl, placeholderProgressBar(holder.itemCountryBinding.itemCountry.context))*/


    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    fun updateCountryList (newCountryList : List<Country>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }


}