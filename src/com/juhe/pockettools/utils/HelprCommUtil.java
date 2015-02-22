package com.juhe.pockettools.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.view.ViewConfiguration;

/**
 * 通用工具
 * 
 * @author daiye
 * 
 */
@SuppressLint("NewApi")
public class HelprCommUtil {
	private static final String a = "Helpr_TCommUtil";

	// public static float a()
	// {
	// return HelprApplication.c().getResources().getDisplayMetrics().density;
	// }
	//
	// public static int a(Context paramContext, float paramFloat)
	// {
	// return (int)(0.5F + paramFloat *
	// paramContext.getResources().getDisplayMetrics().density);
	// }
	//
	// /* Error */
	// public static Bitmap a(String paramString)
	// {
	// // Byte code:
	// // 0: aconst_null
	// // 1: astore_1
	// // 2: aload_0
	// // 3: ifnull +14 -> 17
	// // 6: aload_0
	// // 7: invokevirtual 48 java/lang/String:length ()I
	// // 10: istore_2
	// // 11: aconst_null
	// // 12: astore_1
	// // 13: iload_2
	// // 14: ifne +5 -> 19
	// // 17: aload_1
	// // 18: areturn
	// // 19: invokestatic 19 com/helpr/application/HelprApplication:c
	// ()Landroid/content/Context;
	// // 22: invokevirtual 52 android/content/Context:getAssets
	// ()Landroid/content/res/AssetManager;
	// // 25: astore_3
	// // 26: aload_3
	// // 27: aload_0
	// // 28: invokevirtual 58 android/content/res/AssetManager:open
	// (Ljava/lang/String;)Ljava/io/InputStream;
	// // 31: astore 7
	// // 33: aload 7
	// // 35: astore 5
	// // 37: aconst_null
	// // 38: astore_1
	// // 39: aload 5
	// // 41: ifnull +13 -> 54
	// // 44: aload 5
	// // 46: invokestatic 64 android/graphics/BitmapFactory:decodeStream
	// (Ljava/io/InputStream;)Landroid/graphics/Bitmap;
	// // 49: astore 8
	// // 51: aload 8
	// // 53: astore_1
	// // 54: aload 5
	// // 56: ifnull -39 -> 17
	// // 59: aload 5
	// // 61: invokevirtual 69 java/io/InputStream:close ()V
	// // 64: aload_1
	// // 65: areturn
	// // 66: astore 6
	// // 68: aload 6
	// // 70: invokevirtual 72 java/io/IOException:printStackTrace ()V
	// // 73: aload_1
	// // 74: areturn
	// // 75: astore 4
	// // 77: aconst_null
	// // 78: astore 5
	// // 80: aload 4
	// // 82: invokevirtual 72 java/io/IOException:printStackTrace ()V
	// // 85: aconst_null
	// // 86: astore_1
	// // 87: goto -33 -> 54
	// // 90: astore 4
	// // 92: goto -12 -> 80
	// // Local variable table:
	// // start length slot name signature
	// // 0 95 0 paramString String
	// // 1 86 1 localObject Object
	// // 10 4 2 i int
	// // 25 2 3 localAssetManager android.content.res.AssetManager
	// // 75 6 4 localIOException1 IOException
	// // 90 1 4 localIOException2 IOException
	// // 35 44 5 localInputStream1 java.io.InputStream
	// // 66 3 6 localIOException3 IOException
	// // 31 3 7 localInputStream2 java.io.InputStream
	// // 49 3 8 localBitmap Bitmap
	// // Exception table:
	// // from to target type
	// // 59 64 66 java/io/IOException
	// // 26 33 75 java/io/IOException
	// // 44 51 90 java/io/IOException
	// }
	//
	// public static File a(Bitmap paramBitmap)
	// {
	// boolean bool1 = Environment.getExternalStorageState().equals("mounted");
	// boolean bool2 = false;
	// if (bool1) {
	// bool2 = true;
	// }
	// Log.v("Helpr_TCommUtil", "Helpr_TCommUtilbHaveSdcard :" + bool2);
	// String str;
	// File localFile2;
	// if (bool2)
	// {
	// str =
	// Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString()
	// + "/Camera/";
	// File localFile3 = new File(str);
	// if (!localFile3.exists()) {
	// localFile3.mkdirs();
	// }
	// localFile2 = new File(str, "img" + System.currentTimeMillis() + ".jpg");
	// Log.v("Helpr_TCommUtil", "Helpr_TCommUtilmediaStorageDir :" +
	// localFile2);
	// }
	// FileOutputStream localFileOutputStream;
	// do
	// {
	// try
	// {
	// localFileOutputStream = new FileOutputStream(localFile2);
	// }
	// catch (Exception localException2)
	// {
	// Context localContext;
	// File localFile1;
	// localFileOutputStream = null;
	// }
	// try
	// {
	// paramBitmap.compress(Bitmap.CompressFormat.JPEG, 100,
	// localFileOutputStream);
	// localFileOutputStream.flush();
	// localFileOutputStream.close();
	// return localFile2;
	// }
	// catch (Exception localException1)
	// {
	// continue;
	// }
	// localContext = HelprApplication.c();
	// HelprApplication.c();
	// str = localContext.getDir("InstaMag", 1).getAbsolutePath();
	// localFile1 = new File(str);
	// if (localFile1.exists()) {
	// break;
	// }
	// localFile1.mkdirs();
	// break;
	// } while (localFileOutputStream == null);
	// try
	// {
	// localFileOutputStream.close();
	// return localFile2;
	// }
	// catch (IOException localIOException)
	// {
	// localIOException.printStackTrace();
	// return localFile2;
	// }
	// }
	//
	// public static final String a(Context paramContext)
	// {
	// PackageManager localPackageManager = paramContext.getPackageManager();
	// try
	// {
	// ApplicationInfo localApplicationInfo2 =
	// localPackageManager.getApplicationInfo(paramContext.getPackageName(), 0);
	// localApplicationInfo1 = localApplicationInfo2;
	// }
	// catch (PackageManager.NameNotFoundException localNameNotFoundException)
	// {
	// for (;;)
	// {
	// ApplicationInfo localApplicationInfo1 = null;
	// continue;
	// Object localObject = "(unknown)";
	// }
	// }
	// if (localApplicationInfo1 != null)
	// {
	// localObject =
	// localPackageManager.getApplicationLabel(localApplicationInfo1);
	// return (String)localObject;
	// }
	// }
	//
	// public static String a(HashMap<String, String> paramHashMap)
	// {
	// Object localObject = "";
	// try
	// {
	// Iterator localIterator = paramHashMap.keySet().iterator();
	// for (;;)
	// {
	// if (!localIterator.hasNext()) {
	// return localObject;
	// }
	// String str1 = (String)localIterator.next();
	// String str2 = localObject + "&" + str1 + "=" +
	// URLEncoder.encode((String)paramHashMap.get(str1), "utf-8");
	// localObject = str2;
	// }
	// return "";
	// }
	// catch (Exception localException) {}
	// }
	//
	// public static void a(Activity paramActivity)
	// {
	// try
	// {
	// InputMethodManager localInputMethodManager =
	// (InputMethodManager)paramActivity.getSystemService("input_method");
	// if (localInputMethodManager.isAcceptingText()) {
	// localInputMethodManager.hideSoftInputFromWindow(paramActivity.getCurrentFocus().getWindowToken(),
	// 0);
	// }
	// return;
	// }
	// catch (Exception localException)
	// {
	// localException.printStackTrace();
	// }
	// }
	//
	// public static void a(Context paramContext, EditText paramEditText)
	// {
	// try
	// {
	// InputMethodManager localInputMethodManager =
	// (InputMethodManager)paramContext.getSystemService("input_method");
	// localInputMethodManager.showSoftInputFromInputMethod(paramEditText.getWindowToken(),
	// 0);
	// localInputMethodManager.toggleSoftInputFromWindow(paramEditText.getWindowToken(),
	// 0, 2);
	// return;
	// }
	// catch (Exception localException) {}
	// }
	//
	// public static void a(View paramView, float paramFloat, Context
	// paramContext)
	// {
	// int i = ((ViewGroup)paramView).getChildCount();
	// for (int j = 0;; j++)
	// {
	// if (j >= i) {
	// return;
	// }
	// View localView = ((ViewGroup)paramView).getChildAt(j);
	// if ((localView instanceof TextView))
	// {
	// float f = paramFloat * ((TextView)localView).getTextSize();
	// ((TextView)localView).setTextSize(c(paramContext, f));
	// }
	// if ((localView instanceof View))
	// {
	// ViewGroup.LayoutParams localLayoutParams = localView.getLayoutParams();
	// if (localLayoutParams.width >= 0)
	// {
	// int i5 = (int)(paramFloat * localLayoutParams.width);
	// if (i5 == 0) {
	// i5 = 1;
	// }
	// localLayoutParams.width = i5;
	// }
	// if (localLayoutParams.height >= 0)
	// {
	// int i4 = (int)(paramFloat * localLayoutParams.height);
	// if (i4 == 0) {
	// i4 = 1;
	// }
	// localLayoutParams.height = i4;
	// }
	// if ((localLayoutParams instanceof FrameLayout.LayoutParams))
	// {
	// FrameLayout.LayoutParams localLayoutParams3 =
	// (FrameLayout.LayoutParams)localLayoutParams;
	// int i2 = (int)(paramFloat * localLayoutParams3.leftMargin);
	// int i3 = (int)(paramFloat * localLayoutParams3.rightMargin);
	// localLayoutParams3.setMargins(i2, (int)(paramFloat *
	// localLayoutParams3.topMargin), i3, (int)(paramFloat *
	// localLayoutParams3.bottomMargin));
	// }
	// if ((localLayoutParams instanceof RelativeLayout.LayoutParams))
	// {
	// RelativeLayout.LayoutParams localLayoutParams2 =
	// (RelativeLayout.LayoutParams)localLayoutParams;
	// int n = (int)(paramFloat * localLayoutParams2.leftMargin);
	// int i1 = (int)(paramFloat * localLayoutParams2.rightMargin);
	// localLayoutParams2.setMargins(n, (int)(paramFloat *
	// localLayoutParams2.topMargin), i1, (int)(paramFloat *
	// localLayoutParams2.bottomMargin));
	// }
	// if ((localLayoutParams instanceof LinearLayout.LayoutParams))
	// {
	// LinearLayout.LayoutParams localLayoutParams1 =
	// (LinearLayout.LayoutParams)localLayoutParams;
	// int k = (int)(paramFloat * localLayoutParams1.leftMargin);
	// int m = (int)(paramFloat * localLayoutParams1.rightMargin);
	// localLayoutParams1.setMargins(k, (int)(paramFloat *
	// localLayoutParams1.topMargin), m, (int)(paramFloat *
	// localLayoutParams1.bottomMargin));
	// }
	// localView.setLayoutParams(localLayoutParams);
	// if ((localView instanceof ViewGroup))
	// {
	// ViewGroup localViewGroup = (ViewGroup)localView;
	// if (localViewGroup.getChildCount() > 0) {
	// a(localViewGroup, paramFloat, paramContext);
	// }
	// }
	// }
	// }
	// }
	//
	// public static void a(ViewGroup paramViewGroup, float paramFloat, Context
	// paramContext)
	// {
	// int i = paramViewGroup.getChildCount();
	// for (int j = 0;; j++)
	// {
	// if (j >= i) {
	// return;
	// }
	// View localView = paramViewGroup.getChildAt(j);
	// if ((localView instanceof TextView))
	// {
	// float f = paramFloat * ((TextView)localView).getTextSize();
	// ((TextView)localView).setTextSize(c(paramContext, f));
	// }
	// if ((localView instanceof View))
	// {
	// ViewGroup.LayoutParams localLayoutParams = localView.getLayoutParams();
	// if (localLayoutParams.width >= 0)
	// {
	// int i5 = (int)(paramFloat * localLayoutParams.width);
	// if (i5 == 0) {
	// i5 = 1;
	// }
	// localLayoutParams.width = i5;
	// }
	// if (localLayoutParams.height >= 0)
	// {
	// int i4 = (int)(paramFloat * localLayoutParams.height);
	// if (i4 == 0) {
	// i4 = 1;
	// }
	// localLayoutParams.height = i4;
	// }
	// if ((localLayoutParams instanceof FrameLayout.LayoutParams))
	// {
	// FrameLayout.LayoutParams localLayoutParams3 =
	// (FrameLayout.LayoutParams)localLayoutParams;
	// int i2 = (int)(paramFloat * localLayoutParams3.leftMargin);
	// int i3 = (int)(paramFloat * localLayoutParams3.rightMargin);
	// localLayoutParams3.setMargins(i2, (int)(paramFloat *
	// localLayoutParams3.topMargin), i3, (int)(paramFloat *
	// localLayoutParams3.bottomMargin));
	// }
	// if ((localLayoutParams instanceof RelativeLayout.LayoutParams))
	// {
	// RelativeLayout.LayoutParams localLayoutParams2 =
	// (RelativeLayout.LayoutParams)localLayoutParams;
	// int n = (int)(paramFloat * localLayoutParams2.leftMargin);
	// int i1 = (int)(paramFloat * localLayoutParams2.rightMargin);
	// localLayoutParams2.setMargins(n, (int)(paramFloat *
	// localLayoutParams2.topMargin), i1, (int)(paramFloat *
	// localLayoutParams2.bottomMargin));
	// }
	// if ((localLayoutParams instanceof LinearLayout.LayoutParams))
	// {
	// LinearLayout.LayoutParams localLayoutParams1 =
	// (LinearLayout.LayoutParams)localLayoutParams;
	// int k = (int)(paramFloat * localLayoutParams1.leftMargin);
	// int m = (int)(paramFloat * localLayoutParams1.rightMargin);
	// localLayoutParams1.setMargins(k, (int)(paramFloat *
	// localLayoutParams1.topMargin), m, (int)(paramFloat *
	// localLayoutParams1.bottomMargin));
	// }
	// localView.setLayoutParams(localLayoutParams);
	// if ((localView instanceof ViewGroup))
	// {
	// ViewGroup localViewGroup = (ViewGroup)localView;
	// if (localViewGroup.getChildCount() > 0) {
	// a(localViewGroup, paramFloat, paramContext);
	// }
	// }
	// }
	// }
	// }
	//
	// public static void a(ListView paramListView)
	// {
	// ListAdapter localListAdapter = paramListView.getAdapter();
	// if (localListAdapter == null) {
	// return;
	// }
	// int i = 0;
	// int j = 0;
	// for (;;)
	// {
	// if (i >= localListAdapter.getCount())
	// {
	// ViewGroup.LayoutParams localLayoutParams =
	// paramListView.getLayoutParams();
	// localLayoutParams.height = (j + paramListView.getDividerHeight() * (-1 +
	// localListAdapter.getCount()));
	// paramListView.setLayoutParams(localLayoutParams);
	// paramListView.requestLayout();
	// return;
	// }
	// View localView = localListAdapter.getView(i, null, paramListView);
	// localView.measure(0, 0);
	// j += localView.getMeasuredHeight();
	// i++;
	// }
	// }
	//
	// public static void a(String paramString, Context paramContext)
	// {
	// MediaScannerConnection.scanFile(paramContext, new String[] { paramString
	// }, null, new l());
	// }
	//
	// public static boolean a(long paramLong)
	// {
	// long l = System.currentTimeMillis() - paramLong;
	// return (0L < l) && (l < 1000L);
	// }
	//
	// public static boolean a(Context paramContext, String paramString)
	// {
	// if (paramString == null) {}
	// do
	// {
	// return false;
	// try
	// {
	// PackageInfo localPackageInfo2 =
	// paramContext.getPackageManager().getPackageInfo(paramString, 0);
	// localPackageInfo1 = localPackageInfo2;
	// }
	// catch (Exception localException)
	// {
	// for (;;)
	// {
	// localPackageInfo1 = null;
	// }
	// }
	// catch (PackageManager.NameNotFoundException localNameNotFoundException)
	// {
	// for (;;)
	// {
	// PackageInfo localPackageInfo1 = null;
	// }
	// }
	// } while (localPackageInfo1 == null);
	// return true;
	// }
	//
	// public static boolean a(String paramString1, String paramString2)
	// {
	// if ((paramString1 == null) || (paramString1.length() == 0)) {}
	// label115:
	// int n;
	// label158:
	// int i1;
	// label228:
	// do
	// {
	// do
	// {
	// return false;
	// } while ((paramString2 == null) || (paramString2.length() == 0));
	// new String[20];
	// new String[20];
	// String[] arrayOfString1 = paramString1.split("\\.");
	// String[] arrayOfString2 = paramString2.split("\\.");
	// int i = Math.max(arrayOfString1.length, arrayOfString2.length);
	// ArrayList localArrayList1 = new ArrayList(Collections.nCopies(i,
	// Integer.valueOf(0)));
	// ArrayList localArrayList2 = new ArrayList(Collections.nCopies(i,
	// Integer.valueOf(0)));
	// int j = 0;
	// int k;
	// if (j >= arrayOfString1.length)
	// {
	// k = 0;
	// if (k < arrayOfString2.length) {
	// break label158;
	// }
	// }
	// for (int m = 0;; m++)
	// {
	// if (m >= i)
	// {
	// return true;
	// localArrayList1.add(j, Integer.valueOf(arrayOfString1[j]));
	// j++;
	// break;
	// localArrayList2.add(k, Integer.valueOf(arrayOfString2[k]));
	// k++;
	// break label115;
	// }
	// n = ((Integer)localArrayList2.get(m)).intValue();
	// i1 = ((Integer)localArrayList1.get(m)).intValue();
	// if (n != i1) {
	// break label228;
	// }
	// }
	// } while (n <= i1);
	// return true;
	// }
	//
	// public static int b(Context paramContext)
	// {
	// return b(paramContext,
	// paramContext.getResources().getDisplayMetrics().widthPixels);
	// }
	//
	// public static int b(Context paramContext, float paramFloat)
	// {
	// return (int)(0.5F + paramFloat /
	// paramContext.getResources().getDisplayMetrics().density);
	// }
	//
	// public static boolean b()
	// {
	// Locale localLocale = Locale.getDefault();
	// String str = localLocale.getLanguage();
	// localLocale.getCountry();
	// boolean bool1 = str.equalsIgnoreCase("zh");
	// boolean bool2 = false;
	// if (bool1) {
	// bool2 = true;
	// }
	// return bool2;
	// }
	//
	// public static int c(Context paramContext)
	// {
	// return b(paramContext,
	// paramContext.getResources().getDisplayMetrics().heightPixels);
	// }
	//
	// public static int c(Context paramContext, float paramFloat)
	// {
	// return (int)(0.5F + paramFloat /
	// paramContext.getResources().getDisplayMetrics().scaledDensity);
	// }
	//
	// public static boolean c()
	// {
	// Locale localLocale = Locale.getDefault();
	// String str1 = localLocale.getLanguage();
	// String str2 = localLocale.getCountry();
	// boolean bool1 = str1.equalsIgnoreCase("zh");
	// boolean bool2 = false;
	// if (bool1)
	// {
	// boolean bool3 = str2.equalsIgnoreCase("cn");
	// bool2 = false;
	// if (bool3) {
	// bool2 = true;
	// }
	// }
	// return bool2;
	// }
	//
	// public static float d(Context paramContext)
	// {
	// return paramContext.getResources().getDisplayMetrics().scaledDensity;
	// }
	//
	// public static int d(Context paramContext, float paramFloat)
	// {
	// return (int)(0.5F + paramFloat *
	// paramContext.getResources().getDisplayMetrics().scaledDensity);
	// }
	//
	// public static boolean d()
	// {
	// Locale localLocale = Locale.getDefault();
	// String str1 = localLocale.getLanguage();
	// String str2 = localLocale.getCountry();
	// boolean bool2;
	// if ((!str1.equalsIgnoreCase("zh")) || (!str2.equalsIgnoreCase("TW")))
	// {
	// boolean bool1 = str1.equalsIgnoreCase("zh");
	// bool2 = false;
	// if (bool1)
	// {
	// boolean bool3 = str2.equalsIgnoreCase("HK");
	// bool2 = false;
	// if (!bool3) {}
	// }
	// }
	// else
	// {
	// bool2 = true;
	// }
	// return bool2;
	// }
	//
	// public static float e(Context paramContext)
	// {
	// return paramContext.getResources().getDisplayMetrics().density;
	// }
	//
	// public static boolean e()
	// {
	// String str = Locale.getDefault().getCountry();
	// boolean bool1;
	// if (!str.equalsIgnoreCase("US"))
	// {
	// boolean bool2 = str.equalsIgnoreCase("USA");
	// bool1 = false;
	// if (!bool2) {}
	// }
	// else
	// {
	// bool1 = true;
	// }
	// return bool1;
	// }
	//
	// public static int f(Context paramContext)
	// {
	// return paramContext.getResources().getDisplayMetrics().widthPixels;
	// }
	//
	// public static String f()
	// {
	// Context localContext = HelprApplication.c();
	// String str = "";
	// try
	// {
	// PackageInfo localPackageInfo2 =
	// localContext.getPackageManager().getPackageInfo(localContext.getPackageName(),
	// 0);
	// localPackageInfo1 = localPackageInfo2;
	// }
	// catch (PackageManager.NameNotFoundException localNameNotFoundException)
	// {
	// for (;;)
	// {
	// localNameNotFoundException.printStackTrace();
	// PackageInfo localPackageInfo1 = null;
	// }
	// }
	// if (localPackageInfo1 != null) {
	// str = localPackageInfo1.versionName;
	// }
	// return str;
	// }
	//
	// public static int g(Context paramContext)
	// {
	// return paramContext.getResources().getDisplayMetrics().heightPixels;
	// }
	//
	// @TargetApi(9)
	// protected static String g()
	// {
	// return Build.SERIAL;
	// }

	/**
	 * sd卡是否存在
	 * 
	 * @return
	 */
	public static boolean hasSDCard() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			return true;
		}
		return false;
	}

	/**
	 * 是否有软键盘
	 * 
	 * @param context
	 * @return
	 */
	public static boolean hasNavigationBar(Context context) {
		Boolean hasHardwareMenuKey = null;
		if (hasHardwareMenuKey == null) {
			ViewConfiguration vc = ViewConfiguration.get(context);
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
				// boolean vc.hasPermanentMenuKey();
				try {
					Method m = vc.getClass().getMethod("hasPermanentMenuKey",
							new Class<?>[] {});
					try {
						hasHardwareMenuKey = (Boolean) m.invoke(vc,
								new Object[] {});
					} catch (IllegalArgumentException e) {
						hasHardwareMenuKey = false;
					} catch (IllegalAccessException e) {
						hasHardwareMenuKey = false;
					} catch (InvocationTargetException e) {
						hasHardwareMenuKey = false;
					}
				} catch (NoSuchMethodException e) {
					hasHardwareMenuKey = false;
				}
			}
			if (hasHardwareMenuKey == null) {
//				if (DeviceInfo.EINK_SCREEN)
//					hasHardwareMenuKey = false;
//				else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH)
//					hasHardwareMenuKey = true;
//				else
					hasHardwareMenuKey = false;
			}
		}
		return hasHardwareMenuKey;
	}

	//
	// public static int i()
	// {
	// new DisplayMetrics();
	// Context localContext = HelprApplication.c();
	// DisplayMetrics localDisplayMetrics =
	// localContext.getResources().getDisplayMetrics();
	// double d1 = localContext.getResources().getDisplayMetrics().widthPixels;
	// double d2 = localContext.getResources().getDisplayMetrics().heightPixels;
	// Math.sqrt(Math.pow(d1, 2.0D) + Math.pow(d2, 2.0D));
	// float f1 = localDisplayMetrics.xdpi;
	// float f2 = localDisplayMetrics.widthPixels;
	// localDisplayMetrics.heightPixels;
	// if (f2 / f1 >= 2.4D) {
	// return 5;
	// }
	// return 4;
	// }
	//
	// public static int i(Context paramContext)
	// {
	// Resources localResources = paramContext.getResources();
	// return
	// localResources.getDimensionPixelSize(localResources.getIdentifier("navigation_bar_height",
	// "dimen", "android"));
	// }
}
