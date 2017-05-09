package com.aifeng;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudTopic;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.BatchSmsAttributes;
import com.aliyun.mns.model.MessageAttributes;
import com.aliyun.mns.model.RawTopicMessage;
import com.aliyun.mns.model.TopicMessage;

/**
 * Created by pro on 17-5-7.
 */
public class BatchPublishSMSMessageDemo {
    private static final String AccessId = "LTAI30Yw7x0ArThk";
    private static final String AccessKey = "iFiVqaG5LAJVM3HhbfIW7xa4Qtt0rt";
    private static final String MNSEndpoint = "https://1093588017502147.mns.cn-hangzhou.aliyuncs.com/";
    private static final String Topic = "sms.topic-cn-hangzhou";
    private static final String SignName = "车险汇";
    private static final String TemplateCode = "SMS_65975238";


    public static void main(String[] args) {
        /**
         * Step 1. 获取主题引用
         */
        CloudAccount account = new CloudAccount(AccessId, AccessKey, MNSEndpoint);
        MNSClient client = account.getMNSClient();
        CloudTopic topic = client.getTopicRef(Topic);
        /**
         * Step 2. 设置SMS消息体（必须）
         *
         * 注：目前暂时不支持消息内容为空，需要指定消息内容，不为空即可。
         */
        RawTopicMessage msg = new RawTopicMessage();
        msg.setMessageBody("sms-message");
        /**
         * Step 3. 生成SMS消息属性
         */
        MessageAttributes messageAttributes = new MessageAttributes();
        BatchSmsAttributes batchSmsAttributes = new BatchSmsAttributes();
        // 3.1 设置发送短信的签名（SMSSignName）
        batchSmsAttributes.setFreeSignName(SignName);
        // 3.2 设置发送短信使用的模板（SMSTempateCode）
        batchSmsAttributes.setTemplateCode(TemplateCode);
        // 3.3 设置发送短信所使用的模板中参数对应的值（在短信模板中定义的，没有可以不用设置）
        BatchSmsAttributes.SmsReceiverParams smsReceiverParams = new BatchSmsAttributes.SmsReceiverParams();
        smsReceiverParams.setParam("name", "刘辉");
        smsReceiverParams.setParam("zone", "河南省-濮阳市-华龙区");
        smsReceiverParams.setParam("type", "交强险");
        smsReceiverParams.setParam("member_name", "张紫亚");
        smsReceiverParams.setParam("member_phone", "0371-68945612");
        // 3.4 增加接收短信的号码
        batchSmsAttributes.addSmsReceiver("13818280352", smsReceiverParams);
//        batchSmsAttributes.addSmsReceiver("$YourReceiverPhoneNumber2", smsReceiverParams);
        messageAttributes.setBatchSmsAttributes(batchSmsAttributes);
        try {
            /**
             * Step 4. 发布SMS消息
             */
            TopicMessage ret = topic.publishMessage(msg, messageAttributes);
            System.out.println("MessageId: " + ret.getMessageId());
            System.out.println("MessageMD5: " + ret.getMessageBodyMD5());
        } catch (ServiceException se) {
            System.out.println(se.getErrorCode() + se.getRequestId());
            System.out.println(se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        client.close();
    }
}
