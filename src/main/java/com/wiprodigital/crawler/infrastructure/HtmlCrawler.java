package com.wiprodigital.crawler.infrastructure;

import com.wiprodigital.crawler.domain.RetrievalProcessResult;
import com.wiprodigital.crawler.domain.FileExtension;
import com.wiprodigital.crawler.domain.UnmodifiedWebsiteContent;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import java.net.URL;
import java.util.Set;

class HtmlCrawler extends WebCrawler {

    private final RetrievalProcessResult retrievalProcessResult;
    private final URL pathAllowedForVerification;

    public HtmlCrawler(RetrievalProcessResult retrievalProcessResult, URL pathAllowedForVerification) {
        this.retrievalProcessResult = retrievalProcessResult;
        this.pathAllowedForVerification = pathAllowedForVerification;
    }

    @Override
    public boolean shouldVisit(Page referringPage, WebURL webURL) {
        String url = webURL.getURL().toLowerCase();
        return !FileExtension.STATIC_CONTENT_EXTENSIONS.matcher(url).matches()
                && url.startsWith(pathAllowedForVerification.toString());
    }

    @Override
    public void visit(Page page) {
        String pageUrl = page.getWebURL().getURL();
        UnmodifiedWebsiteContent unmodifiedWebsiteContent = new UnmodifiedWebsiteContent(pageUrl);
        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            Set<WebURL> links = htmlParseData.getOutgoingUrls();
            unmodifiedWebsiteContent.addAll(links);
        }
        retrievalProcessResult.add(unmodifiedWebsiteContent);
    }
}
