package elrain.ua.mypasswords.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import elrain.ua.mypasswords.R;
import elrain.ua.mypasswords.dal.MyPasswordsDBHelper;
import elrain.ua.mypasswords.dal.helper.AccountsHelper;
import elrain.ua.mypasswords.dto.AccountInfo;
import elrain.ua.mypasswords.message.RefreshAccountsMessage;
import elrain.ua.mypasswords.util.UserPreferenceUtil;

/**
 * Created by Denys.Husher on 27.11.2014.
 */
public class AccountInfoActivity extends Activity implements View.OnClickListener {

    @Inject
    MyPasswordsDBHelper mMyPasswordsDBHelper;
    @Inject
    UserPreferenceUtil mUserPreferenceUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_info);
        ((MyPasswordsApp) this.getApplicationContext()).inject(this);
        findViewById(R.id.btnAdd).setOnClickListener(this);
        findViewById(R.id.btnCancel).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAdd:
                EditText etLogin = (EditText) findViewById(R.id.etLogin);
                EditText etPassword = (EditText) findViewById(R.id.etPassword);
                EditText etHttpAddress = (EditText) findViewById(R.id.etHttpAddress);
                EditText etName = (EditText) findViewById(R.id.etName);

                String login = etLogin.getText().toString();
                String password = etPassword.getText().toString();

                if (login.isEmpty()) {
                    etLogin.setError(getString(R.string.act_login_form_error));
                } else if (password.isEmpty()) {
                    etPassword.setError(getString(R.string.act_login_form_error));
                } else {
                    AccountInfo accountInfo = new AccountInfo(etName.getText().toString(), login, password, etHttpAddress.getText().toString());
                    AccountsHelper.addNewAccount(accountInfo, mMyPasswordsDBHelper.getWritableDatabase(), mUserPreferenceUtil.getUserId());
                    EventBus.getDefault().post(new RefreshAccountsMessage());
                    this.finish();
                }
                break;
            case R.id.btnCancel:
                this.finish();
                break;
            default:
                break;
        }
    }
}
