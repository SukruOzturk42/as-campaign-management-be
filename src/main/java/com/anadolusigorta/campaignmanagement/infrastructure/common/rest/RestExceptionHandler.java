package com.anadolusigorta.campaignmanagement.infrastructure.common.rest;

import com.anadolusigorta.campaignmanagement.domain.authorization.exception.JwtTokenException;
import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.common.Constants;
import com.anadolusigorta.campaignmanagement.infrastructure.businessmessage.jpa.repository.BusinessMessageJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.nio.file.AccessDeniedException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class RestExceptionHandler extends BaseController {

    private final MessageSource messageSource;

    private final BusinessMessageJpaRepository businessMessageJpaRepository;

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(CampaignManagementException.class)
    public Response<?> handleCampusApiBusinessException(CampaignManagementException campaignManagementException) {
        return generateErrorResponseFromBusinessMessage(campaignManagementException.getKey(),campaignManagementException.getArgs());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WebExchangeBindException.class)
    public Response<?> handleRequestPropertyBindingError(WebExchangeBindException webExchangeBindException) {
        log.error(webExchangeBindException.getMessage());
        return respond(new ErrorResponse("400", webExchangeBindException.getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response<?> handleMethodArgumentNotValidationException(
            MethodArgumentNotValidException methodArgumentNotValidException) {
        log.error(methodArgumentNotValidException.getMessage());
        return generateErrorResponseFromBusinessMessage( Objects.requireNonNull(methodArgumentNotValidException
                        .getBindingResult()
                        .getFieldError()).getDefaultMessage()
                );

    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public Response<?> handleAccessDeniedException(AccessDeniedException accessDeniedException) {
        log.error(accessDeniedException.getMessage());
        return respond(new ErrorResponse("403", accessDeniedException.getMessage()));
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(JwtTokenException.class)
    public Response<?> handleJwtTokenException(JwtTokenException jwtTokenException) {
        log.error(jwtTokenException.getMessage());
        return respond(new ErrorResponse("401", jwtTokenException.getMessage()));
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Response<?> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException methodArgumentTypeMismatchException) {
        log.error(methodArgumentTypeMismatchException.getMessage());
        return respond(new ErrorResponse("422", "Method Argument Type Mismatch"));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Response<?> handleException(Exception exception) {
        var uuıd= UUID.randomUUID();
        log.error(String.format("error ıd: %s  error description: %s",uuıd,exception.getMessage()));
        return respond(new ErrorResponse("500",String
                .format("Something went wrong log id:%s", uuıd)));
    }

    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public Response<?> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException exception) {
        var uuid= UUID.randomUUID();
        log.error(String.format("error ıd: %s  error description: %s",uuid,exception.getMessage()));
        return respond(new ErrorResponse("500",String
                .format("Maximum upload size exceeded.The field file exceeds its maximum permitted size of 1048576 bytes. log id:%s", uuid)));
    }

    private Response<?> createErrorResponseFromMessageSource(String key, Locale locale, String... args) {
        List<String> messageList = retrieveLocalizationMessage(key, locale, args);
        log.error(String.format("Campaign Management Exception: %s ----> %s", messageList.get(0), messageList.get(1)));
        return respond(new ErrorResponse(messageList.get(0), messageList.get(1)));
    }

    private List<String> retrieveLocalizationMessage(String key, Locale locale, String... args) {
        String message = messageSource.getMessage(key, args, locale);
        return Pattern.compile(Constants.MESSAGE_DELIMITER)
                .splitAsStream(message)
                .collect(Collectors.toList());
    }

    private Response<?> generateErrorResponseFromBusinessMessage(String key, String... args) {
        List<String> messageList = retrieveBusinessMessage(key,args);
        log.error(String.format("Campaign Management Exception: %s ----> %s", messageList.get(0), messageList.get(1)));
        return respond(new ErrorResponse(messageList.get(0), messageList.get(1)));
    }

    private List<String> retrieveBusinessMessage(String key, String... args){
        List<String> messageList = new ArrayList<>();
        String message = businessMessageJpaRepository.findByKey(key)
                .orElseThrow(()-> new CampaignManagementException("business.message.not.found","key",key)).getDescription();
        String code = businessMessageJpaRepository.findByKey(key)
                .orElseThrow(()-> new CampaignManagementException("business.message.not.found","key",key)).getCode();
        if(args != null){
            for(int i=0;i<args.length;i++){
                if(message != null){
                    message = message.replace("{" + i + "}",args[i]);
                }
            }
        }
        messageList.add(code);
        messageList.add(message);
        return messageList;
    }

}