package elrain.ua.mypasswords.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

import elrain.ua.mypasswords.R;
import elrain.ua.mypasswords.dto.AccountInfo;

/**
 * Created by Denys.Husher on 25.11.2014.
 */
public class PasswordsAdapter extends BaseExpandableListAdapter {

    private LayoutInflater mInflater;
    private List<AccountInfo> mAccountInfos;

    public PasswordsAdapter(Context context, List<AccountInfo> accountInfos) {
        mInflater = LayoutInflater.from(context);
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
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (null == convertView) {
            convertView = mInflater.inflate(R.layout.password_card_view, null);
        }
        ((TextView) convertView.findViewById(R.id.tvName)).setText(mAccountInfos.get(groupPosition).getmAccountName());
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


