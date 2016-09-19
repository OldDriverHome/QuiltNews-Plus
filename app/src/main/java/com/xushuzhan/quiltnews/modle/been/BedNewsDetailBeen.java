package com.xushuzhan.quiltnews.modle.been;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by xushuzhan on 2016/9/18.
 */
public class BedNewsDetailBeen {

    /**
     * id : http://api.iclient.ifeng.com/ipadtestdoc?aid=113159973
     * type : doc
     * o : 1
     * documentId : imcp_113159973
     * class : 80070187
     */

    private MetaBean meta;
    /**
     * cate : http://share.iclient.ifeng.com/jpadnews?id=i.ifeng.com/aid=003&imgwidth=480&type=list&pagesize=20
     * documentId : imcp_113159973
     * title : 曼联已婚花心男德比后偷情 花言巧语骗女护士上床
     * author :
     * hasVideo : N
     * source : 凤凰体育
     * thumbnail : http://d.ifengimg.com/w150_h106_q75/p3.ifengimg.com/a/2016_39/df2db54df531331_size49_w640_h640.jpg
     * editTime : 2016/09/18 10:43:00
     * updateTime :
     * wapurl : http://i.ifeng.com/news?aid=113159973
     * channel :
     * introduction :
     * sharesummary :
     * wwwurl : http://t.ifeng.com/appshare/5184369.shtml
     * shareurl : http://share.iclient.ifeng.com/news/sharenews.f?aid=113159973
     * commentsUrl : http://t.ifeng.com/appshare/5184369.shtml
     * commentCount :
     * text : <p style="text-align:center"><img src="http://d.ifengimg.com/mw640_q75/p3.ifengimg.com/a/2016_39/df2db54df531331_size49_w640_h640.jpg" longdesc="" /></p><p>
     偷晴对象</p><p>
     凤凰体育讯 北京时间9月18日，英国《太阳报》消息，在曼彻斯特德比后，曼联大将瓦伦西亚就急匆匆的赶赴希尔顿酒店，他同一位身材丰满的女护士进行了“快餐”式的调情，随后他离开酒店回到家中，这样的一幕正好被狗仔队获悉。</p><p>
     曼彻斯特德比被誉为是身价6.6亿英镑的对决，两支球队都是众星云集，在这场德比前，瓦伦西亚一直是曼联主力，不过由于参加世预赛的缘故，瓦伦西亚一度有可能无法赶上这场比赛，最终，曼联俱乐部花费了6万英镑，用私人飞机将瓦伦西亚从秘鲁接回曼彻斯特，这才让穆帅拥有了这名飞翼。</p><p>
     虽然曼联为比赛做足了准备，可是瓦伦西亚却一门心思的将精力放在了搞外遇上。按照《太阳报》的说法，在Instagram上，瓦伦西亚和一名叫做Sophie Vagsaeter的女人认识了，她是一名兼职女护士，今年24岁。在社交网站上，瓦伦西亚向其发送了一些打招呼的文字，而且还附上晒日光浴和赤裸上身的照片，仿佛在展示自己的好身材，而这样赤裸裸的勾搭非常成功，两人很快约定了偷情的日子。</p><p>
     《太阳报》的消息称，在曼市德比的当天，这位24岁的女护士就来到了曼彻斯特，随后入主了一家希尔顿酒店。在曼市德比结束后数个小时，瓦伦西亚也现身，他甚至还戴着一顶帽子，或许是为了防止被认出来吧。在大约40分钟后，瓦伦西亚从酒店离开，行色匆匆的回家，而他的炮友在三天后才离开。</p><p>
     其实瓦伦西亚是有家室的人，不仅有老婆，而且还有一个10岁的女儿，不过他经常有桃色新闻传出，此前，瓦伦西亚就曾经利用自己的裸照和妹子约炮，看起来，这位曼联飞翼的套路依然没有变，变的只是那些床上的猎物。</p><p>
     （迪斯佩尔）</p>
     * img : [{"url":"http://d.ifengimg.com/mw640_q75/p3.ifengimg.com/a/2016_39/df2db54df531331_size49_w640_h640.jpg","size":{"width":"640","height":"640"}}]
     * program :
     * programNo :
     * sologan :
     * commentType : 0
     */

    private BodyBean body;

    public MetaBean getMeta() {
        return meta;
    }

    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public static class MetaBean {
        private String id;
        private String type;
        private int o;
        private String documentId;
        @SerializedName("class")
        private String classX;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getO() {
            return o;
        }

        public void setO(int o) {
            this.o = o;
        }

        public String getDocumentId() {
            return documentId;
        }

        public void setDocumentId(String documentId) {
            this.documentId = documentId;
        }

        public String getClassX() {
            return classX;
        }

        public void setClassX(String classX) {
            this.classX = classX;
        }
    }

    public static class BodyBean {
        private String cate;
        private String documentId;
        private String title;
        private String author;
        private String hasVideo;
        private String source;
        private String thumbnail;
        private String editTime;
        private String updateTime;
        private String wapurl;
        private String channel;
        private String introduction;
        private String sharesummary;
        private String wwwurl;
        private String shareurl;
        private String commentsUrl;
        private String commentCount;
        private String text;
        private String program;
        private String programNo;
        private String sologan;
        private String commentType;
        /**
         * url : http://d.ifengimg.com/mw640_q75/p3.ifengimg.com/a/2016_39/df2db54df531331_size49_w640_h640.jpg
         * size : {"width":"640","height":"640"}
         */

        private List<ImgBean> img;

        public String getCate() {
            return cate;
        }

        public void setCate(String cate) {
            this.cate = cate;
        }

        public String getDocumentId() {
            return documentId;
        }

        public void setDocumentId(String documentId) {
            this.documentId = documentId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getHasVideo() {
            return hasVideo;
        }

        public void setHasVideo(String hasVideo) {
            this.hasVideo = hasVideo;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getEditTime() {
            return editTime;
        }

        public void setEditTime(String editTime) {
            this.editTime = editTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getWapurl() {
            return wapurl;
        }

        public void setWapurl(String wapurl) {
            this.wapurl = wapurl;
        }

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public String getSharesummary() {
            return sharesummary;
        }

        public void setSharesummary(String sharesummary) {
            this.sharesummary = sharesummary;
        }

        public String getWwwurl() {
            return wwwurl;
        }

        public void setWwwurl(String wwwurl) {
            this.wwwurl = wwwurl;
        }

        public String getShareurl() {
            return shareurl;
        }

        public void setShareurl(String shareurl) {
            this.shareurl = shareurl;
        }

        public String getCommentsUrl() {
            return commentsUrl;
        }

        public void setCommentsUrl(String commentsUrl) {
            this.commentsUrl = commentsUrl;
        }

        public String getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(String commentCount) {
            this.commentCount = commentCount;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getProgram() {
            return program;
        }

        public void setProgram(String program) {
            this.program = program;
        }

        public String getProgramNo() {
            return programNo;
        }

        public void setProgramNo(String programNo) {
            this.programNo = programNo;
        }

        public String getSologan() {
            return sologan;
        }

        public void setSologan(String sologan) {
            this.sologan = sologan;
        }

        public String getCommentType() {
            return commentType;
        }

        public void setCommentType(String commentType) {
            this.commentType = commentType;
        }

        public List<ImgBean> getImg() {
            return img;
        }

        public void setImg(List<ImgBean> img) {
            this.img = img;
        }

        public static class ImgBean {
            private String url;
            /**
             * width : 640
             * height : 640
             */

            private SizeBean size;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public SizeBean getSize() {
                return size;
            }

            public void setSize(SizeBean size) {
                this.size = size;
            }

            public static class SizeBean {
                private String width;
                private String height;

                public String getWidth() {
                    return width;
                }

                public void setWidth(String width) {
                    this.width = width;
                }

                public String getHeight() {
                    return height;
                }

                public void setHeight(String height) {
                    this.height = height;
                }
            }
        }
    }
}
