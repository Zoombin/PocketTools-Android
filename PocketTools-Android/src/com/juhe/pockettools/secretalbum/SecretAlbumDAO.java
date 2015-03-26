package com.juhe.pockettools.secretalbum;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

public class SecretAlbumDAO {
  private DBHelper dbhelper = null;
  
  public SecretAlbumDAO(Context paramContext)
  {
	  dbhelper = new DBHelper(paramContext, "secretmedia.db");
  }
  
  public int a(n paramn)
  {
    SQLiteDatabase localSQLiteDatabase = dbhelper.getWritableDatabase();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("type", Integer.valueOf(paramn.b()));
    localContentValues.put("name", paramn.c());
    localContentValues.put("origpath", paramn.d());
    localContentValues.put("newpath", paramn.e());
    localSQLiteDatabase.beginTransaction();
    int i = -1;
    try
    {
      localSQLiteDatabase.insert("mediaassettable", null, localContentValues);
      Cursor localCursor = localSQLiteDatabase.rawQuery("select max(id) from mediaassettable", null);
      if (localCursor.moveToFirst()) {
        i = (int)localCursor.getLong(0);
      }
      localCursor.close();
      localSQLiteDatabase.setTransactionSuccessful();
      return i;
    }
    finally
    {
      localSQLiteDatabase.endTransaction();
      localSQLiteDatabase.close();
    }
  }
  
  public n a(Context paramContext, int paramInt)
  {
    SQLiteDatabase localSQLiteDatabase = dbhelper.getWritableDatabase();
    String[] arrayOfString1 = { "id", "type", "name", "origpath", "newpath" };
    String[] arrayOfString2 = new String[1];
    arrayOfString2[0] = String.valueOf(paramInt);
    Cursor localCursor = localSQLiteDatabase.query("mediaassettable", arrayOfString1, "id=?", arrayOfString2, null, null, null, null);
    if (localCursor.moveToFirst()) {}
    for (n localn = new n(localCursor.getInt(localCursor.getColumnIndex("id")), localCursor.getInt(localCursor.getColumnIndex("type")), localCursor.getString(localCursor.getColumnIndex("name")), localCursor.getString(localCursor.getColumnIndex("origpath")), localCursor.getString(localCursor.getColumnIndex("newpath")));; localn = null)
    {
      localCursor.close();
      localSQLiteDatabase.close();
      return localn;
    }
  }
  
  public ArrayList<n> a()
  {
    ArrayList localArrayList1 = new ArrayList();
    SQLiteDatabase localSQLiteDatabase = dbhelper.getWritableDatabase();
    Cursor localCursor = localSQLiteDatabase.query("mediaassettable", new String[] { "id", "type", "name", "origpath", "newpath" }, null, null, null, null, "id desc", null);
    for (;;)
    {
      if (!localCursor.moveToNext())
      {
        localCursor.close();
        localSQLiteDatabase.close();
        ArrayList localArrayList2 = null;
        if (localArrayList1 != null)
        {
          int i = localArrayList1.size();
          localArrayList2 = null;
          if (i > 0) {
            localArrayList2 = localArrayList1;
          }
        }
        return localArrayList2;
      }
      localArrayList1.add(new n(localCursor.getInt(localCursor.getColumnIndex("id")), localCursor.getInt(localCursor.getColumnIndex("type")), localCursor.getString(localCursor.getColumnIndex("name")), localCursor.getString(localCursor.getColumnIndex("origpath")), localCursor.getString(localCursor.getColumnIndex("newpath"))));
    }
  }
  
  public boolean a(int paramInt)
  {
    SQLiteDatabase localSQLiteDatabase = dbhelper.getWritableDatabase();
    localSQLiteDatabase.beginTransaction();
    try
    {
      String[] arrayOfString = new String[1];
      arrayOfString[0] = String.valueOf(paramInt);
      int i = localSQLiteDatabase.delete("mediaassettable", "id=?", arrayOfString);
      localSQLiteDatabase.setTransactionSuccessful();
      localSQLiteDatabase.endTransaction();
      localSQLiteDatabase.close();
      if (i > 0) {
        return true;
      }
    }
    finally
    {
      localSQLiteDatabase.endTransaction();
      localSQLiteDatabase.close();
    }
    return false;
  }
  
  public void closeDB()
  {
    if (dbhelper != null) {
    	dbhelper.close();
    }
  }
}
