package com.imlsz.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lsz on 2017/4/24.
 */
public class Kits {
   private final static  String regstr = "((http|ftp|https)://)(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,4})*(/[a-zA-Z0-9\\&%_\\./-~-]*)?";
   private final static Pattern pattern = Pattern.compile(regstr);
   public static boolean checkUrl(String url){
      Matcher matcher = pattern.matcher(url);
      return matcher.matches();
   }

}
