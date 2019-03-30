package com.example.think.videodemo.ui.customerbehavior;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;

import com.example.think.videodemo.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class AvatarBehavior extends CoordinatorLayout.Behavior<CircleImageView>{

    //缩放动画变化的支点
    private static final float ANIM_CHANGE_POSINT = 0.2f;

    private Context context;
    //真个滚动的范围
    private int totalScrollRange;
    //AppBar的高度
    private int appBarHeight;
    //AppBar的宽度
    private int appBarWidth;
    //控件原始大小
    private int originalSize;
    //控件最终大小
    private int finalSize;
    //控件最终缩放的尺寸 设置坐标值需算上该值
    private float scaleSize;

    private float originalX;

    private float finalX;

    private float originalY;

    private float finalY;

    private int toolBarHeight;

    private float appBarStartY;

    private float percent;

    private DecelerateInterpolator moveYInterpolator;

    private AccelerateInterpolator moveXInterpolator;

    private CircleImageView finalView;

    private int finalViewMarginBottom;

    public AvatarBehavior(Context context, AttributeSet attrs){

        super(context, attrs);

        this.context = context;

        moveYInterpolator = new DecelerateInterpolator();

        moveXInterpolator = new AccelerateInterpolator();

        if(attrs != null){

            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AvatarImageBehavior);

            finalSize = (int) a.getDimension(R.styleable.AvatarImageBehavior_finalSize, 0);

            finalX = a.getDimension(R.styleable.AvatarImageBehavior_finalX, 0);

            toolBarHeight = (int) a.getDimension(R.styleable.AvatarImageBehavior_toolBarHeight, 0);

            a.recycle();

        }

    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull CircleImageView child, @NonNull View dependency) {

        return dependency instanceof AppBarLayout;

    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull CircleImageView child, @NonNull View dependency) {

        if(dependency instanceof AppBarLayout) {

            _initVariables(child, dependency);

            percent = (appBarStartY - dependency.getY()) * 1.0f / totalScrollRange;

            float percentY = moveYInterpolator.getInterpolation(percent);

            setViewY(child, originalY, finalY - scaleSize, percentY);

            if(percent > ANIM_CHANGE_POSINT){

                float scalePercent = (percent - ANIM_CHANGE_POSINT) / (1 - ANIM_CHANGE_POSINT);

                float percentX = moveXInterpolator.getInterpolation(scalePercent);

                scaleView(child, originalSize, finalSize, scalePercent);

                setViewX(child, originalX, finalX - scaleSize, percentX);

            }else{

                scaleView(child, originalSize, finalSize, 0);

                setViewX(child, originalX, finalX - scaleSize, 0);

            }

            if(finalView != null){

                if(percentY == 1.0f){

                    finalView.setVisibility(View.VISIBLE);

                }else{

                    finalView.setVisibility(View.GONE);

                }

            }

        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && dependency instanceof CollapsingToolbarLayout){

            if(finalView == null && finalSize != 0 && finalX != 0 && finalViewMarginBottom != 0){

                finalView = new CircleImageView(context);

                finalView.setVisibility(View.GONE);

                ((CollapsingToolbarLayout) dependency).addView(finalView);

                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) finalView.getLayoutParams();

                params.width = finalSize;

                params.height = finalSize;

                params.gravity = Gravity.BOTTOM;

                params.leftMargin = (int) finalX;

                params.bottomMargin = finalViewMarginBottom;

                finalView.setLayoutParams(params);

                finalView.setImageDrawable(child.getDrawable());

                finalView.setBorderColor(child.getBorderColor());

                int borderWidth = (int) ((finalSize * 1.0f / originalSize) * child.getBorderWidth());

                finalView.setBorderWidth(borderWidth);

            }

            if(finalView != null && finalSize != 0 && finalX != 0 && finalViewMarginBottom != 0){

                finalView.setImageDrawable(child.getDrawable());

            }

        }

        return true;

    }

    private void setViewX(View view, float originalX, float finalX, float percent) {

        float calcX = (finalX - originalX) * percent + originalX;

        view.setX(calcX);

    }

    private void setViewY(View view, float originalY, float finalY, float percent) {

        float calcY = (finalY - originalY) * percent + originalY;

        view.setY(calcY);

    }

    private void scaleView(View view, int originalSize, int finalSize, float percent) {

        float calcSize = (finalSize - originalSize) * percent + originalSize;

        float caleScale = calcSize / originalSize;

        view.setScaleX(caleScale);

        view.setScaleY(caleScale);

    }


    private void _initVariables(CircleImageView child, View dependency) {

        if(appBarHeight == 0){

            appBarHeight = dependency.getHeight();

            appBarStartY = dependency.getY();

        }

        if(totalScrollRange == 0){

            totalScrollRange = ((AppBarLayout) dependency).getTotalScrollRange();

        }

        if(originalSize == 0){

            originalSize = child.getWidth();

        }

        if(finalSize == 0){

            finalSize = context.getResources().getDimensionPixelSize(R.dimen.avatar_final_size);

        }

        if(appBarWidth == 0){

            appBarWidth = dependency.getWidth();

        }

        if(originalX == 0){

            originalX = child.getX();

        }

        if(finalX == 0){

            finalX = context.getResources().getDimensionPixelSize(R.dimen.avatar_final_x);

        }

        if(originalY == 0){

            originalY = child.getY();

        }

        if(finalY == 0){

            if(toolBarHeight == 0){

                toolBarHeight = context.getResources().getDimensionPixelSize(R.dimen.toolbar_height);

            }

            int statusBarHeight = context.getResources().getDimensionPixelSize(R.dimen.status_bar_height);

            finalY = (toolBarHeight - finalSize) / 2 + appBarStartY + statusBarHeight;

        }

        if(scaleSize == 0){

            scaleSize = (originalSize - finalSize) * 1.0f / 2;

        }

        if(finalViewMarginBottom == 0){

            finalViewMarginBottom = (toolBarHeight - finalSize) / 2;

        }

    }
}
