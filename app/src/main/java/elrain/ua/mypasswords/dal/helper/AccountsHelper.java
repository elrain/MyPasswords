package elrain.ua.mypasswords.dal.helper;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import elrain.ua.mypasswords.dto.AccountInfo;

/**
 * Created by Denys.Husher on 26.11.2014.
 */
public final class AccountsHelper {
    public static final String TABLE = "accounts";
    public static final String ID = "_id";
    public static final String USER_ID = "userId";
    public static final String ACCOUNT_NAME = "accountName";
    public static final String ACCOUNT_LOGIN = "accountLogin";
    public static final String ACCOUNT_PASSWORDS = "accountPassword";
    public static final String ACCOUNT_HTTP_ADDRESS = "accountSite";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE + " (" + ID + " INTEGER PRIMARY KEY, " + USER_ID + " int, " + ACCOUNT_NAME + " VARCHAR(50), " + ACCOUNT_LOGIN + " VARCHAR(50), " + ACCOUNT_PASSWORDS + " VARCHAR(50), "
            + ACCOUNT_HTTP_ADDRESS + " TEXT)";

    /**
     * Call this method to create <i>Accounts</i> table
     *
     * @param db instance of {@link android.database.sqlite.SQLiteDatabase}
     */
    public static void createTable(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    /**
     * Insert new account into DB for user
     *
     * @param accountInfo information about account
     * @param db          instance of {@link android.database.sqlite.SQLiteDatabase}
     * @param userId      ID of user
     */
    public static void addNewAccount(AccountInfo accountInfo, SQLiteDatabase db, long userId) {
        ContentValues cv = new ContentValues();
        cv.put(USER_ID, userId);
        cv.put(ACCOUNT_NAME, accountInfo.getmAccountName());
        cv.put(ACCOUNT_LOGIN, accountInfo.getmAccountLogin());
        cv.put(ACCOUNT_HTTP_ADDRESS, accountInfo.getmHttpAddress());
        cv.put(ACCOUNT_PASSWORDS, accountInfo.getmAccountPassword());
        db.insert(TABLE, null, cv);
    }

    public static List<AccountInfo> getAccountsForUser(SQLiteDatabase db, long userId) {
        Cursor cursor = null;
        List<AccountInfo> accountInfos = new ArrayList<AccountInfo>();
        try {
            cursor = db.query(TABLE, new String[]{ACCOUNT_NAME, ACCOUNT_LOGIN, ACCOUNT_HTTP_ADDRESS, ACCOUNT_PASSWORDS}, USER_ID + " = ?", new String[]{String.valueOf(userId)}, null, null, null);
            while (cursor.moveToNext()) {
                AccountInfo ai = new AccountInfo(cursor.getString(cursor.getColumnIndex(ACCOUNT_NAME)), cursor.getString(cursor.getColumnIndex(ACCOUNT_LOGIN)), cursor.getString(cursor.getColumnIndex(ACCOUNT_PASSWORDS)),
                        cursor.getString(cursor.getColumnIndex(ACCOUNT_HTTP_ADDRESS)));
                accountInfos.add(ai);
            }
        } finally {
            if (null != cursor)
                cursor.close();
        }
        return accountInfos;
    }

}
