package com.example.think.videodemo.Util.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.think.videodemo.Util.LogUtil;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String packageName = MyDatabaseHelper.class.getName();

    public static final String CREATE_HISTORY = "create table VIDEO_HISTORY (" +
            "id integer primary key autoincrement," +
            "title text," +
            "description text," +
            "category text," +
            "feed text," +
            "author text," +
            "userName text," +
            "userDescription text," +
            "blurred text," +
            "playUrl text)";
    private Context mContext;
    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context,name,factory,version);
        mContext = context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_HISTORY);
        LogUtil.loging(packageName,1,"数据库建立成功");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists VIDEO_HISTORY");
        onCreate(sqLiteDatabase);
        LogUtil.loging(packageName,1,"数据库更新成功");
    }
}
