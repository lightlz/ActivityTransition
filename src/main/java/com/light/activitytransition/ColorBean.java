package com.light.activitytransition;

/**
 * Created by light on 15/5/22.
 */
public class ColorBean {

    private String colorName;

    private int colorRes;

    public ColorBean(String colorName, int colorRes) {
        this.colorName = colorName;
        this.colorRes = colorRes;
    }

    public static ColorBean[] COLOR_BEAN = new ColorBean[]{

            new ColorBean("BAY",R.drawable.bay),
            new ColorBean("BEECH",R.drawable.beech),
            new ColorBean("BRIDGE",R.drawable.bridge),
            new ColorBean("LONELY",R.drawable.lonely),
            new ColorBean("SNOW",R.drawable.snow),
            new ColorBean("TAXI",R.drawable.taxi)
    };

    public static ColorBean getItem(int index){

        ColorBean bean = COLOR_BEAN[index];
        return  bean;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public int getColorRes() {
        return colorRes;
    }

    public void setColorRes(int colorRes) {
        this.colorRes = colorRes;
    }
}
