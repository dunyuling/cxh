package com.aifeng;

import com.aifeng.mgr.admin.model.Admin;
import com.aifeng.mgr.admin.service.impl.AdminService;
import com.aifeng.util.Md5;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by pro on 17-3-11.
 */
public class Init {
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));

        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(new String[]{
                "druid.xml",
                "spring-config.xml",
//                "spring-mvc.xml",
                "spring-redis.xml"

        });
//        BelieverService believerService = classPathXmlApplicationContext.getBean(BelieverService.class);
//        believerService.saveUser("admin", "123456");

        AdminService adminService = classPathXmlApplicationContext.getBean(AdminService.class);
        Admin admin = new Admin();
        admin.setAccount("admin2");
        admin.setPwd(Md5.getMd5("admin2" + "123456"));
        admin.setCode("000002");
        adminService.add(admin);
        /*System.out.println(Md5.getMd5("admin" + "123456"));
        System.out.println("08d879276ccfab2e74eadf6e4a5f4515".length());*/
    }
}

/*
 * TODO 初始化运营数据
 * 1. 后台帐号
 * 2. 省市区，三份要统一
 * 3. 辅助数据，企业号 CorpId,secret
 * 4. 重发消息的配置
 * 5. 货币结算单位是分
 * 6.AddressFee
 */