package ar.com.wolox.android.example.ui.login;

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

    @Inject
    LoginPresenter() {}

    /**
     * @param email EditText with the User Email
     * @param pass EditText with the User Password
     */
    public void onLoginButtonClicked(EditText email, EditText pass) {

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
            }
        }
    }

    private boolean validFormat(String email) {

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
}