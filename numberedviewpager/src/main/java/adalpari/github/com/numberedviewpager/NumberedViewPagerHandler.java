package adalpari.github.com.numberedviewpager;

import static android.support.v4.view.ViewPager.SCROLL_STATE_DRAGGING;
import static android.support.v4.view.ViewPager.SCROLL_STATE_IDLE;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.view.PagerAdapter;
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

    private int numberCounterColor = Color.BLACK;
    private int numberCounterTextColor = Color.WHITE;

    private AnimatorSet numberAnimator;

    public NumberedViewPagerHandler(Context context) {
        super(context);
        initViews(context, null);
    }

    public NumberedViewPagerHandler(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context, attrs);
    }

    public NumberedViewPagerHandler(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context, attrs);
    }

    public NumberedViewPagerHandler(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initViews(context, attrs);
    }

    private void initViews(Context context, AttributeSet attrs) {
        initColors(context, attrs);
        initViewPager(context);
        initCounter(context);
        initAnimation(context);
        initNumberListener();
    }

    private void initColors(Context context, AttributeSet attrs) {
        if (context != null && attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.NumberCounter);

            try {
                numberCounterColor = a.getColor(R.styleable.NumberCounter_nc_color, Color.BLACK);
                numberCounterTextColor = a.getColor(R.styleable.NumberCounter_nc_text_color, Color.WHITE);
            } catch (Throwable throwable) {
            } finally {
                a.recycle();
            }
        }
    }

    private void initViewPager(Context context) {
        viewPager = new ViewPager(context);
        viewPager.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        this.addView(viewPager);
    }

    private void initCounter(Context context) {
        int paddingInPx = context.getResources().getDimensionPixelOffset(R.dimen.number_counter_padding);

        counter = new TextView(context);

        counter.setPadding(paddingInPx, paddingInPx, paddingInPx, paddingInPx);
        counter.setGravity(Gravity.RIGHT);
        counter.setLayoutParams(initLayoutParams(context));
        counter.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        counter.setTextColor(numberCounterTextColor);

        GradientDrawable roundedBackground = initCounterBackground();
        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            counter.setBackgroundDrawable(roundedBackground);
        } else {
            counter.setBackground(roundedBackground);
        }

        this.addView(counter);
    }

    private RelativeLayout.LayoutParams initLayoutParams(Context context) {
        final RelativeLayout.LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        int marginInPx = context.getResources().getDimensionPixelOffset(R.dimen.number_counter_margin);
        layoutParams.setMargins(marginInPx, marginInPx, marginInPx, marginInPx);

        return layoutParams;
    }

    private GradientDrawable initCounterBackground() {
        GradientDrawable roundedBackground = new GradientDrawable();
        roundedBackground.setShape(GradientDrawable.RECTANGLE);
        roundedBackground.setCornerRadii(new float[] { 20, 20, 20, 20, 20, 20, 20, 20 });
        roundedBackground.setColor(numberCounterColor);

        return roundedBackground;
    }

    private void initAnimation(Context context) {
        numberAnimator = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.hide);
        numberAnimator.setStartDelay(500);
        numberAnimator.setTarget(counter);
    }

    private void initNumberListener() {
        if (viewPager == null) {
            return;
        }

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
        if (viewPager != null && counter != null) {
            Context context = counter.getContext();

            int total = viewPager.getAdapter().getCount();
            int realPosition = position + 1;
            String text = context.getString(R.string.counter, realPosition, total);
            counter.setText(text);
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

    public void setAdapter(PagerAdapter pagerAdapter) {
        if (viewPager != null) {
            viewPager.setAdapter(pagerAdapter);
            setImageNumber(0);
        }
    }
}
