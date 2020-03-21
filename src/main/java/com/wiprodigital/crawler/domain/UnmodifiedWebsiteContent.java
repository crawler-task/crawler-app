package com.wiprodigital.crawler.domain;

import edu.uci.ics.crawler4j.url.WebURL;
import lombok.Getter;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class UnmodifiedWebsiteContent {

    @Getter
    private final String verifiedUrl;

    private final Set<WebURL> nestedUrls = new HashSet<>();

    public UnmodifiedWebsiteContent(String verifiedUrl) {
        this.verifiedUrl = verifiedUrl;
    }

    public void addAll(Collection<? extends WebURL> contents) {
        nestedUrls.addAll(contents);
    }

    public Set<WebURL> getNestedUrls() {
        return Collections.unmodifiableSet(nestedUrls);
    }
}
