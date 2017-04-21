package com.aifeng;

import com.aifeng.mgr.admin.model.Admin;
import com.aifeng.mgr.admin.service.impl.AdminService;
import com.aifeng.util.Md5;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by pro on 17-3-11.
 */
public class Init {
    public static void main(String[] args) {
/*
        System.out.println(System.getProperty("user.dir"));
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath*:spring-config.xml,druid.xml");
//        BelieverService believerService = classPathXmlApplicationContext.getBean(BelieverService.class);
//        believerService.saveUser("admin", "123456");

        AdminService adminService = classPathXmlApplicationContext.getBean(AdminService.class);
        Admin admin = new Admin();
        admin.setAccount("admin");
        admin.setPwd(Md5.getMd5("admin" + "123456"));
        adminService.add(admin);
*/
        System.out.println(Md5.getMd5("admin" + "123456"));
        System.out.println("08d879276ccfab2e74eadf6e4a5f4515".length());
    }
}
