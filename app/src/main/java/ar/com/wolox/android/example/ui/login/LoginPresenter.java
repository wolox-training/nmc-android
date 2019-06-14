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

    static final String SHARED_PREFERENCES = "MySharedPreferences";
    static final String EMAIL_KEY = "ar.com.wolox.android.example.emailCredential";
    static final String PASSWORD_KEY = "ar.com.wolox.android.example.passCredential";

    @Inject
    LoginPresenter() {}

    /**
     * @param email String with the User Email
     * @param pass String with the User Password
     * @param context Context of LoginFragment
     */
    public void onLoginButtonClicked(String email, String pass, Context context) {

        if (!email.isEmpty() && validFormat(email) && !pass.isEmpty()) {
            saveCredentials(email, pass, context);
            return;
        }

        if (email.isEmpty() || pass.isEmpty()) {
            getView().setEmptyEmailPass();
        }
        if (!email.isEmpty() && !validFormat(email)) {
            getView().setInvalidEmail();
        }
    }

    private boolean validFormat(String email) {

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    private void saveCredentials(String email, String pass, Context context) {
          SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES, context.MODE_PRIVATE);
          SharedPreferences.Editor editor = sharedPreferences.edit();

          editor.putString(EMAIL_KEY, email);
          editor.putString(PASSWORD_KEY, pass);

          editor.apply();
    }

    /**
     * @param context Context of LoginFragment
     */
    public void onInit(Context context) {
          SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES, context.MODE_PRIVATE);

          String email = sharedPreferences.getString(EMAIL_KEY, "");
          String pass = sharedPreferences.getString(PASSWORD_KEY, "");

          getView().showCredentials(email, pass);
    }
}