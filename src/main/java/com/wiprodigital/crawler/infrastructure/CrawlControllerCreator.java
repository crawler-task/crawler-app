package com.wiprodigital.crawler.infrastructure;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

import java.io.File;

class CrawlControllerCreator {

    private static final int WITHOUT_NESTED_WEBSITE = 0;
    private static final String PROCESSING_STORAGE_PATH = "target/crawler4j";

    static CrawlController crawlController(){
        File crawlStorage = new File(PROCESSING_STORAGE_PATH);
        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorage.getAbsolutePath());
        config.setMaxDepthOfCrawling(WITHOUT_NESTED_WEBSITE);
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        try{
            return new CrawlController(config, pageFetcher, robotstxtServer);
        }catch (Exception ex){
            //TODO LL-9 change to business exception
            throw new IllegalArgumentException("Cannot create CrawlController ", ex);
        }
    }
}
