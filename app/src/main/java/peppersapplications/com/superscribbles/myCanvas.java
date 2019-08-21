package peppersapplications.com.superscribbles;


import android.content.Context;
import android.graphics.*;
import android.util.*;
import android.view.*;
import android.graphics.Bitmap;

import java.util.ArrayList;



public class myCanvas extends View {
    private static final float TOUCH_TOLERANCE = 5;
    private float mX, mY;
    private Path mPath;
    private Paint mPaint;

    private ArrayList<Pathway> paths = new ArrayList<>();

    private int currentColor;
    private int brush;
    private Bitmap mBitmap;
    public Canvas mCanvas;
    private Paint mBitmapPaint = new Paint(Paint.DITHER_FLAG);

    public myCanvas(Context context) {
        this(context, null);
    }

    public myCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);


    }

    public void init(DisplayMetrics metrics) {
        int height = metrics.heightPixels;
        int width = metrics.widthPixels;

        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);

        currentColor = Color.WHITE;
        brush = 5;
    }





    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        mCanvas.drawColor(Color.TRANSPARENT);

        for (Pathway fp : paths) {
            mPaint.setColor(fp.color);
            mPaint.setStrokeWidth(fp.brush);


            mCanvas.drawPath(fp.path, mPaint);

        }

        canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
        canvas.restore();
    }

    private void touchStart(float x, float y) {
        mPath = new Path();
        Pathway fp = new Pathway(currentColor, brush, mPath);
        paths.add(fp);

        mPath.reset();
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }

    private void touchMove(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);

        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
    }

    private void touchUp() {
        mPath.lineTo(mX, mY);
    }

    public void erase(){
        paths.clear();
        mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        invalidate();

    }

    public void blackLines(){
        currentColor = Color.BLACK;
        invalidate();

    }

    public void whiteLines(){
        currentColor = Color.WHITE;
        invalidate();

    }

    public void redLines(){
        currentColor = Color.RED;
        invalidate();

    }

    public void blueLines(){
        currentColor = Color.BLUE;
        invalidate();

    }

    public void greenLines(){
        currentColor = Color.GREEN;
        invalidate();

    }

    public void grayLines(){
        currentColor = Color.DKGRAY;
        invalidate();

    }

    public void yellowLines(){
        currentColor = Color.YELLOW;
        invalidate();

    }

    public void pinkLines(){
        currentColor = Color.rgb(255,20,144);
        invalidate();

    }


    //used for screenshots
    public Bitmap sendBitMap() {
        Bitmap bit;

        bit = mBitmap;
        return bit;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN :
                touchStart(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE :
                touchMove(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP :
                touchUp();
                invalidate();
                break;
        }

        return true;
    }

    public class Pathway {
        public int color;
        int brush;
        public Path path;

        Pathway(int color, int brush, Path path) {
            this.color = color;
            this.brush = brush;
            this.path = path;
        }
    }
}
