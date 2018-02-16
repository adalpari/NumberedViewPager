package adalpari.github.com.numberedviewpager;

import static android.support.v4.view.ViewPager.SCROLL_STATE_DRAGGING;
import static android.support.v4.view.ViewPager.SCROLL_STATE_IDLE;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Adalberto Plaza on 16/02/2018.
 */

public class NumberedViewPagerHandler extends RelativeLayout {

    private ViewPager viewPager;
    private TextView counter;

    private AnimatorSet numberAnimator;

    public NumberedViewPagerHandler(Context context) {
        super(context);
        initViews(context);
    }

    public NumberedViewPagerHandler(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
    }

    public NumberedViewPagerHandler(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context);
    }

    public NumberedViewPagerHandler(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initViews(context);
    }

    private void initViews(Context context) {
        initViewPager(context);
        initCounter(context);
        initAnimation(context);
        initNumberListener();
    }

    private void initViewPager(Context context) {
        viewPager = new ViewPager(context);
        viewPager.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    private void initCounter(Context context) {
        final RelativeLayout.LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int marginInPx = context.getResources().getDimensionPixelOffset(R.dimen.number_counter_margin);
        layoutParams.setMargins(marginInPx, marginInPx, marginInPx, marginInPx);

        counter = new TextView(context);
        counter.setLayoutParams(layoutParams);

        int paddingInPx = context.getResources().getDimensionPixelOffset(R.dimen.number_counter_padding);
        counter.setPadding(paddingInPx, paddingInPx, paddingInPx, paddingInPx);
        counter.setGravity(Gravity.RIGHT | Gravity.TOP);

        Drawable roundedBackground = ContextCompat.getDrawable(context, R.drawable.round);
        final int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            counter.setBackgroundDrawable(roundedBackground);
        } else {
            counter.setBackground(roundedBackground);
        }

//        counter.setTextColor(R.);
        counter.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        counter.setText("TEXTO");
    }

    private void initAnimation(Context context) {
        numberAnimator = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.hide);
        numberAnimator.setStartDelay(500);
        numberAnimator.setTarget(counter);
    }

    private void initNumberListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                setImageNumber(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case SCROLL_STATE_DRAGGING:
                        fadeInImageNumber();
                        break;
                    case SCROLL_STATE_IDLE:
                        fadeOutImageNumber();
                        break;
                }
            }
        });
    }

    private void setImageNumber(int position) {
        int total = getChildCount();
        int realPosition = position + 1;
        if (counter != null) {
            counter.setText(realPosition + "/" + total);
        }
    }

    private void fadeOutImageNumber() {
        if (numberAnimator != null) {
            numberAnimator.start();
        }
    }

    private void fadeInImageNumber() {
        if (numberAnimator != null) {
            numberAnimator.cancel();
        }
        if (counter != null) {
            counter.setAlpha(1.0f);
        }
    }

    public ViewPager getViewPager() {
        return viewPager;
    }
}