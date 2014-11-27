package elrain.ua.mypasswords.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import elrain.ua.mypasswords.dal.MyPasswordsDBHelper;

/**
 * Created by Denis on 11/9/2014.
 */
@Module(library = true, complete = false)
public class MyPasswordsDBHelperModule {

    private final Context mContext;

    public MyPasswordsDBHelperModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    public MyPasswordsDBHelper provideMyPasswordsDBHelper() {
        return MyPasswordsDBHelper.getInstance(mContext);
    }
}
