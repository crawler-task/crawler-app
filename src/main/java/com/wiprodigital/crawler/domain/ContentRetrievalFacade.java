package com.wiprodigital.crawler.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.URL;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContentRetrievalFacade {

    private final ContentRetrievalClient contentRetrievalClient;

    public CrawlingResult retrieve(URL url, Integer maxNestedCrawling) {
        log.info("Start crawling process [URL = {}, maxNestedCrawling={}]", url.toString(), maxNestedCrawling);
        ContentParserCommand contentParserCommand = ContentParserCommand.builder()
                .internalUrlPattern(url)
                .retrievalProcessResult(contentRetrievalClient.retrieve(url, maxNestedCrawling))
                .build();
        return contentParserCommand.parse();
    }
}
