package ar.com.wolox.android.example.ui.login;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.EditText;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Inject;
import ar.com.wolox.wolmo.core.presenter.BasePresenter;

/**
 * LoginPresenter
 */

public class LoginPresenter extends BasePresenter<ILoginView> {

    final String emailReq = "Email is required";
    final String passReq = "Password is required";
    final String invEmail = "Invalid Email";
    final String emailKey = "ar.com.wolox.android.example.emailCredential";
    final String passKey = "ar.com.wolox.android.example.passCredential";

    @Inject
    LoginPresenter() {}

    /**
     * @param email EditText with the User Email
     * @param pass EditText with the User Password
     * @param activity Activity of LoginFragment
     * @param context Context of LoginFragment
     */
    public void onLoginButtonClicked(EditText email, EditText pass, Activity activity, Context context) {

        if (email.getText().toString().isEmpty() && pass.getText().toString().isEmpty()) {
            getView().setEmptyEmailPass(emailReq, passReq);
            return;
        }
        if (!email.getText().toString().isEmpty() && pass.getText().toString().isEmpty()) {
            if (!validFormat(email.getText().toString())) {
                getView().setInvalidEmail(invEmail);
            }
            getView().setEmptyPass(passReq);
            return;
        }
        if (email.getText().toString().isEmpty() && !pass.getText().toString().isEmpty()) {
            getView().setEmptyEmail(emailReq);
            return;
        } else {
            if (!validFormat(email.getText().toString())) {
                getView().setInvalidEmail(invEmail);
                return;
            }
        }
        saveCredentials(email, pass, activity, context);
    }

    private boolean validFormat(String email) {

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    private void saveCredentials(EditText email, EditText pass, Activity activity, Context context) {
          SharedPreferences sharedPreferences = activity.getPreferences(context.MODE_PRIVATE);
          SharedPreferences.Editor editor = sharedPreferences.edit();

          editor.putString(emailKey, email.getText().toString());
          editor.putString(passKey, pass.getText().toString());

          editor.apply();
    }

    /**
     * @param activity Activity of LoginFragment
     */
    public void getCredentials(Activity activity) {
          SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);

          String email = sharedPreferences.getString(emailKey, "");
          String pass = sharedPreferences.getString(passKey, "");

          getView().setCredentials(email, pass);
    }
}