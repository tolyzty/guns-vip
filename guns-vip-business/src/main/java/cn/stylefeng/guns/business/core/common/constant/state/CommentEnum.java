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
public class CommentEnum {
    @Getter
    public enum commentStatus {
        /**
         * 新闻类型相关
         */
        ALL("0", "有效"),
        ALL_01("1", "逻辑删除"),
        ALL_02("2", "审核拒绝");
        String code;
        String message;

        commentStatus(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public static String getDescription(String value) {
            if (value == null) {
                return "";
            } else {
                for (commentStatus ms : commentStatus.values()) {
                    if (ms.getCode().equals(value)) {
                        return ms.getMessage();
                    }
                }
                return "";
            }
        }
    }
    @Getter
    public enum TopStatus {
        /**
         * 正常
         */
        topUp("1", "置顶"),
        /**
         * 删除
         */
        topDe("0", "默认");
        String code;
        String message;

        TopStatus(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public static String getDescription(String value) {
            if (value == null) {
                return "";
            } else {
                for (TopStatus ms : TopStatus.values()) {
                    if (ms.getCode().equals(value)) {
                        return ms.getMessage();
                    }
                }
                return "";
            }
        }

    }

    @Getter
    public enum CommentType {
        /**
         * 新闻类型相关
         */
        ALL("0", "全部"),
        ALL_01("1", "文章评论"),
        ALL_02("2", "留言"),
        ALL_03("3", "文章回复"),
        ALL_04("4","留言回复");
        String code;
        String message;

        CommentType(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public static String getDescription(String value) {
            if (value == null) {
                return "";
            } else {
                for (CommentType ms : CommentType.values()) {
                    if (ms.getCode().equals(value)) {
                        return ms.getMessage();
                    }
                }
                return "";
            }
        }

        public static String getDescriptionName(String name) {
            if (name == null) {
                return "";
            } else {
                for (CommentType ms : CommentType.values()) {
                    if (ms.getMessage().equals(name)) {
                        return ms.getCode();
                    }
                }
                return "";
            }
        }


    }
}
