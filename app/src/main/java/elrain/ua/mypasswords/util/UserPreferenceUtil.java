package elrain.ua.mypasswords.util;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

/**
 * Created by Denis on 11/8/2014.
 */

@Singleton
public class UserPreferenceUtil {

    private static final String NO_USER_ID = "User ID wasn't setted";

    private static final String NAME = "my_pass_pref";
    private static final String KEY_LOGIN = "login";
    private static final String KEY_USER_ID = "userId";
    private SharedPreferences mSharedPreferences;

    public UserPreferenceUtil(Context context) {
        mSharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    public String getLoginFromPref() {
        return null == mSharedPreferences ? "" : mSharedPreferences.getString(KEY_LOGIN, "");
    }

    public void setLoginToPref(String login) {
        mSharedPreferences.edit().putString(KEY_LOGIN, login).apply();
    }

    public void setUserId(long userId) {
        mSharedPreferences.edit().putLong(KEY_USER_ID, userId).apply();
    }

    public long getUserId(){
        long id = mSharedPreferences.getLong(KEY_USER_ID, -1);
        if(id == -1){
            throw  new IllegalArgumentException(NO_USER_ID);
        }
        return id;
    }

}
