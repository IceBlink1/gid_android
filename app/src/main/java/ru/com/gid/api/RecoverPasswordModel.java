package ru.com.gid.api;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecoverPasswordModel {

    @Expose
    @SerializedName("email")
    private String email;

    @Expose
    @SerializedName("code")
    private String code;

    @Expose
    @SerializedName("new_password")
    private String newPassword;


    public RecoverPasswordModel(String email, String code, String newPassword) {
        this.email = email;
        this.code = code;
        this.newPassword = newPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
