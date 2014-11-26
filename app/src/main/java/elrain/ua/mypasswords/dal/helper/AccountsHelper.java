package elrain.ua.mypasswords.dal.helper;


import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Denys.Husher on 26.11.2014.
 */
public final class AccountsHelper {
    public static final String TABLE = "accounts";
    public static final String ID = "_id";
    public static final String USER_ID = "userId";
    public static final String ACCOUNT_NAME = "accountName";
    public static final String ACCOUNT_LOGIN = "accountLogin";
    public static final String ACCOUNT_SITE = "accountSite";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE + " (" + ID + " INTEGER PRIMARY KEY, " + USER_ID + " int, " + ACCOUNT_NAME + " VARCHAR(50), " + ACCOUNT_LOGIN + " VARCHAR(50), " + ACCOUNT_SITE + " TEXT)";

    public static void createTable(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE);
    }


}
