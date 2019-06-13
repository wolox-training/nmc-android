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
        LoginPresenter loginPresenter = new LoginPresenter();
        loginPresenter.attachView(this);

        buttonLogin = getActivity().findViewById(R.id.button_login);
        buttonSignup = getActivity().findViewById(R.id.button_signup);
        textEmail = getActivity().findViewById(R.id.text_mail);
        textPass = getActivity().findViewById(R.id.text_pass);

        loginPresenter.getCredentials(getActivity());

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPresenter.onLoginButtonClicked(textEmail, textPass, getActivity(), getContext());
            }
        });
    }

    @Override
    public void setEmptyEmailPass(String emailReq, String passReq) {
        textEmail.setError(emailReq);
        textPass.setError(passReq);
    }

    @Override
    public void setEmptyEmail(String emailReq) {
        textEmail.setError(emailReq);
    }

    @Override
    public void setEmptyPass(String passReq) {
        textPass.setError(passReq);
    }

    @Override
    public void setInvalidEmail(String invEmail) {
        textEmail.setError(invEmail);
    }

    @Override
    public void setCredentials(String email, String pass) {
        textEmail.setText(email);
        textPass.setText(pass);
    }
}