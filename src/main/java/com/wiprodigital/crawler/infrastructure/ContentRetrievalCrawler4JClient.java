package com.wiprodigital.crawler.infrastructure;

import com.wiprodigital.crawler.domain.ContentRetrievalClient;
import com.wiprodigital.crawler.domain.RetrievalProcessResult;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URL;

@Service
class ContentRetrievalCrawler4JClient implements ContentRetrievalClient {

    private final int numberOfConcurrencyCrawlers;

    public ContentRetrievalCrawler4JClient(@Value("${number.of.concurrency.crawlers}") int numberOfConcurrencyCrawlers) {
        this.numberOfConcurrencyCrawlers = numberOfConcurrencyCrawlers;
    }

    @Override
    public RetrievalProcessResult retrieve(URL url, Integer maxNestedCrawling) {
        CrawlController crawlController = CrawlControllerCreator.crawlController(maxNestedCrawling);
        crawlController.addSeed(url.toString());
        RetrievalProcessResult retrievalProcessResult = new RetrievalProcessResult(url);
        CrawlController.WebCrawlerFactory<HtmlCrawler> factory = () -> new HtmlCrawler(retrievalProcessResult, url);
        crawlController.start(factory, numberOfConcurrencyCrawlers);
        return retrievalProcessResult;
    }
}
