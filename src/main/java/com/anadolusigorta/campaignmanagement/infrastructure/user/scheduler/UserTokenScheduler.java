package com.anadolusigorta.campaignmanagement.infrastructure.user.scheduler;

import com.anadolusigorta.campaignmanagement.infrastructure.user.jpa.repository.UserTokenJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Component
@Slf4j
@Configuration
@EnableScheduling
@RequiredArgsConstructor
@Transactional
public class UserTokenScheduler {

    private final UserTokenJpaRepository userTokenJpaRepository;



    @Scheduled(cron = "${cron.deleteTokenScheduler}")
    public void deleteExpiredUserTokens() {


        if (false) {
           // userTokenJpaRepository.deleteByExpireTimeAfter(LocalDateTime.now());

        }
    }
}
