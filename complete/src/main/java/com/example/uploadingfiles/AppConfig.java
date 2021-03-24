package com.example.uploadingfiles;

import com.example.uploadingfiles.model.VideoInfo;
import com.example.uploadingfiles.repositories.VideoInfoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

@Configuration
public class AppConfig {
    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public VideoInfoRepository getMyDAOBean() {
        return new VideoInfoRepository() {
            @Override
            public long countVideoInfosByLink(String link) {
                return 0;
            }

            @Override
            public void updateVideoInfo(String videoName, String videoDesc, String category, String releaseTime, String eleaseDate, String link) {

            }

            @Override
            public List<VideoInfo> findAll() {
                return null;
            }

            @Override
            public List<VideoInfo> findAll(Sort sort) {
                return null;
            }

            @Override
            public List<VideoInfo> findAllById(Iterable<Long> iterable) {
                return null;
            }

            @Override
            public <S extends VideoInfo> List<S> saveAll(Iterable<S> iterable) {
                return null;
            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends VideoInfo> S saveAndFlush(S s) {
                return null;
            }

            @Override
            public void deleteInBatch(Iterable<VideoInfo> iterable) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public VideoInfo getOne(Long aLong) {
                return null;
            }

            @Override
            public <S extends VideoInfo> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends VideoInfo> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }

            @Override
            public Page<VideoInfo> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public <S extends VideoInfo> S save(S s) {
                return null;
            }

            @Override
            public Optional<VideoInfo> findById(Long aLong) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(Long aLong) {
                return false;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(Long aLong) {

            }

            @Override
            public void delete(VideoInfo videoInfo) {

            }

            @Override
            public void deleteAll(Iterable<? extends VideoInfo> iterable) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public <S extends VideoInfo> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends VideoInfo> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends VideoInfo> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends VideoInfo> boolean exists(Example<S> example) {
                return false;
            }
        };
    }

}
