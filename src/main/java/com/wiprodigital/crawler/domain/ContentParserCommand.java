package com.wiprodigital.crawler.domain;

import lombok.Builder;
import lombok.NonNull;

import java.net.URL;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Builder
class ContentParserCommand {

    @NonNull
    private final URL internalUrlPattern;

    @NonNull
    private final RetrievalProcessResult retrievalProcessResult;
    private final Set<String> internalUrls = new HashSet<>();
    private final Set<String> externalUrls = new HashSet<>();
    private final Set<WebsiteStaticContent> staticContents = new HashSet<>();

    public CrawlingResult parse() {
        for (UnmodifiedWebsiteContent websiteContent : retrievalProcessResult.getUnmodifiedWebsiteContents()) {
            sortUrlByBusinessRoles(websiteContent);
        }
        return CrawlingResult.builder()
                .externalUrls(Collections.unmodifiableSet(externalUrls))
                .internalUrls(Collections.unmodifiableSet(internalUrls))
                .staticContentUrls(Collections.unmodifiableSet(staticContents))
                .build();
    }

    private void sortUrlByBusinessRoles(UnmodifiedWebsiteContent unmodifiedWebsiteContent) {
        WebsiteStaticContent websiteStaticContent = new WebsiteStaticContent(unmodifiedWebsiteContent.getVerifiedUrl());
        for (String url : unmodifiedWebsiteContent.getNestedUrls()) {
            addInternalOrExternalUrl(url);
            websiteStaticContent.add(url);
        }
        staticContents.add(websiteStaticContent);
    }

    private void addInternalOrExternalUrl(String url) {
        if (isInternalIgnoreCase(url) && !WebsiteStaticContent.isStaticContent(url)) {
            internalUrls.add(url);
        } else if (!WebsiteStaticContent.isStaticContent(url)) {
            externalUrls.add(url);
        }
    }

    private boolean isInternalIgnoreCase(String url) {
        return url.toLowerCase().startsWith(internalUrlPattern.toString().toLowerCase());
    }
}