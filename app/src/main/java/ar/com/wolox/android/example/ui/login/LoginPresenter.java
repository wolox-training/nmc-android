package ar.com.wolox.android.example.ui.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import ar.com.wolox.android.example.network.UserServices;
import ar.com.wolox.android.example.network.Users;
import ar.com.wolox.wolmo.core.presenter.BasePresenter;
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * LoginPresenter
 */

public class LoginPresenter extends BasePresenter<ILoginView> {

    private static final String SHARED_PREFERENCES = "MySharedPreferences";
    private static final String EMAIL_KEY = "ar.com.wolox.android.example.emailCredential";
    private static final String PASSWORD_KEY = "ar.com.wolox.android.example.passCredential";
    private RetrofitServices mRetrofitServices;

    @Inject
    LoginPresenter(RetrofitServices retrofitServices) {
        this.mRetrofitServices = retrofitServices;
    }

    /**
     * @param email String with the User Email
     * @param password String with the User Password
     * @param context Context of LoginFragment
     */
    public void onLoginButtonClicked(String email, String password, Context context) {

        if (!isNetworkConnected(context)) {
            getView().setNoNetworkConnection();
            return;
        }

        getUsersFromServer(email, password, context);
    }

    private boolean validFormat(String email) {

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    private void saveCredentials(String email, String password, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(EMAIL_KEY, email);
        editor.putString(PASSWORD_KEY, password);

        editor.apply();

        getView().goHome();
    }

    /**
     * @param context Context of LoginFragment
     */
    public void onInit(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);

        String email = sharedPreferences.getString(EMAIL_KEY, "");
        String password = sharedPreferences.getString(PASSWORD_KEY, "");

        getView().showCredentials(email, password);

        if (!email.isEmpty() && !password.isEmpty()) {
            getView().goHome();
        }
    }

    public void onSignUpButtonClicked() {
        getView().goSignUp();
    }

    public void onTermsAndConditionsClicked() {
        getView().goTermsAndConditions();
    }

    private void getUsersFromServer(String email, String password, Context context) {
        getView().startLoading();
        mRetrofitServices.getService(UserServices.class).getAllUsers().enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                getView().completeLoading();
                if (response.isSuccessful()) {
                    List<Users> usersList = response.body();

                    if (!email.isEmpty() && validFormat(email) && !password.isEmpty()) {
                        if (isEmailAndPasswordRegistered(email, password, usersList)) {
                            saveCredentials(email, password, context);
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
                    if (password.isEmpty()) {
                        getView().setEmptyPassError();
                    }
                } else {
                    getView().unsuccessfulResponse();
                }
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                getView().completeLoading();
            }
        });
    }

    private Boolean isEmailAndPasswordRegistered(String email, String pass, List<Users> usersList) {
        if (usersList == null) {
            return false;
        }
        for (int i = 0; i < usersList.size(); i++) {
            if (email.equals(usersList.get(i).getEmail()) && pass.equals(usersList.get(i).getPassword())) {
                return true;
            }
            if (email.equals(usersList.get(i).getEmail()) && !pass.equals(usersList.get(i).getPassword())) {
                getView().setWrongPassword();
                return false;
            }
        }
        getView().setWrongEmailPassword();
        return false;
    }

    private Boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null;
    }
}