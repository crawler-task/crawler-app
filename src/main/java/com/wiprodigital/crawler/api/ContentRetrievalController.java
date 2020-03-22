package com.wiprodigital.crawler.api;


import com.wiprodigital.crawler.domain.ContentRetrievalFacade;
import com.wiprodigital.crawler.domain.CrawlingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.net.URL;

@RestController
@RequestMapping("/websites")
@RequiredArgsConstructor
class ContentRetrievalController {

    private final ContentRetrievalFacade contentRetrievalFacade;

    @GetMapping
    public ResponseEntity<CrawlingResult> getExitAddresses(@RequestParam("url") String url,
                                                           @RequestParam(value = "maxNestedCrawling", required = false) Integer maxNestedCrawling)
            throws MalformedURLException {
        if (maxNestedCrawling != null && maxNestedCrawling < 0) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(contentRetrievalFacade.retrieve(new URL(url), maxNestedCrawling));
    }
}
