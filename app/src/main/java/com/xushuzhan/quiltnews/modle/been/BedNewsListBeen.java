package com.xushuzhan.quiltnews.modle.been;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by xushuzhan on 2016/8/19.
 */
public class BedNewsListBeen {


    /**
     * errNum : 0
     * errMsg : success
     * retData : [{"title":"当年萨达姆为什么能被轻易抓捕，其中三个人物起了关键性作用","url":"http://toutiao.com/group/6302391997827744002/","abstract":"13年前，美国陆军中校史蒂夫.拉塞尔接到任务，就是追捕萨达姆。之后，他自己撰写了一本书，详细的讲述了是如何抓住萨达姆及其核心成员的。","image_url":"http://p3.pstatp.com/list/9700009aaf9392b768c"},{"title":"LOL神瞎一脚踢出了超次元，螳螂在回城的时空里被击杀了！","url":"http://toutiao.com/group/6295968072420786433/","abstract":"▲ 神瞎一脚踢出了超次元，螳螂在回城的时空里被击杀了！▲ 璀璨钻石的小兵▲ 德玛西亚变身\u2014\u2014盲僧▲ 继龙女开大装小龙，豹女变形装三狼，安妮火熊装buff之后，黄金脆皮鸡演个鸟！真的是瞎子啊！","image_url":"http://p9.pstatp.com/list/7fd0000c18740f871ff"},{"title":"手机特殊的功能！你知道多少？","url":"http://toutiao.com/group/6284809107565117698/","abstract":"手机一些特殊的功能！你知道多少？一：当不喜欢别人打搅自己的时候，又不想关机，给大家一个办法，使自己的号码变成空号。输入 *21*13800000000# ，按打电话时候的拨出键 。当别人拨打你的电话时候就你的号码就变成空号了。再输入 #21#，在按拨出键 又正常了。","image_url":"http://p1.pstatp.com/list/65d0000b27cdf8d7fc5"},{"title":"牛年出生人的命运","url":"http://www.12ky.com/sx/niu/11095.html#0-tsina-1-22675-397232819ff9a47a7b7e80a40613cfe1","abstract":"牛年出生的人做事小心谨慎，脚踏实地，不宜受到他人或是周围环境的影响，有着坚强的意志力。那么如此埋头苦干的人有着怎样的命运呢？牛年出生人的命运是怎样的呢？下面跟随华人开运网一起来看看吧。","image_url":"http://p3.pstatp.com/list/5ac000a5e83d9982e00"},{"title":"中国富人移民美国：偷拍土豪邻居大院子称超羡慕","url":"http://m.ce.cn/ttt/201606/27/t20160627_13207208.shtml","abstract":"如果让我用一种颜色形容达拉斯，我选择的色调是，灰。并非缺乏绿化，事实上，它绿化的非常好。我只是觉得这个城市是灰色的。完全不同于欧洲丰富的色彩，这里你感觉到的最多的是，灰色的楼，灰色的路。德州很多路是石板的，夏天高温，沥青会化掉。","image_url":"http://p3.pstatp.com/list/97a0005b530cd81a356"}]
     */

    private int errNum;
    private String errMsg;
    /**
     * title : 当年萨达姆为什么能被轻易抓捕，其中三个人物起了关键性作用
     * url : http://toutiao.com/group/6302391997827744002/
     * abstract : 13年前，美国陆军中校史蒂夫.拉塞尔接到任务，就是追捕萨达姆。之后，他自己撰写了一本书，详细的讲述了是如何抓住萨达姆及其核心成员的。
     * image_url : http://p3.pstatp.com/list/9700009aaf9392b768c
     */

    private List<RetDataBean> retData;

    public int getErrNum() {
        return errNum;
    }

    public void setErrNum(int errNum) {
        this.errNum = errNum;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public List<RetDataBean> getRetData() {
        return retData;
    }

    public void setRetData(List<RetDataBean> retData) {
        this.retData = retData;
    }

    public static class RetDataBean {
        private String title;
        private String url;
        @SerializedName("abstract")
        private String abstractX;
        private String image_url;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAbstractX() {
            return abstractX;
        }

        public void setAbstractX(String abstractX) {
            this.abstractX = abstractX;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }
    }
}
