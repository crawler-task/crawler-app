package com.wiprodigital.crawler.domain;

import lombok.Getter;

import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class UnmodifiedWebsiteContent {

    @Getter
    private final URL verifiedUrl;

    private final Set<String> nestedUrls = new HashSet<>();

    public UnmodifiedWebsiteContent(URL verifiedUrl) {
        this.verifiedUrl = verifiedUrl;
    }

    public void addAllNestedUrlsByVerificationUrl(Collection<? extends String> contents) {
        nestedUrls.addAll(contents);
    }

    public Set<String> getNestedUrls() {
        return Collections.unmodifiableSet(nestedUrls);
    }
}
