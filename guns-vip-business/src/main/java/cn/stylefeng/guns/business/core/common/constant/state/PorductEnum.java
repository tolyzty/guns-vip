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
package cn.stylefeng.guns.business.core.common.constant.state;

import lombok.Getter;

/**
 *
 * @author tolyzty
 */
public class PorductEnum {
    @Getter
    public enum ProductStatus {
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

        ProductStatus(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public static String getDescription(String value) {
            if (value == null) {
                return "";
            } else {
                for (ProductStatus ms : ProductStatus.values()) {
                    if (ms.getCode().equals(value)) {
                        return ms.getMessage();
                    }
                }
                return "";
            }
        }

    }

    @Getter
    public enum ProductType  {
        /**
         * 类型相关
         */
        ALL("0", "全部"),
        ALL_01("1", "床品"),
        ALL_02("2", "枕头"),
        ALL_03("3", "靠背")
        ;
        String code;
        String message;

        ProductType(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public static String getDescription(String value) {
            if (value == null) {
                return "";
            } else {
                for (ProductType ms : ProductType.values()) {
                    if (ms.getCode().equals(value)) {
                        return ms.getMessage();
                    }
                }
                return "";
            }
        }

    }


    @Getter
    public enum ProductClassify  {
        /**
         * 新闻类型相关
         */
        ALL("0", "全部"),
        ALL_01("1", "电子类"),
        ALL_02("2", "家具类"),
        ALL_03("3", "食品类"),
        ALL_04("4", "其他类");
        String code;
        String message;

        ProductClassify(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public static String getDescription(String value) {
            if (value == null) {
                return "";
            } else {
                for (ProductClassify ms : ProductClassify.values()) {
                    if (ms.getCode().equals(value)) {
                        return ms.getMessage();
                    }
                }
                return "";
            }
        }

    }
}
