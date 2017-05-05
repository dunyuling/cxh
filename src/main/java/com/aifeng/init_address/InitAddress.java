package com.aifeng.init_address;

import com.aifeng.mgr.admin.model.Address;
import com.aifeng.mgr.admin.service.impl.AddressFeeService;
import com.aifeng.mgr.admin.service.impl.AddressService;
import com.fasterxml.jackson.core.type.TypeReference;
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


//        List<AdminRanking> areaList = getAR("areas.json");
//        List<AdminRanking> cityList = getAR("cites.json");
//        List<AdminRanking> provinceList = getAR("provinces.json");

        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(new String[]{
                "druid.xml",
                "spring-config.xml",
//                "spring-mvc.xml",
                "spring-redis.xml"

        });
        AddressService addressService = classPathXmlApplicationContext.getBean(AddressService.class);
        AddressFeeService addressFeeService = classPathXmlApplicationContext.getBean(AddressFeeService.class);
        for (Address address : addressService.getAll()) {
            addressFeeService.saveAddressFee(address.getId(),10);
        }

//        addressService.init(areaList, cityList, provinceList);
    }


    static String basePath = "src/main/java/com/aifeng/init_address/";

    public static List<AdminRanking> getAR(String arName) {
        List<AdminRanking> list = new ArrayList<>();
        File file = new File(basePath + arName);
        ObjectMapper mapper = new ObjectMapper();
        try {
            list = mapper.readValue(file, new TypeReference<List<AdminRanking>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}