package com.example.snowsonz.litetool.express.model;

import java.util.List;

/**
 * author: SnowsonZ
 * created on: 2018/5/7 21:33
 * description:
 */

/*  return:
   {
        "comCode":"",
        "num":"539408362786",
        "auto":[
            {
                "comCode":"zhongtong",
                "id":"",
                "noCount":57,
                "noPre":"539408",
                "startTime":""
            },
            {
                "comCode":"minghangkuaidi",
                "id":"",
                "noCount":3,
                "noPre":"539408",
                "startTime":""
            }
        ]
    }
*/


public class ExpressTypeModel {
    private String comCode;
    private String num;
    private List<ItemType> auto;

    public String getComCode() {
        return comCode;
    }

    public void setComCode(String comCode) {
        this.comCode = comCode;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public List<ItemType> getAuto() {
        return auto;
    }

    public void setAuto(List<ItemType> auto) {
        this.auto = auto;
    }

    public class ItemType {
        private String comCode;
        private String id;
        private long noCount;
        private String startTime;

        public String getComCode() {
            return comCode;
        }

        public void setComCode(String comCode) {
            this.comCode = comCode;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public long getNoCount() {
            return noCount;
        }

        public void setNoCount(long noCount) {
            this.noCount = noCount;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }
    }
}
