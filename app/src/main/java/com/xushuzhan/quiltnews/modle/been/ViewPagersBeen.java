package com.xushuzhan.quiltnews.modle.been;

import java.util.List;

/**
 * Created by xushuzhan on 2016/8/23.
 */
public class ViewPagersBeen {

    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2016-08-23 15:26","title":"自己没车也没私家车，滴滴租车会撼动神州地位吗？","description":"腾讯科技","picUrl":"http://inews.gtimg.com/newsapp_ls/0/510951060_300240/0","url":"http://tech.qq.com/a/20160823/032506.htm"},{"ctime":"2016-08-23 15:41","title":"全球主要的几家手机厂商，只有苹果和三星在赚钱","description":"腾讯科技","picUrl":"http://inews.gtimg.com/newsapp_ls/0/510993602_300240/0","url":"http://tech.qq.com/a/20160823/033164.htm"},{"ctime":"2016-08-23 12:05","title":"网约车细则出台前，没登记的私家车当专车还是算非法运营","description":"腾讯科技","picUrl":"http://inews.gtimg.com/newsapp_ls/0/510549173_300240/0","url":"http://tech.qq.com/a/20160823/024006.htm"},{"ctime":"2016-08-23 13:47","title":"你以为美图是个P图公司，其实95.1%收入都靠卖手机","description":"腾讯科技","picUrl":"http://inews.gtimg.com/newsapp_ls/0/510740927_300240/0","url":"http://tech.qq.com/a/20160823/027427.htm"},{"ctime":"2016-08-23 11:27","title":"房价不会再被拉高吧？用VR看房很逼真 买房会越来越轻松","description":"腾讯科技","picUrl":"http://inews.gtimg.com/newsapp_ls/0/510414490_300240/0","url":"http://tech.qq.com/a/20160823/022059.htm"},{"ctime":"2016-08-23 07:29","title":"传明年苹果要推三款iPhone 双曲屏、玻璃机身都有了","description":"腾讯科技","picUrl":"http://inews.gtimg.com/newsapp_ls/0/509866622_300240/0","url":"http://digi.tech.qq.com/a/20160823/004635.htm"},{"ctime":"2016-08-23 07:02","title":"总说\u201c假的\u201d 罗永浩会讲真话吗？","description":"腾讯科技","picUrl":"http://inews.gtimg.com/newsapp_ls/0/509909474_300240/0","url":"http://digi.tech.qq.com/a/20160823/003315.htm"},{"ctime":"2016-08-23 07:54","title":"乐视终端及会员费收入之谜：越卖越亏，净利率仅0.8%","description":"腾讯科技","picUrl":"http://inews.gtimg.com/newsapp_ls/0/509905378_300240/0","url":"http://tech.qq.com/a/20160823/006134.htm"},{"ctime":"2016-08-23 07:02","title":"锤子T3确认配圆形Home键 但发布会又跳票了？","description":"腾讯科技","picUrl":"http://inews.gtimg.com/newsapp_ls/0/510000533_300240/0","url":"http://digi.tech.qq.com/a/20160823/003305.htm"},{"ctime":"2016-08-23 09:42","title":"每秒2GB的网速不再是梦 用光传播就能实现","description":"腾讯科技","picUrl":"http://inews.gtimg.com/newsapp_ls/0/510111961_300240/0","url":"http://tech.qq.com/a/20160823/014600.htm"}]
     */

    private int code;
    private String msg;
    /**
     * ctime : 2016-08-23 15:26
     * title : 自己没车也没私家车，滴滴租车会撼动神州地位吗？
     * description : 腾讯科技
     * picUrl : http://inews.gtimg.com/newsapp_ls/0/510951060_300240/0
     * url : http://tech.qq.com/a/20160823/032506.htm
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
