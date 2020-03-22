package com.wiprodigital.crawler.domain;

import java.util.regex.Pattern;

public class FileExtension {
    public final static Pattern STATIC_CONTENT_EXTENSIONS
            = Pattern.compile(".*([=./])(css|js|xml|gif|jpg|png|mp3|mp4|zip|gz|pdf).*");
}