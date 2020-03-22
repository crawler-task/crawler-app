package com.wiprodigital.crawler.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URL;

@Service
@RequiredArgsConstructor
public class ContentRetrievalFacade {

    private final ContentRetrievalClient contentRetrievalClient;

    public CrawlingResult retrieve(URL url, Integer maxNestedCrawling) {
        ContentParserCommand contentParserCommand = ContentParserCommand.builder()
                .internalUrlPattern(url)
                .retrievalProcessResult(contentRetrievalClient.retrieve(url, maxNestedCrawling))
                .build();
        return contentParserCommand.parse();
    }
}
