package com.xushuzhan.quiltnews.modle.been;

import java.util.List;

/**
 * Created by xushuzhan on 2016/8/17.
 */
public class VideoListBean {

    /**
     * total : 6545
     * page : 1
     * count : 1
     * videos : [{"id":"XMTY4NjMwOTIyNA==","title":"绝招！如何看穿\u2018盗嫂\u2019渣男【唐唐脱口秀】第110期","link":"http://v.youku.com/v_show/id_XMTY4NjMwOTIyNA==.html","thumbnail":"http://r3.ykimg.com/0542010157B21446641DA422DBC60892","bigThumbnail":"http://r3.ykimg.com/0541010157B21446641DA422DBC60892","thumbnail_v2":"http://r3.ykimg.com/0542010157B21446641DA422DBC60892","duration":473,"category":"搞笑","state":"normal","view_count":993785,"favorite_count":329,"comment_count":841,"up_count":2562,"down_count":16,"published":"2016-08-16 04:40:02","user":{"id":320108715,"name":"Big笑工坊","link":"http://i.youku.com/u/UMTI4MDQzNDg2MA=="},"operation_limit":[],"streamtypes":["3gphd","flvhd","hd","hd2","mp5hd2"],"public_type":"all","tags":"唐唐嘻游路,唐唐的烦恼生活,笑话视频,唐唐爱Game,万万没想到,唐唐神吐槽,唐唐脱口秀,恶搞,Big笑工坊,恶搞短片","day_vv":355545}]
     */

    private int total;
    private int page;
    private int count;
    /**
     * id : XMTY4NjMwOTIyNA==
     * title : 绝招！如何看穿‘盗嫂’渣男【唐唐脱口秀】第110期
     * link : http://v.youku.com/v_show/id_XMTY4NjMwOTIyNA==.html
     * thumbnail : http://r3.ykimg.com/0542010157B21446641DA422DBC60892
     * bigThumbnail : http://r3.ykimg.com/0541010157B21446641DA422DBC60892
     * thumbnail_v2 : http://r3.ykimg.com/0542010157B21446641DA422DBC60892
     * duration : 473
     * category : 搞笑
     * state : normal
     * view_count : 993785
     * favorite_count : 329
     * comment_count : 841
     * up_count : 2562
     * down_count : 16
     * published : 2016-08-16 04:40:02
     * user : {"id":320108715,"name":"Big笑工坊","link":"http://i.youku.com/u/UMTI4MDQzNDg2MA=="}
     * operation_limit : []
     * streamtypes : ["3gphd","flvhd","hd","hd2","mp5hd2"]
     * public_type : all
     * tags : 唐唐嘻游路,唐唐的烦恼生活,笑话视频,唐唐爱Game,万万没想到,唐唐神吐槽,唐唐脱口秀,恶搞,Big笑工坊,恶搞短片
     * day_vv : 355545
     */

    private List<VideosBean> videos;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<VideosBean> getVideos() {
        return videos;
    }

    public void setVideos(List<VideosBean> videos) {
        this.videos = videos;
    }

    public static class VideosBean {
        private String id;
        private String title;
        private String link;
        private String thumbnail;
        private String bigThumbnail;
        private String thumbnail_v2;
        private int duration;
        private String category;
        private String state;
        private String view_count;
        private int favorite_count;
        private int comment_count;
        private int up_count;
        private int down_count;
        private String published;
        /**
         * id : 320108715
         * name : Big笑工坊
         * link : http://i.youku.com/u/UMTI4MDQzNDg2MA==
         */

        private UserBean user;
        private String public_type;
        private String tags;
        private int day_vv;
        private List<?> operation_limit;
        private List<String> streamtypes;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getBigThumbnail() {
            return bigThumbnail;
        }

        public void setBigThumbnail(String bigThumbnail) {
            this.bigThumbnail = bigThumbnail;
        }

        public String getThumbnail_v2() {
            return thumbnail_v2;
        }

        public void setThumbnail_v2(String thumbnail_v2) {
            this.thumbnail_v2 = thumbnail_v2;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getView_count() {
            return view_count;
        }

        public void setView_count(String view_count) {
            this.view_count = view_count;
        }

        public int getFavorite_count() {
            return favorite_count;
        }

        public void setFavorite_count(int favorite_count) {
            this.favorite_count = favorite_count;
        }

        public int getComment_count() {
            return comment_count;
        }

        public void setComment_count(int comment_count) {
            this.comment_count = comment_count;
        }

        public int getUp_count() {
            return up_count;
        }

        public void setUp_count(int up_count) {
            this.up_count = up_count;
        }

        public int getDown_count() {
            return down_count;
        }

        public void setDown_count(int down_count) {
            this.down_count = down_count;
        }

        public String getPublished() {
            return published;
        }

        public void setPublished(String published) {
            this.published = published;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public String getPublic_type() {
            return public_type;
        }

        public void setPublic_type(String public_type) {
            this.public_type = public_type;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public int getDay_vv() {
            return day_vv;
        }

        public void setDay_vv(int day_vv) {
            this.day_vv = day_vv;
        }

        public List<?> getOperation_limit() {
            return operation_limit;
        }

        public void setOperation_limit(List<?> operation_limit) {
            this.operation_limit = operation_limit;
        }

        public List<String> getStreamtypes() {
            return streamtypes;
        }

        public void setStreamtypes(List<String> streamtypes) {
            this.streamtypes = streamtypes;
        }

        public static class UserBean {
            private int id;
            private String name;
            private String link;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }
        }
    }
}
