package ru.com.gid.api;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginModel {


    @Expose
    @SerializedName("email")
    String email;

    @Expose
    @SerializedName("username")
    String username;

    @Expose
    @SerializedName("password")
    String password;

    @Expose
    @SerializedName("common_name")
    String commonName;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String login) {
        this.commonName = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LoginModel(String email, String username, String commonName, String password) {
        setEmail(email);
        setCommonName(commonName);
        setPassword(password);
        setUsername(username);
    }
}
