package ar.com.wolox.android.example.ui.login;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import ar.com.wolox.wolmo.core.presenter.BasePresenter;

/**
 * LoginPresenter
 */

public class LoginPresenter extends BasePresenter<ILoginView> {

    private static final String SHARED_PREFERENCES = "MySharedPreferences";
    private static final String EMAIL_KEY = "ar.com.wolox.android.example.emailCredential";
    private static final String PASSWORD_KEY = "ar.com.wolox.android.example.passCredential";
    private LoginAdapterAPI loginAdapterAPI;
    private String mEmail;
    private String mPassword;

    @Inject
    public LoginPresenter(LoginAdapterAPI loginAdapterAPI) {
        this.loginAdapterAPI = loginAdapterAPI;
    }

    /**
     * @param email String with the User Email
     * @param password String with the User Password
     */
    public void onLoginButtonClicked(String email, String password) {

        if (!email.isEmpty() && validFormat(email) && !password.isEmpty()) {
            mEmail = email;
            mPassword = password;
            getUsersFromServer(email, password);
        }

        if (email.isEmpty()) {
            getView().setEmptyEmailError();
        } else {
            if (!validFormat(email)) {
                getView().setInvalidEmailError();
            }
        }

        if (password.isEmpty()) {
            getView().setEmptyPassError();
        }
    }

    private boolean validFormat(String email) {

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    /**
     * @param context Context of LoginFragment
     */
    public void onInit(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);

        String email = sharedPreferences.getString(EMAIL_KEY, "");
        String password = sharedPreferences.getString(PASSWORD_KEY, "");

        getView().showCredentials(email, password);

        if (!email.isEmpty() && !password.isEmpty()) {
            getView().goHome();
        }
    }

    public void onSignUpButtonClicked() {
        getView().goSignUp();
    }

    public void onTermsAndConditionsClicked() {
        getView().goTermsAndConditions();
    }

    /**
     * @param email String
     * @param password String
     */
    public void getUsersFromServer(String email, String password) {
        getView().startLoading();
        loginAdapterAPI.getUsers(email, password,
                () -> onLoginSuccess(),
                () -> onLoginError(),
                () -> onUnsuccessfulResponse());
    }

    private void onLoginSuccess() {
        getView().completeLoading();
        getView().saveCredentials(mEmail, mPassword);
        getView().goHome();
    }

    private void onLoginError() {
        getView().completeLoading();
        getView().setWrongEmailPassword();
    }

    private void onUnsuccessfulResponse() {
        getView().completeLoading();
        getView().unsuccessfulResponse();
    }
}