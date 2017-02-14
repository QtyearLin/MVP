/*
 * Copyright (c) 2016 咖枯 <kaku201313@163.com | 3772304@qq.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package core.utils;

import android.util.SparseArray;

import java.util.HashMap;
import java.util.Map;

public class HostUtils {

    /**
     * 多少种Host类型
     */
    public static final int TYPE_COUNT = 4;

    /**
     * 网易
     */
    public static final int HOST_TYEP_DEFAULT = 1;

    /**
     * HOST_GANKIO
     */
    public static final int HOST_TYEP_GANK = 2;

    /**
     * HOST_DOUBAN
     */
    public static final int HOST_TYEP_DOUBAN = 3;

    /**
     * HOST_DONGTING
     */
    public static final int HOST_TYEP_DONGTING = 4;


    public static final String HOST = "http://c.m.163.com/";
    private final static String HOST_GANKIO = "http://gank.io/api";
    private final static String HOST_DOUBAN = "https://api.douban.com";
    private final static String HOST_DONGTING = "http://api.dongting.com";


    static SparseArray<String> sHostMap = new SparseArray<String>(TYPE_COUNT) {
        {
            put(HOST_TYEP_DEFAULT, HOST);
            put(HOST_TYEP_GANK, HOST_GANKIO);
            put(HOST_TYEP_DOUBAN, HOST_DOUBAN);
            put(HOST_TYEP_DONGTING, HOST_DONGTING);
        }

    };

    public static String getHostByType(int hostType) {
        return sHostMap.get(hostType, HOST);
    }
}
