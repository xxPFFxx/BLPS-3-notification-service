package com.example.uploadingfiles.services;

import com.example.uploadingfiles.POJO.DeliveryMessageInformation;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    @KafkaListener(topics = "NotificationTopicb", groupId = "OrderInfoToDelivery", containerFactory = "kafkaListenerContainerFactory")
    public void consume(DeliveryMessageInformation message) {
        System.out.println("Consumed message: " + message.getLink());
    }

}
