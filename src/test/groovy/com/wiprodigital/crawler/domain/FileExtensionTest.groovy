package com.wiprodigital.crawler.domain


import spock.lang.Specification

class FileExtensionTest extends Specification {

    def "Verify recognize static content extension"() {
        expect:
        FileExtension.STATIC_CONTENT_EXTENSIONS.matcher(url).matches()

        where:
        url << [
                "https://wiprodigital.comrodigital.com%2F&format=xml",
                "https://gtag/.js?id=AW-701955939.js.sdfsd",
                "https://gtag/.js?id=AW-701955939/js.sdfsd",
                "https://gtag/.js?id=AW-701955939.min.js/test",
                "https://gtag/.js?id=AW-701955939.min.css.test",
        ]
    }

    def "Verify recognize non static content extension"() {
        expect:
        !FileExtension.STATIC_CONTENT_EXTENSIONS.matcher(url).matches()

        where:
        url << [
                "https://wiprodigital.comrodigital.com",
                "https://gtag/id=AW-701955939",
        ]
    }
}