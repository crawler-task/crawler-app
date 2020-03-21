package com.wiprodigital.crawler.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RetrievalProcessResult {

    private final List<UnmodifiedWebsiteContent> unmodifiedWebsiteContents = new ArrayList<>();

    public void add(UnmodifiedWebsiteContent websiteContent) {
        unmodifiedWebsiteContents.add(websiteContent);
    }

    public List<UnmodifiedWebsiteContent> getUnmodifiedWebsiteContents() {
        return Collections.unmodifiableList(unmodifiedWebsiteContents);
    }
}
