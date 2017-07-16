package com.rd.animation;

import android.animation.IntEvaluator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by Andy on 2/7/17.
 */

public class SwapAnimation extends AbsAnimation<ValueAnimator> {

    private static final String ANIMATION_X_COORDINATE = "ANIMATION_X_COORDINATE";
    private static final int COORDINATE_NONE = -1;

    private int xStartCoordinate = COORDINATE_NONE;
    private int xEndCoordinate = COORDINATE_NONE;

    public SwapAnimation(@NonNull ValueAnimation.UpdateListener listener) {
        super(listener);
    }

    @NonNull
    @Override
    public ValueAnimator createAnimator() {
        ValueAnimator animator = new ValueAnimator();
        animator.setDuration(AbsAnimation.DEFAULT_ANIMATION_TIME);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                onAnimateUpdated(animation);
            }
        });

        return animator;
    }

    @Override
    public SwapAnimation progress(float progress) {
        if (animator != null) {
            long playTime = (long) (progress * animationDuration);

            if (animator.getValues() != null && animator.getValues().length > 0) {
                animator.setCurrentPlayTime(playTime);
            }
        }

        return this;
    }

    @NonNull
    public SwapAnimation with(int startValue, int endValue) {
        if (animator != null && hasChanges(startValue, endValue)) {

            xStartCoordinate = startValue;
            xEndCoordinate = endValue;

            PropertyValuesHolder holder = createColorPropertyHolder();
            animator.setValues(holder);
        }

        return this;
    }

    private PropertyValuesHolder createColorPropertyHolder() {
        PropertyValuesHolder holder = PropertyValuesHolder.ofInt(ANIMATION_X_COORDINATE, xStartCoordinate, xEndCoordinate);
        holder.setEvaluator(new IntEvaluator());

        return holder;
    }

    private void onAnimateUpdated(@NonNull ValueAnimator animation) {
        int xCoordinate = (int) animation.getAnimatedValue(ANIMATION_X_COORDINATE);

        if (listener != null) {
            listener.onSwapAnimationUpdated(xCoordinate);
        }
    }

    @SuppressWarnings("RedundantIfStatement")
    private boolean hasChanges(int startValue, int endValue) {
        if (xStartCoordinate != startValue) {
            return true;
        }

        if (xEndCoordinate != endValue) {
            return true;
        }

        return false;
    }
}
