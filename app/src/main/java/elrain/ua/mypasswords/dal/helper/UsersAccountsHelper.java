package elrain.ua.mypasswords.dal.helper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import elrain.ua.mypasswords.dal.MyPasswordsDBHelper;

/**
 * Created by Denis on 11/9/2014.
 */
public class UsersAccountsHelper {

    public static final String TABLE = "accounts";
    public static final String ID = "_id";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE + " ( " + ID + " INTEGER PRIMARY KEY, " + LOGIN + " VARCHAR(50), " + PASSWORD + " VARCHAR(50))";

    public static void createTable(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    public static boolean isCredentialsRight(MyPasswordsDBHelper helper, String login, String password) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(TABLE, new String[]{ID}, LOGIN + " = ? AND " + PASSWORD + " = ?", new String[]{login, password}, null, null, null);
            return cursor.moveToNext();
        } finally {
            if (null != cursor) {
                cursor.close();
            }
        }
    }

    public static void insertUserCredentials(MyPasswordsDBHelper helper, String login, String password) {
        if (isCredentialsRight(helper, login, password)) {
            return;
        }
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(LOGIN, login);
        cv.put(PASSWORD, password);
        db.insert(TABLE, null, cv);
    }
}
