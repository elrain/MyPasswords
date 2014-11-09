package elrain.ua.mypasswords.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Denis on 11/8/2014.
 */
public final class PreferenceUtil {

    private static final String NAME = "my_pass_pref";
    private static final String KEY_LOGIN = "login";

    public static String getLoginFromPref(Context context) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return null == sp ? "" : sp.getString(KEY_LOGIN, "");
    }

    public static void setLoginToPref(Context context, String login) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        sp.edit().putString(KEY_LOGIN, login).apply();
    }
}
