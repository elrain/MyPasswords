package elrain.ua.mypasswords.dal;

import android.content.Context;

import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;

import javax.inject.Singleton;

import elrain.ua.mypasswords.dal.helper.AccountHelper;
import elrain.ua.mypasswords.util.StringXorUtil;

/**
 * Created by Denis on 11/9/2014.
 */
@Singleton
public class MyPasswordsDBHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "my_pass.db";
    private static final String DB_PASSWORD = "";
    private static MyPasswordsDBHelper mInstance;

    public MyPasswordsDBHelper(Context context) {
        super(context.getApplicationContext(), DB_NAME, null, DB_VERSION);
    }

    public static synchronized MyPasswordsDBHelper getInstance(Context context) {
        if (null == mInstance) {
            mInstance = new MyPasswordsDBHelper(context);
        }
        SQLiteDatabase.loadLibs(context);
        return mInstance;
    }

    public synchronized SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase(StringXorUtil.XorString(DB_PASSWORD));
//        return super.getWritableDatabase("");
    }

    public synchronized SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase(StringXorUtil.XorString(DB_PASSWORD));
//        return super.getReadableDatabase("");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        AccountHelper.createTable(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
}
