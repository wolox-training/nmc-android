package ar.com.wolox.android.example.ui.login;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ar.com.wolox.android.R;
import ar.com.wolox.android.example.ui.home.HomeActivity;
import ar.com.wolox.android.example.ui.signup.SingUpActivity;
import ar.com.wolox.wolmo.core.fragment.WolmoFragment;

/**
 * LoginFragment
 */

public class LoginFragment extends WolmoFragment<LoginPresenter> implements ILoginView {

    Button buttonLogin, buttonSignup;
    EditText textEmail, textPass;
    TextView termsAndConditions;

    @Override
    public int layout() {
        return R.layout.fragment_login;
    }

    @Override
    public void init() {

        buttonLogin = getActivity().findViewById(R.id.button_login);
        buttonSignup = getActivity().findViewById(R.id.button_signup);
        textEmail = getActivity().findViewById(R.id.text_mail);
        textPass = getActivity().findViewById(R.id.text_pass);
        termsAndConditions = getActivity().findViewById(R.id.footer);

        getPresenter().attachView(this);

        getPresenter().onInit(getContext());

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPresenter().onLoginButtonClicked(textEmail.getText().toString(), textPass.getText().toString(), getContext());
            }
        });

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPresenter().onSignUpButtonClicked();
            }
        });

        termsAndConditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPresenter().onTermsAndConditionsClicked();
            }
        });
    }

    @Override
    public void setEmptyEmailError() {
        textEmail.setError(getString(R.string.email_required));
    }

    @Override
    public void setEmptyPassError() {
        textPass.setError(getString(R.string.password_required));
    }

    @Override
    public void setInvalidEmailError() {
        textEmail.setError(getString(R.string.invalid_email));
    }

    @Override
    public void showCredentials(String email, String pass) {
        textEmail.setText(email);
        textPass.setText(pass);
    }

    @Override
    public void goSignUp() {
        Intent intent = new Intent(getContext(), SingUpActivity.class);
        startActivity(intent);
    }

    @Override
    public void goHome() {
        Intent intent = new Intent(getContext(), HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void goTermsAndConditions() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.terms_and_conditions)));
        startActivity(browserIntent);
    }
}