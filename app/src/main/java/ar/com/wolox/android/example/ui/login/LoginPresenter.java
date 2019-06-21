package ar.com.wolox.android.example.ui.login;

import android.content.Context;
import android.content.SharedPreferences;

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
    private List<Users> mUsers;
    private String mEmail = "", mPassword = "";
    private Context mContext;

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

        mEmail = email;
        mPassword = pass;
        mContext = context;

        getUsersFromServer();
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
        getView().startLoading();
        mRetrofitServices.getService(UserServices.class).getAllUsers().enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                getView().completeLoading();
                if (response.isSuccessful()) {
                    mUsers = response.body();

                    if (!mEmail.isEmpty() && validFormat(mEmail) && !mPassword.isEmpty()) {
                        if (isEmailAndPasswordRegistered(mEmail, mPassword)) {
                            saveCredentials(mEmail, mPassword, mContext);
                        }
                        return;
                    }

                    if (mEmail.isEmpty()) {
                        getView().setEmptyEmailError();
                    } else {
                        if (!validFormat(mEmail)) {
                            getView().setInvalidEmailError();
                        }
                    }
                    if (mPassword.isEmpty()) {
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

    private Boolean isEmailAndPasswordRegistered(String email, String pass) {
        if (mUsers == null) {
            return false;
        }
        for (int i = 0; i < mUsers.size(); i++) {
            if (email.equals(mUsers.get(i).getEmail()) && pass.equals(mUsers.get(i).getPassword())) {
                return true;
            }
        }
        return false;
    }
}