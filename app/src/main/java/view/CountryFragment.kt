package view

import ViewModel.CountryViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.basiccountriesapplication.R
import com.example.basiccountriesapplication.databinding.FragmentCountryBinding
import util.Util



class CountryFragment : Fragment() {
 private lateinit var binding:FragmentCountryBinding
    private lateinit var viewModel : CountryViewModel
    private var countryUuid = 0
    private val util=Util()
    private  lateinit var databinding:FragmentCountryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        databinding=DataBindingUtil.inflate(inflater,R.layout.fragment_country,container,false)
        // Inflate the layout for this fragment
        return databinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentCountryBinding.inflate(layoutInflater)

        viewModel.getDataFromRoom(countryUuid)

        viewModel = ViewModelProvider(this).get(CountryViewModel::class.java)


        observeLiveData()
    }

    private fun observeLiveData() {

        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country->
            country?.let {
                databinding.selectedcountry=country
                /*
                binding.countryName.text = country.countryName
                binding.countryCapital.text = country.countryCapital
                binding.countryCurrency.text = country.countryCurrency
                binding.countryLanguage.text = country.countryLanguage
                binding.countryRegion.text = country.countryRegion
                context?.let {


                }*/




            }
        })
    }


}