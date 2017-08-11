package com.ezreal.timeselectview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by wudeng on 2017/8/8.
 */

public class DateSelectView extends LinearLayout{

    private WheelView mWvYear;
    private WheelView mWvMonth;
    private WheelView mWvDay;

    /**
     * 线颜色
     */
    private int lineColor = 0xff007de0;
    /**
     * 线宽度
     */
    private float lineHeight = 2f;
    /**
     * 默认字体
     */
    private float normalFont = 14.0f;
    /**
     * 选中字体
     */
    private float selectedFont = 22.0f;
    /**
     * 单元格高度
     */
    private int unitHeight = 50;
    /**
     * 显示item个数
     */
    private int itemNumber = 7;
    /**
     * 默认字体颜色
     */
    private int normalColor = 0xff000000;
    /**
     * 选中字体颜色
     */
    private int selectedColor = 0xff007de0;
    /**
     * 蒙板高度
     */
    private float maskHeight = 48.0f;


    public DateSelectView(Context context) {
        this(context,null);
    }

    public DateSelectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();

        initDate();
        initListener();
    }

    public int getLineColor() {
        return lineColor;
    }

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
    }

    public float getLineHeight() {
        return lineHeight;
    }

    public void setLineHeight(float lineHeight) {
        this.lineHeight = lineHeight;
    }

    public float getNormalFont() {
        return normalFont;
    }

    public void setNormalFont(float normalFont) {
        this.normalFont = normalFont;
    }

    public float getSelectedFont() {
        return selectedFont;
    }

    public void setSelectedFont(float selectedFont) {
        this.selectedFont = selectedFont;
    }

    public int getUnitHeight() {
        return unitHeight;
    }

    public void setUnitHeight(int unitHeight) {
        this.unitHeight = unitHeight;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public int getNormalColor() {
        return normalColor;
    }

    public void setNormalColor(int normalColor) {
        this.normalColor = normalColor;
    }

    public int getSelectedColor() {
        return selectedColor;
    }

    public void setSelectedColor(int selectedColor) {
        this.selectedColor = selectedColor;
    }

    public float getMaskHeight() {
        return maskHeight;
    }

    public void setMaskHeight(float maskHeight) {
        this.maskHeight = maskHeight;
    }

    private void initView(){
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View rootView = layoutInflater.inflate(R.layout.view_time_picker, this);
        mWvYear = (WheelView) rootView.findViewById(R.id.wv_year);
        mWvMonth = (WheelView) rootView.findViewById(R.id.wv_month);
        mWvDay = (WheelView) rootView.findViewById(R.id.wv_day);
    }

    private void initDate(){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String[] split = sdf.format(new Date()).split("-");
        int currentYear = Integer.parseInt(split[0]);
        int currentMonth = Integer.parseInt(split[1]);
        int currentDay = Integer.parseInt(split[2]);

        mWvYear.setData(getYearData(currentYear));
        mWvYear.setDefault(1);
        mWvMonth.setData(getMonthData());
        mWvMonth.setDefault(currentMonth - 1);
        mWvDay.setData(getDayData(getMaxDay(currentYear, currentMonth)));
        mWvDay.setDefault(currentDay - 1);
    }


    private void initListener(){
        mWvYear.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                changeDayData();
            }

            @Override
            public void selecting(int id, String text) {

            }
        });

        mWvMonth.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                changeDayData();
            }

            @Override
            public void selecting(int id, String text) {

            }
        });
    }

    /**
     * 根据当前年份生成年份列表，填充 wheel view
     * @param currentYear 当前年份
     * @return 年份列表（1900 - currentYear + 1）
     */
    private ArrayList<String> getYearData(int currentYear) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = currentYear + 1; i >= 1900; i--) {
            list.add(String.valueOf(i));
        }
        return list;
    }

    /**
     * 生成月份列表，填充 wheel view
     * @return 月份列表
     */
    private ArrayList<String> getMonthData() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            list.add(String.valueOf(i));
        }
        return list;
    }

    /**
     * 根据本月最大天数生成日期列表,填充 wheel view
     * @param maxDay 当月最大天数
     * @return 日期列表
     */
    private ArrayList<String> getDayData(int maxDay){
        ArrayList<String> list = new ArrayList<>();
        for (int i=1;i <= maxDay;i++){
            list.add(String.valueOf(i));
        }
        return list;
    }

    /**
     * 根据选择的年份和月份，得到当月日期最大值（如闰年2月为29天）
     * @param year 当前年份
     * @param month 当前月份
     * @return 当月日期最大值
     */
    private int getMaxDay(int year,int month){
        if (month == 2){
            if (isLeapYear(year)){
                return 29;
            }else {
                return 28;
            }
        }else if (month == 1 || month == 3 || month == 5 || month == 7
                || month == 8 || month ==10 || month == 12){
            return 31;
        }else {
            return 30;
        }
    }

    /**
     * 判断当前年份是否是闰年
     * @param year 年份
     * @return true if  year is leapYear else false
     */
    private boolean isLeapYear(int year){
        return (year % 100 == 0 && year % 400 == 0)
                || (year % 100 != 0 && year % 4 == 0);
    }

    /**
     * 根据选择的年份和月份，更新日期的的数值
     */
    private void changeDayData(){
        int selectDay = getDay();
        int currentYear = getYear();
        int currentMonth = getMonth();
        int maxDay = getMaxDay(currentYear,currentMonth);
        ArrayList<String> day = getDayData(maxDay);
        if (day.size() != mWvDay.getData().size()){
            mWvDay.setData(day);
            if (selectDay > maxDay){
                mWvDay.setDefault(maxDay - 1);
            }else {
                mWvDay.setDefault(selectDay - 1);
            }

        }
    }

    /**
     * 获取当前选择的年份
     * @return year,like 2017
     */
    public int getYear(){
        return Integer.parseInt(mWvYear.getSelectedText());
    }

    /**
     * 获取当前选择的月份
     * @return month,like 8
     */
    public int getMonth(){
        return Integer.parseInt(mWvMonth.getSelectedText());
    }

    /**
     * 获取当前选择的日期
     * @return day,like 16
     */
    public int getDay(){
        return Integer.parseInt(mWvDay.getSelectedText());
    }


}
