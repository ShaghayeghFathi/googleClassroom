package com.example.test;

public class ExampleItem {
    private int mImageResourse;
    private String mText1;
    private String mText2;
    private String mText3;

    public ExampleItem(int ImageResourse,String text1,String text2,String text3)
    {
        mImageResourse=ImageResourse;
        mText1=text1;
        mText2=text2;
        mText3=text3;
    }
    public int getImageResourse(){
        return mImageResourse;
    }

    public String getmText1() {
        return mText1;
    }

    public String getmText2() {
        return mText2;
    }

    public String getmText3() {
        return mText3;
    }
}
