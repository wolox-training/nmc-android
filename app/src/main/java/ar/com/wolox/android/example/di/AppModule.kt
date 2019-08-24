package ar.com.wolox.android.example.di

import ar.com.wolox.android.example.ui.home.HomeActivity
import ar.com.wolox.android.example.ui.home.HomeFragment
import ar.com.wolox.android.example.ui.home.news.newsCreation.NewsCreationActivity
import ar.com.wolox.android.example.ui.home.news.newsCreation.NewsCreationFragment
import ar.com.wolox.android.example.ui.home.news.NewsFragment
import ar.com.wolox.android.example.ui.home.news.individualNew.IndividualNewActivity
import ar.com.wolox.android.example.ui.home.news.individualNew.IndividualNewFragment
import ar.com.wolox.android.example.ui.home.profile.Allergic
import ar.com.wolox.android.example.ui.home.profile.Diet
import ar.com.wolox.android.example.ui.home.profile.OnboardingVideos
import ar.com.wolox.android.example.ui.home.profile.ProfileFragment
import ar.com.wolox.android.example.ui.home.profile.turnOnCooktop.TurnOnCooktop
import ar.com.wolox.android.example.ui.login.LoginActivity
import ar.com.wolox.android.example.ui.login.LoginFragment
import ar.com.wolox.android.example.ui.signup.SingUpActivity
import ar.com.wolox.android.example.ui.signup.SignUpFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppModule {

    @ContributesAndroidInjector
    internal abstract fun loginActivity(): LoginActivity

    @ContributesAndroidInjector
    internal abstract fun signUpActivity(): SingUpActivity

    @ContributesAndroidInjector
    internal abstract fun homeActivity(): HomeActivity

    @ContributesAndroidInjector
    internal abstract fun loginFragment(): LoginFragment

    @ContributesAndroidInjector
    internal abstract fun signUpFragment(): SignUpFragment

    @ContributesAndroidInjector
    internal abstract fun homeFragment(): HomeFragment

    @ContributesAndroidInjector
    internal abstract fun newsFragment(): NewsFragment

    @ContributesAndroidInjector
    internal abstract fun profileFragment(): ProfileFragment

    @ContributesAndroidInjector
    internal abstract fun newsCreationActivity(): NewsCreationActivity

    @ContributesAndroidInjector
    internal abstract fun newsCreationFragment(): NewsCreationFragment

    @ContributesAndroidInjector
    internal abstract fun individualNewActivity(): IndividualNewActivity

    @ContributesAndroidInjector
    internal abstract fun individualNewFragment(): IndividualNewFragment

    @ContributesAndroidInjector
    internal abstract fun dietFragment(): Diet

    @ContributesAndroidInjector
    internal abstract fun allergicFragment(): Allergic

    @ContributesAndroidInjector
    internal abstract fun turnOnCooktop(): TurnOnCooktop

    @ContributesAndroidInjector
    internal abstract fun onboardingVideos(): OnboardingVideos
}
