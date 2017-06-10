package com.aifeng.init_address;

import com.aifeng.mgr.admin.dao.impl.AgentDao;
import com.aifeng.mgr.admin.model.Agent;
import com.aifeng.mgr.admin.service.impl.AgentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pro on 17-4-24.
 */
public class InitAddress {

    public static void main(String[] args) {
//        HashMap<String,AdminRanking> areasMap = getAR(" /home/pro/IdeaProjects/cxh/src/main/java/com/aifeng/init_address/cites.json");
//        System.out.println("areasMap size: " + areasMap.size());


        List<AdminRanking> areaList = getAR("areas.json");
        List<AdminRanking> cityList = getAR("cites.json");
        List<AdminRanking> provinceList = getAR("provinces.json");

        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(new String[]{
                "druid.xml",
                "spring-config.xml",
//                "spring-mvc.xml",
                "spring-redis.xml"

        });
//        AddressService addressService = classPathXmlApplicationContext.getBean(AddressService.class);
//        addressService.init(areaList, cityList, provinceList);
//
//        AddressFeeService addressFeeService = classPathXmlApplicationContext.getBean(AddressFeeService.class);
//        for (Address address : addressService.getAll()) {
//            addressFeeService.saveAddressFee(address.getId(),10);
//        }

/*        VisitRecordService visitRecordService = classPathXmlApplicationContext.getBean(VisitRecordService.class);
        visitRecordService.visitRemind();*/

//        AgentMessageService agentMessageService = classPathXmlApplicationContext.getBean(AgentMessageService.class);
//        agentMessageService.repeatSend();

//        AgentService agentService = classPathXmlApplicationContext.getBean(AgentService.class);
//        agentService.auditCancel(48);

        AgentDao agentDao = classPathXmlApplicationContext.getBean(AgentDao.class);
        Agent agent = agentDao.getActiveAgentByMobile("13818280352");
        System.out.println(agent.getName() + " \t ==========");


    }


    static String basePath = "src/main/java/com/aifeng/init_address/";

    public static List<AdminRanking> getAR(String arName) {
        List<AdminRanking> list = new ArrayList<>();
        File file = new File(basePath + arName);
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.readValue(file, new com.fasterxml.jackson.core.type.TypeReference<List<AdminRanking>>() {

            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}

//TODO 会员短信群发
//TODO 弹出error细化