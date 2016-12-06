package uk.dom.quizapp.ui;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Dom on 06/12/2016.
 */
public class QuizViewPager extends ViewPager {

    public QuizViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public QuizViewPager(Context context) {
        super(context);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}
