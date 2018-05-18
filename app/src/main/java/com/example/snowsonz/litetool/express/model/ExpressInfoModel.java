package com.example.snowsonz.litetool.express.model;

import java.util.List;

/**
 * author: SnowsonZ
 * created on: 2018/5/7 21:34
 * description:
 */
/* return
    {
        "message": "ok",
        "nu": "539408362786",
        "ischeck": "0",
        "condition": "00",
        "com": "zhongtong",
        "status": "200",
        "state": "0",
        "data": [
            {
                "time": "2018-05-07 20:25:31",
                "ftime": "2018-05-07 20:25:31",
                "context": "【天津市】 快件离开 【天津河西科大】 发往 【天津中转部】",
                "location": "天津河西科大"
            },
            {
                "time": "2018-05-07 19:27:49",
                "ftime": "2018-05-07 19:27:49",
                "context": "【天津市】 【天津河西科大】（15712292513） 的 任志勇 （18522101327） 已揽收",
                "location": "天津河西科大"
            }
        ]
    }
    */
public class ExpressInfoModel {
    private String message;
    private String nu;
    private String ischeck;
    private String condition;
    private String com;
    private String status;
    private String state;
    private List<InfoItemModel> data;

    public List<InfoItemModel> getData() {
        return data;
    }

    public void setData(List<InfoItemModel> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNu() {
        return nu;
    }

    public void setNu(String nu) {
        this.nu = nu;
    }

    public String getIscheck() {
        return ischeck;
    }

    public void setIscheck(String ischeck) {
        this.ischeck = ischeck;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public class InfoItemModel {
        private String time;
        private String ftime;
        private String context;
        private String location;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getFtime() {
            return ftime;
        }

        public void setFtime(String ftime) {
            this.ftime = ftime;
        }

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }
    }
}
