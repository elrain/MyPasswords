package elrain.ua.mypasswords.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import de.greenrobot.event.EventBus;
import elrain.ua.mypasswords.R;
import elrain.ua.mypasswords.activity.AccountInfoActivity;
import elrain.ua.mypasswords.dal.MyPasswordsDBHelper;
import elrain.ua.mypasswords.dal.helper.AccountsHelper;
import elrain.ua.mypasswords.dto.AccountInfo;
import elrain.ua.mypasswords.message.RefreshAccountsMessage;
import elrain.ua.mypasswords.util.UserPreferenceUtil;

/**
 * Created by Denys.Husher on 25.11.2014.
 */
public class PasswordsAdapter extends BaseExpandableListAdapter {

    private LayoutInflater mInflater;
    private List<AccountInfo> mAccountInfos;
    private MyPasswordsDBHelper mMyPasswordsDBHelper;
    private UserPreferenceUtil mUserPreferenceUtil;
    private Context mContext;

    public PasswordsAdapter(Context context, List<AccountInfo> accountInfos, MyPasswordsDBHelper myPasswordsDBHelper, UserPreferenceUtil userPreferenceUtil) {
        mContext = context;
        mMyPasswordsDBHelper = myPasswordsDBHelper;
        mUserPreferenceUtil = userPreferenceUtil;
        mInflater = LayoutInflater.from(mContext);
        mAccountInfos = accountInfos;
    }

    @Override
    public int getGroupCount() {
        return mAccountInfos.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (null == convertView) {
            convertView = mInflater.inflate(R.layout.password_card_view, null);
        }
        ((TextView) convertView.findViewById(R.id.tvName)).setText(mAccountInfos.get(groupPosition).getmAccountName());
        convertView.findViewById(R.id.ibDeleteItem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountsHelper.deleteAccountForUser(mMyPasswordsDBHelper.getWritableDatabase(), mUserPreferenceUtil.getUserId(), mAccountInfos.get(groupPosition).getmId());
                EventBus.getDefault().post(new RefreshAccountsMessage());
            }
        });

        convertView.findViewById(R.id.ibEditItem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AccountInfoActivity.class);
                intent.putExtra(AccountsHelper.ID, mAccountInfos.get(groupPosition).getmId());
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(null == convertView){
            convertView = mInflater.inflate(R.layout.child_account_text, null);
        }
        ((TextView) convertView.findViewById(R.id.tvLogin)).setText(mAccountInfos.get(groupPosition).getmAccountLogin());
        ((TextView) convertView.findViewById(R.id.tvPassword)).setText(mAccountInfos.get(groupPosition).getmAccountPassword());
        ((TextView) convertView.findViewById(R.id.tvHttpAddress)).setText(mAccountInfos.get(groupPosition).getmHttpAddress());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}


