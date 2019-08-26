/**
 * Copyright 2018-2020 stylefeng & tolyzty (sn93@qq.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.stylefeng.guns.core.constant.state;

import lombok.Getter;

/**
 *
 * @author tolyzty
 */
public class NewsEnum  {
    @Getter
    public enum NewsStatus {
        /**
         * 正常
         */
        OK("0", "正常"),
        /**
         * 删除
         */
        HANG("1", "关闭");
        String code;
        String message;

        NewsStatus(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public static String getDescription(String value) {
            if (value == null) {
                return "";
            } else {
                for (NewsStatus ms : NewsStatus.values()) {
                    if (ms.getCode().equals(value)) {
                        return ms.getMessage();
                    }
                }
                return "";
            }
        }

    }

    @Getter
    public enum NewsType{
        /**
         * 新闻类型相关
         */
        ALL("0", "全部"),
        ALL_01("1", "新闻文章"),
        ALL_02("2", "行业动态"),
        ALL_03("3", "关于我们"),
        ALL_04("4", "热点新闻");
        String code;
        String message;

        NewsType(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public static String getDescription(String value) {
            if (value == null) {
                return "";
            } else {
                for (NewsType ms : NewsType.values()) {
                    if (ms.getCode().equals(value)) {
                        return ms.getMessage();
                    }
                }
                return "";
            }
        }

 /*       @Override
        public String getLabel() {
            return message;
        }

        @Override
        public String getValue() {
            return code;
        }*/
    }
}
