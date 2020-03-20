package ru.com.gid.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangePasswordModel {

    @Expose
    @SerializedName("old_pass")
    private String oldPassword;

    @Expose
    @SerializedName("new_pass")
    private String newPassword;

    public ChangePasswordModel(String oldPassword, String newPassword) {
        this.newPassword = newPassword;
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
