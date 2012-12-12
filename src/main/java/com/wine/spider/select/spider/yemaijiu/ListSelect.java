package com.wine.spider.select.spider.yemaijiu;

import com.wine.spider.entity.ListEntity;
import com.wine.spider.entity.SearchEntity;
import com.wine.spider.select.Select;
import com.wine.spider.service.SearchService;
import com.wine.spider.util.UUIDUtil;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunday
 * Date: 12-12-11
 * Time: 下午11:44
 * To change this template use File | Settings | File Templates.
 */
@Service
public class ListSelect implements Select<ListEntity,SearchEntity> {
    private final static Logger logger = LoggerFactory.getLogger(ListSelect.class);

    @Override
    public List<ListEntity> execute(Document document,SearchEntity searchEntity) {
        Elements pageCountElement = document.select(".goods-page-min label");
        if(pageCountElement == null){
            if (logger.isWarnEnabled()){
                logger.warn("ListSelect.execute:找不到页码！serachId:"+searchEntity.getId());
            }
            return Collections.emptyList();
        }
        String pageCount = StringUtils.split(pageCountElement.text(),"/")[1];
        if (StringUtils.isBlank(pageCount)){
            if (logger.isWarnEnabled()){
                logger.warn("ListSelect.execute:页码等于0！serachId:"+searchEntity.getId());
            }
            return Collections.emptyList();
        }
        String path = "/z2-p";
        Integer count = Integer.valueOf(pageCount);
        List<ListEntity> result = new ArrayList<ListEntity>(count);
        for (int i = 1; i <= count; i++) {
            ListEntity listEntity = new ListEntity();
            listEntity.setSearchEntity(searchEntity);
            listEntity.setUri(path + i + "/");
            listEntity.setGmtCreate(new Date());
            listEntity.setGmtModified(new Date());
            listEntity.setUuid(UUIDUtil.random());
            result.add(listEntity);
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        URL url = new URL("http://list.yesmywine.com/z2-p1");

        Document doc = Jsoup.parse(url, 5000);
        Select select = new ListSelect();
        List<ListEntity> result = select.execute(doc,new SearchEntity());
        for (int i = 0; i < result.size(); i++) {
            ListEntity entity = result.get(i);
            System.out.println(entity.getUri());
        }
    }
}
