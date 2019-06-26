package ar.com.wolox.android.example.ui.login;

import java.util.List;

import javax.inject.Inject;

import ar.com.wolox.android.example.network.UserServices;
import ar.com.wolox.android.example.network.Users;
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Adapter API
 */

public class LoginAdapterAPI {

    RetrofitServices mRetrofitServices;

    @Inject
    LoginAdapterAPI(RetrofitServices retrofitServices) {
        this.mRetrofitServices = retrofitServices;
    }

    /**
     * @param email String
     * @param password String
     * @param onSuccess Runnable
     * @param onError Runnable
     * @param onUnsuccessfulResponse Runnable
     */
    public void getUsers(String email, String password, Runnable onSuccess, Runnable onError, Runnable onUnsuccessfulResponse) {

        mRetrofitServices.getService(UserServices.class).getAllUsers().enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                if (response.isSuccessful()) {

                    List<Users> usersList = response.body();

                    if (isEmailAndPasswordRegistered(email, password, usersList)) {
                        onSuccess.run();
                    } else {
                        onError.run();
                    }

                } else {
                    onUnsuccessfulResponse.run();
                }
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                onUnsuccessfulResponse.run();
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
        }
        return false;
    }
}
