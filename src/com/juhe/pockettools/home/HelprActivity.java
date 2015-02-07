package com.juhe.pockettools.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.juhe.pockettools.R;
import com.juhe.pockettools.utils.HelprCommUtil;


@SuppressLint("NewApi")
public class HelprActivity
  extends FullscreenActivity
{
//  private static int G = 0;
//  public static final int a = 10003;
//  public static final String b = "appOpenedTimesHelpr";
//  public static String d = "infos_current_item";
//  private static final int l = 10001;
//  private static final int m = 10002;
//  private ViewPager A;
//  private ViewPager B;
//  private ViewPager C;
//  private ViewPager D;
//  private ViewPager E;
//  private Button F;
//  private boolean H = false;
//  private ImageView I;
//  private ImageView J;
//  private Bitmap K;
//  private float L;
//  private float M;
//  private EditText N;
//  private HelprHomeSearchView O;
//  private boolean P = false;
//  private int Q = 5;
//  private int R = 7;
//  private String S = "";
//  private com.fotoable.helpr.infos.z T = new a(this);
//  private mainTabScrollView.a U = new l(this);
//  private com.fotoable.helpr.maintab.a V = new w(this);
//  private com.fotoable.helpr.maintab.l W = new x(this);
//  private com.fotoable.helpr.maintab.d X = new y(this);
//  private com.fotoable.helpr.maintab.i Y = new z(this);
//  protected String c = "Help_activity";
  View view;
//  IconPageIndicator f;
//  IconPageIndicator g;
//  IconPageIndicator h;
//  IconPageIndicator i;
//  IconPageIndicator j;
//  BroadcastReceiver k = new v(this);
//  private LinearLayout n;
//  private FrameLayout o;
//  private FrameLayout p;
//  private mainTabScrollView q;
//  private infosViewPagerAdapter r;
//  private DailyToolPagerAdapter s;
//  private ReadDiscoverAdapter t;
//  private LifeSearchPageAdapter u;
//  private PocketMarketAdapter v;
//  private LinearLayout w;
//  private LinearLayout x;
//  private LinearLayout y;
//  private LinearLayout z;
//  
//  private void A()
//  {
//    startActivity(new Intent(this, PackageMainActivity.class));
//    overridePendingTransition(2130968579, 2130968589);
//  }
//  
//  private void B()
//  {
//    startActivity(new Intent(this, DreamMainActivity.class));
//    overridePendingTransition(2130968579, 2130968589);
//  }
//  
//  private void C()
//  {
//    startActivity(new Intent(this, ExChangeMainActivity.class));
//    overridePendingTransition(2130968579, 2130968589);
//  }
//  
//  private void D()
//  {
//    startActivity(new Intent(this, UnitExchangeMainActivity.class));
//    overridePendingTransition(2130968579, 2130968589);
//  }
//  
//  private void E()
//  {
//    startActivity(new Intent(this, MetroMainActivity.class));
//    overridePendingTransition(2130968579, 2130968589);
//  }
//  
//  private void F()
//  {
//    startActivity(new Intent(this, MobileLocaleMainActivity.class));
//    overridePendingTransition(2130968579, 2130968589);
//  }
//  
//  private void G()
//  {
//    startActivity(new Intent(this, CommonPhoneMainActivity.class));
//    overridePendingTransition(2130968579, 2130968589);
//  }
//  
//  private void H()
//  {
//    startActivity(new Intent(this, IdCardMainActivity.class));
//    overridePendingTransition(2130968579, 2130968589);
//  }
//  
//  private void I()
//  {
//    startActivity(new Intent(this, OilMainActivity.class));
//    overridePendingTransition(2130968579, 2130968589);
//  }
//  
//  private void J()
//  {
//    startActivity(new Intent(this, BusinessMainActivity.class));
//    overridePendingTransition(2130968579, 2130968589);
//  }
//  
//  private void K()
//  {
//    startActivity(new Intent(this, WallpaperMainActivity.class));
//    overridePendingTransition(2130968579, 2130968589);
//  }
//  
//  private void L()
//  {
//    startActivity(new Intent(this, BatteryMainActivity.class));
//    overridePendingTransition(2130968579, 2130968589);
//  }
//  
//  private void M()
//  {
//    startActivity(new Intent(this, ViolationMainActivity.class));
//    overridePendingTransition(2130968579, 2130968589);
//  }
//  
//  private void N()
//  {
//    startActivity(new Intent(this, TrainMainActivity.class));
//    overridePendingTransition(2130968579, 2130968589);
//  }
//  
//  private void O()
//  {
//    startActivity(new Intent(this, WaiMaiMainActivity.class));
//    overridePendingTransition(2130968579, 2130968589);
//  }
//  
//  private void P()
//  {
//    startActivity(new Intent(this, TicketMainActivity.class));
//    overridePendingTransition(2130968579, 2130968589);
//  }
//  
//  private void Q()
//  {
//    startActivity(new Intent(this, GroupMainActivity.class));
//    overridePendingTransition(2130968579, 2130968589);
//  }
//  
//  private void R()
//  {
//    startActivity(new Intent(this, MovieMainActivity.class));
//    overridePendingTransition(2130968579, 2130968589);
//  }
//  
//  private void S()
//  {
//    startActivity(new Intent(this, TicketBrowserActivity.class));
//    overridePendingTransition(2130968579, 2130968589);
//  }
//  
//  private void T()
//  {
//    startActivity(new Intent(this, GameCenterMainActivity.class));
//    overridePendingTransition(2130968579, 2130968589);
//  }
//  
//  private void U()
//  {
//    startActivity(new Intent(this, MemoryMainActivity.class));
//    overridePendingTransition(2130968579, 2130968589);
//  }
//  
//  private void V()
//  {
//    startActivity(new Intent(this, AccountMainActivity.class));
//    overridePendingTransition(2130968579, 2130968589);
//  }
//  
//  private void W()
//  {
//    startActivity(new Intent(this, DailyReadMainActivity.class));
//    overridePendingTransition(2130968579, 2130968589);
//  }
//  
//  private void X()
//  {
//    startActivity(new Intent(this, DailyQuoteMainActivity.class));
//    overridePendingTransition(2130968579, 2130968589);
//  }
//  
//  private void Y()
//  {
//    XGPushManager.registerPush(getApplicationContext());
//    Z();
//    XGCustomPushNotificationBuilder localXGCustomPushNotificationBuilder = a(getApplicationContext());
//    if (localXGCustomPushNotificationBuilder != null)
//    {
//      XGPushManager.setPushNotificationBuilder(getApplicationContext(), 2, localXGCustomPushNotificationBuilder);
//      XGPushManager.setDefaultNotificationBuilder(getApplicationContext(), localXGCustomPushNotificationBuilder);
//    }
//  }
//  
//  private void Z()
//  {
//    SharedPreferences localSharedPreferences = getSharedPreferences("tagInfo", 0);
//    String str1 = localSharedPreferences.getString("OsVersion", "");
//    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
//    getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
//    String str2 = String.valueOf(localDisplayMetrics.widthPixels) + "*" + String.valueOf(localDisplayMetrics.heightPixels);
//    getResources().getConfiguration().locale.getCountry();
//    String str3 = Build.VERSION.RELEASE.substring(0, 3);
//    Log.d(this.c, this.c + str3);
//    String str4;
//    if ((str2.equalsIgnoreCase("720*1280")) || (str2.equalsIgnoreCase("480*800")) || (str2.equalsIgnoreCase("1080*1920")) || (str2.equalsIgnoreCase("540*960")) || (str2.equalsIgnoreCase("480*854")) || (str2.equalsIgnoreCase("800*1280")) || (str2.equalsIgnoreCase("320*480")))
//    {
//      XGPushManager.setTag(getApplicationContext(), str2);
//      str4 = str2;
//    }
//    for (;;)
//    {
//      if ((!str1.equalsIgnoreCase(str3)) && (!str1.equalsIgnoreCase(""))) {
//        XGPushManager.deleteTag(getApplicationContext(), "android" + str1);
//      }
//      localSharedPreferences.edit().putString("OsVersion", str3).commit();
//      XGPushManager.setTag(getApplicationContext(), "android" + str3);
//      if (!com.fotoable.helpr.Utils.k.c()) {
//        break;
//      }
//      XGPushManager.setTag(getApplicationContext(), "简体中文");
//      XGPushManager.deleteTag(getApplicationContext(), "繁体中文");
//      XGPushManager.deleteTag(getApplicationContext(), "英文");
//      XGPushManager.deleteTag(getApplicationContext(), "简体中文" + str2);
//      XGPushManager.deleteTag(getApplicationContext(), "繁体中文" + str2);
//      XGPushManager.deleteTag(getApplicationContext(), "英文" + str2);
//      XGPushManager.setTag(getApplicationContext(), "简体中文" + str4);
//      XGPushManager.deleteTag(getApplicationContext(), "繁体中文" + str4);
//      XGPushManager.deleteTag(getApplicationContext(), "英文" + str4);
//      return;
//      str4 = "其他分辩率";
//      XGPushManager.setTag(getApplicationContext(), "其他分辩率");
//    }
//    if (com.fotoable.helpr.Utils.k.d())
//    {
//      XGPushManager.setTag(getApplicationContext(), "繁体中文");
//      XGPushManager.deleteTag(getApplicationContext(), "简体中文");
//      XGPushManager.deleteTag(getApplicationContext(), "英文");
//      XGPushManager.deleteTag(getApplicationContext(), "繁体中文" + str2);
//      XGPushManager.deleteTag(getApplicationContext(), "简体中文" + str2);
//      XGPushManager.deleteTag(getApplicationContext(), "英文" + str2);
//      XGPushManager.setTag(getApplicationContext(), "繁体中文" + str4);
//      XGPushManager.deleteTag(getApplicationContext(), "简体中文" + str4);
//      XGPushManager.deleteTag(getApplicationContext(), "英文" + str4);
//      return;
//    }
//    XGPushManager.setTag(getApplicationContext(), "英文");
//    XGPushManager.deleteTag(getApplicationContext(), "简体中文");
//    XGPushManager.deleteTag(getApplicationContext(), "繁体中文");
//    XGPushManager.deleteTag(getApplicationContext(), "英文" + str2);
//    XGPushManager.deleteTag(getApplicationContext(), "简体中文" + str2);
//    XGPushManager.deleteTag(getApplicationContext(), "繁体中文" + str2);
//    XGPushManager.setTag(getApplicationContext(), "英文" + str4);
//    XGPushManager.deleteTag(getApplicationContext(), "简体中文" + str4);
//    XGPushManager.deleteTag(getApplicationContext(), "繁体中文" + str4);
//  }
//  
//  private XGCustomPushNotificationBuilder a(Context paramContext)
//  {
//    XGCustomPushNotificationBuilder localXGCustomPushNotificationBuilder = new XGCustomPushNotificationBuilder();
//    localXGCustomPushNotificationBuilder.setDefaults(3).setFlags(16);
//    localXGCustomPushNotificationBuilder.setLayoutId(2130903206);
//    localXGCustomPushNotificationBuilder.setLayoutTextId(2131361916);
//    localXGCustomPushNotificationBuilder.setLayoutTitleId(2131362111);
//    localXGCustomPushNotificationBuilder.setLayoutIconId(2131362461);
//    localXGCustomPushNotificationBuilder.setLayoutIconDrawableId(2130837877);
//    localXGCustomPushNotificationBuilder.setIcon(Integer.valueOf(2130837878));
//    localXGCustomPushNotificationBuilder.setLayoutTimeId(2131362462);
//    return localXGCustomPushNotificationBuilder;
//  }
//  
//  private void a(LinearLayout paramLinearLayout1, LinearLayout paramLinearLayout2)
//  {
//    paramLinearLayout1.setVisibility(0);
//    paramLinearLayout2.setVisibility(0);
//    Animation localAnimation1 = AnimationUtils.loadAnimation(this, 2130968615);
//    localAnimation1.setAnimationListener(new j(this, paramLinearLayout1));
//    paramLinearLayout1.startAnimation(localAnimation1);
//    Animation localAnimation2 = AnimationUtils.loadAnimation(this, 2130968617);
//    localAnimation2.setAnimationListener(new k(this, paramLinearLayout2));
//    paramLinearLayout2.startAnimation(localAnimation2);
//  }
//  
//  private void a(String paramString)
//  {
//    WebMainActivity.a(this, paramString, "", null);
//  }
//  
//  private void a(JSONObject paramJSONObject)
//  {
//    if (paramJSONObject == null) {}
//    String str1;
//    String str2;
//    String str3;
//    do
//    {
//      return;
//      str1 = com.fotoable.b.a.a(paramJSONObject, "lastVersion");
//      str2 = com.fotoable.b.a.a(paramJSONObject, "versionDetail");
//      str3 = String.format("lastVersion%s", new Object[] { str1 });
//    } while ((com.fotoable.helpr.Utils.h.a(str3, false)) || (!com.fotoable.helpr.Utils.f.b(this)));
//    CustomStyleDialog.Builder localBuilder = new CustomStyleDialog.Builder(this);
//    localBuilder.a(str2);
//    localBuilder.b(String.format(getResources().getString(2131231242), new Object[] { str1 }));
//    localBuilder.b(51);
//    localBuilder.b(getResources().getString(2131230812), new p(this));
//    localBuilder.a(getResources().getString(2131231243), new q(this));
//    CustomStyleDialog localCustomStyleDialog = localBuilder.a();
//    localCustomStyleDialog.setCanceledOnTouchOutside(false);
//    localCustomStyleDialog.show();
//    com.fotoable.helpr.Utils.h.b(str3, true);
//  }
//  
//  private void a(boolean paramBoolean)
//  {
//    com.fotoable.helpr.share.c.a(this, paramBoolean);
//    HashMap localHashMap = new HashMap();
//    StringBuilder localStringBuilder = new StringBuilder("TimeLIne:");
//    if (paramBoolean) {}
//    for (String str = "1";; str = "0")
//    {
//      localHashMap.put("shareBy", str);
//      FlurryAgent.logEvent("shareHelprLinkToWechat", localHashMap);
//      return;
//    }
//  }
//  
//  private void aa()
//  {
//    if (this.O == null)
//    {
//      ViewGroup localViewGroup = (ViewGroup)findViewById(16908290);
//      this.O = new HelprHomeSearchView(this, null);
//      this.O.a(getSupportFragmentManager());
//      this.O.setListener(new o(this));
//      localViewGroup.addView(this.O);
//    }
//    this.O.setVisibility(0);
//    this.O.bringToFront();
//  }
//  
//  private void ab()
//  {
//    int i1 = 1 + com.fotoable.helpr.Utils.h.a("appOpenedTimesHelpr", 0);
//    com.fotoable.helpr.Utils.h.b("appOpenedTimesHelpr", i1);
//    boolean bool = com.fotoable.helpr.Utils.h.a("hadRateInAppStore", false);
//    WeChatShareAssistant localWeChatShareAssistant = new WeChatShareAssistant(this);
//    if ((i1 == this.Q) && (!bool)) {
//      ac();
//    }
//    for (;;)
//    {
//      b();
//      return;
//      if ((i1 == this.R) && (localWeChatShareAssistant.b())) {
//        ad();
//      }
//    }
//  }
//  
//  private void ac()
//  {
//    CustomStyleDialog.Builder localBuilder = new CustomStyleDialog.Builder(this);
//    localBuilder.a(getResources().getString(2131231232));
//    localBuilder.b(getResources().getString(2131231233));
//    localBuilder.b(getResources().getString(2131231234), new r(this));
//    localBuilder.a(getResources().getString(2131231235), new s(this));
//    CustomStyleDialog localCustomStyleDialog = localBuilder.a();
//    localCustomStyleDialog.setCanceledOnTouchOutside(false);
//    localCustomStyleDialog.show();
//  }
//  
//  private void ad()
//  {
//    CustomStyleDialog.Builder localBuilder = new CustomStyleDialog.Builder(this);
//    localBuilder.a(getResources().getString(2131231240));
//    localBuilder.b(getResources().getString(2131231241));
//    localBuilder.b(getResources().getString(2131231238), new t(this));
//    localBuilder.a(getResources().getString(2131231237), new u(this));
//    CustomStyleDialog localCustomStyleDialog = localBuilder.a();
//    localCustomStyleDialog.setCanceledOnTouchOutside(true);
//    localCustomStyleDialog.show();
//  }
//  
//  private void b()
//  {
//    com.helpr.application.c.a().i();
//    com.helpr.application.c.a().j();
//  }
//  
//  private void b(int paramInt)
//  {
//    String str = String.format("%s_%s", new Object[] { com.fotoable.helpr.Utils.m.d, getResources().getString(paramInt) });
//    com.fotoable.helpr.Utils.h.b(str, 1 + com.fotoable.helpr.Utils.h.a(str, 0));
//    c(paramInt);
//  }
//  
//  private void b(LinearLayout paramLinearLayout1, LinearLayout paramLinearLayout2)
//  {
//    paramLinearLayout1.setVisibility(0);
//    paramLinearLayout2.setVisibility(0);
//    Animation localAnimation1 = AnimationUtils.loadAnimation(this, 2130968614);
//    localAnimation1.setAnimationListener(new m(this, paramLinearLayout1));
//    paramLinearLayout1.startAnimation(localAnimation1);
//    Animation localAnimation2 = AnimationUtils.loadAnimation(this, 2130968618);
//    localAnimation2.setAnimationListener(new n(this, paramLinearLayout2));
//    paramLinearLayout2.startAnimation(localAnimation2);
//  }
//  
//  private void b(String paramString)
//  {
//    QQMisicBrowserActivity.a(this, paramString, "", null);
//  }
//  
//  private void c(int paramInt)
//  {
//    try
//    {
//      String str1 = getResources().getString(paramInt);
//      String str2 = "HomeViewButtonClicked " + str1;
//      HashMap localHashMap = new HashMap();
//      String str3 = com.fotoable.helpr.a.b.a().d().b;
//      if ((str3 != null) && (str3.length() > 0)) {
//        localHashMap.put("Place", str3);
//      }
//      localHashMap.put("itemName", str1);
//      FlurryAgent.logEvent(str2, localHashMap, true);
//      this.S = str1;
//      return;
//    }
//    catch (Exception localException) {}
//  }
//  
//  private void d()
//  {
//    HelprApplication.b();
//    com.fotoable.helpr.weather.j.b();
//    com.fotoable.pm.a.b();
//    com.fotoable.helpr.dream.k.b();
//    com.fotoable.helpr.wallpaper.w.b();
//    com.helpr.application.c.a().b();
//    finish();
//  }
//  
//  private void d(int paramInt)
//  {
//    if (this.P) {
//      return;
//    }
//    this.P = true;
//    switch (paramInt)
//    {
//    }
//    for (;;)
//    {
//      b(paramInt);
//      return;
//      k();
//      continue;
//      l();
//      continue;
//      m();
//      continue;
//      n();
//      continue;
//      o();
//      continue;
//      u();
//      continue;
//      v();
//      continue;
//      p();
//      continue;
//      x();
//      continue;
//      s();
//      continue;
//      r();
//      continue;
//      t();
//      continue;
//      y();
//      continue;
//      B();
//      continue;
//      C();
//      continue;
//      D();
//      continue;
//      q();
//      continue;
//      z();
//      continue;
//      a("http://m.toutiao.com");
//      continue;
//      A();
//      continue;
//      E();
//      continue;
//      F();
//      continue;
//      G();
//      continue;
//      H();
//      continue;
//      I();
//      continue;
//      J();
//      continue;
//      M();
//      continue;
//      N();
//      continue;
//      O();
//      continue;
//      a("http://m.zmqnw.com.cn/?from=koudaigongju");
//      continue;
//      a("http://m.haodf.com/touch?from=koudaigongju");
//      continue;
//      Q();
//      continue;
//      P();
//      continue;
//      R();
//      continue;
//      a("http://m.xiangha.com/?xh=koudaigongju");
//      continue;
//      S();
//      continue;
//      K();
//      continue;
//      L();
//      continue;
//      T();
//      continue;
//      U();
//      continue;
//      V();
//      continue;
//      b("http://y.qq.com");
//      continue;
//      a("http://breadtrip.com/explore/");
//      continue;
//      a("http://m.duitang.com");
//      continue;
//      a("http://www.u17.com/");
//      continue;
//      W();
//      continue;
//      X();
//      continue;
//      a("http://ai.m.taobao.com?pid=mm_103840808_8134995_28298120");
//      continue;
//      a("http://r.union.meituan.com/url/visit/?a=1&key=lEdpyDfG8gj5X9ORTi2zkw0JcI4H6q1F&url=http://i.meituan.com/?nodown");
//      continue;
//      a("http://ai.m.taobao.com/bu.html?id=1&pid=mm_103840808_8134995_28310078");
//      continue;
//      a("http://ai.m.taobao.com/channel.html?id=2&pid=mm_103840808_8134995_28304075");
//      continue;
//      a("http://ai.m.taobao.com/channel.html?id=1&pid=mm_103840808_8134995_28322058");
//      continue;
//      a("http://ai.m.taobao.com/bu.html?id=2&pid=mm_103840808_8134995_28316071");
//      continue;
//      a("http://u.ctrip.com/union/CtripRedirect.aspx?TypeID=2&Allianceid=29581&sid=468563&OUID=&jumpUrl=http://www.ctrip.com");
//      continue;
//      a(com.helpr.application.c.a().d());
//      continue;
//      a("http://touch.lecai.com/?noClientdl=1&agentId=3179#path=page%2Fmain");
//      continue;
//      a("http://maoyan.com");
//      continue;
//      a("http://m.wepiao.com/index.html");
//      continue;
//      a("http://m.yhd.com/1?tracker_u=109692322");
//      continue;
//      a("http://ai.m.taobao.com/bu.html?back=true&id=6&pid=mm_103840808_8134995_28868855");
//      continue;
//      a("http://m.58.com/bj/?utm_source=uc_jp_0220");
//      continue;
//      a("http://api.kuaidadi.com:9898/taxi/h5/index.htm?source=bjyuntu&key=dshdsh2387327ewh32ydshwehehe");
//      continue;
//      String str = com.fotoable.helpr.a.b.a().d().b;
//      double d1 = com.fotoable.helpr.a.b.a().h.a;
//      double d2 = com.fotoable.helpr.a.b.a().h.b;
//      Object[] arrayOfObject = new Object[3];
//      arrayOfObject[0] = str;
//      arrayOfObject[1] = String.valueOf(d1);
//      arrayOfObject[2] = String.valueOf(d2);
//      a(String.format("http://pay.xiaojukeji.com/api/v2/webapp?city=%s&maptype=&fromlat=%s&fromlng=%s&fromaddr=&toaddr=&toshop=&channel=1284&d=130002030203", arrayOfObject));
//    }
//  }
//  
//  private void e()
//  {
//    DisplayMetrics localDisplayMetrics = getResources().getDisplayMetrics();
//    int i1 = localDisplayMetrics.widthPixels;
//    this.r = new infosViewPagerAdapter(this, (int)(localDisplayMetrics.heightPixels - com.fotoable.helpr.Utils.k.a(this, 25.0F) - i1 * 2 / 3.0F), getSupportFragmentManager());
//    this.r.a(this.T);
//    this.A.setAdapter(this.r);
//    this.f = ((IconPageIndicator)findViewById(2131361945));
//    this.f.setViewPager(this.A);
//    this.A.setOnPageChangeListener(new d(this));
//    this.A.setOnClickListener(new e(this));
//    this.A.setCurrentItem(G);
//    this.s = new DailyToolPagerAdapter(this, getSupportFragmentManager());
//    this.s.a(this.V);
//    this.B.setAdapter(this.s);
//    this.g = ((IconPageIndicator)findViewById(2131361952));
//    this.g.setViewPager(this.B);
//    this.g.setSideListener(new f(this));
//    this.t = new ReadDiscoverAdapter(this, getSupportFragmentManager());
//    this.t.a(this.W);
//    this.D.setAdapter(this.t);
//    this.h = ((IconPageIndicator)findViewById(2131361955));
//    this.h.setViewPager(this.D);
//    this.h.setSideListener(new g(this));
//    this.u = new LifeSearchPageAdapter(this, getSupportFragmentManager());
//    this.u.a(this.X);
//    this.C.setAdapter(this.u);
//    this.i = ((IconPageIndicator)findViewById(2131361958));
//    this.i.setViewPager(this.C);
//    this.i.setSideListener(new h(this));
//    this.v = new PocketMarketAdapter(this, getSupportFragmentManager());
//    this.v.a(this.Y);
//    this.E.setAdapter(this.v);
//    this.j = ((IconPageIndicator)findViewById(2131361961));
//    this.j.setViewPager(this.E);
//    this.j.setSideListener(new i(this));
//  }
//  
//  private void f()
//  {
//    if (this.J != null)
//    {
//      this.L = this.J.getMeasuredHeight();
//      this.M = this.I.getMeasuredHeight();
//      float f1 = 0.6F;
//      if ((this.L >= 0.0F) && (this.M > 0.0F)) {
//        f1 = 1.0F - this.L / this.M;
//      }
//      Log.v(this.c, this.c + "mToolContainer height :" + this.p.getHeight());
//      Log.v(this.c, this.c + "bottomImgHeight :" + this.L);
//      Log.v(this.c, this.c + "wallpaperHeight :" + this.M);
//      Log.v(this.c, this.c + "scaleHeight :" + f1);
//      Bitmap localBitmap1 = com.fotoable.helpr.wallpaper.w.a().d();
//      int i1 = localBitmap1.getWidth();
//      int i2 = localBitmap1.getHeight();
//      int i3 = getResources().getDisplayMetrics().widthPixels;
//      int i4 = getResources().getDisplayMetrics().heightPixels;
//      int i5 = this.n.getWidth();
//      int i6 = this.n.getHeight();
//      float f2 = i1 / i3;
//      float f3 = i2 / i4;
//      int i7 = (int)(f3 * (i4 - i6));
//      int i8 = (int)(f3 * i6);
//      int i9 = (int)(f2 * i5);
//      if (i7 + i8 > localBitmap1.getHeight()) {
//        i8 = localBitmap1.getHeight() - i7;
//      }
//      Bitmap localBitmap2 = Bitmap.createBitmap(localBitmap1, 0, i7, i9, i8, null, false);
//      this.J.setImageBitmap(localBitmap2);
//    }
//  }
//  
//  private void g()
//  {
//    int i1 = com.fotoable.helpr.Utils.k.f(this);
//    com.fotoable.helpr.Utils.k.g(this);
//    LinearLayout.LayoutParams localLayoutParams1 = (LinearLayout.LayoutParams)this.p.getLayoutParams();
//    localLayoutParams1.height = (i1 / 2 + com.fotoable.helpr.Utils.k.a(this, 15.0F));
//    this.p.setLayoutParams(localLayoutParams1);
//    LinearLayout.LayoutParams localLayoutParams2 = (LinearLayout.LayoutParams)this.o.getLayoutParams();
//    localLayoutParams2.height = (1 + (i1 / 2 + com.fotoable.helpr.Utils.k.a(this, 15.0F) + i1 / 9));
//    this.o.setLayoutParams(localLayoutParams2);
//  }
//  
//  private void h()
//  {
//    this.x.setVisibility(4);
//    this.w.setVisibility(4);
//    this.y.setVisibility(4);
//    this.z.setVisibility(4);
//  }
//  
//  private void i()
//  {
//    IntentFilter localIntentFilter = new IntentFilter();
//    localIntentFilter.addAction("LOCATION_DID_UPDATE_LOCATION_NOTIFICATION");
//    localIntentFilter.addAction("ApplicationWillEnterForegroundNoti");
//    localIntentFilter.addAction("android.intent.action.DATE_CHANGED");
//    localIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
//    localIntentFilter.addAction("HOMEWALLPAPER_CHANGE_NOTIFICATION");
//    localIntentFilter.addAction("NEW_VERSION_NOTIFICATION");
//    registerReceiver(this.k, localIntentFilter);
//  }
//  
//  private void j()
//  {
//    startActivity(new Intent(this, HelprSettingAcitivity.class));
//    overridePendingTransition(2130968579, 2130968589);
//  }
//  
//  private void k()
//  {
//    startActivity(new Intent(this, FlashLightActivity.class));
//    overridePendingTransition(2130968579, 2130968589);
//  }
//  
//  private void l()
//  {
//    com.fotoable.helpr.weather.j.a().a(null);
//    startActivity(new Intent(this, WeatherMainActivity.class));
//    overridePendingTransition(2130968579, 2130968589);
//  }
//  
//  private void m()
//  {
//    startActivity(new Intent(this, CalendarActivity.class));
//    overridePendingTransition(2130968579, 2130968589);
//  }
//  
//  private void n()
//  {
//    startActivity(new Intent(this, PMMainActivity.class));
//    overridePendingTransition(2130968579, 2130968589);
//  }
//  
//  private void o()
//  {
//    startActivity(new Intent(this, CameraMirrorActivity.class));
//    overridePendingTransition(2130968582, 2130968589);
//  }
//  
//  private void p()
//  {
//    startActivity(new Intent(this, CalculatorMainActivity.class));
//    overridePendingTransition(2130968579, 2130968589);
//  }
//  
//  private void q()
//  {
//    startActivity(new Intent(this, DaysMatterMainActivity.class));
//    overridePendingTransition(2130968579, 2130968589);
//  }
//  
//  private void r()
//  {
//    startActivity(new Intent(this, SizeTableActivity.class));
//    overridePendingTransition(2130968579, 2130968589);
//  }
//  
//  private void s()
//  {
//    startActivity(new Intent(this, RulerMainActivity.class));
//    overridePendingTransition(2130968579, 2130968589);
//  }
//  
//  private void t()
//  {
//    startActivity(new Intent(this, DictionaryMainActivity.class));
//    overridePendingTransition(2130968579, 2130968589);
//  }
//  
//  private void u()
//  {
//    startActivity(new Intent(this, QRScannerActivity.class));
//    overridePendingTransition(2130968579, 2130968589);
//  }
//  
//  private void v()
//  {
//    if (com.fotoable.helpr.lockpattern.a.a(this) == null)
//    {
//      startActivityForResult(new Intent(this, SetLockPatternActivity.class), 10001);
//      overridePendingTransition(2130968582, 2130968589);
//      return;
//    }
//    startActivityForResult(new Intent(this, VerifyLockPatternActivity.class), 10002);
//    overridePendingTransition(2130968582, 2130968589);
//  }
//  
//  private void w()
//  {
//    startActivity(new Intent(this, SecretAlbumActivity.class));
//  }
//  
//  private void x()
//  {
//    startActivity(new Intent(this, NovelMainActivity.class));
//    overridePendingTransition(2130968579, 2130968589);
//  }
//  
//  private void y()
//  {
//    startActivityForResult(new Intent(this, ConstelltionMainActivity.class), 10003);
//    overridePendingTransition(2130968579, 2130968589);
//  }
//  
//  private void z()
//  {
//    SecretMainActivity.a(this, NotePadMainActivity.a, 0);
//  }
//  
//  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
//  {
//    switch (paramInt1)
//    {
//    }
//    do
//    {
//      do
//      {
//        return;
//      } while (paramInt2 != -1);
//      w();
//      return;
//      if (paramInt2 == -1) {
//        w();
//      }
//    } while (paramInt2 != -1);
//    this.r.b();
//  }
  
  protected void onCreate(Bundle bundle) {
    super.onCreate(bundle);
    view = View.inflate(this, R.layout.activity_helpr_activity, null);
	if (HelprCommUtil.hasNavigationBar(this)) {
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
	}
    setContentView(view);
    
//    this.I = ((ImageView)findViewById(2131361805));
//    this.K = com.fotoable.helpr.wallpaper.w.a().f();
//    this.I.setImageBitmap(this.K);
//    com.crashlytics.android.e.a(this);
//    this.J = ((ImageView)findViewById(2131361947));
//    this.n = ((LinearLayout)findViewById(2131361948));
//    this.o = ((FrameLayout)findViewById(2131361946));
//    this.p = ((FrameLayout)findViewById(2131361949));
//    this.q = ((mainTabScrollView)findViewById(2131361962));
//    this.q.setListener(this.U);
//    g();
//    this.A = ((ViewPager)findViewById(2131361944));
//    this.B = ((ViewPager)findViewById(2131361951));
//    this.C = ((ViewPager)findViewById(2131361957));
//    this.D = ((ViewPager)findViewById(2131361954));
//    this.E = ((ViewPager)findViewById(2131361960));
//    this.w = ((LinearLayout)findViewById(2131361950));
//    this.x = ((LinearLayout)findViewById(2131361956));
//    this.y = ((LinearLayout)findViewById(2131361953));
//    this.z = ((LinearLayout)findViewById(2131361959));
//    this.N = ((EditText)findViewById(2131361942));
//    this.N.setOnTouchListener(new aa(this));
//    this.F = ((Button)findViewById(2131361943));
//    this.F.setOnClickListener(new ab(this));
//    this.J.getViewTreeObserver().addOnGlobalLayoutListener(new b(this));
//    i();
//    com.fotoable.helpr.a.b.a().b();
//    if (com.fotoable.helpr.Utils.h.a("autoHomeInfoLastPage", true)) {
//      G = com.fotoable.helpr.Utils.h.a("currentHomeInfoLastPage", 0);
//    }
//    for (;;)
//    {
//      e();
//      try
//      {
//        Y();
//        label407:
//        ab();
//        return;
//        G = com.fotoable.helpr.Utils.h.a("homeInfoLastPage", 0);
//      }
//      catch (Exception localException)
//      {
//        break label407;
//      }
//    }
  }
//  
//  protected void onDestroy()
//  {
//    unregisterReceiver(this.k);
//    if ((this.K != null) && (!this.K.isRecycled()))
//    {
//      this.I.setImageBitmap(null);
//      this.K.recycle();
//    }
//    super.onDestroy();
//  }
//  
//  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
//  {
//    if ((paramInt == 4) && (paramKeyEvent.getRepeatCount() == 0))
//    {
//      if ((this.r != null) && (this.r.d())) {
//        return true;
//      }
//      if ((this.O != null) && (this.O.getVisibility() == 0))
//      {
//        this.O.setVisibility(8);
//        return true;
//      }
//      if (!this.H)
//      {
//        Toast.makeText(this, 2131230852, 0).show();
//        this.H = true;
//        new Handler().postDelayed(new c(this), 2000L);
//        return true;
//      }
//      d();
//      return true;
//    }
//    return super.onKeyDown(paramInt, paramKeyEvent);
//  }
//  
//  protected void onResume()
//  {
//    super.onResume();
//    if ((this.r != null) && (G == 0)) {
//      this.r.c();
//    }
//    if ((this.r != null) && (G == 1)) {
//      this.r.a();
//    }
//    if ((this.r != null) && (G == 2)) {
//      this.r.b();
//    }
//    this.P = false;
//    if ((this.S != null) && (this.S.length() > 0))
//    {
//      String str1 = "HomeViewButtonClicked " + this.S;
//      HashMap localHashMap = new HashMap();
//      String str2 = com.fotoable.helpr.a.b.a().d().b;
//      if ((str2 != null) && (str2.length() > 0)) {
//        localHashMap.put("Place", str2);
//      }
//      localHashMap.put("itemName", this.S);
//      FlurryAgent.endTimedEvent(str1, localHashMap);
//      this.S = "";
//    }
//  }
}