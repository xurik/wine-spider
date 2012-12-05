package com.wine.spider.web.converter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: sunday
 * Date: 12/4/12
 * Time: 12:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class MappingFastJson2JsonView extends AbstractView{
    /**
     * Default content type. Overridable as bean property.
     */
    public static final String DEFAULT_CONTENT_TYPE = "application/json";
    public final static Charset UTF8    = Charset.forName("UTF-8");

    private Charset             charset = UTF8;
    private boolean disableCaching = true;
    private SerializerFeature[] serializerFeature;
    private Set<String> modelKeys;
    private boolean extractValueFromSingleKeyModel = false;
    /**
     * Construct a new {@code JacksonJsonView}, setting the content type to {@code application/json}.
     */
    public MappingFastJson2JsonView() {
        setContentType(DEFAULT_CONTENT_TYPE);
        setExposePathVariables(false);
    }
    @Override
    protected void prepareResponse(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType(getContentType());
        response.setCharacterEncoding(this.charset.displayName());
        if (this.disableCaching) {
            response.addHeader("Pragma", "no-cache");
            response.addHeader("Cache-Control", "no-cache, no-store, max-age=0");
            response.addDateHeader("Expires", 1L);
        }
    }

    protected Object filterModel(Map<String, Object> model) {
        Map<String, Object> result = new HashMap<String, Object>(model.size());
        Set<String> renderedAttributes = (!CollectionUtils.isEmpty(this.modelKeys) ? this.modelKeys : model.keySet());
        for (Map.Entry<String, Object> entry : model.entrySet()) {
            if (!(entry.getValue() instanceof BindingResult) && renderedAttributes.contains(entry.getKey())) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        return (this.extractValueFromSingleKeyModel && result.size() == 1 ? result.values().iterator().next() : result);
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        OutputStream out = response.getOutputStream();
        byte[] bytes;
        Object value = filterModel(model);
        if (charset == UTF8) {
            if (serializerFeature != null) {
                bytes = JSON.toJSONBytes(value, serializerFeature);
            } else {
                bytes = JSON.toJSONBytes(value);
            }

        } else {
            String text;
            if (serializerFeature != null) {
                text = JSON.toJSONString(value, serializerFeature);
            } else {
                text = JSON.toJSONString(value);
            }
            bytes = text.getBytes(charset);
        }

        out.write(bytes);
    }

    public boolean isDisableCaching() {
        return disableCaching;
    }

    public void setDisableCaching(boolean disableCaching) {
        this.disableCaching = disableCaching;
    }

    public SerializerFeature[] getSerializerFeature() {
        return serializerFeature;
    }

    public void setSerializerFeature(SerializerFeature[] serializerFeature) {
        this.serializerFeature = serializerFeature;
    }

    public Set<String> getModelKeys() {
        return modelKeys;
    }

    public void setModelKeys(Set<String> modelKeys) {
        this.modelKeys = modelKeys;
    }

    public boolean isExtractValueFromSingleKeyModel() {
        return extractValueFromSingleKeyModel;
    }

    public void setExtractValueFromSingleKeyModel(boolean extractValueFromSingleKeyModel) {
        this.extractValueFromSingleKeyModel = extractValueFromSingleKeyModel;
    }
}
