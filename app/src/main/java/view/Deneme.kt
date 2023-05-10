package view

import Adapter.CountryAdapter
import Model.Country
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basiccountriesapplication.R
import com.example.basiccountriesapplication.databinding.ActivityDenemeBinding
import com.example.basiccountriesapplication.databinding.FragmentFeedBinding

class deneme : AppCompatActivity() {
    private  lateinit var binding:ActivityDenemeBinding
    private  val countryAdapter= CountryAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDenemeBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        binding.denemerecycleview.layoutManager= LinearLayoutManager(this)
        binding.denemerecycleview.adapter=countryAdapter
        countryAdapter.updateCountryList(refreshData())

7

    }
    fun refreshData():ArrayList<Country>{
        val countries= arrayListOf<Country>()
        val country= Country("Turkey","Avrupa","Ankara","Tl","Türkçe","ic_launcher_background.xml")
        val country2= Country("Almanya","Avrupa","Berlin","Euro","Almanca","ic_launcher_background.xml")

        val country3= Country("Mısır","Afrika","Kahaire","Paund","Mısırca","ic_launcher_background.xml")
        val countrylist= arrayListOf<Country>(country,country2,country3)

    return countrylist


    }
}