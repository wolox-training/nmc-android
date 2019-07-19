package ar.com.wolox.android.example.ui.home.news.individualNew

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import ar.com.wolox.android.R
import ar.com.wolox.android.example.network.News
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_individual_new.*
import javax.inject.Inject

class IndividualNewFragment @Inject constructor() : WolmoFragment<IndividualNewPresenter>(), IIndividualNewView {

    override fun layout(): Int = R.layout.fragment_individual_new

    override fun init() {
        val id = arguments?.getParcelable<News>(NEW_ID)?.id

        arguments?.getParcelable<News>(NEW_ID)?.run {
            new_title.text = title
            new_time.text = readableCreationTime
            new_description.text = text
        }

        individual_new_image.setImageURI(Uri.parse(HARDCODED_SHIBA))

        individual_new_back_button.setOnClickListener {
            /**I need to go back to the News List View when this button is clicked*/
        }

        vSwipeRefreshLayoutIndividualNew.setOnRefreshListener {
            presenter.onRefreshIndividualNew(id!!)
        }
    }

    override fun refreshNewData(new: News) {
        new_title.text = new.title
        new_time.text = new.readableCreationTime
        new_description.text = new.text
    }

    override fun nothingNewToShow() {
        Toast.makeText(context, R.string.nothing_new_to_show, Toast.LENGTH_SHORT).show()
    }

    override fun onRefreshNewError() {
        Toast.makeText(context, R.string.fail_refreshing_individual_new, Toast.LENGTH_SHORT).show()
    }

    override fun startLoading() {
        vSwipeRefreshLayoutIndividualNew.isRefreshing = true
    }

    override fun completeLoading() {
        vSwipeRefreshLayoutIndividualNew.isRefreshing = false
    }

    companion object {
        /**I put this image because the original ones from the server doesn't load.*/
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
