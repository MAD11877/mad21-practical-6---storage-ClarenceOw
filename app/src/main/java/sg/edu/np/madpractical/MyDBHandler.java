package sg.edu.np.madpractical;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.FontsContract;
import android.util.Log;

import java.sql.Array;
import java.sql.SQLInput;
import java.util.ArrayList;


public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UserAccountsDB.db";
    private static final String USERS = "Users";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_DESCRIPTION = "Description";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_FOLLOWED = "Followed";


    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE = "CREATE TABLE " + USERS + "(" + COLUMN_NAME + " TEXT," + COLUMN_DESCRIPTION + " TEXT," + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_FOLLOWED + " INTEGER" + ")";
        db.execSQL(CREATE_TABLE);
        // CREATE TABLE ()
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + USERS);
        onCreate(db);
    }

    public void addUser(User user){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_DESCRIPTION, user.getDescription());
        values.put(COLUMN_ID, user.getId());
        values.put(COLUMN_FOLLOWED, user.isFollowed());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(USERS, null, values);
        db.close();
    }

    public User findUser(String username){
        String query = "SELECT * FROM " + USERS + " WHERE " + COLUMN_NAME + "=\"" + username + "\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        User queryData = new User();
        if (cursor.moveToFirst()){
            queryData.setName(cursor.getString(0));
            queryData.setDescription(cursor.getString(1));
            queryData.setId(cursor.getInt(2));
            queryData.setFollowed(cursor.getInt(3)==1);
            cursor.close();
        }
        else{
            queryData=null;
        }
        db.close();
        return queryData;
    }

    public ArrayList<User> getUser(){
        ArrayList<User> userlist = new ArrayList<>();
        String query = "SELECT * FROM " + USERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            User qData = new User();
            qData.setName(cursor.getString(0));
            qData.setDescription(cursor.getString(1));
            qData.setId(cursor.getInt(2));
            qData.setFollowed(cursor.getInt(3)==1);
            Log.v("Main", "" + cursor.getInt(2)+ " " +(cursor.getInt(3)==1));
            userlist.add(qData);
        }
        cursor.close();
        db.close();
        return userlist;

    }

    public boolean deleteUser(String username){
        boolean result = false;
        String query = "SELECT * FROM " + USERS + " WHERE " + COLUMN_NAME + " = \"" + username + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        User user = new User();
        if (cursor.moveToFirst()){
            user.setId(Integer.parseInt(cursor.getString(0)));
            db.delete(USERS, COLUMN_ID + " = ?", new String[] {String.valueOf(user.getId())});
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
    public void updateUser(User u){
        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        values.put(COLUMN_ID, u.getId());
        values.put(COLUMN_NAME, u.getName());
        values.put(COLUMN_DESCRIPTION, u.getDescription());
        values.put(COLUMN_FOLLOWED, u.isFollowed());
        db.update(USERS, values, COLUMN_ID+" =?", new String[] {String.valueOf(u.getId())});
        db.close();
    }
}
