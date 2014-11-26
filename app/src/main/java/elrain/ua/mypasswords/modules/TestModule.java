package elrain.ua.mypasswords.modules;

import dagger.Module;
import dagger.Provides;
import elrain.ua.mypasswords.activity.LoginActivity;
import elrain.ua.mypasswords.activity.helper.TestClass;

/**
 * Created by Denis on 11/9/2014.
 */
@Module(injects = {LoginActivity.class}, library = true, complete = false)
public class TestModule {

    @Provides
    public TestClass provideTestClass(){
        return new TestClass();
    }
}
