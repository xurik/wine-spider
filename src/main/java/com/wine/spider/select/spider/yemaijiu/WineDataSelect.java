package com.wine.spider.select.spider.yemaijiu;

import com.alibaba.fastjson.JSON;
import com.wine.spider.entity.ItemEntity;
import com.wine.spider.entity.WineDataEntity;
import com.wine.spider.helper.WineryHelper;
import com.wine.spider.model.SelectModel;
import com.wine.spider.select.Select;
import com.wine.spider.service.FileDownLoadService;
import com.wine.spider.service.impl.FileDownLoadServiceImpl;
import com.wine.spider.util.ElementUtil;
import com.wine.spider.util.JacksonUtil;
import net.sf.cglib.beans.BeanMap;
import org.apache.commons.lang.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created with IntelliJ IDEA.
 * User: sunday
 * Date: 12-12-17
 * Time: 上午10:23
 * To change this template use File | Settings | File Templates.
 */
public class WineDataSelect implements Select<WineDataEntity,ItemEntity> {
    private static final Logger logger = LoggerFactory.getLogger(WineDataSelect.class);
    @Autowired
    @Qualifier("imagePath")
    private String imagePath;
    @Autowired
    private FileDownLoadService fileDownLoadService;

    private static Map<String,String>detailMap;
    static {
        detailMap = new HashMap<String, String>();
        //参考年份
        detailMap.put("year","referenceYear");
        //葡萄酒种类
        detailMap.put("zonglei","wineKinds");
        //葡萄品种
        detailMap.put("pinzhong","grapeVarieties");
        //国家
        detailMap.put("changuo","country");
        //产区
        detailMap.put("chandi","region");
        //级别
        detailMap.put("jibie","rank");
        //酒精度
        detailMap.put("jiujingdu","alcoholicStrength");
        //规格
        detailMap.put("rongliang","standard");
        //色泽
        detailMap.put("sezhe","color");
        //香味
        detailMap.put("xiangwei","fragrance");
        //口感
        detailMap.put("kougan","mouthfeel");
        //配菜建议
        detailMap.put("caiyao","collocationDishes");
        //建议醒酒时间
        detailMap.put("time","sleepingTime");
        //最佳品尝温
        detailMap.put("pincwendu","tastingTemperature");
        //适用场合
        detailMap.put("canghe","occasion");
        //酒体
        detailMap.put("jiuti","wineBody");
    }
    /***
     *       图片属性
     *       */
    private final static String[] imageNames = new String[]{"longdesc","alt","src","original"};
    @Override
    public List<WineDataEntity> execute(Document document, ItemEntity itemEntity) {
        List<WineDataEntity> list = new ArrayList<WineDataEntity>();
        WineDataEntity wineDataEntity = new WineDataEntity();
        //图片
        List<String> images =  getImages(document, itemEntity);
        if (images != null && images.size()>0){
            wineDataEntity.setImages(JSON.toJSONString(images));
        }
        //中英文名和价格
        setFullNameAndPrices(document,wineDataEntity);
        //酒庄
        if (StringUtils.isBlank(wineDataEntity.getFullNameZh())){
            wineDataEntity.setWinery(WineryHelper.fromName(wineDataEntity.getFullNameZh()));
        }
        //详情
        setDetails(document, wineDataEntity);
        list.add(wineDataEntity) ;
        return list;
    }

    /**
     * 详情
     * @param document
     * @param wineDataEntity
     */
    private void setDetails(Document document,WineDataEntity wineDataEntity){
        Elements elements = document.select("div.xiangqing span");
        if (elements == null || elements.isEmpty()){
            return;
        }
        BeanMap beanMap = BeanMap.create(wineDataEntity);

        Iterator<Element> elementIterator =  elements.iterator();
        while (elementIterator.hasNext()){
            Element element = elementIterator.next();
            String className = element.className();
            String title = element.attr("title");
            if (StringUtils.isBlank(title)){
                continue;
            }
            if (detailMap.containsKey(className)) {
                beanMap.put(detailMap.get(className),title);
            }
        }
    }

    /**
     * 设置中英文名
     * @param document
     * @param wineDataEntity
     */
    private void setFullNameAndPrices(Document document,WineDataEntity wineDataEntity){
        Elements elements = document.select("div.promotionMiddleTop");
        if (elements == null || elements.isEmpty()){
            return;
        }
        Element element = elements.first();
        String zh =element.select("h1").text();
        String en = element.select("span").first().text();
        wineDataEntity.setFullNameZh(zh);
        wineDataEntity.setFullNameEn(en);
        Elements prices = element.select("li");
        if (prices.size()>0){
            Element p1 = prices.get(0).select("b").first();
            if (p1 == null){
                return;
            }
            wineDataEntity.setPrice1(p1.text());
        }
        if (prices.size()>1){
            Elements p2 = prices.get(1).select("ins");
            if (p2 ==null || p2.isEmpty()){
                return;
            }
            wineDataEntity.setPrice2(p2.get(0).text());
            if (p2.size()>1){
                wineDataEntity.setPrice3(p2.get(1).text());
            }
            if (p2.size()>2){
                wineDataEntity.setPrice4(p2.get(2).text());
            }
            if (p2.size()>3){
                wineDataEntity.setPrice5(p2.get(3).text());
            }
        }
    }

    /**
     * 获取图片
     * @param document
     * @param itemEntity
     * @return
     */
    private List<String> getImages(Document document, ItemEntity itemEntity){
        Elements elements = document.select("ul#image_list li img");
        Iterator<Element> images = elements.iterator();
        List<String> result = new ArrayList<String>();
        while (images.hasNext()){
            Element element = images.next();

            downImage(element, itemEntity.getId(), result);
        }
        return result;
    }

    /**
     * 下载图片
     * @param element
     * @param id
     * @param list
     * @return
     */
    private List<String> downImage(Element element,Long id,List<String> list){
        for (String a: imageNames){
            String url = element.attr(a);
            if (StringUtils.isBlank(url)){
                continue;
            }
            String fileName = "";
            try {
                fileName = url.substring(url.lastIndexOf("/")+1);
            } catch (Exception e){
                logger.error("url error!url:{}",url);
                continue;
            }

            fileName = "/"+id + "/" + fileName;
            list.add(fileName);
            fileDownLoadService.downLoad(imagePath + fileName, url);
        }
        return list;
    }
}
