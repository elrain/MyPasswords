package elrain.ua.mypasswords.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import elrain.ua.mypasswords.R;
import elrain.ua.mypasswords.dal.helper.AccountsHelper;
import elrain.ua.mypasswords.dto.AccountInfo;

/**
 * Created by Denys.Husher on 25.11.2014.
 */
public class PasswordsAdapter extends BaseExpandableListAdapter {

    private Cursor mCursor;
    private LayoutInflater mInflater;

    public PasswordsAdapter(Context context, Cursor c) {
        mCursor = c;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return mCursor.getCount() / mCursor.getColumnCount();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mCursor.getColumnCount() - 1;
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
        if(null == convertView){
            convertView = mInflater.inflate(R.layout.main_activity, null);
        }
        ((TextView)convertView.findViewById(R.id.tvName)).setText(mCursor.getString(mCursor.getColumnIndex(AccountsHelper.ACCOUNT_NAME)));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}


