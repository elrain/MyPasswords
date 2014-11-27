package elrain.ua.mypasswords.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import javax.inject.Inject;

import elrain.ua.mypasswords.R;
import elrain.ua.mypasswords.activity.helper.DialogGetterHelper;
import elrain.ua.mypasswords.dal.MyPasswordsDBHelper;
import elrain.ua.mypasswords.dal.helper.UsersAccountsHelper;
import elrain.ua.mypasswords.util.UserPreferenceUtil;
import elrain.ua.mypasswords.util.ScreenOrientationUtil;


public class LoginActivity extends Activity implements View.OnClickListener {

    private boolean mIsFirst = false;
    private Button mBtnLogin;
    private EditText mEtLogin;
    private EditText mEtPassword;

    @Inject
    MyPasswordsDBHelper mDbHelper;
    @Inject
    UserPreferenceUtil mUserPreferenceUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ((MyPasswordsApp) this.getApplicationContext()).inject(this);
        ScreenOrientationUtil.setScreenOrientation(LoginActivity.this);

        initInterface();
        mIsFirst = mUserPreferenceUtil.getLoginFromPref().isEmpty();
        if (mIsFirst) {
            addFirstUser();
        } else {
            mEtLogin.setText(mUserPreferenceUtil.getLoginFromPref());
            mEtPassword.requestFocus();
        }
    }

    private void addFirstUser() {
        AlertDialog addUser = DialogGetterHelper.getFirstUserDialog(LoginActivity.this);
        addUser.show();
    }

    private void initInterface() {
        mBtnLogin = (Button) findViewById(R.id.btnLogin);
        mBtnLogin.setOnClickListener(LoginActivity.this);
        mEtLogin = (EditText) findViewById(R.id.etLogin);
        mEtPassword = (EditText) findViewById(R.id.etPassword);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                String login = mEtLogin.getText().toString();
                String password = mEtPassword.getText().toString();

                if (login.isEmpty()) {
                    mEtLogin.setError(getString(R.string.act_login_form_error));
                } else if (password.isEmpty()) {
                    mEtPassword.setError(getString(R.string.act_login_form_error));
                } else {
                    if (mIsFirst)
                        doLoginAndInsertToDB(login, password);
                    else
                        doLoginWithoutInsertToDB(login, password);

                    mUserPreferenceUtil.setLoginToPref(login);
                }
                break;
            default:
                break;
        }
    }

    private void doLoginWithoutInsertToDB(String login, String password) {
        openMainActivity();
    }

    private void openMainActivity() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        this.finish();
    }

    private void doLoginAndInsertToDB(String login, String password) {
        long userId = UsersAccountsHelper.insertUserCredentials(mDbHelper, login, password);
        mUserPreferenceUtil.setUserId(userId);
        openMainActivity();
    }
}
