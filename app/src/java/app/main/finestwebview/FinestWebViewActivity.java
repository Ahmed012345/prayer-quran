package com.zaker.android.sapeh.app.main.finestwebview;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.net.MailTo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.snackbar.Snackbar;
import com.marcoscg.materialtoast.MaterialToast;
import com.nineoldandroids.view.ViewHelper;
import com.thefinestartist.converters.UnitConverter;
import com.thefinestartist.utils.etc.APILevel;
import com.thefinestartist.utils.service.ClipboardManagerUtil;
import com.thefinestartist.utils.ui.DisplayUtil;
import com.thefinestartist.utils.ui.ViewUtil;
import com.zaker.android.sapeh.ARApplication;
import com.zaker.android.sapeh.R;
import com.zaker.android.sapeh.app.main.MainContract;
import com.zaker.android.sapeh.app.main.cookiebar.CookieBar;
import com.zaker.android.sapeh.app.main.finestwebview.enums.Position;
import com.zaker.android.sapeh.app.main.finestwebview.helpers.BitmapHelper;
import com.zaker.android.sapeh.app.main.finestwebview.helpers.ColorHelper;
import com.zaker.android.sapeh.app.main.finestwebview.helpers.TypefaceHelper;
import com.zaker.android.sapeh.app.main.finestwebview.helpers.UrlParser;
import com.zaker.android.sapeh.app.main.finestwebview.listeners.BroadCastManager;
import com.zaker.android.sapeh.app.main.finestwebview.views.ShadowLayout;
import com.zaker.android.sapeh.app.main.recordview.OnBasketAnimationEnd;
import com.zaker.android.sapeh.app.main.recordview.OnRecordClickListener;
import com.zaker.android.sapeh.app.main.recordview.OnRecordListener;
import com.zaker.android.sapeh.app.main.recordview.RecordButton;
import com.zaker.android.sapeh.app.main.recordview.RecordView;
import com.zaker.android.sapeh.util.AndroidUtils;

import java.util.concurrent.TimeUnit;

//MailTo Imports

/**
 * Created by Leonardo on 11/14/15.
 */
public class FinestWebViewActivity extends LocalizationActivity
        implements AppBarLayout.OnOffsetChangedListener, View.OnClickListener {

  protected int key;

  protected boolean rtl;
  protected int theme;

  protected int statusBarColor;

  protected int toolbarColor;
  protected int toolbarScrollFlags;

  protected int iconDefaultColor;
  protected int iconDisabledColor;
  protected int iconPressedColor;
  protected int iconSelector;

  protected boolean showIconClose;
  protected boolean disableIconClose;
  protected boolean showIconBack;
  protected boolean disableIconBack;
  protected boolean showIconForward;
  protected boolean disableIconForward;
  protected boolean showIconMenu;
  protected boolean disableIconMenu;

  protected boolean showSwipeRefreshLayout;
  protected int swipeRefreshColor;
  protected int[] swipeRefreshColors;

  protected boolean showDivider;
  protected boolean gradientDivider;
  protected int dividerColor;
  protected float dividerHeight;

  protected boolean showProgressBar;
  protected int progressBarColor;
  protected float progressBarHeight;
  protected Position progressBarPosition;

  protected String titleDefault;
  protected boolean updateTitleFromHtml;
  protected float titleSize;
  protected String titleFont;
  protected int titleColor;

  protected boolean showUrl;
  protected float urlSize;
  protected String urlFont;
  protected int urlColor;

  protected int menuColor;
  protected int menuDropShadowColor;
  protected float menuDropShadowSize;
  protected int menuSelector;

  protected float menuTextSize;
  protected String menuTextFont;
  protected int menuTextColor;

  protected int menuTextGravity;
  protected float menuTextPaddingLeft;
  protected float menuTextPaddingRight;

  protected boolean showMenuRefresh;
  protected int stringResRefresh;
  protected boolean showMenuFind;
  protected int stringResFind;
  protected boolean showMenuShareVia;
  protected int stringResShareVia;
  protected boolean showMenuCopyLink;
  protected int stringResCopyLink;
  protected boolean showMenuOpenWith;
  protected int stringResOpenWith;

  protected int animationCloseEnter;
  protected int animationCloseExit;

  protected boolean backPressToClose;
  protected int stringResCopiedToClipboard;

  protected Boolean webViewSupportZoom;
  protected Boolean webViewMediaPlaybackRequiresUserGesture;
  protected Boolean webViewBuiltInZoomControls;
  protected Boolean webViewDisplayZoomControls;
  protected Boolean webViewAllowFileAccess;
  protected Boolean webViewAllowContentAccess;
  protected Boolean webViewLoadWithOverviewMode;
  protected Boolean webViewSaveFormData;
  protected Integer webViewTextZoom;
  protected Boolean webViewUseWideViewPort;
  protected Boolean webViewSupportMultipleWindows;
  protected WebSettings.LayoutAlgorithm webViewLayoutAlgorithm;
  protected String webViewStandardFontFamily;
  protected String webViewFixedFontFamily;
  protected String webViewSansSerifFontFamily;
  protected String webViewSerifFontFamily;
  protected String webViewCursiveFontFamily;
  protected String webViewFantasyFontFamily;
  protected Integer webViewMinimumFontSize;
  protected Integer webViewMinimumLogicalFontSize;
  protected Integer webViewDefaultFontSize;
  protected Integer webViewDefaultFixedFontSize;
  protected Boolean webViewLoadsImagesAutomatically;
  protected Boolean webViewBlockNetworkImage;
  protected Boolean webViewBlockNetworkLoads;
  protected Boolean webViewJavaScriptEnabled;
  protected Boolean webViewAllowUniversalAccessFromFileURLs;
  protected Boolean webViewAllowFileAccessFromFileURLs;
  protected String webViewGeolocationDatabasePath;
  protected Boolean webViewAppCacheEnabled;
  protected String webViewAppCachePath;
  protected Boolean webViewDatabaseEnabled;
  protected Boolean webViewDomStorageEnabled;
  protected Boolean webViewGeolocationEnabled;
  protected Boolean webViewJavaScriptCanOpenWindowsAutomatically;
  protected String webViewDefaultTextEncodingName;
  protected String webViewUserAgentString;
  protected Boolean webViewNeedInitialFocus;
  protected Integer webViewCacheMode;
  protected Integer webViewMixedContentMode;
  protected Boolean webViewOffscreenPreRaster;

  protected String injectJavaScript;

  protected String mimeType;
  protected String encoding;
  protected String data;
  protected String url;
  protected CoordinatorLayout coordinatorLayout;
  protected AppBarLayout appBar;
  protected Toolbar toolbar;
  protected RelativeLayout toolbarLayout;
  protected TextView title;
  protected TextView urlTv;
  protected AppCompatImageButton close;
  protected AppCompatImageButton back;
  protected AppCompatImageButton forward;
  protected AppCompatImageButton more;
  protected SwipeRefreshLayout swipeRefreshLayout;
  protected WebView webView;
  protected View gradient;
  protected View divider;
  protected ProgressBar progressBar;
  protected RelativeLayout menuLayout;
  protected ShadowLayout shadowLayout;
  protected LinearLayout menuBackground;
  protected LinearLayout menuRefresh;
  protected TextView menuRefreshTv;
  protected LinearLayout menuFind;
  protected TextView menuFindTv;
  protected LinearLayout menuShareVia;
  protected TextView menuShareViaTv;
  protected LinearLayout menuCopyLink;
  protected TextView menuCopyLinkTv;
  protected LinearLayout menuOpenWith;
  protected TextView menuOpenWithTv;
  protected FrameLayout webLayout;
  DownloadListener downloadListener = new DownloadListener() {
    @Override
    public void onDownloadStart(String url, String userAgent, String contentDisposition,
                                String mimetype, long contentLength) {
      BroadCastManager.onDownloadStart(FinestWebViewActivity.this, key, url, userAgent,
              contentDisposition, mimetype, contentLength);
    }
  };

  protected void initializeOptions() {
    Intent intent = getIntent();
    if (intent == null) {
      return;
    }

    FinestWebView.Builder builder = (FinestWebView.Builder) intent.getSerializableExtra("builder");

    // set theme before resolving attributes depending on those
    setTheme(builder.theme != null ? builder.theme : 0);

    // resolve themed attributes
    TypedValue typedValue = new TypedValue();
    TypedArray typedArray = obtainStyledAttributes(typedValue.data, new int[]{
            R.attr.colorPrimaryDark, R.attr.colorPrimary, R.attr.colorAccent,
            android.R.attr.textColorPrimary, android.R.attr.textColorSecondary,
            android.R.attr.selectableItemBackground, android.R.attr.selectableItemBackgroundBorderless
    });
    int colorPrimaryDark = typedArray.getColor(0, ContextCompat.getColor(this, R.color.finestGray));
    int colorPrimary = typedArray.getColor(1, ContextCompat.getColor(this, R.color.finestWhite));
    int colorAccent = typedArray.getColor(2, ContextCompat.getColor(this, R.color.finestBlack));
    int textColorPrimary =
            typedArray.getColor(3, ContextCompat.getColor(this, R.color.finestBlack));
    int textColorSecondary =
            typedArray.getColor(4, ContextCompat.getColor(this, R.color.finestSilver));
    int selectableItemBackground =
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ? typedArray.getResourceId(5, 0)
                    : R.drawable.selector_light_theme;
    int selectableItemBackgroundBorderless =
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ? typedArray.getResourceId(6, 0)
                    : R.drawable.selector_light_theme;
    typedArray.recycle();

    key = builder.key;

    rtl = builder.rtl != null ? builder.rtl : getResources().getBoolean(R.bool.is_right_to_left);

    statusBarColor = builder.statusBarColor != null ? builder.statusBarColor : colorPrimaryDark;

    toolbarColor = builder.toolbarColor != null ? builder.toolbarColor : colorPrimary;
    toolbarScrollFlags = builder.toolbarScrollFlags != null ? builder.toolbarScrollFlags :
            AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                    | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS;

    iconDefaultColor = builder.iconDefaultColor != null ? builder.iconDefaultColor : colorAccent;
    iconDisabledColor = builder.iconDisabledColor != null ? builder.iconDisabledColor
            : ColorHelper.disableColor(iconDefaultColor);
    iconPressedColor =
            builder.iconPressedColor != null ? builder.iconPressedColor : iconDefaultColor;
    iconSelector =
            builder.iconSelector != null ? builder.iconSelector : selectableItemBackgroundBorderless;

    showIconClose = builder.showIconClose != null ? builder.showIconClose : true;
    disableIconClose = builder.disableIconClose != null ? builder.disableIconClose : false;
    showIconBack = builder.showIconBack != null ? builder.showIconBack : true;
    disableIconBack = builder.disableIconBack != null ? builder.disableIconBack : false;
    showIconForward = builder.showIconForward != null ? builder.showIconForward : true;
    disableIconForward = builder.disableIconForward != null ? builder.disableIconForward : false;
    showIconMenu = builder.showIconMenu != null ? builder.showIconMenu : true;
    disableIconMenu = builder.disableIconMenu != null ? builder.disableIconMenu : false;

    showSwipeRefreshLayout =
            builder.showSwipeRefreshLayout != null ? builder.showSwipeRefreshLayout : true;
    swipeRefreshColor = builder.swipeRefreshColor != null ? builder.swipeRefreshColor : colorAccent;
    if (builder.swipeRefreshColors != null) {
      int[] colors = new int[builder.swipeRefreshColors.length];
      for (int i = 0; i < builder.swipeRefreshColors.length; i++) {
        colors[i] = builder.swipeRefreshColors[i];
      }
      swipeRefreshColors = colors;
    }

    showDivider = builder.showDivider != null ? builder.showDivider : true;
    gradientDivider = builder.gradientDivider != null ? builder.gradientDivider : true;
    dividerColor = builder.dividerColor != null ? builder.dividerColor
            : ContextCompat.getColor(this, R.color.finestBlack10);
    dividerHeight = builder.dividerHeight != null ? builder.dividerHeight
            : getResources().getDimension(R.dimen.defaultDividerHeight);

    showProgressBar = builder.showProgressBar != null ? builder.showProgressBar : true;
    progressBarColor = builder.progressBarColor != null ? builder.progressBarColor : colorAccent;
    progressBarHeight = builder.progressBarHeight != null ? builder.progressBarHeight
            : getResources().getDimension(R.dimen.defaultProgressBarHeight);
    progressBarPosition = builder.progressBarPosition != null ? builder.progressBarPosition
            : Position.BOTTON_OF_TOOLBAR;

    titleDefault = builder.titleDefault;
    updateTitleFromHtml = builder.updateTitleFromHtml != null ? builder.updateTitleFromHtml : true;
    titleSize = builder.titleSize != null ? builder.titleSize
            : getResources().getDimension(R.dimen.defaultTitleSize);
    titleFont = builder.titleFont != null ? builder.titleFont : "Roboto-Medium.ttf";
    titleColor = builder.titleColor != null ? builder.titleColor : textColorPrimary;

    showUrl = builder.showUrl != null ? builder.showUrl : true;
    urlSize = builder.urlSize != null ? builder.urlSize
            : getResources().getDimension(R.dimen.defaultUrlSize);
    urlFont = builder.urlFont != null ? builder.urlFont : "Roboto-Regular.ttf";
    urlColor = builder.urlColor != null ? builder.urlColor : textColorSecondary;

    menuColor = builder.menuColor != null ? builder.menuColor
            : ContextCompat.getColor(this, R.color.finestWhite);
    menuDropShadowColor = builder.menuDropShadowColor != null ? builder.menuDropShadowColor
            : ContextCompat.getColor(this, R.color.finestBlack10);
    menuDropShadowSize = builder.menuDropShadowSize != null ? builder.menuDropShadowSize
            : getResources().getDimension(R.dimen.defaultMenuDropShadowSize);
    menuSelector = builder.menuSelector != null ? builder.menuSelector : selectableItemBackground;

    menuTextSize = builder.menuTextSize != null ? builder.menuTextSize
            : getResources().getDimension(R.dimen.defaultMenuTextSize);
    menuTextFont = builder.menuTextFont != null ? builder.menuTextFont : "Roboto-Regular.ttf";
    menuTextColor = builder.menuTextColor != null ? builder.menuTextColor
            : ContextCompat.getColor(this, R.color.finestBlack);

    menuTextGravity = builder.menuTextGravity != null ? builder.menuTextGravity
            : Gravity.CENTER_VERTICAL | Gravity.START;
    menuTextPaddingLeft = builder.menuTextPaddingLeft != null ? builder.menuTextPaddingLeft
            : rtl ? getResources().getDimension(R.dimen.defaultMenuTextPaddingRight)
            : getResources().getDimension(R.dimen.defaultMenuTextPaddingLeft);
    menuTextPaddingRight = builder.menuTextPaddingRight != null ? builder.menuTextPaddingRight
            : rtl ? getResources().getDimension(R.dimen.defaultMenuTextPaddingLeft)
            : getResources().getDimension(R.dimen.defaultMenuTextPaddingRight);

    showMenuRefresh = builder.showMenuRefresh != null ? builder.showMenuRefresh : true;
    stringResRefresh =
            builder.stringResRefresh != null ? builder.stringResRefresh : R.string.refresh;
    showMenuFind = builder.showMenuFind != null ? builder.showMenuFind : false;
    stringResFind = builder.stringResFind != null ? builder.stringResFind : R.string.find;
    showMenuShareVia = builder.showMenuShareVia != null ? builder.showMenuShareVia : true;
    stringResShareVia =
            builder.stringResShareVia != null ? builder.stringResShareVia : R.string.share_via;
    showMenuCopyLink = builder.showMenuCopyLink != null ? builder.showMenuCopyLink : true;
    stringResCopyLink =
            builder.stringResCopyLink != null ? builder.stringResCopyLink : R.string.copy_link;
    showMenuOpenWith = builder.showMenuOpenWith != null ? builder.showMenuOpenWith : true;
    stringResOpenWith =
            builder.stringResOpenWith != null ? builder.stringResOpenWith : R.string.open_with;

    animationCloseEnter = builder.animationCloseEnter != null ? builder.animationCloseEnter
            : R.anim.modal_activity_close_enter;
    animationCloseExit = builder.animationCloseExit != null ? builder.animationCloseExit
            : R.anim.modal_activity_close_exit;

    backPressToClose = builder.backPressToClose != null ? builder.backPressToClose : false;
    stringResCopiedToClipboard =
            builder.stringResCopiedToClipboard != null ? builder.stringResCopiedToClipboard
                    : R.string.copied_to_clipboard;

    webViewSupportZoom = builder.webViewSupportZoom;
    webViewMediaPlaybackRequiresUserGesture = builder.webViewMediaPlaybackRequiresUserGesture;
    webViewBuiltInZoomControls =
            builder.webViewBuiltInZoomControls != null ? builder.webViewBuiltInZoomControls : false;
    webViewDisplayZoomControls =
            builder.webViewDisplayZoomControls != null ? builder.webViewDisplayZoomControls : false;
    webViewAllowFileAccess =
            builder.webViewAllowFileAccess != null ? builder.webViewAllowFileAccess : true;
    webViewAllowContentAccess = builder.webViewAllowContentAccess;
    webViewLoadWithOverviewMode =
            builder.webViewLoadWithOverviewMode != null ? builder.webViewLoadWithOverviewMode : true;
    webViewSaveFormData = builder.webViewSaveFormData;
    webViewTextZoom = builder.webViewTextZoom;
    webViewUseWideViewPort = builder.webViewUseWideViewPort;
    webViewSupportMultipleWindows = builder.webViewSupportMultipleWindows;
    webViewLayoutAlgorithm = builder.webViewLayoutAlgorithm;
    webViewStandardFontFamily = builder.webViewStandardFontFamily;
    webViewFixedFontFamily = builder.webViewFixedFontFamily;
    webViewSansSerifFontFamily = builder.webViewSansSerifFontFamily;
    webViewSerifFontFamily = builder.webViewSerifFontFamily;
    webViewCursiveFontFamily = builder.webViewCursiveFontFamily;
    webViewFantasyFontFamily = builder.webViewFantasyFontFamily;
    webViewMinimumFontSize = builder.webViewMinimumFontSize;
    webViewMinimumLogicalFontSize = builder.webViewMinimumLogicalFontSize;
    webViewDefaultFontSize = builder.webViewDefaultFontSize;
    webViewDefaultFixedFontSize = builder.webViewDefaultFixedFontSize;
    webViewLoadsImagesAutomatically = builder.webViewLoadsImagesAutomatically;
    webViewBlockNetworkImage = builder.webViewBlockNetworkImage;
    webViewBlockNetworkLoads = builder.webViewBlockNetworkLoads;
    webViewJavaScriptEnabled =
            builder.webViewJavaScriptEnabled != null ? builder.webViewJavaScriptEnabled : true;
    webViewAllowUniversalAccessFromFileURLs = builder.webViewAllowUniversalAccessFromFileURLs;
    webViewAllowFileAccessFromFileURLs = builder.webViewAllowFileAccessFromFileURLs;
    webViewGeolocationDatabasePath = builder.webViewGeolocationDatabasePath;
    webViewAppCacheEnabled =
            builder.webViewAppCacheEnabled != null ? builder.webViewAppCacheEnabled : true;
    webViewAppCachePath = builder.webViewAppCachePath;
    webViewDatabaseEnabled = builder.webViewDatabaseEnabled;
    webViewDomStorageEnabled =
            builder.webViewDomStorageEnabled != null ? builder.webViewDomStorageEnabled : true;
    webViewGeolocationEnabled = builder.webViewGeolocationEnabled;
    webViewJavaScriptCanOpenWindowsAutomatically =
            builder.webViewJavaScriptCanOpenWindowsAutomatically;
    webViewDefaultTextEncodingName = builder.webViewDefaultTextEncodingName;
    webViewUserAgentString = builder.webViewUserAgentString;
    webViewNeedInitialFocus = builder.webViewNeedInitialFocus;
    webViewCacheMode = builder.webViewCacheMode;
    webViewMixedContentMode = builder.webViewMixedContentMode;
    webViewOffscreenPreRaster = builder.webViewOffscreenPreRaster;

    injectJavaScript = builder.injectJavaScript;

    mimeType = builder.mimeType;
    encoding = builder.encoding;
    data = builder.data;
    url = builder.url;
  }

  protected void bindViews() {
    coordinatorLayout = findViewById(R.id.coordinatorLayout);

    appBar = findViewById(R.id.appBar);
    toolbar = findViewById(R.id.toolbar);
    toolbarLayout = findViewById(R.id.toolbarLayout);

    title = findViewById(R.id.title);
    urlTv = findViewById(R.id.url);

    close = findViewById(R.id.close);
    back = findViewById(R.id.back);
    forward = findViewById(R.id.forward);
    more = findViewById(R.id.more);

    close.setOnClickListener(this);
    back.setOnClickListener(this);
    forward.setOnClickListener(this);
    more.setOnClickListener(this);

    swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

    gradient = findViewById(R.id.gradient);
    divider = findViewById(R.id.divider);
    progressBar = findViewById(R.id.progressBar);

    menuLayout = findViewById(R.id.menuLayout);
    shadowLayout = findViewById(R.id.shadowLayout);
    menuBackground = findViewById(R.id.menuBackground);

    menuRefresh = findViewById(R.id.menuRefresh);
    menuRefreshTv = findViewById(R.id.menuRefreshTv);
    menuFind = findViewById(R.id.menuFind);
    menuFindTv = findViewById(R.id.menuFindTv);
    menuShareVia = findViewById(R.id.menuShareVia);
    menuShareViaTv = findViewById(R.id.menuShareViaTv);
    menuCopyLink = findViewById(R.id.menuCopyLink);
    menuCopyLinkTv = findViewById(R.id.menuCopyLinkTv);
    menuOpenWith = findViewById(R.id.menuOpenWith);
    menuOpenWithTv = findViewById(R.id.menuOpenWithTv);

    webLayout = findViewById(R.id.webLayout);
    webView = new WebView(this);
    webLayout.addView(webView);
  }

  protected void layoutViews() {
    setSupportActionBar(toolbar);

    { // AppBar
      float toolbarHeight = getResources().getDimension(R.dimen.toolbarHeight);
      if (!gradientDivider) {
        toolbarHeight += dividerHeight;
      }
      CoordinatorLayout.LayoutParams params =
              new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                      (int) toolbarHeight);
      appBar.setLayoutParams(params);
      coordinatorLayout.requestLayout();
    }

    { // Toolbar
      float toolbarHeight = getResources().getDimension(R.dimen.toolbarHeight);
      LinearLayout.LayoutParams params =
              new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) toolbarHeight);
      toolbarLayout.setMinimumHeight((int) toolbarHeight);
      toolbarLayout.setLayoutParams(params);
      coordinatorLayout.requestLayout();
    }

    { // TextViews
      int maxWidth = getMaxWidth();
      title.setMaxWidth(maxWidth);
      urlTv.setMaxWidth(maxWidth);
      requestCenterLayout();
    }

    { // Icons
      updateIcon(close, rtl ? R.drawable.more : R.drawable.close);
      updateIcon(back, R.drawable.back);
      updateIcon(forward, R.drawable.forward);
      updateIcon(more, rtl ? R.drawable.close : R.drawable.more);
    }

    { // Divider
      if (gradientDivider) {
        float toolbarHeight = getResources().getDimension(R.dimen.toolbarHeight);
        CoordinatorLayout.LayoutParams params =
                (CoordinatorLayout.LayoutParams) gradient.getLayoutParams();
        params.setMargins(0, (int) toolbarHeight, 0, 0);
        gradient.setLayoutParams(params);
      }
    }

    { // ProgressBar
      progressBar.setMinimumHeight((int) progressBarHeight);
      CoordinatorLayout.LayoutParams params =
              new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                      (int) progressBarHeight);
      float toolbarHeight = getResources().getDimension(R.dimen.toolbarHeight);
      switch (progressBarPosition) {
        case TOP_OF_TOOLBAR:
          params.setMargins(0, 0, 0, 0);
          break;
        case BOTTON_OF_TOOLBAR:
          params.setMargins(0, (int) toolbarHeight - (int) progressBarHeight, 0, 0);
          break;
        case TOP_OF_WEBVIEW:
          params.setMargins(0, (int) toolbarHeight, 0, 0);
          break;
        case BOTTOM_OF_WEBVIEW:
          params.setMargins(0, DisplayUtil.getHeight() - (int) progressBarHeight, 0, 0);
          break;
      }
      progressBar.setLayoutParams(params);
    }

    { // WebLayout
      float toolbarHeight = getResources().getDimension(R.dimen.toolbarHeight);
      int statusBarHeight = DisplayUtil.getStatusBarHeight();
      int screenHeight = DisplayUtil.getHeight();
      float webLayoutMinimumHeight = screenHeight - toolbarHeight - statusBarHeight;
      if (showDivider && !gradientDivider) {
        webLayoutMinimumHeight -= dividerHeight;
      }
      webLayout.setMinimumHeight((int) webLayoutMinimumHeight);
    }
  }

  @SuppressLint("SetJavaScriptEnabled")
  protected void initializeViews() {
    setSupportActionBar(toolbar);

    { // StatusBar
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(statusBarColor);
      }
    }

    { // AppBar
      appBar.addOnOffsetChangedListener(this);
    }

    { // Toolbar
      toolbar.setBackgroundColor(toolbarColor);
      AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
      params.setScrollFlags(toolbarScrollFlags);
      toolbar.setLayoutParams(params);
    }

    { // TextViews
      title.setText(titleDefault);
      title.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleSize);
      title.setTypeface(TypefaceHelper.get(this, titleFont));
      title.setTextColor(titleColor);

      urlTv.setVisibility(showUrl ? View.VISIBLE : View.GONE);
      urlTv.setText(UrlParser.getHost(url));
      urlTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, urlSize);
      urlTv.setTypeface(TypefaceHelper.get(this, urlFont));
      urlTv.setTextColor(urlColor);

      requestCenterLayout();
    }

    { // Icons
      close.setBackgroundResource(iconSelector);
      back.setBackgroundResource(iconSelector);
      forward.setBackgroundResource(iconSelector);
      more.setBackgroundResource(iconSelector);

      close.setVisibility(showIconClose ? View.VISIBLE : View.GONE);
      close.setEnabled(!disableIconClose);

      if ((showMenuRefresh
              || showMenuFind
              || showMenuShareVia
              || showMenuCopyLink
              || showMenuOpenWith) && showIconMenu) {
        more.setVisibility(View.VISIBLE);
      } else {
        more.setVisibility(View.GONE);
      }
      more.setEnabled(!disableIconMenu);
    }

    { // WebView
      webView.setWebChromeClient(new MyWebChromeClient());
      webView.setWebViewClient(new MyWebViewClient());
      webView.setDownloadListener(downloadListener);

      WebSettings settings = webView.getSettings();

      if (webViewSupportZoom != null) {
        settings.setSupportZoom(webViewSupportZoom);
      }
      if (webViewMediaPlaybackRequiresUserGesture != null
              && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        settings.setMediaPlaybackRequiresUserGesture(webViewMediaPlaybackRequiresUserGesture);
      }
      if (webViewBuiltInZoomControls != null) {
        settings.setBuiltInZoomControls(webViewBuiltInZoomControls);

        if (webViewBuiltInZoomControls) {
          // Remove NestedScrollView to enable BuiltInZoomControls
          ((ViewGroup) webView.getParent()).removeAllViews();
          swipeRefreshLayout.addView(webView);
          swipeRefreshLayout.removeViewAt(1);
        }
      }
      if (webViewDisplayZoomControls != null
              && Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
        settings.setDisplayZoomControls(webViewDisplayZoomControls);
      }

      if (webViewAllowFileAccess != null) {
        settings.setAllowFileAccess(webViewAllowFileAccess);
      }
      if (webViewAllowContentAccess != null
              && Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
        settings.setAllowContentAccess(webViewAllowContentAccess);
      }
      if (webViewLoadWithOverviewMode != null) {
        settings.setLoadWithOverviewMode(webViewLoadWithOverviewMode);
      }
      if (webViewSaveFormData != null) {
        settings.setSaveFormData(webViewSaveFormData);
      }
      if (webViewTextZoom != null
              && Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
        settings.setTextZoom(webViewTextZoom);
      }
      if (webViewUseWideViewPort != null) {
        settings.setUseWideViewPort(webViewUseWideViewPort);
      }
      if (webViewSupportMultipleWindows != null) {
        settings.setSupportMultipleWindows(webViewSupportMultipleWindows);
      }
      if (webViewLayoutAlgorithm != null) {
        settings.setLayoutAlgorithm(webViewLayoutAlgorithm);
      }
      if (webViewStandardFontFamily != null) {
        settings.setStandardFontFamily(webViewStandardFontFamily);
      }
      if (webViewFixedFontFamily != null) {
        settings.setFixedFontFamily(webViewFixedFontFamily);
      }
      if (webViewSansSerifFontFamily != null) {
        settings.setSansSerifFontFamily(webViewSansSerifFontFamily);
      }
      if (webViewSerifFontFamily != null) {
        settings.setSerifFontFamily(webViewSerifFontFamily);
      }
      if (webViewCursiveFontFamily != null) {
        settings.setCursiveFontFamily(webViewCursiveFontFamily);
      }
      if (webViewFantasyFontFamily != null) {
        settings.setFantasyFontFamily(webViewFantasyFontFamily);
      }
      if (webViewMinimumFontSize != null) {
        settings.setMinimumFontSize(webViewMinimumFontSize);
      }
      if (webViewMinimumLogicalFontSize != null) {
        settings.setMinimumLogicalFontSize(webViewMinimumLogicalFontSize);
      }
      if (webViewDefaultFontSize != null) {
        settings.setDefaultFontSize(webViewDefaultFontSize);
      }
      if (webViewDefaultFixedFontSize != null) {
        settings.setDefaultFixedFontSize(webViewDefaultFixedFontSize);
      }
      if (webViewLoadsImagesAutomatically != null) {
        settings.setLoadsImagesAutomatically(webViewLoadsImagesAutomatically);
      }
      if (webViewBlockNetworkImage != null) {
        settings.setBlockNetworkImage(webViewBlockNetworkImage);
      }
      if (webViewBlockNetworkLoads != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
        settings.setBlockNetworkLoads(webViewBlockNetworkLoads);
      }
      if (webViewJavaScriptEnabled != null) {
        settings.setJavaScriptEnabled(webViewJavaScriptEnabled);
      }
      if (webViewAllowUniversalAccessFromFileURLs != null
              && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        settings.setAllowUniversalAccessFromFileURLs(webViewAllowUniversalAccessFromFileURLs);
      }
      if (webViewAllowFileAccessFromFileURLs != null
              && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        settings.setAllowFileAccessFromFileURLs(webViewAllowFileAccessFromFileURLs);
      }
      if (webViewGeolocationDatabasePath != null) {
        settings.setGeolocationDatabasePath(webViewGeolocationDatabasePath);
      }
      if (webViewAppCacheEnabled != null) {
        settings.setAppCacheEnabled(webViewAppCacheEnabled);
      }
      if (webViewAppCachePath != null) {
        settings.setAppCachePath(webViewAppCachePath);
      }
      if (webViewDatabaseEnabled != null) {
        settings.setDatabaseEnabled(webViewDatabaseEnabled);
      }
      if (webViewDomStorageEnabled != null) {
        settings.setDomStorageEnabled(webViewDomStorageEnabled);
      }
      if (webViewGeolocationEnabled != null) {
        settings.setGeolocationEnabled(webViewGeolocationEnabled);
      }
      if (webViewJavaScriptCanOpenWindowsAutomatically != null) {
        settings.setJavaScriptCanOpenWindowsAutomatically(
                webViewJavaScriptCanOpenWindowsAutomatically);
      }
      if (webViewDefaultTextEncodingName != null) {
        settings.setDefaultTextEncodingName(webViewDefaultTextEncodingName);
      }
      if (webViewUserAgentString != null) {
        settings.setUserAgentString(webViewUserAgentString);
      }
      if (webViewNeedInitialFocus != null) {
        settings.setNeedInitialFocus(webViewNeedInitialFocus);
      }
      if (webViewCacheMode != null) {
        settings.setCacheMode(webViewCacheMode);
      }
      if (webViewMixedContentMode != null
              && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        settings.setMixedContentMode(webViewMixedContentMode);
      }
      if (webViewOffscreenPreRaster != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        settings.setOffscreenPreRaster(webViewOffscreenPreRaster);
      }

      //            // Other webview options
      //            webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
      //            webView.setScrollbarFadingEnabled(false);
      //            //Additional Webview Properties
      //            webView.setSoundEffectsEnabled(true);
      //            webView.setHorizontalFadingEdgeEnabled(false);
      //            webView.setKeepScreenOn(true);
      //            webView.setScrollbarFadingEnabled(true);
      //            webView.setVerticalFadingEdgeEnabled(false);

      if (data != null) {
        webView.loadData(data, mimeType, encoding);
      } else if (url != null) {
        webView.loadUrl(url);
      }
    }

    { // SwipeRefreshLayout
      swipeRefreshLayout.setEnabled(showSwipeRefreshLayout);
      if (showSwipeRefreshLayout) {
        swipeRefreshLayout.post(new Runnable() {
          @Override
          public void run() {
            swipeRefreshLayout.setRefreshing(true);
          }
        });
      }

      if (swipeRefreshColors == null) {
        swipeRefreshLayout.setColorSchemeColors(swipeRefreshColor);
      } else {
        swipeRefreshLayout.setColorSchemeColors(swipeRefreshColors);
      }

      swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
          webView.reload();
        }
      });
    }

    { // Divider
      gradient.setVisibility(showDivider && gradientDivider ? View.VISIBLE : View.GONE);
      divider.setVisibility(showDivider && !gradientDivider ? View.VISIBLE : View.GONE);
      if (gradientDivider) {
        int dividerWidth = DisplayUtil.getWidth();
        Bitmap bitmap =
                BitmapHelper.getGradientBitmap(dividerWidth, (int) dividerHeight, dividerColor);
        BitmapDrawable drawable = new BitmapDrawable(getResources(), bitmap);
        ViewUtil.setBackground(gradient, drawable);

        CoordinatorLayout.LayoutParams params =
                (CoordinatorLayout.LayoutParams) gradient.getLayoutParams();
        params.height = (int) dividerHeight;
        gradient.setLayoutParams(params);
      } else {
        divider.setBackgroundColor(dividerColor);

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) divider.getLayoutParams();
        params.height = (int) dividerHeight;
        divider.setLayoutParams(params);
      }
    }

    { // ProgressBar
      progressBar.setVisibility(showProgressBar ? View.VISIBLE : View.GONE);
      progressBar.getProgressDrawable().setColorFilter(progressBarColor, PorterDuff.Mode.SRC_IN);
      progressBar.setMinimumHeight((int) progressBarHeight);
      CoordinatorLayout.LayoutParams params =
              new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                      (int) progressBarHeight);
      float toolbarHeight = getResources().getDimension(R.dimen.toolbarHeight);
      switch (progressBarPosition) {
        case TOP_OF_TOOLBAR:
          params.setMargins(0, 0, 0, 0);
          break;
        case BOTTON_OF_TOOLBAR:
          params.setMargins(0, (int) toolbarHeight - (int) progressBarHeight, 0, 0);
          break;
        case TOP_OF_WEBVIEW:
          params.setMargins(0, (int) toolbarHeight, 0, 0);
          break;
        case BOTTOM_OF_WEBVIEW:
          params.setMargins(0, DisplayUtil.getHeight() - (int) progressBarHeight, 0, 0);
          break;
      }
      progressBar.setLayoutParams(params);
    }

    { // Menu
      GradientDrawable drawable = new GradientDrawable();
      drawable.setCornerRadius(getResources().getDimension(R.dimen.defaultMenuCornerRadius));
      drawable.setColor(menuColor);
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        menuBackground.setBackground(drawable);
      } else {
        menuBackground.setBackgroundDrawable(drawable);
      }

      shadowLayout.setShadowColor(menuDropShadowColor);
      shadowLayout.setShadowSize(menuDropShadowSize);

      RelativeLayout.LayoutParams params =
              new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                      ViewGroup.LayoutParams.WRAP_CONTENT);
      int margin =
              (int) (getResources().getDimension(R.dimen.defaultMenuLayoutMargin) - menuDropShadowSize);
      params.setMargins(0, margin, margin, 0);
      params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
      params.addRule(rtl ? RelativeLayout.ALIGN_PARENT_LEFT : RelativeLayout.ALIGN_PARENT_RIGHT);
      shadowLayout.setLayoutParams(params);

      menuRefresh.setVisibility(showMenuRefresh ? View.VISIBLE : View.GONE);
      menuRefresh.setBackgroundResource(menuSelector);
      menuRefresh.setGravity(menuTextGravity);
      menuRefreshTv.setText(stringResRefresh);
      menuRefreshTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, menuTextSize);
      menuRefreshTv.setTypeface(TypefaceHelper.get(this, menuTextFont));
      menuRefreshTv.setTextColor(menuTextColor);
      menuRefreshTv.setPadding((int) menuTextPaddingLeft, 0, (int) menuTextPaddingRight, 0);

      menuFind.setVisibility(showMenuFind ? View.VISIBLE : View.GONE);
      menuFind.setBackgroundResource(menuSelector);
      menuFind.setGravity(menuTextGravity);
      menuFindTv.setText(stringResFind);
      menuFindTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, menuTextSize);
      menuFindTv.setTypeface(TypefaceHelper.get(this, menuTextFont));
      menuFindTv.setTextColor(menuTextColor);
      menuFindTv.setPadding((int) menuTextPaddingLeft, 0, (int) menuTextPaddingRight, 0);

      menuShareVia.setVisibility(showMenuShareVia ? View.VISIBLE : View.GONE);
      menuShareVia.setBackgroundResource(menuSelector);
      menuShareVia.setGravity(menuTextGravity);
      menuShareViaTv.setText(stringResShareVia);
      menuShareViaTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, menuTextSize);
      menuShareViaTv.setTypeface(TypefaceHelper.get(this, menuTextFont));
      menuShareViaTv.setTextColor(menuTextColor);
      menuShareViaTv.setPadding((int) menuTextPaddingLeft, 0, (int) menuTextPaddingRight, 0);

      menuCopyLink.setVisibility(showMenuCopyLink ? View.VISIBLE : View.GONE);
      menuCopyLink.setBackgroundResource(menuSelector);
      menuCopyLink.setGravity(menuTextGravity);
      menuCopyLinkTv.setText(stringResCopyLink);
      menuCopyLinkTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, menuTextSize);
      menuCopyLinkTv.setTypeface(TypefaceHelper.get(this, menuTextFont));
      menuCopyLinkTv.setTextColor(menuTextColor);
      menuCopyLinkTv.setPadding((int) menuTextPaddingLeft, 0, (int) menuTextPaddingRight, 0);

      menuOpenWith.setVisibility(showMenuOpenWith ? View.VISIBLE : View.GONE);
      menuOpenWith.setBackgroundResource(menuSelector);
      menuOpenWith.setGravity(menuTextGravity);
      menuOpenWithTv.setText(stringResOpenWith);
      menuOpenWithTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, menuTextSize);
      menuOpenWithTv.setTypeface(TypefaceHelper.get(this, menuTextFont));
      menuOpenWithTv.setTextColor(menuTextColor);
      menuOpenWithTv.setPadding((int) menuTextPaddingLeft, 0, (int) menuTextPaddingRight, 0);
    }
  }

  protected int getMaxWidth() {
    if (forward.getVisibility() == View.VISIBLE) {
      return DisplayUtil.getWidth() - UnitConverter.dpToPx(100);
    } else {
      return DisplayUtil.getWidth() - UnitConverter.dpToPx(52);
    }
  }

  protected void updateIcon(ImageButton icon, @DrawableRes int drawableRes) {
    StateListDrawable states = new StateListDrawable();
    {
      Bitmap bitmap = BitmapHelper.getColoredBitmap(this, drawableRes, iconPressedColor);
      BitmapDrawable drawable = new BitmapDrawable(getResources(), bitmap);
      states.addState(new int[]{android.R.attr.state_pressed}, drawable);
    }
    {
      Bitmap bitmap = BitmapHelper.getColoredBitmap(this, drawableRes, iconDisabledColor);
      BitmapDrawable drawable = new BitmapDrawable(getResources(), bitmap);
      states.addState(new int[]{-android.R.attr.state_enabled}, drawable);
    }
    {
      Bitmap bitmap = BitmapHelper.getColoredBitmap(this, drawableRes, iconDefaultColor);
      BitmapDrawable drawable = new BitmapDrawable(getResources(), bitmap);
      states.addState(new int[]{}, drawable);
    }
    icon.setImageDrawable(states);

    //        int[][] states = new int[][]{
    //                new int[]{-android.R.attr.state_enabled}, // disabled
    //                new int[]{android.R.attr.state_pressed}, // pressed
    //                new int[]{}  // default
    //        };
    //
    //        int[] colors = new int[]{
    //                iconDisabledColor,
    //                iconPressedColor,
    //                iconDefaultColor
    //        };
    //
    //        ColorStateList colorStateList = new ColorStateList(states, colors);
    //
    //        Drawable drawable = ContextCompat.getDrawable(this, drawableRes);
    //        if (APILevel.require(21)) {
    //            VectorDrawable vectorDrawable = (VectorDrawable) drawable;
    //            vectorDrawable.setTintList(colorStateList);
    //            icon.setImageDrawable(vectorDrawable);
    //        } else {
    //            VectorDrawableCompat vectorDrawable = (VectorDrawableCompat) drawable;
    //            vectorDrawable.setTintList(colorStateList);
    //            icon.setImageDrawable(vectorDrawable);
    //        }
  }

  public static final int REQ_CODE_REC_AUDIO_AND_WRITE_EXTERNAL = 101;
  public static final int REQ_CODE_RECORD_AUDIO = 303;
  public static final int REQ_CODE_WRITE_EXTERNAL_STORAGE = 404;
  public static final int REQ_CODE_READ_EXTERNAL_STORAGE_IMPORT = 405;
  public static final int REQ_CODE_READ_EXTERNAL_STORAGE_PLAYBACK = 406;
  public static final int REQ_CODE_IMPORT_AUDIO = 11;

  private MainContract.UserActionsListener presenter;

  RecordView recordView;
  RecordButton recordButton;

  Context context = this;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initializeOptions();

    setContentView(R.layout.finest_web_view);
    bindViews();
    layoutViews();
    initializeViews();
    presenter = ARApplication.getInjector().provideMainPresenter();


    recordView = findViewById(R.id.record_view);
    recordButton = findViewById(R.id.record_button);
    //IMPORTANT
    recordButton.setRecordView(recordView);


    //ListenForRecord must be false ,otherwise onClick will not be called
    recordButton.setOnRecordClickListener(new OnRecordClickListener() {
      @Override
      public void onClick(View v) {
        Log.d("RecordButton", "RECORD BUTTON CLICKED");

      }
    });

    //Cancel Bounds is when the Slide To Cancel text gets before the timer . default is 8
    recordView.setCancelBounds(8);
    recordView.setSmallMicColor(Color.parseColor("#c2185b"));
    recordView.setSlideToCancelText(getResources().getString(R.string.slide_to_cancel));
    //disable Sounds
    recordView.setSoundEnabled(true);
    //prevent recording under one Second (it's false by default)
    recordView.setLessThanSecondAllowed(false);
    //change slide To Cancel Text Color
    recordView.setSlideToCancelTextColor(getResources().getColor(R.color.gray_light));
    //change slide To Cancel Arrow Color
    recordView.setSlideToCancelArrowColor(getResources().getColor(R.color.gray_light));
    //change Counter Time (Chronometer) color
    recordView.setCounterTimeColor(getResources().getColor(R.color.gray_dark));
    recordView.setCustomSounds(R.raw.record_start, R.raw.record_finished, R.raw.record_error);


    recordView.setOnRecordListener(new OnRecordListener() {
      @Override
      public void onStart() {
        Log.d("RecordView", "onStart");

        if (checkRecordPermission2()) {
          if (checkStoragePermission2()) {
            //Start or stop recording
            presenter.startRecording(getApplicationContext());
          }
        }

      }

      @Override
      public void onCancel() {
        presenter.cancelRecording();
        presenter.deleteActiveRecord(true);

      }

      @Override
      public void onFinish(long recordTime) {
        String time = getHumanTimeText(recordTime);
        new MaterialToast(context)
                .setMessage(getResources().getString(R.string.recordtoast) + time)
                .setIcon(R.drawable.time)
                .setDuration(Toast.LENGTH_LONG)
                .setBackgroundColor(R.color.settings_title_bg)
                .show();
        Log.d("RecordView", "onFinish");

        presenter.stopRecording(false);


      }


      @Override
      public void onLessThanSecond() {
        Log.d("RecordView", "onLessThanSecond");
      }
    });


    recordView.setOnBasketAnimationEndListener(new OnBasketAnimationEnd() {
      @Override
      public void onAnimationEnd() {
        Log.d("RecordView", "Basket Animation Finished");
      }
    });

  }

  @SuppressLint("DefaultLocale")
  private String getHumanTimeText(long milliseconds) {
    return String.format("%02d:%02d",
            TimeUnit.MILLISECONDS.toMinutes(milliseconds),
            TimeUnit.MILLISECONDS.toSeconds(milliseconds) -
                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliseconds)));
  }

  public void startFileSelector() {
    Intent intent_upload = new Intent();
    intent_upload.setType("audio/*");
    intent_upload.addCategory(Intent.CATEGORY_OPENABLE);
//		intent_upload.setAction(Intent.ACTION_GET_CONTENT);
    intent_upload.setAction(Intent.ACTION_OPEN_DOCUMENT);
    startActivityForResult(intent_upload, REQ_CODE_IMPORT_AUDIO);
  }

  private boolean checkRecordPermission2() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      if (checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
        requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO}, REQ_CODE_RECORD_AUDIO);
        return false;
      }
    }
    return true;
  }

  private boolean checkStoragePermission2() {
    if (presenter.isStorePublic()) {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
          AndroidUtils.showDialog(this, R.string.warning, R.string.need_write_permission,
                  new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      requestPermissions(
                              new String[]{
                                      Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                      Manifest.permission.READ_EXTERNAL_STORAGE},
                              REQ_CODE_WRITE_EXTERNAL_STORAGE);
                    }
                  }, null
//							new View.OnClickListener() {
//								@Override
//								public void onClick(View v) {
//									presenter.setStoragePrivate(getApplicationContext());
//									presenter.startRecording();
//								}
//							}
          );
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    if (requestCode == REQ_CODE_REC_AUDIO_AND_WRITE_EXTERNAL && grantResults.length > 0
            && grantResults[0] == PackageManager.PERMISSION_GRANTED
            && grantResults[1] == PackageManager.PERMISSION_GRANTED
            && grantResults[2] == PackageManager.PERMISSION_GRANTED) {
      presenter.startRecording(getApplicationContext());
    } else if (requestCode == REQ_CODE_RECORD_AUDIO && grantResults.length > 0
            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
      if (checkStoragePermission2()) {
        presenter.startRecording(getApplicationContext());
      }
    } else if (requestCode == REQ_CODE_WRITE_EXTERNAL_STORAGE && grantResults.length > 0
            && grantResults[0] == PackageManager.PERMISSION_GRANTED
            && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
      if (checkRecordPermission2()) {
        presenter.startRecording(getApplicationContext());
      }
    } else if (requestCode == REQ_CODE_READ_EXTERNAL_STORAGE_IMPORT && grantResults.length > 0
            && grantResults[0] == PackageManager.PERMISSION_GRANTED
            && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
      startFileSelector();
    } else if (requestCode == REQ_CODE_READ_EXTERNAL_STORAGE_PLAYBACK && grantResults.length > 0
            && grantResults[0] == PackageManager.PERMISSION_GRANTED
            && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
      presenter.startPlayback();
    } else if (requestCode == REQ_CODE_WRITE_EXTERNAL_STORAGE && grantResults.length > 0
            && (grantResults[0] == PackageManager.PERMISSION_DENIED
            || grantResults[1] == PackageManager.PERMISSION_DENIED)) {
      presenter.setStoragePrivate(getApplicationContext());
      presenter.startRecording(getApplicationContext());
    }
  }

  @Override
  public void onBackPressed() {
    if (menuLayout.getVisibility() == View.VISIBLE) {
      hideMenu();
    } else if (backPressToClose || !webView.canGoBack()) {
      exitActivity();
    } else {
      webView.goBack();
    }
  }

  @Override
  public void onClick(View v) {
    int viewId = v.getId();
    if (viewId == R.id.close) {
      if (rtl) {
        showMenu();
      } else {
        exitActivity();
      }
    } else if (viewId == R.id.back) {
      if (rtl) {
        webView.goForward();
      } else {
        webView.goBack();
      }
    } else if (viewId == R.id.forward) {
      if (rtl) {
        webView.goBack();
      } else {
        webView.goForward();
      }
    } else if (viewId == R.id.more) {
      if (rtl) {
        exitActivity();
      } else {
        showMenu();
      }
    } else if (viewId == R.id.menuLayout) {
      hideMenu();
    } else if (viewId == R.id.menuRefresh) {
      webView.reload();
      hideMenu();
    } else if (viewId == R.id.menuFind) {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
        webView.showFindDialog("", true);
      }
      hideMenu();
    } else if (viewId == R.id.menuShareVia) {
      Intent sendIntent = new Intent();
      sendIntent.setAction(Intent.ACTION_SEND);
      sendIntent.putExtra(Intent.EXTRA_TEXT, webView.getUrl());
      sendIntent.setType("text/plain");
      startActivity(Intent.createChooser(sendIntent, getResources().getString(stringResShareVia)));

      hideMenu();
    } else if (viewId == R.id.menuCopyLink) {
      ClipboardManagerUtil.setText(webView.getUrl());

      Snackbar snackbar = Snackbar.make(coordinatorLayout, getString(stringResCopiedToClipboard),
              Snackbar.LENGTH_LONG);
      View snackbarView = snackbar.getView();
      snackbarView.setBackgroundColor(toolbarColor);
      if (snackbarView instanceof ViewGroup) {
        updateChildTextView((ViewGroup) snackbarView);
      }
      snackbar.show();

      hideMenu();
    } else if (viewId == R.id.menuOpenWith) {
      Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(webView.getUrl()));
      startActivity(browserIntent);

      hideMenu();
    }
  }

  protected void updateChildTextView(ViewGroup viewGroup) {
    if (viewGroup == null || viewGroup.getChildCount() == 0) {
      return;
    }

    for (int i = 0; i < viewGroup.getChildCount(); i++) {
      View view = viewGroup.getChildAt(i);
      if (view instanceof TextView) {
        TextView textView = (TextView) view;
        textView.setTextColor(titleColor);
        textView.setTypeface(TypefaceHelper.get(this, titleFont));
        textView.setLineSpacing(0, 1.1f);
        textView.setIncludeFontPadding(false);
      }

      if (view instanceof ViewGroup) {
        updateChildTextView((ViewGroup) view);
      }
    }
  }

  protected void showMenu() {
    menuLayout.setVisibility(View.VISIBLE);
    Animation popupAnim = AnimationUtils.loadAnimation(this, R.anim.popup_flyout_show);
    shadowLayout.startAnimation(popupAnim);
  }

  protected void hideMenu() {
    Animation popupAnim = AnimationUtils.loadAnimation(this, R.anim.popup_flyout_hide);
    shadowLayout.startAnimation(popupAnim);
    popupAnim.setAnimationListener(new Animation.AnimationListener() {
      @Override
      public void onAnimationStart(Animation animation) {
      }

      @Override
      public void onAnimationEnd(Animation animation) {
        menuLayout.setVisibility(View.GONE);
      }

      @Override
      public void onAnimationRepeat(Animation animation) {
      }
    });
  }

  protected void exitActivity() {
    super.onBackPressed();
    overridePendingTransition(animationCloseEnter, animationCloseExit);
  }

  @Override
  public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
    if (toolbarScrollFlags == 0) {
      return;
    }

    ViewHelper.setTranslationY(gradient, verticalOffset);
    ViewHelper.setAlpha(gradient,
            1 - (float) Math.abs(verticalOffset) / (float) appBarLayout.getTotalScrollRange());

    switch (progressBarPosition) {
      case BOTTON_OF_TOOLBAR:
        ViewHelper.setTranslationY(progressBar,
                Math.max(verticalOffset, progressBarHeight - appBarLayout.getTotalScrollRange()));
        break;
      case TOP_OF_WEBVIEW:
        ViewHelper.setTranslationY(progressBar, verticalOffset);
        break;
      case TOP_OF_TOOLBAR:
      case BOTTOM_OF_WEBVIEW:
      default:
        break;
    }

    if (menuLayout.getVisibility() == View.VISIBLE
            && Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
      ViewHelper.setTranslationY(menuLayout,
              Math.max(verticalOffset, -getResources().getDimension(R.dimen.defaultMenuLayoutMargin)));
    }
  }

  protected void requestCenterLayout() {
    int maxWidth;
    if (webView.canGoBack() || webView.canGoForward()) {
      maxWidth = DisplayUtil.getWidth() - UnitConverter.dpToPx(48) * 4;
    } else {
      maxWidth = DisplayUtil.getWidth() - UnitConverter.dpToPx(48) * 2;
    }

    title.setMaxWidth(maxWidth);
    urlTv.setMaxWidth(maxWidth);
    title.requestLayout();
    urlTv.requestLayout();
  }

  @Override
  public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);

    if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
      layoutViews();
    } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
      layoutViews();
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    BroadCastManager.unregister(FinestWebViewActivity.this, key);
    if (webView == null) {
      return;
    }
    if (APILevel.require(11)) {
      webView.onPause();
    }
    destroyWebView();
  }

  // Wait for zoom control to fade away
  // https://code.google.com/p/android/issues/detail?id=15694
  // http://stackoverflow.com/a/5966151/1797648
  private void destroyWebView() {
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        if (webView != null) {
          webView.destroy();
        }
      }
    }, ViewConfiguration.getZoomControlsTimeout() + 1000L);
  }

  public class MyWebChromeClient extends WebChromeClient {

    @Override
    public void onProgressChanged(WebView view, int progress) {
      BroadCastManager.onProgressChanged(FinestWebViewActivity.this, key, progress);

      if (showSwipeRefreshLayout) {
        if (swipeRefreshLayout.isRefreshing() && progress == 100) {
          swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
              swipeRefreshLayout.setRefreshing(false);
            }
          });
        }

        if (!swipeRefreshLayout.isRefreshing() && progress != 100) {
          swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
              swipeRefreshLayout.setRefreshing(true);
            }
          });
        }
      }

      if (progress == 100) {
        progress = 0;
      }
      progressBar.setProgress(progress);
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
      BroadCastManager.onReceivedTitle(FinestWebViewActivity.this, key, title);
    }

    @Override
    public void onReceivedTouchIconUrl(WebView view, String url, boolean precomposed) {
      BroadCastManager.onReceivedTouchIconUrl(FinestWebViewActivity.this, key, url, precomposed);
    }
  }

  public class MyWebViewClient extends WebViewClient {

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
      BroadCastManager.onPageStarted(FinestWebViewActivity.this, key, url);
      if (!url.contains("docs.google.com") && url.endsWith(".pdf")) {
        webView.loadUrl("http://docs.google.com/gview?embedded=true&url=" + url);
      }
    }

    @Override
    public void onPageFinished(WebView view, String url) {
      BroadCastManager.onPageFinished(FinestWebViewActivity.this, key, url);

      if (updateTitleFromHtml) {
        title.setText(view.getTitle());
      }
      urlTv.setText(UrlParser.getHost(url));
      requestCenterLayout();

      if (view.canGoBack() || view.canGoForward()) {
        back.setVisibility(showIconBack ? View.VISIBLE : View.GONE);
        forward.setVisibility(showIconForward ? View.VISIBLE : View.GONE);
        back.setEnabled(!disableIconBack && (rtl ? view.canGoForward() : view.canGoBack()));
        forward.setEnabled(!disableIconForward && (rtl ? view.canGoBack() : view.canGoForward()));
      } else {
        back.setVisibility(View.GONE);
        forward.setVisibility(View.GONE);
      }

      if (injectJavaScript != null) {
        webView.evaluateJavascript(injectJavaScript, null);
      }
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
      if (url.endsWith(".mp4")) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse(url), "video/*");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        view.getContext().startActivity(intent);
        // If we return true, onPageStarted, onPageFinished won't be called.
        return true;
      } else if (url.startsWith("tel:") || url.startsWith("sms:") || url.startsWith("smsto:") || url
              .startsWith("mms:") || url.startsWith("mmsto:")) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        view.getContext().startActivity(intent);
        return true; // If we return true, onPageStarted, onPageFinished won't be called.
      }
      /*******************************************************
       * Added in support for mailto:
       *******************************************************/
      else if (url.startsWith("mailto:")) {

        MailTo mt = MailTo.parse(url);

        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setType("text/html");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{mt.getTo()});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, mt.getSubject());
        emailIntent.putExtra(Intent.EXTRA_CC, mt.getCc());
        emailIntent.putExtra(Intent.EXTRA_TEXT, mt.getBody());

        startActivity(emailIntent);

        return true;
      } else {
        return super.shouldOverrideUrlLoading(view, url);
      }
    }

    @Override
    public void onLoadResource(WebView view, String url) {
      BroadCastManager.onLoadResource(FinestWebViewActivity.this, key, url);
    }

    @Override
    public void onPageCommitVisible(WebView view, String url) {
      BroadCastManager.onPageCommitVisible(FinestWebViewActivity.this, key, url);
    }
  }
}
