package elrain.ua.mypasswords.activity;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;

import elrain.ua.mypasswords.R;
import elrain.ua.mypasswords.adapter.PasswordsAdapter;
import elrain.ua.mypasswords.util.ScreenOrientationUtil;

/**
 * Created by Denys.Husher on 25.11.2014.
 */
public class MainActivity extends Activity {

    private PasswordsAdapter mPasswordsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        ScreenOrientationUtil.setScreenOrientation(MainActivity.this);

        mPasswordsAdapter = new PasswordsAdapter(null, null);

    }
}
