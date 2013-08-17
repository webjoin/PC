/*
 * Copyright 2009 the original author or authors. Licensed under the JLERP
 * License, Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. Unless required by applicable law or agreed to
 * in writing, software distributed under the License is distributed on an "AS
 * IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pc.pconsumption.resources;

import java.util.List;

/**
 * Page POJO
 * 
 * @author Jack Dou
 * @since Sep 14, 2009
 * @param <T>
 */
public class Page<T> {
    private List<T> data;
    private long totalCount;
    /**
     * @return the data
     */
    public List<T> getData() {
        return data;
    }
    /**
     * @param data the data to set
     */
    public void setData(List<T> data) {
        this.data = data;
    }
    /**
     * @return the totalCount
     */
    public long getTotalCount() {
        return totalCount;
    }
    /**
     * @param totalCount the totalCount to set
     */
    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
}