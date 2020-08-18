package com.diandian.web.controller.toc;


import com.diandian.common.config.WxConfig;
import com.diandian.common.dto.OrderCreateEvent;
import com.diandian.common.dto.WxModelMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class IndexController {

   /* @RequestMapping({"/home", "/home"})
    public String home() {
        return "redirect:home.html";
    }*/
   @Autowired
   private WxConfig wxConfig;

    @Autowired
    private ApplicationContext applicationContext;

    public String home() {
        applicationContext.publishEvent(new OrderCreateEvent(applicationContext,new WxModelMsg()));
        String token = wxConfig.getTokenFromWx();
        log.info(token);
        return "redirect:home.html";
    }

}
