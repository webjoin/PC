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
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

/**
 * 对服务器端返回给客户端Page<T>类型进行序列化为JSON字符�? * 
 * @author Jack Dou
 * @since Sep 14, 2009
 * @param <T>
 */
@Produces(MediaType.APPLICATION_JSON)
@Provider
public class PageWriter<T> implements MessageBodyWriter<Page<T>> {

    public long getSize(Page<T> page, Class<?> type, Type genericType,
            Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    public boolean isWriteable(Class<?> type, Type genericType,
            Annotation[] annotations, MediaType mediaType) {
        return Page.class.isAssignableFrom(type);
    }

    public void writeTo(Page<T> data, Class<?> type, Type genericType,
            Annotation[] annotations, MediaType mediaType,
            MultivaluedMap<String, Object> httpHeaders, OutputStream out)
            throws IOException, WebApplicationException {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        out.write(JSONSerializer.toJSON(data, jsonConfig).toString().getBytes(
        		"UTF-8"));
    }
}
