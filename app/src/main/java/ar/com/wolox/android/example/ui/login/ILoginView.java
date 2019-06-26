package ar.com.wolox.android.example.ui.login;

/**
 * LoginInterface
 */

public interface ILoginView {
    void setEmptyEmailError();

    void setEmptyPassError();

    void setInvalidEmailError();

    void showCredentials(String email, String pass);

    void goSignUp();

    void goHome();

    void goTermsAndConditions();

    void setWrongEmailPassword();

    void setNoNetworkConnection();

    void startLoading();

    void completeLoading();

    void unsuccessfulResponse();

    void saveCredentials(String mEmail, String mPassword);
}