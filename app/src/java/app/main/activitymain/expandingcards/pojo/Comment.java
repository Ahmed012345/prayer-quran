package com.zaker.android.sapeh.app.main.activitymain.expandingcards.pojo;

public class Comment {
    private int commentPersonName;
    private int commentText;
    private int commentDate;
    private int simple_Headbig;

    public Comment( int commentPersonName, int commentText, int commentDate, int simple_Headbig) {
        this.commentPersonName = commentPersonName;
        this.commentText = commentText;
        this.commentDate = commentDate;
        this.simple_Headbig = simple_Headbig;
    }


    public int getCommentPersonName() {
        return commentPersonName;
    }

    public void setCommentPersonName(int commentPersonName) {
        this.commentPersonName = commentPersonName;
    }

    public int getCommentText() {

        return commentText;
    }

    public void setCommentText(int commentText) {

        this.commentText = commentText;
    }


    public int getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(int commentDate) {
        this.commentDate = commentDate;
    }

    public int getSimple_Headbig() {
        return simple_Headbig;
    }

    public void setSimple_Headbig(int simple_Headbig) {
        this.simple_Headbig = simple_Headbig;
    }
}
