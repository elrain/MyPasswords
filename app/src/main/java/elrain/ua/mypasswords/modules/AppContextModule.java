package elrain.ua.mypasswords.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Denis on 11/9/2014.
 */
@Module(library = true)
public class AppContextModule {
    private final Context mContext;

    public AppContextModule(Context context){
        mContext = context.getApplicationContext();
    }

    @Provides
    public Context provideContext() {
        return mContext;
    }
}
