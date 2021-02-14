package org.crawler;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class URLCrawler {
    private static final URLResponseFetcher URL_RESPONSE_FETCHER = new URLResponseFetcher();

    public static void main(String[] arg) throws IOException {
        System.out.println(Arrays.asList(arg));
        HttpResponse response = URL_RESPONSE_FETCHER.getURLResponse(arg[0]);

        if(response.getStatusLine().getStatusCode() != 200) {
            System.exit(response.getStatusLine().getStatusCode());
        }

        final String html = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);

        printHeaders(response);
        writeContent(arg[1], html);
        printTitleIfHtml(response, arg[0], html);
    }

    private static void printTitleIfHtml(HttpResponse response, String url, String html) throws IOException {
        System.out.println("******************Title*************");
        final String contentType = response.getEntity().getContentType().getValue().split(";")[0];
        if(contentType.equals("text/html")) {
            Document doc = Jsoup.parse(html, url, Parser.htmlParser());
            System.out.println("Title :- " + doc.getElementsByTag("title"));
        } else {
            System.out.println("Content is not html, hence no Title");
        }
        System.out.println("******************Title Ends*************");
    }

    private static void writeContent(String fileName, String html) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(fileName);
        outputStream.write(html.getBytes(StandardCharsets.UTF_8));
    }

    private static void printHeaders(HttpResponse response) {
        System.out.println("******************Response Headers*************");
        Header[] headers = response.getAllHeaders();
        for(Header header : headers){
            System.out.println(header);
            System.out.println("Key : " + header.getName() + " ,Value : " + header.getValue());
        }
        System.out.println("******************Response Headers Ends*************");
    }
}
