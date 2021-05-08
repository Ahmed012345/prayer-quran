package com.zaker.android.sapeh.app.main.activitymain;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import com.zaker.android.sapeh.R;

import java.util.LinkedHashMap;
import java.util.Map;

public class ColorTextView extends AppCompatTextView {

    private LinkedHashMap<String, Integer> mTxtColors;
    private boolean mSpaces;

    public ColorTextView(Context context) {
        super(context);
    }

    public ColorTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        if(context.getString(R.string.lang).equals("ar")) {
            setTypeface(FontManger.yad);
        }if (context.getString(R.string.lang).equals("zh")) {
            setTypeface(FontManger.asian);
        }if (context.getString(R.string.lang).equals("cs")){
            setTypeface(FontManger.english);
        }if (context.getString(R.string.lang).equals("nl")){
            setTypeface(FontManger.english);
        }if (context.getString(R.string.lang).equals("en")){
            setTypeface(FontManger.english);
        } if(context.getString(R.string.lang).equals("fr")) {
            setTypeface(FontManger.english);
        }if (context.getString(R.string.lang).equals("de")) {
            setTypeface(FontManger.english);
        }if (context.getString(R.string.lang).equals("in")){
            setTypeface(FontManger.english);
        }if (context.getString(R.string.lang).equals("it")){
            setTypeface(FontManger.english);
        }if (context.getString(R.string.lang).equals("ja")){
            setTypeface(FontManger.asian);
        }if (context.getString(R.string.lang).equals("ko")){
            setTypeface(FontManger.asian);
        }if (context.getString(R.string.lang).equals("fa")){
            setTypeface(FontManger.yad);
        }if (context.getString(R.string.lang).equals("pl")){
            setTypeface(FontManger.english);
        } if(context.getString(R.string.lang).equals("pt")) {
            setTypeface(FontManger.english);
        }if (context.getString(R.string.lang).equals("ro")) {
            setTypeface(FontManger.english);
        }if (context.getString(R.string.lang).equals("ru")){
            setTypeface(FontManger.russi);
        }if (context.getString(R.string.lang).equals("es")){
            setTypeface(FontManger.english);
        }if (context.getString(R.string.lang).equals("th")){
            setTypeface(FontManger.english);
        }if(context.getString(R.string.lang).equals("ms")){
            setTypeface(FontManger.english);
        }if(context.getString(R.string.lang).equals("hi")){
            setTypeface(FontManger.english);
        }if(context.getString(R.string.lang).equals("bn")){
            setTypeface(FontManger.english);
        }if(context.getString(R.string.lang).equals("ur")){
            setTypeface(FontManger.yad);
        }if(context.getString(R.string.lang).equals("sv")){
            setTypeface(FontManger.english);
        }if(context.getString(R.string.lang).equals("sq")){
            setTypeface(FontManger.english);
        }if(context.getString(R.string.lang).equals("az")){
            setTypeface(FontManger.english);
        }if(context.getString(R.string.lang).equals("bs")){
        setTypeface(FontManger.english);
       }if(context.getString(R.string.lang).equals("bg")){
            setTypeface(FontManger.russi);
        }if(context.getString(R.string.lang).equals("ha")){
            setTypeface(FontManger.english);
        }if(context.getString(R.string.lang).equals("no")){
            setTypeface(FontManger.english);
        }if(context.getString(R.string.lang).equals("so")){
            setTypeface(FontManger.english);
        }if(context.getString(R.string.lang).equals("sw")){
            setTypeface(FontManger.english);
        }else if (context.getString(R.string.lang).equals("tr"))
            setTypeface(FontManger.english);

        setup(attrs);
    }

    public ColorTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup(attrs);
    }

    public void setup(AttributeSet attrs) {
        @SuppressLint("CustomViewStyleable")
        TypedArray attrsArray = getContext().obtainStyledAttributes(attrs, R.styleable.ColorTextViewAttrs, 0, 0);
        initAttributesArray(attrsArray);
        attrsArray.recycle();

        setColorWords();
    }

    public void setSpaces(boolean spaces) {
        this.mSpaces = spaces;
    }

    public boolean isSpaces() {
        return mSpaces;
    }

    private void initAttributesArray(TypedArray attrsArray) {

        if (attrsArray == null) return;

        String txt1 = attrsArray.getString(R.styleable.ColorTextViewAttrs_clt_txt1);
        int color1 = attrsArray.getColor(R.styleable.ColorTextViewAttrs_clt_color1, Color.BLACK);
        String txt2 = attrsArray.getString(R.styleable.ColorTextViewAttrs_clt_txt2);
        int color2 = attrsArray.getColor(R.styleable.ColorTextViewAttrs_clt_color2, Color.BLACK);
        String txt3 = attrsArray.getString(R.styleable.ColorTextViewAttrs_clt_txt3);
        int color3 = attrsArray.getColor(R.styleable.ColorTextViewAttrs_clt_color3, Color.BLACK);
        String txt4 = attrsArray.getString(R.styleable.ColorTextViewAttrs_clt_txt4);
        int color4 = attrsArray.getColor(R.styleable.ColorTextViewAttrs_clt_color4, Color.BLACK);
        String txt5 = attrsArray.getString(R.styleable.ColorTextViewAttrs_clt_txt5);
        int color5 = attrsArray.getColor(R.styleable.ColorTextViewAttrs_clt_color5, Color.BLACK);
        String txt6 = attrsArray.getString(R.styleable.ColorTextViewAttrs_clt_txt6);
        int color6 = attrsArray.getColor(R.styleable.ColorTextViewAttrs_clt_color6, Color.BLACK);
        String txt7 = attrsArray.getString(R.styleable.ColorTextViewAttrs_clt_txt7);
        int color7 = attrsArray.getColor(R.styleable.ColorTextViewAttrs_clt_color7, Color.BLACK);
        String txt8 = attrsArray.getString(R.styleable.ColorTextViewAttrs_clt_txt8);
        int color8 = attrsArray.getColor(R.styleable.ColorTextViewAttrs_clt_color8, Color.BLACK);
        String txt9 = attrsArray.getString(R.styleable.ColorTextViewAttrs_clt_txt9);
        int color9 = attrsArray.getColor(R.styleable.ColorTextViewAttrs_clt_color9, Color.BLACK);
        String txt10 = attrsArray.getString(R.styleable.ColorTextViewAttrs_clt_txt10);
        int color10 = attrsArray.getColor(R.styleable.ColorTextViewAttrs_clt_color10, Color.BLACK);
        String txt11 = attrsArray.getString(R.styleable.ColorTextViewAttrs_clt_txt11);
        int color11 = attrsArray.getColor(R.styleable.ColorTextViewAttrs_clt_color11, Color.BLACK);
        String txt12 = attrsArray.getString(R.styleable.ColorTextViewAttrs_clt_txt12);
        int color12 = attrsArray.getColor(R.styleable.ColorTextViewAttrs_clt_color12, Color.BLACK);
        String txt13 = attrsArray.getString(R.styleable.ColorTextViewAttrs_clt_txt13);
        int color13 = attrsArray.getColor(R.styleable.ColorTextViewAttrs_clt_color13, Color.BLACK);
        String txt14 = attrsArray.getString(R.styleable.ColorTextViewAttrs_clt_txt14);
        int color14 = attrsArray.getColor(R.styleable.ColorTextViewAttrs_clt_color14, Color.BLACK);
        String txt15 = attrsArray.getString(R.styleable.ColorTextViewAttrs_clt_txt15);
        int color15 = attrsArray.getColor(R.styleable.ColorTextViewAttrs_clt_color15, Color.BLACK);
        String txt16 = attrsArray.getString(R.styleable.ColorTextViewAttrs_clt_txt16);
        int color16 = attrsArray.getColor(R.styleable.ColorTextViewAttrs_clt_color16, Color.BLACK);
        String txt17 = attrsArray.getString(R.styleable.ColorTextViewAttrs_clt_txt17);
        int color17 = attrsArray.getColor(R.styleable.ColorTextViewAttrs_clt_color17, Color.BLACK);
        String txt18 = attrsArray.getString(R.styleable.ColorTextViewAttrs_clt_txt18);
        int color18 = attrsArray.getColor(R.styleable.ColorTextViewAttrs_clt_color18, Color.BLACK);
        String txt19 = attrsArray.getString(R.styleable.ColorTextViewAttrs_clt_txt19);
        int color19 = attrsArray.getColor(R.styleable.ColorTextViewAttrs_clt_color19, Color.BLACK);
        String txt20 = attrsArray.getString(R.styleable.ColorTextViewAttrs_clt_txt20);
        int color20 = attrsArray.getColor(R.styleable.ColorTextViewAttrs_clt_color20, Color.BLACK);
        String txt21 = attrsArray.getString(R.styleable.ColorTextViewAttrs_clt_txt21);
        int color21 = attrsArray.getColor(R.styleable.ColorTextViewAttrs_clt_color21, Color.BLACK);
        String txt22 = attrsArray.getString(R.styleable.ColorTextViewAttrs_clt_txt22);
        int color22 = attrsArray.getColor(R.styleable.ColorTextViewAttrs_clt_color22, Color.BLACK);
        String txt23 = attrsArray.getString(R.styleable.ColorTextViewAttrs_clt_txt23);
        int color23 = attrsArray.getColor(R.styleable.ColorTextViewAttrs_clt_color23, Color.BLACK);
        String txt24 = attrsArray.getString(R.styleable.ColorTextViewAttrs_clt_txt24);
        int color24 = attrsArray.getColor(R.styleable.ColorTextViewAttrs_clt_color24, Color.BLACK);
        String txt25 = attrsArray.getString(R.styleable.ColorTextViewAttrs_clt_txt25);
        int color25 = attrsArray.getColor(R.styleable.ColorTextViewAttrs_clt_color25, Color.BLACK);
        String txt26 = attrsArray.getString(R.styleable.ColorTextViewAttrs_clt_txt26);
        int color26 = attrsArray.getColor(R.styleable.ColorTextViewAttrs_clt_color26, Color.BLACK);

        mSpaces = attrsArray.getBoolean(R.styleable.ColorTextViewAttrs_clt_spaces, false);

        addTextColor(txt1, color1); addTextColor(txt2, color2); addTextColor(txt3, color3); addTextColor(txt4, color4);
        addTextColor(txt5, color5); addTextColor(txt6, color6); addTextColor(txt7, color7); addTextColor(txt8, color8);
        addTextColor(txt9, color9); addTextColor(txt10, color10); addTextColor(txt11, color11); addTextColor(txt12, color12);
        addTextColor(txt13, color13); addTextColor(txt14, color14); addTextColor(txt15, color15); addTextColor(txt16, color16);
        addTextColor(txt17, color17); addTextColor(txt18, color18); addTextColor(txt19, color19); addTextColor(txt20, color20);
        addTextColor(txt21, color21); addTextColor(txt22, color22); addTextColor(txt23, color23); addTextColor(txt24, color24);
        addTextColor(txt25, color25); addTextColor(txt26, color26);

    }

    private LinkedHashMap<String, Integer> getTxtColors() {
        if (mTxtColors == null) mTxtColors = new LinkedHashMap<>();
        return mTxtColors;
    }

    public void addTextColor(String text, int color) {
        if (text != null && text.trim().length() > 0 && color != 0) getTxtColors().put((isSpaces() ? " " : "") + text, color);
    }

    public void addTextColorRes(int text, int color) {
        addTextColor(getContext().getString(text), ContextCompat.getColor(getContext(),color));
    }

    public void apply() {

        LinkedHashMap<String, Integer> txtColors = (LinkedHashMap<String, Integer>) getTxtColors().clone();
        getTxtColors().clear();

        for (Map.Entry<String, Integer> entry : txtColors.entrySet())
            addTextColor(entry.getKey(), entry.getValue());
        setColorWords();
    }

    public void setColorWords() {

        String text = "";
        for (Map.Entry<String, Integer> entry : getTxtColors().entrySet()) text += entry.getKey();
        Spannable s = new SpannableString(text);

        for (Map.Entry<String, Integer> entry : getTxtColors().entrySet()) {
            String str = entry.getKey();
            int color = entry.getValue();
            if (!TextUtils.isEmpty(str)) s.setSpan(new ForegroundColorSpan(color), text.indexOf(str), text.indexOf(str) + str.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        setText(s, BufferType.SPANNABLE);
    }


}

