package com.zaker.android.sapeh.app.main.recordview;



public interface OnRecordListener {
    void onStart();
    void onCancel();
    void onFinish(long recordTime);
    void onLessThanSecond();
}
