package com.radiusnetworks.ibeaconreference;

/**
 * Created by ���� on 2017/4/26.
 */
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by xuekai on 2016/10/26.
 */

public class MeterView extends View {
    private String color_outcircle = "#DEDEDE";
    private String color_bg_outcircle = "#2690F8";
    private String color_bg_incircle = "#58ADE4";
    private String color_progress = "#87CEEB";
    private String color_smart_circle = "#C2B9B0";
    private String color_indicator_left = "#E1DCD6";
    private String color_indicator_right = "#F4EFE9";

    /**
     * ��ǰ����
     */
    private int progress = 0;
    private String category = "";
    private String unit = "";
    /**
     * Ҫ�������ݵ�ʵ�ʿ��
     */
    private int contentWidth;
    /**
     * view��ʵ�ʿ��
     */
    private int viewWidth;
    /**
     * view��ʵ�ʸ߶�
     */
    private int viewHeight;
    /**
     * �⻷�ߵĿ��
     */
    private int outCircleWidth = 1;
    /**
     * �⻷�İ뾶
     */
    private int outCircleRadius = 0;
    /**
     * �ڻ��İ뾶
     */
    private int inCircleRedius = 0;
    /**
     * �ڻ����⻷�ľ���
     */
    private int outAndInDistance = 0;
    /**
     * �ڻ��Ŀ��
     */
    private int inCircleWidth = 0;
    /**
     * �̶��̾����������Բ�ľ���
     */
    private int dialOutCircleDistance = 0;
    /**
     * �������ĵ�����
     */
    private int[] centerPoint = new int[2];


    /**
     * �̶��ߵ�����
     */
    private int dialCount = 0;
    /**
     * ÿ�����γ���һ������
     */
    private int dialPer = 0;
    /**
     * ���ߵĳ���
     */
    private int dialLongLength = 0;
    /**
     * ���ߵĳ���
     */
    private int dialShortLength = 0;
    /**
     * �̶��߾���Բ����Զ�ľ���
     */
    private int dialRadius = 0;


    /**
     * Բ����ʼ�ĽǶ�
     */
    private int startAngle = 0;
    /**
     * Բ�������ĽǶ�
     */
    private int allAngle = 0;

    private Paint mPaint;
    /**
     * �̶��������ֵ�����
     */
    private int figureCount = 6;


    public MeterView(Context context) {
        this(context, null);
    }

    public MeterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MeterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        initValues();
    }

    /**
     * ��ʼ���ߴ�
     */
    private void initValues() {
        viewWidth = getMeasuredWidth();
        viewHeight = getMeasuredHeight();
        contentWidth = viewWidth > viewHeight ? viewHeight : viewWidth;
        outCircleRadius = contentWidth / 2 - outCircleWidth;
        outAndInDistance = (int) (contentWidth / 26.5);
        inCircleWidth = (int) (contentWidth / 18.7);
        centerPoint[0] = viewWidth / 2;
        centerPoint[1] = viewHeight / 2;
        inCircleRedius = outCircleRadius - outAndInDistance - inCircleWidth / 2;
        startAngle = 150;
        allAngle = 240;
        dialOutCircleDistance = inCircleWidth;

        dialCount = 50;
        dialPer = 5;
        dialLongLength = (int) (dialOutCircleDistance / 1.2);
        dialShortLength = (int) (dialLongLength / 1.8);
        dialRadius = inCircleRedius - dialOutCircleDistance;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawStatic(canvas);
        drawDynamic(canvas);

    }

    /**
     * ���ƾ�̬�Ĳ���
     *
     * @param canvas
     */
    private void drawStatic(Canvas canvas) {
        drawOutCircle(canvas);
        drawCircleWithRound(startAngle, allAngle, inCircleWidth, inCircleRedius, color_outcircle, canvas);
        drawDial(startAngle, allAngle, dialCount, dialPer, dialLongLength, dialShortLength, dialRadius, canvas);
        drawBackGround(canvas);
        drawFigure(canvas, figureCount);
    }

    private void drawFigure(Canvas canvas, int count) {
        int figure = 0;
        int angle;
        for (int i = 0; i < count; i++) {
            figure = (int) (100 / (1f * count - 1) * i);
            angle = (int) ((allAngle) / ((count - 1) * 1f) * i) + startAngle;
            int[] pointFromAngleAndRadius = getPointFromAngleAndRadius(angle, dialRadius - dialLongLength * 2);
            mPaint.setTextSize(15);
            mPaint.setTextAlign(Paint.Align.CENTER);
            canvas.save();
            canvas.rotate(angle + 90, pointFromAngleAndRadius[0], pointFromAngleAndRadius[1]);
            canvas.drawText(figure + unit, pointFromAngleAndRadius[0], pointFromAngleAndRadius[1], mPaint);
            canvas.restore();
        }
    }

    /**
     * ���ڲ㱳��
     *
     * @param canvas
     */
    private void drawBackGround(Canvas canvas) {
        mPaint.setColor(Color.parseColor(color_bg_outcircle));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(outCircleRadius / 3 / 2);
        canvas.drawCircle(centerPoint[0], centerPoint[1], outCircleRadius / 3, mPaint);
        mPaint.setColor(Color.parseColor(color_bg_incircle));
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(centerPoint[0], centerPoint[1], (outCircleRadius / 3f / 2), mPaint);
    }

    /**
     * ���̶���
     *
     * @param startAngle  ��ʼ���ĽǶ�
     * @param allAngle    �ܹ������ĽǶ�
     * @param dialCount   �ܹ����ߵ�����
     * @param per         ÿ����������һ�γ���
     * @param longLength  ����Ů�ĳ���
     * @param shortLength ���ߵĳ���
     * @param radius      ����Բ����Զ�ĵط��İ뾶
     */
    private void drawDial(int startAngle, int allAngle, int dialCount, int per, int longLength, int shortLength, int radius, Canvas canvas) {
        int length;
        int angle;
        for (int i = 0; i <= dialCount; i++) {
            angle = (int) ((allAngle) / (dialCount * 1f) * i) + startAngle;

            if (i % 5 == 0) {
                length = longLength;
            } else {
                length = shortLength;
            }
            drawSingleDial(angle, length, radius, canvas);
        }
    }

    /**
     * ���̶��е�һ����
     *
     * @param angle  �����ĽǶ�
     * @param length �ߵĳ���
     * @param radius ����Բ����Զ�ĵط��İ뾶
     */
    private void drawSingleDial(int angle, int length, int radius, Canvas canvas) {
        int[] startP = getPointFromAngleAndRadius(angle, radius);
        int[] endP = getPointFromAngleAndRadius(angle, radius - length);
        canvas.drawLine(startP[0], startP[1], endP[0], endP[1], mPaint);
    }

    /**
     * ��������Բ
     *
     * @param canvas
     */
    private void drawOutCircle(Canvas canvas) {
        mPaint.setStrokeWidth(outCircleWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.parseColor(color_outcircle));
        canvas.drawCircle(centerPoint[0], centerPoint[1], outCircleRadius, mPaint);
    }

    /**
     * ���ƶ�̬�Ĳ���
     *
     * @param canvas
     */
    private void drawDynamic(Canvas canvas) {

        drawProgress(progress, canvas);
        drawIndicator(progress, canvas);
        drawCurrentProgressTv(progress, canvas);
    }

    /**
     * ���Ƶ�ǰ����������
     *
     * @param progress
     * @param canvas
     */
    private void drawCurrentProgressTv(int progress, Canvas canvas) {
        mPaint.setTextSize(25);
        mPaint.setColor(Color.BLACK);
        mPaint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float baseLine1 = centerPoint[1] + (outCircleRadius / 20f * 11 - fontMetrics.top - fontMetrics.bottom);
        canvas.drawText("��ǰ"+this.category+progress+unit, centerPoint[0], baseLine1, mPaint);

    }

    /**
     * ��ָ���Լ����ı���
     *
     * @param progress
     * @param canvas
     */
    private void drawIndicator(int progress, Canvas canvas) {
        drawPointer(canvas);
        drawIndicatorBg(canvas);
    }

    /**
     * ָ�����Զ���İ뾶�Ϳ̶��ߵ�һ��
     */
    private void drawPointer(Canvas canvas) {
        RectF rectF = new RectF(centerPoint[0] - (int) (outCircleRadius / 3f / 2 / 2),
                centerPoint[1] - (int) (outCircleRadius / 3f / 2 / 2), centerPoint[0] + (int) (outCircleRadius / 3f / 2 / 2), centerPoint[1] + (int) (outCircleRadius / 3f / 2 / 2));
        int angle = (int) ((allAngle) / (100 * 1f) * progress) + startAngle;
        //ָ��Ķ�������
        int[] peakPoint = getPointFromAngleAndRadius(angle, dialRadius);
        //���㳯�ϣ����ĵײ��������
        int[] bottomLeft = getPointFromAngleAndRadius(angle - 90, (int) (outCircleRadius / 3f / 2 / 2));
        //���㳯�ϣ��Ҳ�ĵײ��������
        int[] bottomRight = getPointFromAngleAndRadius(angle + 90, (int) (outCircleRadius / 3f / 2 / 2));
        Path path = new Path();
        mPaint.setColor(Color.parseColor(color_indicator_left));
        path.moveTo(centerPoint[0], centerPoint[1]);
        path.lineTo(peakPoint[0], peakPoint[1]);
        path.lineTo(bottomLeft[0], bottomLeft[1]);
        path.close();
        canvas.drawPath(path, mPaint);
        canvas.drawArc(rectF, angle - 180, 100, true, mPaint);
        Log.e("InstrumentView", "drawPointer" + angle);


        mPaint.setColor(Color.parseColor(color_indicator_right));
        path.reset();
        path.moveTo(centerPoint[0], centerPoint[1]);
        path.lineTo(peakPoint[0], peakPoint[1]);
        path.lineTo(bottomRight[0], bottomRight[1]);
        path.close();
        canvas.drawPath(path, mPaint);

        canvas.drawArc(rectF, angle + 80, 100, true, mPaint);


    }

    private void drawIndicatorBg(Canvas canvas) {
        mPaint.setColor(Color.parseColor(color_smart_circle));
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(centerPoint[0], centerPoint[1], (outCircleRadius / 3f / 2 / 4), mPaint);
    }

    /**
     * ���ݽ��Ȼ�������
     *
     * @param progress ������Ϊ100.��СΪ0
     */
    private void drawProgress(int progress, Canvas canvas) {
        float ratio = progress / 100f;
        int angle = (int) (allAngle * ratio);
        drawCircleWithRound(startAngle, angle, inCircleWidth, inCircleRedius, color_progress, canvas);

    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress,String category,String unit) {
        this.progress = progress;
        this.category = category;
        this.unit = unit;
        invalidate();
    }

    /**
     * ��һ������ΪԲ����Բ������
     *
     * @param startAngle ���߿�ʼ�ĽǶ�
     * @param allAngle   �����߹��ĽǶ�
     * @param radius     ���ߵİ뾶
     * @param width      ���ߵĺ��
     */
    private void drawCircleWithRound(int startAngle, int allAngle, int width, int radius, String color, Canvas canvas) {
        mPaint.setStrokeWidth(width);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.parseColor(color));
        RectF rectF = new RectF(centerPoint[0] - radius, centerPoint[1] - radius, centerPoint[0] + radius, centerPoint[1] + radius);
        canvas.drawArc(rectF, startAngle, allAngle, false, mPaint);
        drawArcRoune(radius, startAngle, width, canvas);
        drawArcRoune(radius, startAngle + allAngle, width, canvas);
    }

    /**
     * ����Բ�����˵�Բ
     *
     * @param radius Բ���İ뾶
     * @param angle  ������Բ���Ķ��ٶȵ�λ��
     * @param width  Բ���Ŀ��
     */
    private void drawArcRoune(int radius, int angle, int width, Canvas canvas) {
        int[] point = getPointFromAngleAndRadius(angle, radius);
        mPaint.setStrokeWidth(0);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(point[0], point[1], width / 2, mPaint);
    }

    /**
     * ���ݽǶȺͰ뾶����һ���������
     *
     * @param angle
     * @param radius
     * @return
     */
    private int[] getPointFromAngleAndRadius(int angle, int radius) {
        double x = radius * Math.cos(angle * Math.PI / 180) + centerPoint[0];
        double y = radius * Math.sin(angle * Math.PI / 180) + centerPoint[1];
        return new int[]{(int) x, (int) y};
    }
}