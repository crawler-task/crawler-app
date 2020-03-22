package com.wiprodigital.crawler.infrastructure;

import com.wiprodigital.crawler.infrastructure.exception.CrawlControllerCreatorException;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

import java.io.File;

class CrawlControllerCreator {

    private static final String PROCESSING_STORAGE_PATH = "target/crawler4j";

    static CrawlController crawlController(Integer maxNestedCrawling) {
        File crawlStorage = new File(PROCESSING_STORAGE_PATH);
        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorage.getAbsolutePath());
        setSearchingNestedWebsite(config, maxNestedCrawling);
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        try {
            return new CrawlController(config, pageFetcher, robotstxtServer);
        } catch (Exception ex) {
            throw CrawlControllerCreatorException.cannotCreateController(config);
        }
    }

    private static void setSearchingNestedWebsite(CrawlConfig config, Integer maxDepthOfCrawling) {
        if (maxDepthOfCrawling != null) {
            config.setMaxDepthOfCrawling(maxDepthOfCrawling);
        }
    }
}
