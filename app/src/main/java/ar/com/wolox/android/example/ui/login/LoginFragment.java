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
        SharedPreferences sharedPref = getActivity().getSharedPreferences(getString(R.string.sharedPref), Context.MODE_PRIVATE);
        Boolean logged = false;
        buttonLogin = getActivity().findViewById(R.id.button_login);
        buttonSignup = getActivity().findViewById(R.id.button_signup);
        textMail = getActivity().findViewById(R.id.text_mail);
        textPass = getActivity().findViewById(R.id.text_pass);

        logged = isLogged(sharedPref);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (textMail.getText().toString().equals("") && textPass.getText().toString().equals("")) {
                    textMail.setError("El Mail es requerido");
                    textPass.setError("La Contraseña es Requerida");
                } else {
                    if (!formatoValido(textMail.getText().toString())) {
                        textMail.setError("Formato de Mail inválido");
                    } else {
                        guardarCredenciales(sharedPref);
                    }
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

    private boolean isLogged(SharedPreferences sh) {
        String mail = sh.getString(getString(R.string.sharedPref), getString(R.string.none));
        Log.d(getString(R.string.cred), mail);
        return !mail.equals(getString(R.string.none));
    }

    private void guardarCredenciales(SharedPreferences sh) {
        SharedPreferences.Editor editor = sh.edit();
        editor.putString(getString(R.string.sharedPref), textMail.getText().toString());
        editor.apply();
        Log.d(getString(R.string.cred), "Guardadas OK");
    }
}
