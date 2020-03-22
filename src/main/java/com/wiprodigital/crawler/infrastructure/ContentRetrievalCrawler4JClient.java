package com.wiprodigital.crawler.infrastructure;

import com.wiprodigital.crawler.domain.ContentRetrievalClient;
import com.wiprodigital.crawler.domain.RetrievalProcessResult;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URL;

@Service
@RequiredArgsConstructor
class ContentRetrievalCrawler4JClient implements ContentRetrievalClient {

    private static final int NUMBER_OF_CONCURRENCY_CRAWLERS = 1;

    @Override
    public RetrievalProcessResult retrieve(URL url)  {
        CrawlController crawlController = CrawlControllerCreator.crawlController();
        crawlController.addSeed(url.toString());
        RetrievalProcessResult retrievalProcessResult = new RetrievalProcessResult(url);
        CrawlController.WebCrawlerFactory<HtmlCrawler> factory = () -> new HtmlCrawler(retrievalProcessResult, url);
        crawlController.start(factory, NUMBER_OF_CONCURRENCY_CRAWLERS);
        return retrievalProcessResult;
    }
}
