package cn.wsgwz.excelhelper;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by admin on 2017/7/28 0028.
 */

public class MyContentProvider extends ContentProvider {
    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    public final   class DbHelper extends SQLiteOpenHelper {
        public static final int VERSION  = 0;
        public static final String DB_NAME = "excelhelper.db";


        public static final String TABLE_NAME = "table_name";
        public static final String _ID = "_id";
        public static final String _CONFIG_PATH = "_config_path";
        public static final String _IS_START = "_is_start";
        public static final String _SUSPENSION_COLOR = "_suspension_color";
        public static final String _SPEED_VIEW_X_LOCATION = "_speed_view_x_location";
        public static final String _SPEED_VIEW_Y_LOCATION = "_speed_view_y_location";
        public static final String _IS_CAPTURE = "_is_capture";
        public static final String _STYLE_MAINACTIVITY = "_style_mainActivity";
        public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, DB_NAME,factory, VERSION );
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
           /* String sql = "create table "+TABLE_NAME+"("+_ID+" integer primary key autoincrement,"+ _CONFIG_PATH+" text,"+_IS_START+" integer,"+_SUSPENSION_COLOR+" varchar(16),"
                    +_SPEED_VIEW_X_LOCATION+" real,"+_SPEED_VIEW_Y_LOCATION+" real,"+_IS_CAPTURE+" integer,"+_STYLE_MAINACTIVITY+" integer"+");";
            String sql2 = "insert into "+TABLE_NAME+"("+_CONFIG_PATH+","+_IS_START+","+_IS_CAPTURE+","+_SPEED_VIEW_X_LOCATION+","+_SPEED_VIEW_Y_LOCATION+","+_STYLE_MAINACTIVITY+") "+"values("+"'未选择',1,1,100,314,0"+")";
            */
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("drop table if exists "+TABLE_NAME);
            onCreate(sqLiteDatabase);
        }
    }
}
