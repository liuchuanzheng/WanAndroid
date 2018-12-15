package com.liuchuanzheng.wanandroid.modules.login.beans;

import java.util.List;

/**
 * @author 刘传政
 * @date 2018/12/15 0015 21:20
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
public class LoginResponseBean {
    /**
     * data : {"chapterTops":[],"collectIds":[7455,7371,5871,7494,3902,7604,7606,7654,7632],"email":"","icon":"","id":6321,"password":"","token":"","type":0,"username":"18501231486"}
     * errorCode : 0
     * errorMsg :
     */

    private DataBean data;
    private int errorCode;
    private String errorMsg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public static class DataBean {
        /**
         * chapterTops : []
         * collectIds : [7455,7371,5871,7494,3902,7604,7606,7654,7632]
         * email :
         * icon :
         * id : 6321
         * password :
         * token :
         * type : 0
         * username : 18501231486
         */

        private String email;
        private String icon;
        private int id;
        private String password;
        private String token;
        private int type;
        private String username;
        private List<?> chapterTops;
        private List<Integer> collectIds;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public List<?> getChapterTops() {
            return chapterTops;
        }

        public void setChapterTops(List<?> chapterTops) {
            this.chapterTops = chapterTops;
        }

        public List<Integer> getCollectIds() {
            return collectIds;
        }

        public void setCollectIds(List<Integer> collectIds) {
            this.collectIds = collectIds;
        }
    }
}
