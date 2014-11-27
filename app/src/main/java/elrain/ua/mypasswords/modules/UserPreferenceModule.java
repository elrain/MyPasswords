package elrain.ua.mypasswords.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import elrain.ua.mypasswords.activity.AccountInfoActivity;
import elrain.ua.mypasswords.activity.LoginActivity;
import elrain.ua.mypasswords.activity.MainActivity;
import elrain.ua.mypasswords.util.UserPreferenceUtil;

/**
 * Created by Denys.Husher on 27.11.2014.
 */

@Module(injects = {MainActivity.class, LoginActivity.class, AccountInfoActivity.class}, library = true, complete = false)
public class UserPreferenceModule {

    private Context mContext;

    public UserPreferenceModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    public UserPreferenceUtil provideUserPreferenceUtil() {
        return new UserPreferenceUtil(mContext);
    }

}
