package com.wiprodigital.crawler.domain


import spock.lang.Specification

class ContentParserCommandTest extends Specification {

    def "Recognise all content of crawling website correctly"() {
        given:
        URL url = new URL("http://www.test.pl")
        def contentParserCommand = ContentParserCommand.builder()
                .internalUrlPattern(url)
                .retrievalProcessResult(createRetrievalProcessResult(url))
                .build()

        when:
        def result = contentParserCommand.parse()

        then:
        result.internalUrls.size() == 2
        result.internalUrls.contains("http://www.test.pl/plot")
        result.internalUrls.contains("http://www.test.pl/lot")
        result.externalUrls.size() == 1
        result.externalUrls.contains("http://www.234234324.pl/plot")
        result.staticContentUrls.size() == 1
        result.staticContentUrls[0].ownerOfStaticContentUrl == url
        result.staticContentUrls[0].urls.contains("http://www.test.pl/w.js")
    }

    RetrievalProcessResult createRetrievalProcessResult(URL url) {
        def retrievalProcessResult = new RetrievalProcessResult(url)
        retrievalProcessResult.add(createUnmodifiedWebsiteContent(url))
        return retrievalProcessResult
    }

    UnmodifiedWebsiteContent createUnmodifiedWebsiteContent(URL url) {
        def content = new UnmodifiedWebsiteContent(url);
        content.addAllNestedUrlsByVerificationUrl(
                Arrays.asList("http://www.test.pl/w.js",
                        "http://www.test.pl/plot",
                        "http://www.234234324.pl/plot",
                        "http://www.test.pl/lot"))
        return content;
    }

}
