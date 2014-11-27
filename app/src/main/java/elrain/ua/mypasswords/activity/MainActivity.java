package elrain.ua.mypasswords.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import elrain.ua.mypasswords.R;
import elrain.ua.mypasswords.adapter.PasswordsAdapter;
import elrain.ua.mypasswords.dal.MyPasswordsDBHelper;
import elrain.ua.mypasswords.dal.helper.AccountsHelper;
import elrain.ua.mypasswords.message.RefreshAccountsMessage;
import elrain.ua.mypasswords.util.ScreenOrientationUtil;
import elrain.ua.mypasswords.util.UserPreferenceUtil;

/**
 * Created by Denys.Husher on 25.11.2014.
 */
public class MainActivity extends Activity {

    private PasswordsAdapter mPasswordsAdapter;
    private ExpandableListView mExpandableListView;

    @Inject
    UserPreferenceUtil mUserPreferenceUtil;
    @Inject
    MyPasswordsDBHelper mMyPasswordsDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        EventBus.getDefault().register(this);
        ((MyPasswordsApp) this.getApplicationContext()).inject(this);
        ScreenOrientationUtil.setScreenOrientation(MainActivity.this);
        mPasswordsAdapter = new PasswordsAdapter(MainActivity.this, AccountsHelper.getAccountsForUser(mMyPasswordsDBHelper.getReadableDatabase(), mUserPreferenceUtil.getUserId()));
        mExpandableListView = (ExpandableListView) findViewById(R.id.elvPasswords);
        mExpandableListView.setAdapter(mPasswordsAdapter);

        getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar_bg));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onEvent(RefreshAccountsMessage message) {
        mPasswordsAdapter = new PasswordsAdapter(MainActivity.this, AccountsHelper.getAccountsForUser(mMyPasswordsDBHelper.getReadableDatabase(), mUserPreferenceUtil.getUserId()));
        mExpandableListView.setAdapter(mPasswordsAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_new:
                startActivity(new Intent(MainActivity.this, AccountInfoActivity.class));
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
