package ru.com.gid.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SteamLoginModel {

    @Expose
    @SerializedName("login")
    private String login;

    @Expose
    @SerializedName("password")
    private String password;

    @Expose
    @SerializedName("auth_code")
    private String authCode;

    @Expose
    @SerializedName("2fa")
    private String twoFactorAuthentication;

    public SteamLoginModel(String login, String password, String authCode, String twoFactorAuthentication) {
        this.login = login;
        this.password = password;
        this.authCode = authCode;
        this.twoFactorAuthentication = twoFactorAuthentication;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getTwoFactorAuthentication() {
        return twoFactorAuthentication;
    }

    public void setTwoFactorAuthentication(String twoFactorAuthentication) {
        this.twoFactorAuthentication = twoFactorAuthentication;
    }
}
