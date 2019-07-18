package ar.com.wolox.android.example.ui.home.news.individualNew

import android.net.Uri
import android.os.Bundle
import ar.com.wolox.android.R
import ar.com.wolox.android.example.network.News
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_individual_new.*
import javax.inject.Inject

class IndividualNewFragment @Inject constructor() : WolmoFragment<IndividualNewPresenter>(), IIndividualNewView {

    override fun layout(): Int = R.layout.fragment_individual_new

    override fun init() {
        new_title.text = arguments?.getParcelable<News>(NEW_ID)?.title
        new_time.text = arguments?.getParcelable<News>(NEW_ID)?.readableCreationTime
        new_description.text = arguments?.getParcelable<News>(NEW_ID)?.text
        individual_new_image.setImageURI(Uri.parse(HARDCODED_SHIBA))
    }

    companion object {
        private const val HARDCODED_SHIBA = "https://webbonbon.com/wp-content/uploads/2018/01/cropped-doge.jpg"

        private const val NEW_ID = "NEW_ID"

        fun newInstance(new: News): IndividualNewFragment {
            val individualNewFragment = IndividualNewFragment()
            val args = Bundle(1)
            args.putParcelable(NEW_ID, new)
            individualNewFragment.arguments = args
            return individualNewFragment
        }
    }
}
