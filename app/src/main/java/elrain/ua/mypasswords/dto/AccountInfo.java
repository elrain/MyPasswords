package elrain.ua.mypasswords.dto;

/**
 * Created by Denys.Husher on 26.11.2014.
 */
public class AccountInfo {
    private String mHttpAddress;
    private String mAccountName;
    private String mAccountLogin;
    private String mAccountPassword;

    public String getmHttpAddress() {
        return mHttpAddress;
    }

    public void setmHttpAddress(String mHttpAddress) {
        this.mHttpAddress = mHttpAddress;
    }

    public String getmAccountName() {
        return mAccountName;
    }

    public void setmAccountName(String mAccountName) {
        this.mAccountName = mAccountName;
    }

    public String getmAccountLogin() {
        return mAccountLogin;
    }

    public void setmAccountLogin(String mAccountLogin) {
        this.mAccountLogin = mAccountLogin;
    }

    public String getmAccountPassword() {
        return mAccountPassword;
    }

    public void setmAccountPassword(String mAccountPassword) {
        this.mAccountPassword = mAccountPassword;
    }
}
