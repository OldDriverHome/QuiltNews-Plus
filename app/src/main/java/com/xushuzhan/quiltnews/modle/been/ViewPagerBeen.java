package com.xushuzhan.quiltnews.modle.been;

import java.util.List;

/**
 * Created by xushuzhan on 2016/9/18.
 */
public class ViewPagerBeen {

    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2016-09-18 17:32","title":"外媒：法国前总统希拉克因肺部感染住院","description":"搜狐国际","picUrl":"http://photocdn.sohu.com/20160918/Img468631831_ss.jpeg","url":"http://news.sohu.com/20160918/n468634381.shtml"},{"ctime":"2016-09-18 14:35","title":"美轰炸叙政府军致62死 俄美代表在联合国针锋相对","description":"搜狐国际","picUrl":"","url":"http://news.sohu.com/20160918/n468619846.shtml"},{"ctime":"2016-09-18 14:45","title":"美国一日内发生两起垃圾桶炸弹爆炸案 真凶不明","description":"搜狐国际","picUrl":"","url":"http://news.sohu.com/20160918/n468620364.shtml"},{"ctime":"2016-09-18 15:08","title":"俄驻纽约总领馆在曼哈顿发生爆炸后加强安全措施","description":"搜狐国际","picUrl":"","url":"http://news.sohu.com/20160918/n468625547.shtml"},{"ctime":"2016-09-18 15:13","title":"俄杜马选举开锣 反对派难求发声机会","description":"搜狐国际","picUrl":"","url":"http://news.sohu.com/20160918/n468622368.shtml"},{"ctime":"2016-09-18 15:27","title":"美国警方在纽约发现第三个爆炸装置 调查仍在进行","description":"搜狐国际","picUrl":"http://photocdn.sohu.com/20160918/Img468614984_ss.jpg","url":"http://news.sohu.com/20160918/n468624053.shtml"},{"ctime":"2016-09-18 15:27","title":"纽约曼哈顿爆炸亲历者：爆炸声比雷声还要响","description":"搜狐国际","picUrl":"http://photocdn.sohu.com/20160918/Img468614022_ss.jpeg","url":"http://news.sohu.com/20160918/n468623500.shtml"},{"ctime":"2016-09-18 15:34","title":"维州或成澳首个安乐死合法化州 过半内阁成员力挺","description":"搜狐国际","picUrl":"http://photocdn.sohu.com/20160918/Img468614326_ss.jpeg","url":"http://news.sohu.com/20160918/n468625565.shtml"},{"ctime":"2016-09-18 15:45","title":"克里米亚首次举行俄国家杜马选举：气氛很平静","description":"搜狐国际","picUrl":"http://photocdn.sohu.com/20160918/Img468622369_ss.jpg","url":"http://news.sohu.com/20160918/n468625490.shtml"},{"ctime":"2016-09-18 15:47","title":"快讯：中非共和国发生暴力事件 致至少20人死亡","description":"搜狐国际","picUrl":"http://photocdn.sohu.com/20160918/Img468623501_ss.jpeg","url":"http://news.sohu.com/20160918/n468625462.shtml"}]
     */

    private int code;
    private String msg;
    /**
     * ctime : 2016-09-18 17:32
     * title : 外媒：法国前总统希拉克因肺部感染住院
     * description : 搜狐国际
     * picUrl : http://photocdn.sohu.com/20160918/Img468631831_ss.jpeg
     * url : http://news.sohu.com/20160918/n468634381.shtml
     */

    private List<NewslistBean> newslist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }

    public static class NewslistBean {
        private String ctime;
        private String title;
        private String description;
        private String picUrl;
        private String url;

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
