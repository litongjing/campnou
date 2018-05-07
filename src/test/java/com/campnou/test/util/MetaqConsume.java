package com.campnou.test.util;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.taobao.metaq.client.MetaPushConsumer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @Author:LiTongjing
 * @Description:
 * @Date:Create by 下午1:53 2018/3/20
 */
public class MetaqConsume {
    /**
     * 当前例子是PushConsumer用法，使用方式给用户感觉是消息从MetaQ服务器推到了应用客户端。
     * 但是实际PushConsumer内部是使用长轮询Pull方式从MetaQ服务器拉消息，然后再回调用户Listener方法
     */
    public static void main(String[] args) throws InterruptedException, MQClientException {
        /**
         * 一个应用创建一个Consumer，由应用来维护此对象，可以设置为全局对象或者单例
         * 注意：ConsumerGroupName需要由应用来保证唯一
         */
        MetaPushConsumer consumer = new MetaPushConsumer("cid_onsite_order_msg");

        /**
         * 一个consumer对象可以订阅多个topic 注意：可以通过指定 Tag 的方式来订阅指定 Topic 下的某一种类型的消息 Tag
         * 表达式说明: 1. * 表示订阅指定 topic 下的所有消息 2. TagA || TagC || TagD 表示订阅指定 topic
         * 下 tags 分别等于 TagA 或 TagC 或 TagD 的消息
         */
        consumer.subscribe("ONSITE_ORDER_MSG", "*");

        consumer.registerMessageListener(new MessageListenerConcurrently() {
                                             /**
                                              * 1、默认msgs里只有一条消息，可以通过设置consumeMessageBatchMaxSize参数来批量接收消息
                                              * 2、如果设置为批量消费方式，要么都成功，要么都失败.
                                              * 3、此方法由MetaQ客户端多个线程回调，需要应用来处理并发安全问题
                                              * 4、抛异常与返回ConsumeConcurrentlyStatus.RECONSUME_LATER等价
                                              * 5、每条消息失败后，会尝试重试，重试5次都失败，则丢弃
                                              */
                                             //@Override
                                             public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                                                 //System.out.println(Thread.currentThread().getName() + " Receive New Messages: " + msgs);
                                                 for (MessageExt msg : msgs) {
                                                     /**
                                                      * 对消息进行处理. 1. 如果消息处理成功, 请 return
                                                      * ConsumeConcurrentlyStatus.CONSUME_SUCCESS; 2. 如果消息处理失败, 请
                                                      * return
                                                      * ConsumeConcurrentlyStatus.RECONSUME_LATER,服务端会再次对该消息进行投递;
                                                      */
                                                     //System.out.println(msg.getTags().toString());
                                                     String a = new String(msg.getBody());
                                                     System.out.println(a);
//                                                     if (msg.getTags().equals("TagA")) {
//                                                         return ConsumeConcurrentlyStatus.RECONSUME_LATER;
//                                                     }
                                                 }
                                                 //return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                                                 return null;
                                                 //return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                                             }
                                         }
        );

        /**
         * Consumer对象在使用之前必须要调用start初始化，初始化一次即可
         */
        consumer.start();

        System.out.println("Consumer Started.");
    }

    public static <T extends Serializable> T getObjectFromBytes(byte[] objBytes) throws IOException, ClassNotFoundException {
        if (objBytes == null || objBytes.length == 0) {
            return null;
        }
        ByteArrayInputStream bi = new ByteArrayInputStream(objBytes);
        ObjectInputStream oi = new ObjectInputStream(bi);
        T outObject = (T) oi.readObject();
        return outObject;
    }
}

