package com.busu.cyy.customview.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.busu.cyy.customview.R;
import com.busu.cyy.customview.Util.DateUtil;

import java.util.Calendar;

/**
 * Created by Cyy513 on 2018/4/17.
 */

public class CalanderView extends View {


    Paint mpaint;
    Paint titletxtpaint;
    Paint weektitlepaint;
    Paint solidCircle;
    Paint hollowCircle;

    private int width;
    private String date_text = "2018年3月";


    private int TITLERECTHEIGHT = 150;
    private int TITLEPO = TITLERECTHEIGHT/2;

    private int WEEKWIDTH;

    private String[] weeks ={"日","一","二","三","四","五","六"};
    public CalanderView(Context context) {
        super(context);
        init();
    }

    //初始化
    private void init() {

        mpaint = new Paint();
        mpaint.setColor(getResources().getColor(R.color.calender));
        mpaint.setStrokeWidth(150);
        mpaint.setAntiAlias(true);
        titletxtpaint = new Paint();
        titletxtpaint.setColor(Color.WHITE);
        titletxtpaint.setTextSize(50);
        titletxtpaint.setTextAlign(Paint.Align.CENTER);
        titletxtpaint.setAntiAlias(true);
        weektitlepaint = new Paint();
        weektitlepaint.setColor(getResources().getColor(R.color.calender));
        weektitlepaint.setTextSize(50);
        weektitlepaint.setTextAlign(Paint.Align.CENTER);
        weektitlepaint.setAntiAlias(true);

        solidCircle = new Paint();
        solidCircle.setColor(getResources().getColor(R.color.calender));
        solidCircle.setStyle(Paint.Style.FILL);
        solidCircle.setAntiAlias(true);

        hollowCircle = new Paint();
        hollowCircle.setColor(getResources().getColor(R.color.calender));
        hollowCircle.setStyle(Paint.Style.STROKE);
        hollowCircle.setAntiAlias(true);
    }

    public CalanderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CalanderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        WEEKWIDTH = width/7;
        setMeasuredDimension(widthMeasureSpec,heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制Title
        DrawTitle(canvas);
        //绘制日期
        DrawText(canvas,date_text);
        //绘制星期
        DrawWeekTxt(canvas,weeks);
        //绘制天数
        DrawDate(canvas);
    }

    private void DrawDate(Canvas canvas) {

        int IRow =0;
        int ICol =0;
        //计算绘制几行数据
        for (int i=1;i<=DateUtil.GetDayOfMonth();i++)
        {
            ICol = (i+DateUtil.getFirstDayWeek(2018,DateUtil.GetCurrentMonth())-1)%7;
            IRow = (i+DateUtil.getFirstDayWeek(2018,DateUtil.GetCurrentMonth())-1)/7;
            //绘制
            if (DateUtil.getFirstDayWeek(2018,DateUtil.GetCurrentMonth())==7)
            {

                canvas.drawText(i+"",WEEKWIDTH*ICol+WEEKWIDTH/2,TITLERECTHEIGHT*IRow+TITLERECTHEIGHT/2+TITLERECTHEIGHT,weektitlepaint);

            }else {

                canvas.drawText(i+"",WEEKWIDTH*ICol+WEEKWIDTH/2,TITLERECTHEIGHT*IRow+TITLERECTHEIGHT/2+TITLERECTHEIGHT*2,weektitlepaint);
            }
        }

    }

    //绘制星期数
    private void DrawWeekTxt(Canvas canvas, String[] weeks) {
        Rect rect = new Rect();
        for (int i=0;i<weeks.length;i++)
        {
            weektitlepaint.getTextBounds(weeks[i],0,1,rect);
            canvas.drawText(weeks[i],(WEEKWIDTH/2+i*WEEKWIDTH),TITLERECTHEIGHT*2-TITLEPO+30,weektitlepaint);
        }
    }

    //绘制标题上的日期
    private void DrawText(Canvas canvas, String date_text) {
        Rect rect = new Rect();
        titletxtpaint.getTextBounds(date_text,0,1,rect);
        canvas.drawText(date_text,width/2,TITLEPO+rect.height()/2,titletxtpaint);
    }

    private void DrawTitle(Canvas canvas) {

        RectF rectF = new RectF(0,0,width,TITLERECTHEIGHT);
        canvas.drawRect(rectF,mpaint);
    }
}