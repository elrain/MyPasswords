package elrain.ua.mypasswords.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import elrain.ua.mypasswords.R;
import elrain.ua.mypasswords.activity.helper.DialogGetterHelper;
import elrain.ua.mypasswords.activity.helper.TestClass;
import elrain.ua.mypasswords.dal.MyPasswordsDBHelper;
import elrain.ua.mypasswords.dal.helper.AccountHelper;
import elrain.ua.mypasswords.util.PreferenceUtil;
import elrain.ua.mypasswords.util.ScreenOrientationUtil;


public class LoginActivity extends Activity implements View.OnClickListener {

    private boolean mIsFirst = false;
    private Button mBtnLogin;
    private EditText mEtLogin;
    private EditText mEtPassword;

    @Inject
    MyPasswordsDBHelper mDbHelper;
    @Inject
    TestClass testClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ((MyPasswordsApp) this.getApplicationContext()).inject(this);
        ScreenOrientationUtil.setScreenOrientation(LoginActivity.this);

        initInterface();
        mIsFirst = PreferenceUtil.getLoginFromPref(LoginActivity.this).isEmpty();
        if (mIsFirst) {
            addFirstUser();
        } else {
            mEtLogin.setText(PreferenceUtil.getLoginFromPref(LoginActivity.this));
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

                    PreferenceUtil.setLoginToPref(LoginActivity.this, login);
                }
                break;
            default:
                break;
        }
    }

    private void doLoginWithoutInsertToDB(String login, String password) {

        Toast.makeText(this, Boolean.toString(AccountHelper.isCredentialsRight(mDbHelper, login, password)), Toast.LENGTH_SHORT).show();
    }

    private void doLoginAndInsertToDB(String login, String password) {
        AccountHelper.insertUserCredentials(mDbHelper, login, password);
    }
}
