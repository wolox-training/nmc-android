package ar.com.wolox.android.example.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * User class for the REST API
 */

public class Users {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("picture")
    @Expose
    private String picture;

    @SerializedName("cover")
    @Expose
    private String cover;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("location")
    @Expose
    private String location;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("phone")
    @Expose
    private String phone;

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return password;
    }
}