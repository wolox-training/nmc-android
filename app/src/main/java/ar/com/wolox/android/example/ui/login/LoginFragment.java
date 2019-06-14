package ar.com.wolox.android.example.ui.login;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ar.com.wolox.android.R;
import ar.com.wolox.wolmo.core.fragment.WolmoFragment;

/**
 * LoginFragment
 */

public class LoginFragment extends WolmoFragment<LoginPresenter> implements ILoginView {

    Button buttonLogin, buttonSignup;
    EditText textEmail, textPass;

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

        getPresenter().attachView(this);

        getPresenter().onInit(getContext());

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPresenter().onLoginButtonClicked(textEmail.getText().toString(), textPass.getText().toString(), getContext());
            }
        });
    }

    @Override
    public void setEmptyEmailPass() {
        if (textEmail.getText().toString().isEmpty()) {
            textEmail.setError(getString(R.string.email_required));
        }
        if (textPass.getText().toString().isEmpty()) {
            textPass.setError(getString(R.string.password_required));
        }
    }

    @Override
    public void setInvalidEmail() {
        textEmail.setError(getString(R.string.invalid_email));
    }

    @Override
    public void showCredentials(String email, String pass) {
        textEmail.setText(email);
        textPass.setText(pass);
    }
}