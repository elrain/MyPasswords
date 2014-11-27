package elrain.ua.mypasswords.dal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import javax.inject.Singleton;

import elrain.ua.mypasswords.dal.helper.AccountsHelper;
import elrain.ua.mypasswords.dal.helper.UsersAccountsHelper;

/**
 * Created by Denis on 11/9/2014.
 */
@Singleton
public class MyPasswordsDBHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "my_pass.db";
    private static MyPasswordsDBHelper mInstance;

    public MyPasswordsDBHelper(Context context) {
        super(context.getApplicationContext(), DB_NAME, null, DB_VERSION);
    }

    public static synchronized MyPasswordsDBHelper getInstance(Context context) {
        if (null == mInstance) {
            mInstance = new MyPasswordsDBHelper(context);
        }
        return mInstance;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        UsersAccountsHelper.createTable(sqLiteDatabase);
        AccountsHelper.createTable(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
}
