package com.example.uploadingfiles.services;

import com.example.uploadingfiles.POJO.DeliveryMessageInformation;
import com.example.uploadingfiles.exceptions.VideoInfoNotFoundException;
import com.example.uploadingfiles.model.User;
import com.example.uploadingfiles.model.VideoInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Set;


@Component
public class KafkaConsumer {
    @Autowired
    private VideoInfoService videoInfoService;

    @Autowired
    private JavaMailSender emailSender;

    @Transactional
    @KafkaListener(topics = "NotificationTopicb", groupId = "OrderInfoToDelivery", containerFactory = "kafkaListenerContainerFactory")
    public void consume(DeliveryMessageInformation message) throws VideoInfoNotFoundException {
        System.out.println("Consumed message: " + message.getLink());
    }

}
