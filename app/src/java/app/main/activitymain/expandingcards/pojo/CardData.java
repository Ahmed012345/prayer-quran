package com.zaker.android.sapeh.app.main.activitymain.expandingcards.pojo;


import com.zaker.android.sapeh.app.main.expandinglibrary.ECCardData;

import java.util.List;

public class CardData implements ECCardData<Comment> {

    private Integer headBackgroundResource;
    private Integer mainBackgroundResource;

    private List<Comment> listItems;






    public Integer getHeadBackgroundResource() {
        return headBackgroundResource;
    }

    public void setHeadBackgroundResource(Integer headBackgroundResource) {
        this.headBackgroundResource = headBackgroundResource;
    }

    public Integer getMainBackgroundResource() {
        return mainBackgroundResource;
    }

    public void setMainBackgroundResource(Integer mainBackgroundResource) {
        this.mainBackgroundResource = mainBackgroundResource;
    }


    @Override
    public List<Comment> getListItems() {
        return listItems;
    }

    public void setListItems(List<Comment> listItems) {
        this.listItems = listItems;
    }
}
