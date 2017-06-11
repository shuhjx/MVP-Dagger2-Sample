package com.shuh.osplearning.model.impl;

import com.shuh.osplearning.base.BaseRecyclerAdapter;

/**
 * Created by pc-135 on 2017/6/7.
 */
public class HomeModel extends BaseRecyclerAdapter.BaseModel {

    //image size 304*228
    private String id, imgurl, docurl, time, title, channelname;
    private boolean has_content;

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "HomeModel{" +
                "id='" + id + '\'' +
                ", imgurl='" + imgurl + '\'' +
                ", docurl='" + docurl + '\'' +
                ", time='" + time + '\'' +
                ", title='" + title + '\'' +
                ", channelname='" + channelname + '\'' +
                ", has_content=" + has_content +
                '}';
    }

    //{
    // "imgurl":"http://cms-bucket.nosdn.127.net/catchpic/d/d9/d967adc6e2049a26118df414c5f6367a.jpg",
    // "has_content":true,
    // "docurl":"http://war.163.com/17/0607/08/CMAJUDLP000181KT.html",
    // "id":13333,
    // "time":"2017-06-07 08:34:04",
    // "title":"巴基斯坦军费将增7%:军人参与反恐工资将上涨10%",
    // "channelname":"war"}
    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getDocurl() {
        return docurl;
    }

    public void setDocurl(String docurl) {
        this.docurl = docurl;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getChannelname() {
        return channelname;
    }

    public void setChannelname(String channelname) {
        this.channelname = channelname;
    }

    public boolean isHas_content() {
        return has_content;
    }

    public void setHas_content(boolean has_content) {
        this.has_content = has_content;
    }
}
