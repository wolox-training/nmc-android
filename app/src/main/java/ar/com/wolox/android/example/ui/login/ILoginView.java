package ar.com.wolox.android.example.ui.login;

/**
 * LoginInterface
 */

interface ILoginView {
    void setEmptyEmailPass(String emailReq, String passReq);

    void setInvalidEmail(String invEmail);

    void setEmptyPass(String passReq);

    void setEmptyEmail(String emailReq);
}