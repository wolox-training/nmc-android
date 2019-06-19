package ar.com.wolox.android.example.di

import ar.com.wolox.android.example.ui.home.HomeActivity
import ar.com.wolox.android.example.ui.home.HomeFragment
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
}
