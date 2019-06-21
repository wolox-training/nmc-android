package ar.com.wolox.android.example.ui.login;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import ar.com.wolox.android.R;
import ar.com.wolox.android.example.ui.home.HomeActivity;
import ar.com.wolox.android.example.ui.signup.SingUpActivity;
import ar.com.wolox.wolmo.core.fragment.WolmoFragment;

/**
 * LoginFragment
 */

public class LoginFragment extends WolmoFragment<LoginPresenter> implements ILoginView {

    private Button mButtonLogin, mButtonSignup;
    private EditText mTextEmail, mTextPass;
    private TextView mTermsAndConditions;
    private ProgressBar mLoginProgressBar;
    private static final String URL = "http://www.wolox.com.ar";

    @Override
    public int layout() {
        return R.layout.fragment_login;
    }

    @Override
    public void init() {

        mButtonLogin = getActivity().findViewById(R.id.button_login);
        mButtonSignup = getActivity().findViewById(R.id.button_signup);
        mTextEmail = getActivity().findViewById(R.id.text_mail);
        mTextPass = getActivity().findViewById(R.id.text_pass);
        mLoginProgressBar = getActivity().findViewById(R.id.loginProgressBar);
        mTermsAndConditions = getActivity().findViewById(R.id.footer);

        getPresenter().onInit(getContext());

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPresenter().onLoginButtonClicked(mTextEmail.getText().toString(), mTextPass.getText().toString(), getContext());
            }
        });

        mButtonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPresenter().onSignUpButtonClicked();
            }
        });

        mTermsAndConditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPresenter().onTermsAndConditionsClicked();
            }
        });
    }

    @Override
    public void setEmptyEmailError() {
        mTextEmail.setError(getString(R.string.email_required));
    }

    @Override
    public void setEmptyPassError() {
        mTextPass.setError(getString(R.string.password_required));
    }

    @Override
    public void setInvalidEmailError() {
        mTextEmail.setError(getString(R.string.invalid_email));
    }

    @Override
    public void showCredentials(String email, String pass) {
        mTextEmail.setText(email);
        mTextPass.setText(pass);
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
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
        startActivity(browserIntent);
    }

    @Override
    public void startLoading() {
        mLoginProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void completeLoading() {
        mLoginProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void unsuccessfulResponse() {
        Toast.makeText(getContext(), R.string.unsuccessful_response, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setWrongEmailPassword(String email, String password) {
        if (!mTextEmail.getText().toString().equals(email) && !mTextPass.getText().toString().equals(password)) {
            Toast.makeText(getContext(), R.string.wrong_email_passowrd, Toast.LENGTH_SHORT).show();
        }
        if (mTextEmail.getText().toString().equals(email) && !mTextPass.getText().toString().equals(password)) {
            Toast.makeText(getContext(), R.string.wrong_password, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setNoNetworkConnection() {
        Toast.makeText(getContext(), R.string.no_network_connection, Toast.LENGTH_SHORT).show();
    }
}