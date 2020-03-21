package com.wiprodigital.crawler.domain;

import java.net.URL;

public interface ContentRetrievalClient {

    RetrievalProcessResult retrieve(URL url);
}
