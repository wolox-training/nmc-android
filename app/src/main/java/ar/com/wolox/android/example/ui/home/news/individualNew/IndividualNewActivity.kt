package ar.com.wolox.android.example.ui.home.news.individualNew

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import ar.com.wolox.android.R
import ar.com.wolox.android.example.network.News
import ar.com.wolox.wolmo.core.activity.WolmoActivity
import javax.inject.Inject

class IndividualNewActivity @Inject constructor() : WolmoActivity() {

    @Inject internal lateinit var individualNewFragment: IndividualNewFragment

    companion object {
        fun starter(context: Context, noticia: News) {
            val intent = Intent(context, IndividualNewActivity::class.java)
            /**intent.putExtra("noticia",noticia)
             *  I need to make "News" parcelable
             */
            startActivity(context, intent, intent.extras)
        }
    }

    override fun layout(): Int = R.layout.activity_individual_new

    override fun init() {
        replaceFragment(R.id.activityIndividualNewBaseContent, individualNewFragment)
    }
}