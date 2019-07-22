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

    override fun layout(): Int = R.layout.activity_individual_new

    override fun init() {
        replaceFragment(R.id.activityIndividualNewBaseContent, IndividualNewFragment.newInstance(intent.getParcelableExtra(INDIVIDUAL_NEW)))
    }

    companion object {

        private const val INDIVIDUAL_NEW = "INDIVIDUAL_NEW"

        fun starter(context: Context, new: News) {
            val intent = Intent(context, IndividualNewActivity::class.java)
            intent.putExtra(INDIVIDUAL_NEW, new)
            startActivity(context, intent, intent.extras)
        }
    }
}