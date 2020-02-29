package com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_utils;

public class DataModle {

    public int drawableId, drawableId1, drawableId2;
    public String admob, admob1,admob2;


    public DataModle(int drawableId, int drawableId1, int drawableId2){

        this.drawableId=drawableId;
        this.drawableId1=drawableId1;
        this.drawableId2=drawableId2;
    }
    public DataModle(String admob, String admob1, String admob2){
        this.admob= admob;
        this.admob1= admob1;
        this.admob2= admob2;
    }

}
