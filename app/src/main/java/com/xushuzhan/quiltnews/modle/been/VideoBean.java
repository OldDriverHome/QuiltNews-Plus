package com.xushuzhan.quiltnews.modle.been;

/**
 * Created by xushuzhan on 2016/8/17.
 */
public class VideoBean {

    /**
     * status : 1
     * msg : ok
     * mp4 : http://k.youku.com/player/getFlvPath/sid/447144457466620992ca7_00/st/mp4/fileid/030020010057B2B81B2B8F059195D79EC62BB7-FA21-39CA-F309-46380E54E660?k=f95d7c2013e853ab2412b5db&hd=1&myp=0&ts=1400&r=s0KfeG/MpJGxT8zBRdWN/IHAQeg1csU4bRevfhnqJ6/BMgDT/rW3T8KIIPVPdImu4f4vx9wWnBpCUaDEeA6cBamz1TQQBUrIr852hd/R/s7FxG0COhQQ+Oy2Fdg/rC69&type=&sid=447144457466620992ca7&token=9667&oip=1863149095&did=b7c92863cc4b2382aba5c3d65c84f49d&ctype=20&ev=1&ep=3byowjhTHScB%2BzPA0EE4RrA3Am8ghY87XwGC7A9Y7rL47xppCmehkwJoWpX%2FClYEa5X%2FTRddSuyt%2FuSGTrMUgJpl0W9dDay85pnRdOfbOgdAkxjLsNFiEJ6P%2FQX8twqa
     * m3u8 : http://pl.youku.com/playlist/m3u8?ts=1471444574&keyframe=1&vid=XMTY4NjgwMTM0NA==&type=flv&sid=447144457466620992ca7&token=9667&oip=1863149095&ly=yunyouflvku&&did=b7c92863cc4b2382aba5c3d65c84f49d&ctype=20&ev=1&ep=3byowjhTHScB%2BzPA0EE4RizhifMj%2FmQqfEPz26gtGHh3shonuJgI7uYfL0kjVnqD
     */

    private String status;
    private String msg;
    private String mp4;
    private String m3u8;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMp4() {
        return mp4;
    }

    public void setMp4(String mp4) {
        this.mp4 = mp4;
    }

    public String getM3u8() {
        return m3u8;
    }

    public void setM3u8(String m3u8) {
        this.m3u8 = m3u8;
    }
}
