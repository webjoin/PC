/*
 * Copyright 2008 the original author or authors.
 *
 * Licensed under the JLERP License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pc.pconsumption.resources;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;
/**
 * 对客户端上�?给服务器端的Json字符串进行反序列化为JavaBean对象
 * </br></br>
 * 客户端上送的json字符串必须符合如下例格式:
 * <pre>
 * {add:[{"name":"李四","sex":"0","age":"12"},
 *  {"name":"lisi","sex":"1","age":"14"},
 *  {"name":"lisi","sex":"0","age":"32"}]
 * }
 * </pre>
 * @author Jack Dou
 * @since Dec 10, 2008
 */
@Consumes("application/json")
@Provider
public class MapReader<E> implements MessageBodyReader<Map<String, List<E>>> {

    public boolean isReadable(Class<?> type, Type genericType,
            Annotation[] annotations, MediaType mediaType) {
        return type.isAssignableFrom(Map.class);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Map<String, List<E>> readFrom(Class<Map<String, List<E>>> type,
            Type genericType, Annotation[] annotations, MediaType mediaType,
            MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
            throws IOException, WebApplicationException {
        Map<String, List<E>> returnMap = new HashMap<String, List<E>>();
        String formData = JaxrsUtils.inputStream2String(entityStream);
        JSONObject fromObj = JSONObject.fromObject(formData);
        Class<E> cls = getActualClass(genericType);
        Set keys = fromObj.keySet();
        Iterator it = keys.iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            JSONArray arr = fromObj.getJSONArray(key);
            List<E> list = new ArrayList<E>();
            for (int i=0, size = arr.size(); i<size; i++) {
                JSONObject tempObj = (JSONObject) arr.get(i);
                JSONObject.toBean(tempObj, cls);
                list.add((E) JSONObject.toBean(tempObj, cls));
            }
            returnMap.put(key, list);               
        }
        return returnMap;
    }

    /**
     * 获取List中包含的实际模型类型
     * 
     * @param genericType
     * @return 实际模型类型
     */
    @SuppressWarnings({ "unchecked", "restriction" })
    public Class<E> getActualClass(Type genericType) {
        ParameterizedTypeImpl pti = (ParameterizedTypeImpl) genericType;
        Type[] types = pti.getActualTypeArguments();
        ParameterizedTypeImpl type = (ParameterizedTypeImpl) types[1];
        Type[] types2 = type.getActualTypeArguments();
        Class<E> type2 = (Class<E>) types2[0];
        return type2;
    }
}
