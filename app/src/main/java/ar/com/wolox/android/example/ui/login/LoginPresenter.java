package ar.com.wolox.android.example.ui.login;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import ar.com.wolox.android.example.network.GetService;
import ar.com.wolox.android.example.network.Users;
import ar.com.wolox.wolmo.core.presenter.BasePresenter;
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * LoginPresenter
 */

public class LoginPresenter extends BasePresenter<ILoginView> implements Callback<List<Users>> {

    static final String SHARED_PREFERENCES = "MySharedPreferences";
    static final String EMAIL_KEY = "ar.com.wolox.android.example.emailCredential";
    static final String PASSWORD_KEY = "ar.com.wolox.android.example.passCredential";
    RetrofitServices mRetrofitServices;
    String mEmail, mPassword;
    List<Users> mUsers;

    @Inject
    LoginPresenter(RetrofitServices retrofitServices) {
        this.mRetrofitServices = retrofitServices;
    }

    /**
     * @param email String with the User Email
     * @param pass String with the User Password
     * @param context Context of LoginFragment
     */
    public void onLoginButtonClicked(String email, String pass, Context context) {

        if (mUsers == null) {
            getUsersFromServer();
        }

        if (!email.isEmpty() && validFormat(email) && !pass.isEmpty()) {
            if (isEmailAndPasswordRegistered(email, pass)) {
                saveCredentials(email, pass, context);
            }
            return;
        }

        if (email.isEmpty()) {
            getView().setEmptyEmailError();
        } else {
            if (!validFormat(email)) {
                getView().setInvalidEmailError();
            }
        }
        if (pass.isEmpty()) {
            getView().setEmptyPassError();
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

        getView().goHome();
    }

    /**
     * @param context Context of LoginFragment
     */
    public void onInit(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES, context.MODE_PRIVATE);

        String email = sharedPreferences.getString(EMAIL_KEY, "");
        String pass = sharedPreferences.getString(PASSWORD_KEY, "");

        getUsersFromServer();

        getView().showCredentials(email, pass);

        if (!email.isEmpty() && !pass.isEmpty()) {
            getView().goHome();
        }
    }

    public void onSignUpButtonClicked() {
        getView().goSignUp();
    }

    public void onTermsAndConditionsClicked() {
        getView().goTermsAndConditions();
    }

    private void getUsersFromServer() {
        mRetrofitServices.getService(GetService.class).getAllUsers().enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
        if (response.isSuccessful()) {
            this.mUsers = response.body();
        }
    }

    @Override
    public void onFailure(Call<List<Users>> call, Throwable t) {

    }

    private Boolean isEmailAndPasswordRegistered(String email, String pass) {
        for (int i = 0; i < mUsers.size(); i++) {
            if (email.equals(mUsers.get(i).getEmail()) && pass.equals(mUsers.get(i).getPassword())) {
                return true;
            }
        }
        return false;
    }
}