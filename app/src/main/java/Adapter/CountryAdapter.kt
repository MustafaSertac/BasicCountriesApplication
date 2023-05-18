package Adapter

import Model.Country
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView

import com.example.basiccountriesapplication.R
import com.example.basiccountriesapplication.databinding.ItemCountryBinding
import util.Util
import view.FeedFragmentDirections





class CountryAdapter(val countryList: ArrayList<Country>): RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(),CountryListener {

private lateinit var util:Util

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)

   //     val view = inflater.inflate(R.layout.item_country,parent,false)
        val view=DataBindingUtil.inflate<ItemCountryBinding>(inflater,R.layout.item_country,parent,false)
        return CountryViewHolder(view)

    }

    class CountryViewHolder(var view: ItemCountryBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.view.country=countryList[position]
        holder.view.listener=this

    }
      /*  holder.view.findViewById<TextView>(R.id.name).text = countryList[position].countryName
        holder.view.findViewById<TextView>(R.id.region).text = countryList[position].countryRegion

        holder.view.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment()
            Navigation.findNavController(it).navigate(action)
        }

        util.downloadFromUrl(   holder.view.findViewById<ImageView>(R.id.imageView),holder.view.context,countryList[position].imageUrl,util.placeholderProgressbar(holder.view.context))

    }
*/

    fun updateCountryList(newCountryList: List<Country>) {
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

    override fun onClick(view: View) {

view.findViewById<TextView>(R.id.uuidtext).toString().let{
    val uuid=it

        }
     val action=FeedFragmentDirections.actionFeedFragmentToCountryFragment( )
        Navigation.findNavController(view).navigate(action)
    }
}