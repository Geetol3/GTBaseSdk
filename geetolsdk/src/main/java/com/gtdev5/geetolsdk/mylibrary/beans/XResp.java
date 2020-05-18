package com.gtdev5.geetolsdk.mylibrary.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zl
 * 2020/05/15
 * 接口回调
 */
public class XResp implements Serializable {
    /**
     * 域名
     */
    private String host;
    private List<XRedirect> xRedirect;

    public static class XRedirect implements Serializable {
        /**
         * 接口名
         */
        private String action;
        /**
         * 别名
         */
        private String command;

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getCommand() {
            return command;
        }

        public void setCommand(String command) {
            this.command = command;
        }

        @Override
        public String toString() {
            return "XRedirect{" +
                    "action='" + action + '\'' +
                    ", command='" + command + '\'' +
                    '}';
        }
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public List<XRedirect> getxRedirect() {
        return xRedirect;
    }

    public void setxRedirect(List<XRedirect> xRedirect) {
        this.xRedirect = xRedirect;
    }

    @Override
    public String toString() {
        return "XResp{" +
                "host='" + host + '\'' +
                ", xRedirect=" + xRedirect +
                '}';
    }
}
