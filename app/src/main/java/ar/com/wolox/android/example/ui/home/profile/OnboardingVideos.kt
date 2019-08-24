package ar.com.wolox.android.example.ui.home.profile

import ar.com.wolox.android.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import javax.inject.Inject

class OnboardingVideos @Inject constructor() : WolmoFragment<OnboardingVideosPresenter>(), IOnboardingVideos {

    override fun layout(): Int = R.layout.fragment_onboarding_videos

    override fun init() {
    }

    companion object {
        fun newInstance() = OnboardingVideos()
    }
}

interface IOnboardingVideos