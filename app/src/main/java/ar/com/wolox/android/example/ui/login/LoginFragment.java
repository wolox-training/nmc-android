package ar.com.wolox.android.example.ui.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ar.com.wolox.android.R;
import ar.com.wolox.wolmo.core.fragment.WolmoFragment;

/**
 * Login Fragment
 */

public class LoginFragment extends WolmoFragment {

    Button buttonLogin, buttonSignup;
    EditText textMail, textPass;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public int layout() {
        return R.layout.fragment_login;
    }

    @Override
    public void init() {
        buttonLogin = getActivity().findViewById(R.id.button_login);
        buttonSignup = getActivity().findViewById(R.id.button_signup);
        textMail = getActivity().findViewById(R.id.text_mail);
        textPass = getActivity().findViewById(R.id.text_pass);

        obtenerCredenciales();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = textMail.getText().toString();
                Boolean m, p;
                m = textMail.getText().toString().equals("");
                p = textPass.getText().toString().equals("");

                if (m && p) {
                    textMail.setError(getString(R.string.reqMail));
                    textPass.setError(getString(R.string.reqPass));
                    return;
                }

                if (!m && p) {
                    textPass.setError(getString(R.string.reqPass));
                    if (!formatoValido(mail)) {
                        textMail.setError(getString(R.string.invMail));
                    }
                    return;
                }

                if (m) {
                    textMail.setError(getString(R.string.reqMail));
                    return;
                }

                if (!formatoValido(mail)) {
                    textMail.setError(getString(R.string.invMail));
                } else {
                    guardarCredenciales();
                }
            }
        });

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    private boolean formatoValido(String email) {

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    private void guardarCredenciales() {
        SharedPreferences shm = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor edm = shm.edit();

        edm.putString(getString(R.string.shMail), textMail.getText().toString());
        edm.putString(getString(R.string.shPass), textPass.getText().toString());

        edm.apply();

        Log.d(getString(R.string.cred), "Guardadas OK");
    }

    private void obtenerCredenciales() {
        SharedPreferences shm = getActivity().getPreferences(Context.MODE_PRIVATE);

        String mail = shm.getString(getString(R.string.shMail), "");
        String pass = shm.getString(getString(R.string.shPass), "");

        textMail.setText(mail);
        textPass.setText(pass);

        Log.d(getString(R.string.cred), mail);
        Log.d(getString(R.string.cred), pass);
    }
}
