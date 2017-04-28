package com.aifeng.constants;

import com.aifeng.mgr.admin.constants.InsuranceType;
import com.aifeng.ws.wx.UserResponse;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * Created by pro on 17-4-20.
 */
public class test {
    public static void main(String[] args) {
//        System.out.println((int)(59/10));
//        System.out.println(1 & 2);
//        InsuranceType insuranceType = InsuranceType.IT_0;
//        System.out.println(insuranceType.getIndex() + " \t " + insuranceType.getType());
//
//        System.out.println(InsuranceType.getTypeByIndex(2));

        String str = "{\"access_token\":\"7amP-tQUTpaLKy4QXnl1T6uuPBB5X5wbAxiok7QCyifzAOQUQLbVimT9G97J780P\",\"expires_in\":7200}";
        String str2 = "{\"UserId\":\"lhg0\",\"DeviceId\":\"fda342eb9920b46b1dc28517d12ba102\"}";
        str2 = str2.replace("UserId","user_id").replace("DeviceId","device_id");

        String str3 = "{\"user_id\":\"lhg0\",\"device_id\":\"fda342eb9920b46b1dc28517d12ba102\"}";
        String str4 = "{\"user_id\":\"lhg0\",\"device_id\":\"fda342eb9920b46b1dc28517d12ba102\"}";

        ObjectMapper mapper = new ObjectMapper();

//JSON from file to Objectgaiy
        try {
            UserResponse ur = mapper.readValue(str2, UserResponse.class);
            System.out.println("ur: " + ur);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
