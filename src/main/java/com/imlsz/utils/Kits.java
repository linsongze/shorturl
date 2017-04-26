package com.imlsz.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lsz on 2017/4/24.
 */
public class Kits {
  private static final String regex = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
  private static final Pattern pattern = Pattern.compile(regex);
   public static boolean checkUrl(String url){
      Matcher matcher = pattern.matcher(url);
      return matcher.matches();
   }

}
