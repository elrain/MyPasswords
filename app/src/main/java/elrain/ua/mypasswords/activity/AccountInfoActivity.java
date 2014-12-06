package elrain.ua.mypasswords.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    private Button mBtnAddEdit;
    private EditText etLogin;
    private EditText etPassword;
    private EditText etHttpAddress;
    private EditText etName;
    private AccountInfo mAccountInfo = new AccountInfo();

    @Inject
    MyPasswordsDBHelper mMyPasswordsDBHelper;
    @Inject
    UserPreferenceUtil mUserPreferenceUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_info);
        ((MyPasswordsApp) this.getApplicationContext()).inject(this);
        mBtnAddEdit = (Button) findViewById(R.id.btnAdd);
        etLogin = (EditText) findViewById(R.id.etLogin);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etHttpAddress = (EditText) findViewById(R.id.etHttpAddress);
        etName = (EditText) findViewById(R.id.etName);
        long rowId = getIntent().getLongExtra(AccountsHelper.ID, -1);
        if (rowId != -1) {
            setTitle(getString(R.string.act_main_add_edit_account_title));
            mBtnAddEdit.setText(getString(R.string.act_main_btn_edit_account));
            mAccountInfo = AccountsHelper.getAccountByRowId(mMyPasswordsDBHelper.getReadableDatabase(), mUserPreferenceUtil.getUserId(), rowId);
            mAccountInfo.setmId(rowId);
            etHttpAddress.setText(mAccountInfo.getmHttpAddress());
            etLogin.setText(mAccountInfo.getmAccountLogin());
            etPassword.setText(mAccountInfo.getmAccountPassword());
            etName.setText(mAccountInfo.getmAccountName());
        }
        mBtnAddEdit.setOnClickListener(this);
        findViewById(R.id.btnCancel).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAdd:
                if (isNotEmptyRows()) {
                    if (mAccountInfo.getmId() == 0) {
                        mAccountInfo = new AccountInfo(etName.getText().toString(), etLogin.getText().toString(), etPassword.getText().toString(), etHttpAddress.getText().toString());
                        AccountsHelper.addNewAccount(mAccountInfo, mMyPasswordsDBHelper.getWritableDatabase(), mUserPreferenceUtil.getUserId());
                    } else {
                        mAccountInfo.setmAccountLogin(etLogin.getText().toString());
                        mAccountInfo.setmHttpAddress(etHttpAddress.getText().toString());
                        mAccountInfo.setmAccountPassword(etPassword.getText().toString());
                        mAccountInfo.setmAccountName(etName.getText().toString());
                        AccountsHelper.updateAccountInfo(mMyPasswordsDBHelper.getWritableDatabase(), mUserPreferenceUtil.getUserId(), mAccountInfo);
                    }
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

    private boolean isNotEmptyRows() {
        if (etName.getText().toString().isEmpty()) {
            etName.setError(getString(R.string.act_login_form_error));
        } else if (etLogin.getText().toString().isEmpty()) {
            etLogin.setError(getString(R.string.act_login_form_error));
            return false;
        } else if (etPassword.getText().toString().isEmpty()) {
            etPassword.setError(getString(R.string.act_login_form_error));
            return false;
        }
        return true;
    }
}