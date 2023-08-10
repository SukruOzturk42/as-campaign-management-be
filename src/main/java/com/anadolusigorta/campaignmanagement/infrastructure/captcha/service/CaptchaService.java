package com.anadolusigorta.campaignmanagement.infrastructure.captcha.service;

import com.anadolusigorta.campaignmanagement.infrastructure.captcha.rest.dto.GenerateCaptchaResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class CaptchaService {

    public GenerateCaptchaResponse generateCaptcha() {

        Random randI = new Random();
        int num1 = randI.nextInt(10) + 1;
        int num2 = randI.nextInt(10) + 1;
        GenerateCaptchaResponse generateCaptchaResponse = new GenerateCaptchaResponse();
        List<Integer> listOfCaptchaElements = new ArrayList<>();
        listOfCaptchaElements.add(num1);
        listOfCaptchaElements.add(num2);
        generateCaptchaResponse.setCaptcha(listOfCaptchaElements);



        return generateCaptchaResponse;
    }

    public Boolean validateCaptcha(List<Integer> captcha, int result) {

        if (captcha != null && captcha.get(0) + captcha.get(1) == result){
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

}
