package elrain.ua.mypasswords.dal.helper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import elrain.ua.mypasswords.dal.MyPasswordsDBHelper;

/**
 * Created by Denis on 11/9/2014.
 */
public class UsersAccountsHelper {

    public static final String TABLE = "usersLogPass";
    public static final String ID = "_id";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE + " ( " + ID + " INTEGER PRIMARY KEY, " + LOGIN + " VARCHAR(50), " + PASSWORD + " VARCHAR(50))";

    public static void createTable(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    public static boolean isCredentialsRight(SQLiteDatabase db, String login, String password) {
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

    public static long insertUserCredentials(SQLiteDatabase db, String login, String password) {
        if (isCredentialsRight(db, login, password)) {
            return -1;
        }
        ContentValues cv = new ContentValues();
        cv.put(LOGIN, login);
        cv.put(PASSWORD, password);
        return db.insert(TABLE, null, cv);
    }
}
