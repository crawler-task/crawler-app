package com.wiprodigital.crawler.api;


import com.wiprodigital.crawler.domain.ContentRetrievalFacade;
import com.wiprodigital.crawler.domain.CrawlingResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
@RestController
@RequestMapping("/websites")
@RequiredArgsConstructor
class ContentRetrievalController {

    private final ContentRetrievalFacade contentRetrievalFacade;

    @GetMapping
    public ResponseEntity<CrawlingResult> getExitAddresses(@RequestParam("url") String url,
                                                           @RequestParam(value = "maxNestedCrawling", required = false) Integer maxNestedCrawling) {
        if (maxNestedCrawling != null && maxNestedCrawling < 0) {
            return ResponseEntity.badRequest().build();
        }
        try {
            return ResponseEntity.ok(contentRetrievalFacade.retrieve(new URL(url), maxNestedCrawling));
        } catch (MalformedURLException ex) {
            log.warn("Cannot processing url [URL={}]", url, ex);
            return ResponseEntity.badRequest().build();
        }

    }
}
