package util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.basiccountriesapplication.R
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class Util

{
    fun ImageView.downloadFromUrl(url: String?, progressDrawable: CircularProgressDrawable){

        val options = RequestOptions()
            .placeholder(progressDrawable)
            .error(R.mipmap.ic_launcher_round)

        Glide.with(context)
            .setDefaultRequestOptions(options)
            .load(url)
            .into(this)

    }
    fun placeholderProgressbar(context:Context):CircularProgressDrawable{
        return CircularProgressDrawable(context).apply {
            strokeWidth=8f
            centerRadius=40f
            start()

        }
    }
         @BindingAdapter("android:downloadUrl")
        fun downloadImage(view:ImageView,url: String?){
            view.downloadFromUrl(url,placeholderProgressbar(view.context))

    }
}