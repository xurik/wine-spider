package com.wine.spider.service;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunday
 * Date: 11/25/12
 * Time: 6:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, IllegalAccessException, InstantiationException {
        Document doc = Jsoup.connect("http://list.yesmywine.com/z2-p1").get();
        GroovyClassLoader groovyClassLoader = new GroovyClassLoader(ClassUtils.getDefaultClassLoader());
        GroovyObject groovyObject = null;
        Map<String, String> m = new HashMap<String, String>();
        m.put("name", "chenlb");
        File file = ResourceUtils.getFile("classpath:groovy/ProtoBusinessGroovyImpl.groovy");
        Class<?> groovyClass = groovyClassLoader.parseClass(file);

        groovyObject = (GroovyObject) groovyClass.newInstance();

        Object result = groovyObject.invokeMethod("foo", doc);
        System.out.println(result);
        Elements newsHeadlines = doc.select("li[data-goodsid]");
        Iterator<Element> iterator = newsHeadlines.iterator();
        while(!iterator.hasNext()){
            Element element = iterator.next();
            System.out.println(element.attr("data-goodsid"));
            Thread.sleep(3000);
        }
    }
}
