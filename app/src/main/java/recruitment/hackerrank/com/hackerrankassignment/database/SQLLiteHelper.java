package recruitment.hackerrank.com.hackerrankassignment.database;

import android.content.ContentValues;
import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

import recruitment.hackerrank.com.hackerrankassignment.Model.UserNumbers;

public class SQLLiteHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "NumberDB";

    private static final String TABLE_NAME = "userData";

    // Books Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "firstNumber";
    private static final String KEY_AUTHOR = "secondNumber";

    private static final String[] COLUMNS = {KEY_ID,KEY_TITLE,KEY_AUTHOR};

    public SQLLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create userData table
        String CREATE_USER_DATA_TABLE = "CREATE TABLE userData ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "firstNumber INTEGER, "+
                "secondNumber INTEGER )";

        // create userData table
        db.execSQL(CREATE_USER_DATA_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older userData table if existed
        db.execSQL("DROP TABLE IF EXISTS userData");

        // create fresh userData table
        this.onCreate(db);
    }

    public void insertNumbers(UserNumbers userData) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, userData.getFirstNumber());
        values.put(KEY_AUTHOR, userData.getSecondNumber());

        db.insert(TABLE_NAME,
                null,
                values);

        db.close();
    }

}
