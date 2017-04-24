package com.imlsz.web;

import com.imlsz.domain.Usage;
import com.imlsz.service.StoreService;
import com.imlsz.utils.Kits;
import com.imlsz.utils.ShortUtils;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by lsz on 2017/4/24.
 */
@Controller
@RequestMapping("/")
public class IndexController {
    @Resource
    StoreService storeService;

    @RequestMapping({"/",""})
    @ResponseBody
    public String index(){
        return Usage.string();
    }
    @RequestMapping("/to/short")
    @ResponseBody
    public String toShort(String url){
        if(StringUtils.isEmpty(url)) return "";
        if(!Kits.checkUrl(url))return "";
        String code = "";
        try{
            code = ShortUtils._10_to_62(storeService.incrAndGet());
            storeService.save(code,url);
        }catch (Exception e){
            e.printStackTrace();
        }
        return code;
    }
    @RequestMapping("/{shortCode}")
    public String redict(@PathVariable("shortCode") String shortCode,HttpServletResponse response){
        if(shortCode == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);//设置状态码
            return null;
        }
        String url = storeService.get(shortCode);
        if(StringUtils.isEmpty(url)){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);//设置状态码
            return null;
        }
        return "redirect:"+url;
    }
}
