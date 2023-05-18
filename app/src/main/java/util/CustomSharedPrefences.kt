package util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.core.content.edit

class CustomSharedPrefences {


    companion object {
        private val PREFENCES_TIME="prefences_time"
        private var sharedPrefences:SharedPreferences?=null
        @Volatile private var instance:CustomSharedPrefences?=null
private val lock=Any()
        operator fun invoke(context:Context): CustomSharedPrefences = instance ?: synchronized(lock){
            instance ?: makeCustomSharedPrefences(context).also {
                instance=it
            }
        }





  private fun makeCustomSharedPrefences(context: Context):CustomSharedPrefences{
      sharedPrefences=PreferenceManager.getDefaultSharedPreferences(context)
      return CustomSharedPrefences()

  }
    }

      fun savetime(time:Long){
          sharedPrefences?.edit(commit = true){
              putLong(PREFENCES_TIME,time)
          }
    }

    fun getTime()= sharedPrefences?.getLong(PREFENCES_TIME,0)

}



