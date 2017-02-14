package core.view;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import core.R;

/**
 * Created by Tyearlin on 2014/6/22
 */
public class CoreExpandTextView extends LinearLayout {
    private static final int defaultTextColor = Color.BLACK;
    private static final int defaultmTextSize = 18;
    private static final int defaultLine = 3;

    private TextView mContentView;
    private ImageView mExpandView;
    private Paint mPaint;

    private float mTextSize;
    private boolean isExpand;
    private int maxLine;

    public CoreExpandTextView(Context context) {
        super(context);
        initViews(context, null, 0, 0);
    }

    public CoreExpandTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context, attrs, 0, 0);
    }

    public CoreExpandTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CoreExpandTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initViews(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initViews(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        initalize();
        initWithAttrs(context, attrs);
    }


    /**
     * @param context
     * @param attrs
     */
    protected void initWithAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.StyleExpandText);
        int textColor = a.getColor(R.styleable.StyleExpandText_style_textColor, defaultTextColor);
        mTextSize = a.getDimensionPixelSize(R.styleable.StyleExpandText_style_textSize, defaultmTextSize);
        maxLine = a.getInt(R.styleable.StyleExpandText_style_maxLine, defaultLine);
        isExpand = a.getBoolean(R.styleable.StyleExpandText_style_expand, false);
        bindTextView(textColor, mTextSize);
        a.recycle();
    }

    protected void initalize() {
        setOrientation(VERTICAL);
        setGravity(Gravity.RIGHT);
        mContentView = new TextView(getContext());
        addView(mContentView, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        mExpandView = new ImageView(getContext());
        int padding = dip2px(getContext(), 5);
        mExpandView.setPadding(padding, padding, padding, padding);
        mExpandView.setImageResource(R.drawable.ic_s_expand_flag_);
        LayoutParams llp = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        addView(mExpandView, llp);

    }

    private void initExpand() {
        //只有当测量的行数大于最大的行数的时候
        if (measureTextLength() > maxLine) {
            if (isExpand) {
                //展开
                RotateAnimation animation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation
                        .RELATIVE_TO_SELF, 0.5f);
                animation.setDuration(0);
                animation.setFillAfter(true);
                mExpandView.startAnimation(animation);
            } else {
                int height = mContentView.getLineHeight() * maxLine;
                mContentView.setHeight(height);
            }
        }
    }

    @SuppressLint("NewApi")
    protected void bindTextView(int color, float size) {
        mContentView.setTextColor(color);
        mContentView.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
    }

    public void refreshText() {
        final float measureTextLength = measureTextLength();
        if (measureTextLength <= maxLine) {
            mExpandView.setVisibility(View.GONE);
        } else {
            initExpand();
            bindListener();
        }
    }

    /**
     * 判断textview的字数占几行
     *
     * @return
     */
    private float measureTextLength() {
        if (mPaint == null) {
            mPaint = new Paint();
        }
        mPaint.setTextSize(mContentView.getTextSize());
        float tvW = getContext().getResources().getDisplayMetrics().widthPixels;
        return (mPaint.measureText(mContentView.getText() + "") + 0.5f) / tvW;
    }

    protected void bindListener() {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                isExpand = !isExpand;
                mContentView.clearAnimation();
                final int deltaValue;
                final int startValue = mContentView.getHeight();
                int durationMillis = 350;
                if (isExpand) {
                    //展开
                    deltaValue = mContentView.getLineHeight() * mContentView.getLineCount() - startValue;
                    RotateAnimation animation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f,
                            Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setDuration(durationMillis);
                    animation.setFillAfter(true);
                    mExpandView.startAnimation(animation);
                } else {
                    //缩进
                    deltaValue = mContentView.getLineHeight() * maxLine - startValue;
                    RotateAnimation animation = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f,
                            Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setDuration(durationMillis);
                    animation.setFillAfter(true);
                    mExpandView.startAnimation(animation);
                }
                Animation animation = new Animation() {
                    protected void applyTransformation(float interpolatedTime, Transformation t) {
                        mContentView.setHeight((int) (startValue + deltaValue * interpolatedTime));
                    }

                };
                animation.setDuration(durationMillis);
                mContentView.startAnimation(animation);
            }
        });
    }

    public TextView getTextView() {
        return mContentView;
    }

    public void setText(CharSequence charSequence) {
        mContentView.setText(charSequence);
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
