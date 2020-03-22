package com.wiprodigital.crawler.infrastructure.exception;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;

public class CrawlControllerCreatorException extends RuntimeException {

    private CrawlControllerCreatorException(String message) {
        super(message);
    }

    public static CrawlControllerCreatorException cannotCreateController(CrawlConfig crawlConfig) {
        String message = String.format("Cannot create CrawlController with configuration [config = %s]", crawlConfig);
        return new CrawlControllerCreatorException(message);
    }

}