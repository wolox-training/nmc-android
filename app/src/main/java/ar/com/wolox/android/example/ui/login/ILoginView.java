package ar.com.wolox.android.example.ui.login;

/**
 * LoginInterface
 */

interface ILoginView {
    void setEmptyEmailError();

    void setEmptyPassError();

    void setInvalidEmailError();

    void showCredentials(String email, String pass);

    void goSignUp();

    void goHome();

    void goTermsAndConditions();

    void waitUntilGetUsers();

    void setWrongEmailPassword(String email, String password);

    void setNoNetworkConnection();
}