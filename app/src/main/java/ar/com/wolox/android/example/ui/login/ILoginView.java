package ar.com.wolox.android.example.ui.login;

/**
 * LoginInterface
 */

interface ILoginView {
    void setEmptyEmailPass();

    void setInvalidEmail();

    void showCredentials(String email, String pass);
}