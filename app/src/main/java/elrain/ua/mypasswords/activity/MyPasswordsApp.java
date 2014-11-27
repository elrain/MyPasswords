package elrain.ua.mypasswords.activity;

import android.app.Application;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;
import elrain.ua.mypasswords.modules.AppContextModule;
import elrain.ua.mypasswords.modules.MyPasswordsDBHelperModule;
import elrain.ua.mypasswords.modules.TestModule;

/**
 * Created by Denis on 11/9/2014.
 */
public class MyPasswordsApp extends Application {

    protected ObjectGraph mGraph;

    protected List<Object> getModules() {
        return Arrays.asList(
                new AppContextModule(this),
                new TestModule(),
                new MyPasswordsDBHelperModule(this)
        );
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mGraph = ObjectGraph.create(getModules().toArray());
    }

    public void inject(Object object) {
        if (mGraph == null) {
            mGraph = ObjectGraph.create(getModules().toArray());
        }
        mGraph.inject(object);
    }

    public <T> T createObject(Class<T> c) {
        return mGraph.get(c);
    }
}
