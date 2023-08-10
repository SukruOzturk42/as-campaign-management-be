
package com.anadolusigorta.campaignmanagement.infrastructure.captcha.rest.controller;

import com.anadolusigorta.campaignmanagement.infrastructure.captcha.service.CaptchaService;
import com.anadolusigorta.campaignmanagement.infrastructure.captcha.rest.dto.GenerateCaptchaResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.anadolusigorta.campaignmanagement.infrastructure.common.annotation.ApiController;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.BaseController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@ApiController
@RequiredArgsConstructor
public class CaptchaController extends BaseController {
    private final CaptchaService captchaService;

    @GetMapping("generate-captcha")
    public GenerateCaptchaResponse generateCaptcha() {
        return captchaService.generateCaptcha();
    }

    @GetMapping("validate-captcha")
    public Boolean validateCaptcha(@RequestParam(value = "captcha") List<Integer> captcha, @RequestParam(value="result") int result) {
        return captchaService.validateCaptcha(captcha,result);
    }

}