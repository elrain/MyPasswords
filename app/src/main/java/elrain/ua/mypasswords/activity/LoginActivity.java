package elrain.ua.mypasswords.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import elrain.ua.mypasswords.R;
import elrain.ua.mypasswords.activity.helper.DialogGetterHelper;
import elrain.ua.mypasswords.util.PreferenceUtil;
import elrain.ua.mypasswords.util.ScreenOrientationUtil;


public class LoginActivity extends Activity implements View.OnClickListener {

    private static boolean isFirst = false;
    private Button mBtnLogin;
    private EditText mEtLogin;
    private EditText mEtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ScreenOrientationUtil.setScreenOrientation(LoginActivity.this);

        initInterface();

        if (PreferenceUtil.getLoginFromPref(LoginActivity.this).isEmpty())
            addFirstUser();
        else{
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
                PreferenceUtil.setLoginToPref(LoginActivity.this, login);
                break;
            default:
                break;
        }
    }
}
