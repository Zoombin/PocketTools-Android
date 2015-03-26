package com.juhe.pockettools.secretalbum;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore.Audio.Media;
//import android.provider.MediaStore.Images.Media;
//import android.provider.MediaStore.Video.Media;
import android.util.Log;
import com.juhe.pockettools.HelprApplication;
import java.io.File;
import java.util.ArrayList;

public class SecretFileHelper
{
//  private static final String a = "SecretFileHelper";
//  private static SecretFileHelper d = null;
//  private Context b = HelprApplication.c();
//  private SecretAlbumDAO c = new SecretAlbumDAO(this.b);
//  
//  public static SecretFileHelper getInstant()
//  {
//    if (d == null) {}
//    try
//    {
//      if (d == null) {
//        d = new l();
//      }
//      return d;
//    }
//    finally {}
//  }
//  
//  public static String a(Context paramContext)
//  {
//    boolean bool1 = Environment.getExternalStorageState().equals("mounted");
//    boolean bool2 = false;
//    if (bool1) {
//      bool2 = true;
//    }
//    Log.v("SecretFileHelper", "SecretFileHelperbHaveSdcard :" + bool2);
//    String str1;
//    if (bool2)
//    {
//      str1 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString() + "/Camera/";
//      File localFile2 = new File(str1);
//      if (!localFile2.exists()) {
//        localFile2.mkdirs();
//      }
//    }
//    for (;;)
//    {
//      String str2 = "img" + System.currentTimeMillis() + ".jpg";
//      return str1 + str2;
//      str1 = paramContext.getDir("Helpr", 2).getAbsolutePath() + "/";
//      File localFile1 = new File(str1);
//      if (!localFile1.exists()) {
//        localFile1.mkdirs();
//      }
//    }
//  }
//  
//  public static String a(Context paramContext, Uri paramUri)
//  {
//    int i;
//    String str1;
//    if (Build.VERSION.SDK_INT >= 19)
//    {
//      i = 1;
//      if ((i == 0) || (!DocumentsContract.isDocumentUri(paramContext, paramUri))) {
//        break label247;
//      }
//      if (!c(paramUri)) {
//        break label97;
//      }
//      String[] arrayOfString3 = DocumentsContract.getDocumentId(paramUri).split(":");
//      boolean bool4 = "primary".equalsIgnoreCase(arrayOfString3[0]);
//      str1 = null;
//      if (bool4) {
//        str1 = Environment.getExternalStorageDirectory() + "/" + arrayOfString3[1];
//      }
//    }
//    label97:
//    label247:
//    boolean bool1;
//    do
//    {
//      boolean bool2;
//      do
//      {
//        return str1;
//        i = 0;
//        break;
//        if (d(paramUri))
//        {
//          String str3 = DocumentsContract.getDocumentId(paramUri);
//          return a(paramContext, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(str3).longValue()), null, null);
//        }
//        bool2 = e(paramUri);
//        str1 = null;
//      } while (!bool2);
//      String[] arrayOfString1 = DocumentsContract.getDocumentId(paramUri).split(":");
//      String str2 = arrayOfString1[0];
//      Uri localUri;
//      if ("image".equals(str2)) {
//        localUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
//      }
//      for (;;)
//      {
//        String[] arrayOfString2 = new String[1];
//        arrayOfString2[0] = arrayOfString1[1];
//        return a(paramContext, localUri, "_id=?", arrayOfString2);
//        if ("video".equals(str2))
//        {
//          localUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
//        }
//        else
//        {
//          boolean bool3 = "audio".equals(str2);
//          localUri = null;
//          if (bool3) {
//            localUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
//          }
//        }
//      }
//      if ("content".equalsIgnoreCase(paramUri.getScheme())) {
//        return a(paramContext, paramUri, null, null);
//      }
//      bool1 = "file".equalsIgnoreCase(paramUri.getScheme());
//      str1 = null;
//    } while (!bool1);
//    return paramUri.getPath();
//  }
//  
//  /* Error */
//  public static String a(Context paramContext, Uri paramUri, String paramString, String[] paramArrayOfString)
//  {
//    // Byte code:
//    //   0: aconst_null
//    //   1: astore 4
//    //   3: iconst_1
//    //   4: anewarray 48	java/lang/String
//    //   7: dup
//    //   8: iconst_0
//    //   9: ldc 227
//    //   11: aastore
//    //   12: astore 5
//    //   14: aload_0
//    //   15: invokevirtual 231	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
//    //   18: aload_1
//    //   19: aload 5
//    //   21: aload_2
//    //   22: aload_3
//    //   23: aconst_null
//    //   24: invokevirtual 237	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
//    //   27: astore 7
//    //   29: aload 7
//    //   31: ifnull +63 -> 94
//    //   34: aload 7
//    //   36: invokeinterface 242 1 0
//    //   41: ifeq +53 -> 94
//    //   44: aload 7
//    //   46: aload 7
//    //   48: ldc 227
//    //   50: invokeinterface 246 2 0
//    //   55: invokeinterface 250 2 0
//    //   60: astore 8
//    //   62: aload 7
//    //   64: ifnull +10 -> 74
//    //   67: aload 7
//    //   69: invokeinterface 253 1 0
//    //   74: aload 8
//    //   76: areturn
//    //   77: astore 6
//    //   79: aload 4
//    //   81: ifnull +10 -> 91
//    //   84: aload 4
//    //   86: invokeinterface 253 1 0
//    //   91: aload 6
//    //   93: athrow
//    //   94: aload 7
//    //   96: ifnull +10 -> 106
//    //   99: aload 7
//    //   101: invokeinterface 253 1 0
//    //   106: aconst_null
//    //   107: areturn
//    //   108: astore 6
//    //   110: aload 7
//    //   112: astore 4
//    //   114: goto -35 -> 79
//    // Local variable table:
//    //   start	length	slot	name	signature
//    //   0	117	0	paramContext	Context
//    //   0	117	1	paramUri	Uri
//    //   0	117	2	paramString	String
//    //   0	117	3	paramArrayOfString	String[]
//    //   1	112	4	localObject1	Object
//    //   12	8	5	arrayOfString	String[]
//    //   77	15	6	localObject2	Object
//    //   108	1	6	localObject3	Object
//    //   27	84	7	localCursor	Cursor
//    //   60	15	8	str	String
//    // Exception table:
//    //   from	to	target	type
//    //   14	29	77	finally
//    //   34	62	108	finally
//  }
//  
//  public static String a(Uri paramUri)
//  {
//    Context localContext = HelprApplication.c();
//    int i = Build.VERSION.SDK_INT;
//    String str = null;
//    if (i < 19)
//    {
//      String[] arrayOfString = { "_data" };
//      Cursor localCursor = localContext.getContentResolver().query(paramUri, arrayOfString, null, null, null);
//      str = null;
//      if (localCursor != null)
//      {
//        int j = localCursor.getColumnIndexOrThrow("_data");
//        localCursor.moveToFirst();
//        str = localCursor.getString(j);
//      }
//    }
//    return str;
//  }
//  
//  public static String b(Uri paramUri)
//  {
//    int i;
//    String str;
//    if (Build.VERSION.SDK_INT >= 19)
//    {
//      i = 1;
//      Context localContext = HelprApplication.c();
//      if (paramUri == null) {
//        break label109;
//      }
//      if (i == 0) {
//        break label58;
//      }
//      str = a(localContext, paramUri);
//      Log.v("SecretFileHelper", "SecretFileHelper fileName:" + str);
//    }
//    label58:
//    do
//    {
//      return str;
//      i = 0;
//      break;
//      str = a(paramUri);
//    } while ((paramUri.getScheme().toString().compareTo("content") == 0) || (paramUri.getScheme().compareTo("file") != 0));
//    paramUri.toString();
//    return paramUri.toString().replace("file://", "");
//    label109:
//    return null;
//  }
//  
//  public static void b()
//  {
//    if (d != null)
//    {
//      if (d.c != null) {
//        d.c.b();
//      }
//      d = null;
//    }
//  }
//  
//  public static boolean c(Uri paramUri)
//  {
//    return "com.android.externalstorage.documents".equals(paramUri.getAuthority());
//  }
//  
//  private String d()
//  {
//    String str = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString() + "/Helpr/Pics/.Data/";
//    File localFile = new File(str);
//    if (!localFile.exists()) {
//      localFile.mkdirs();
//    }
//    return str;
//  }
//  
//  public static boolean d(Uri paramUri)
//  {
//    return "com.android.providers.downloads.documents".equals(paramUri.getAuthority());
//  }
//  
//  public static boolean e(Uri paramUri)
//  {
//    return "com.android.providers.media.documents".equals(paramUri.getAuthority());
//  }
//  
//  public n a(String paramString, boolean paramBoolean)
//  {
//    String str1 = d();
//    String str2 = h.b(paramString);
//    String str3 = "encrypt" + System.currentTimeMillis() + ".jpg" + ".hpr";
//    String str4 = str1 + str3;
//    try
//    {
//      boolean bool = h.c(paramString, str4);
//      Log.v("SecretFileHelper", "SecretFileHelper isSuccess:" + bool);
//      if (bool)
//      {
//        n localn = new n();
//        localn.c(str4);
//        localn.b(0);
//        localn.a(str2);
//        if (!paramBoolean) {
//          localn.b(paramString);
//        }
//        int i = this.c.a(localn);
//        Log.v("SecretFileHelper", "SecretFileHelpersave file sid:" + i);
//        if (i > 0)
//        {
//          Log.v("SecretFileHelper", "SecretFileHelperencryptFile success sid:" + i + " denst file:" + str4);
//          localn.a(i);
//          this.b.getContentResolver().delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "_data = ?", new String[] { paramString });
//        }
//        return localn;
//      }
//    }
//    catch (Exception localException)
//    {
//      localException.printStackTrace();
//    }
//    return null;
//  }
//  
//  public String a(int paramInt, String paramString1, String paramString2)
//  {
//    Log.v("SecretFileHelper", "SecretFileHelperdstPath：" + paramString2);
//    if (paramString2 != null) {}
//    Object localObject;
//    for (;;)
//    {
//      try
//      {
//        if (paramString2.length() == 0)
//        {
//          localObject = a(this.b);
//          boolean bool = h.c(paramString1, (String)localObject);
//          Log.v("SecretFileHelper", "SecretFileHelper isSuccess:" + bool + " dstPath :" + (String)localObject);
//          if (bool)
//          {
//            Intent localIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
//            localIntent.setData(Uri.fromFile(new File((String)localObject)));
//            this.b.sendBroadcast(localIntent);
//            if (!this.c.a(paramInt)) {
//              break;
//            }
//            h.i(paramString1);
//            return localObject;
//          }
//        }
//        else
//        {
//          File localFile = new File(h.c(paramString2));
//          if ((localFile.exists()) || (localFile.mkdirs())) {
//            break label203;
//          }
//          String str = a(this.b);
//          localObject = str;
//          continue;
//        }
//        return null;
//      }
//      catch (Exception localException)
//      {
//        localException.printStackTrace();
//      }
//      label203:
//      localObject = paramString2;
//    }
//    return localObject;
//  }
//  
//  public boolean a(int paramInt, String paramString)
//  {
//    boolean bool = this.c.a(paramInt);
//    if (bool) {
//      bool = h.i(paramString);
//    }
//    return bool;
//  }
//  
//  public ArrayList<n> c()
//  {
//    return this.c.a();
//  }
}
