package ar.com.wolox.android.example.network;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * REST API Interface
 */

public interface GetService {
    @GET("https://android-training.herokuapp.com/users")
    Call<ServerComunication> getAllUsers();
}