package com.wiprodigital.crawler.domain;

import lombok.Getter;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;

@Getter
class WebsiteStaticContent {

    private final URL ownerOfStaticContentUrl;
    private final Set<String> urls = new HashSet<>();

    public WebsiteStaticContent(URL ownerOfStaticContentUrl) {
        this.ownerOfStaticContentUrl = ownerOfStaticContentUrl;
    }

    public static boolean isStaticContent(String url) {
        return FileExtension.STATIC_CONTENT_EXTENSIONS.matcher(url).matches();
    }

    void add(String url) {
        if (isStaticContent(url)) {
            urls.add(url);
        }
    }
}