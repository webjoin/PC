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
import java.util.HashSet;
import java.util.Iterator;
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
 * 
 * @author Jack Dou
 * @since Dec 10, 2008
 */
@Consumes("application/json")
@Provider
public class SetReader<E> implements MessageBodyReader<Set<E>> {
    private Set<E> returnSet = new HashSet<E>();
    public boolean isReadable(Class<?> type, Type genericType,
            Annotation[] annotations, MediaType mediaType) {
        return type.isAssignableFrom(Set.class);
    }
    
    @SuppressWarnings("unchecked")
    public Set<E> readFrom(Class<Set<E>> type, Type genericType,
            Annotation[] annotations, MediaType mediaType,
            MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
            throws IOException, WebApplicationException {
        String formData = JaxrsUtils.inputStream2String(entityStream);
        JSONObject fromObj = JSONObject.fromObject(formData);
        Iterator<?> it = fromObj.keys();
        JSONArray arr = (JSONArray) fromObj.getJSONArray((String) it.next());
        Class<E> cls = getActualClass(genericType);
        for (int i=0, size = arr.size(); i<size; i++) {
            JSONObject tempObj = (net.sf.json.JSONObject) arr.get(i);
            returnSet.add((E) JSONObject.toBean(tempObj, cls));
        }
        return returnSet;
    }
    
    /**
     * 获取Set中包含的实际模型类型
     * 
     * @param genericType
     * @return 实际模型类型
     */
    @SuppressWarnings("unchecked")
    public Class<E> getActualClass(Type genericType) {
        ParameterizedTypeImpl pti = (ParameterizedTypeImpl) genericType;
        Type[] types = pti.getActualTypeArguments();
        Class<E> type = (Class<E>) types[0];
        return type;
    }
}
