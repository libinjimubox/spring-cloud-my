package com.company.cloud.rocket.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQReplyListener;
import org.springframework.stereotype.Service;

/**
 * @author bin.li
 * @date 2020/9/6
 */
@Service
@RocketMQMessageListener(consumerGroup = "oneway", topic = "oneway")
@Slf4j
public class ConsumerOneWayListener implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        log.info("topic:{}, message:{}", "oneway", message);
    }
}
