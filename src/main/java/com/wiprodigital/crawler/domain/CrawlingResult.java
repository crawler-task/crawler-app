package com.wiprodigital.crawler.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.io.Serializable;
import java.util.Set;

@Builder
@Getter
public class CrawlingResult implements Serializable {

    @NonNull
    private Set<String> internalUrls;
    @NonNull
    private Set<String> externalUrls;
    @NonNull
    private Set<WebsiteStaticContent> staticContentUrls;

}