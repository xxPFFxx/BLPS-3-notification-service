package com.example.uploadingfiles.services;

import com.example.uploadingfiles.POJO.NotifyAuthorMessage;
import com.example.uploadingfiles.POJO.NotifySubscribersMessage;
import com.example.uploadingfiles.POJO.UpdateNotificationsSentMessage;
import com.example.uploadingfiles.exceptions.VideoInfoNotFoundException;
import com.example.uploadingfiles.model.User;
import com.example.uploadingfiles.model.VideoInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Set;


@Component
public class KafkaConsumer {
    @Autowired
    private VideoInfoService videoInfoService;

    @Autowired
    private JavaMailSender emailSender;

    private final KafkaTemplate<String, UpdateNotificationsSentMessage> kafkaTemplate;
    private static final String TOPIC = "NotificationTopich";

    @Autowired
    public KafkaConsumer(KafkaTemplate<String, UpdateNotificationsSentMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Transactional
    @KafkaListener(topics = "NotificationTopicb", groupId = "MainToNotification", containerFactory = "kafkaListenerContainerFactory")
    public void consume(NotifySubscribersMessage message) throws VideoInfoNotFoundException {
        System.out.println("Consumed message: " + message.getLink());
        ArrayList<String> subscribersLogins = new ArrayList<>();
        VideoInfo videoInfo = videoInfoService.getVideo(message.getLink());
        User creator = videoInfo.getUser();
        Set<User> subscribers = creator.getSubscribers();
        for (User subscriber: subscribers){
            subscribersLogins.add(subscriber.getUsername());
            SimpleMailMessage EMessage = new SimpleMailMessage();
            EMessage.setFrom("LabTestTempMail@gmail.com");
            EMessage.setTo(subscriber.getEmail());
            EMessage.setSubject("Video uploaded");
            EMessage.setText(videoInfo.getUser().getUsername() + " uploaded new video! WATCH NOW! Link: " + videoInfo.getLink());
            emailSender.send(EMessage);

        }
        kafkaTemplate.send(TOPIC, new UpdateNotificationsSentMessage(videoInfo.getLink(), subscribersLogins));
    }

    @Transactional
    @KafkaListener(topics = "NotifyAuthorTopicFromMain", groupId = "NotifyAuthor", containerFactory = "NotifyAuthorKafkaListenerContainerFactory")
    public void notifyAuthorConsume(NotifyAuthorMessage message) throws VideoInfoNotFoundException {
        System.out.println(message.getLinks());
        //kafkaTemplate.send(TOPIC, new UpdateNotificationsSentMessage(videoInfo.getLink(), subscribersLogins));
    }



}
