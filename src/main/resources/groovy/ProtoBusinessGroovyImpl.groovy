import org.jsoup.select.Elements
import org.jsoup.nodes.Element

def foo(doc) {
    def list = [];
    Elements newsHeadlines = doc.select("li[data-goodsid]");
    Iterator<Element> iterator = newsHeadlines.iterator();
    while(iterator.hasNext()){
        Element element = iterator.next();
        String id =  element.attr("data-goodsid");
        list.add(id)
        Thread.sleep(1000);
    }
    return list;
}