/*
 * Copyright 2006 the original author or authors.
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
import java.io.InputStreamReader;
import java.io.Reader;
/**
 * Jaxrs utils
 * 
 * @author Jack Dou
 * @since Sep 11, 2009
 */
public final class JaxrsUtils {
    /**
     * private constructor.
     */
    private JaxrsUtils() {

    }

    /**
     * 转化读取输入流中的数据为字符串型
     * 
     * @param in 输入�?
     * @return 输入流转化后的字符串
     * @throws IOException 输入流转化字符串失败，则报IO异常
     */
    public static String inputStream2String(InputStream in) throws IOException {
        Reader reader = new InputStreamReader(in,SYS_CHARSET);
        StringBuilder sb = new StringBuilder();
        char[] c = new char[1024];
        int l;
        while ((l = reader.read(c)) != -1) {
            sb.append(c, 0, l);
        }
        return sb.toString();
    }
    
    /**
     * 系统编码设置
     */
    public static final String SYS_CHARSET = "UTF-8";
}
