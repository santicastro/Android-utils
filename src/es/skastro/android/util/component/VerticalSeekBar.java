package es.skastro.android.util.component;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.SeekBar;

/***
 * Based on https://github.com/AndroSelva/Vertical-SeekBar-Android
 * 
 */
public class VerticalSeekBar extends SeekBar {

    public VerticalSeekBar(Context context) {
        super(context);
    }

    public VerticalSeekBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public VerticalSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(h, w, oldh, oldw);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(heightMeasureSpec, widthMeasureSpec);
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
    }

    protected void onDraw(Canvas c) {
        c.rotate(-90);
        c.translate(-getHeight(), 0);
        // if (getThumb() != null)
        // Log.d("thumb", "thumb " + getThumb()..toShortString());
        super.onDraw(c);
    }

    @Override
    public synchronized void setProgress(int progress) {
        super.setProgress(progress);
        Log.d("progress", "progress: " + progress);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isEnabled()) {
            return false;
        }
        setSelected(true);
        switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN:
        case MotionEvent.ACTION_MOVE:
        case MotionEvent.ACTION_UP:
            int i = getMax() - (int) (getMax() * event.getY() / getHeight());
            i = Math.min(getMax(), i);
            i = Math.max(0, i);
            if (i != lastValue) {
                lastValue = i;
                setProgress(i);
                onSizeChanged(getWidth(), getHeight(), 0, 0);
            }
            break;

        case MotionEvent.ACTION_CANCEL:
            break;
        }
        setSelected(false);
        return true;
    }

    private int lastValue = -1;

}
