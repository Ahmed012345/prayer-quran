package com.zaker.android.sapeh.app.main.finestwebview.helpers;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Leonardo on 11/23/15.
 */
public class UrlParser {

  private UrlParser() {
  }

  public static String getHost(String url) {
    try {
      return new URL(url).getHost();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
    return url;
  }
}
