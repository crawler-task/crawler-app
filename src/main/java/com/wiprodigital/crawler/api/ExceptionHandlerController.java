package com.wiprodigital.crawler.api;

import com.wiprodigital.crawler.infrastructure.exception.CrawlControllerCreatorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
class ExceptionHandlerController {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(CrawlControllerCreatorException.class)
    public void handleConflict(CrawlControllerCreatorException ex) {
        log.error(ex.getMessage(), ex);
    }
}