package com.wiprodigital.crawler.domain;

import lombok.Getter;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RetrievalProcessResult {

    @Getter
    private final URL requestedUrl;
    private final List<UnmodifiedWebsiteContent> unmodifiedWebsiteContents = new ArrayList<>();

    public RetrievalProcessResult(URL requestedUrl) {
        this.requestedUrl = requestedUrl;
    }

    public void add(UnmodifiedWebsiteContent websiteContent) {
        unmodifiedWebsiteContents.add(websiteContent);
    }

    public List<UnmodifiedWebsiteContent> getUnmodifiedWebsiteContents() {
        return Collections.unmodifiableList(unmodifiedWebsiteContents);
    }
}
