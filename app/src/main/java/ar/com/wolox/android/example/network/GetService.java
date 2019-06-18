package ar.com.wolox.android.example.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * REST API Interface
 */

public interface GetService {
    @GET("/users")
    Call<List<Users>> getAllUsers();
}