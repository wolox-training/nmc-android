package ar.com.wolox.android.example.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Server Comunication Class
 */

public class ServerComunication {
    @SerializedName("users")
    @Expose
    private List<Users> users;

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }
}
