package com.juhe.pockettools.secretalbum;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
  private static final int a = 1;
  
  public DBHelper(Context paramContext, String paramString)
  {
    this(paramContext, paramString, null);
  }
  
  public DBHelper(Context paramContext, String paramString, SQLiteDatabase.CursorFactory paramCursorFactory)
  {
    this(paramContext, paramString, null, 1);
  }
  
  public DBHelper(Context paramContext, String paramString, SQLiteDatabase.CursorFactory paramCursorFactory, int paramInt)
  {
    super(paramContext, paramString, paramCursorFactory, paramInt);
  }
  
  @Override
  public void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS mediaassettable(id integer primary key autoincrement,origpath text,newpath text, name text,type integer)");
  }
  
  @Override
  public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS mediaassettable");
    onCreate(paramSQLiteDatabase);
  }
}

