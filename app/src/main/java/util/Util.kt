package util

import android.content.Context
import android.widget.ImageView
import com.example.basiccountriesapplication.R
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class Util

{
    fun  downloadFromUrl(imageView: ImageView,context: Context,url:String?,progressDrawable: CircularProgressDrawable){
        val options=RequestOptions.placeholderOf(progressDrawable)
            .error(R.mipmap.ic_launcher)
        Glide.with(context).setDefaultRequestOptions(options)
            .load(url)
            .into(imageView)


    }
    fun placeholderProgressbar(context:Context):CircularProgressDrawable{
        return CircularProgressDrawable(context).apply {
            strokeWidth=8f
            centerRadius=40f
            start()

        }
    }
}