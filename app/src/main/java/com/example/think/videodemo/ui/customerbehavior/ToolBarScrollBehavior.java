package com.example.think.videodemo.ui.customerbehavior;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.OverScroller;

import com.example.think.videodemo.R;

import java.lang.ref.WeakReference;

public class ToolBarScrollBehavior extends CoordinatorLayout.Behavior<View> {

    private WeakReference<View> depencyView;

    private WeakReference<TabLayout> tabLayout;

    private OverScroller overScroller;

    private Handler handler;

    private boolean isScroll = false;

    private Context context;

    private ArgbEvaluator evaluator;

    public ToolBarScrollBehavior(Context context, AttributeSet attrs){

        super(context, attrs);
        this.context = context;
        overScroller = new OverScroller(context);
        handler = new Handler();
        evaluator = new ArgbEvaluator();
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {

        if(dependency != null && dependency.getId() == R.id.toolbar){

            depencyView = new WeakReference<>(dependency);
            tabLayout = new WeakReference<>((TabLayout)((LinearLayout)child).getChildAt(0));
            return true;

        }
        return false;
    }
    //颜色分配
    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        final float progress = Math.abs(dependency.getTranslationY() / (dependency.getHeight()));

        child.setTranslationY(dependency.getHeight() + dependency.getTranslationY());

        final int colorPrimary = getColor(R.color.colorPrimary);
        final int evalute1 = (Integer) evaluator.evaluate(progress, Color.WHITE, colorPrimary);
        final int evalute2 = (Integer) evaluator.evaluate(progress, colorPrimary, Color.WHITE);

        getTabLayoutView().setBackgroundColor(evalute1);
        getTabLayoutView().setTabTextColors(evalute2, evalute2);
        getTabLayoutView().setSelectedTabIndicatorColor(evalute2);

        return true;
    }

    @Override
    public boolean onLayoutChild(@NonNull CoordinatorLayout parent, @NonNull View child, int layoutDirection) {

        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
        if(params != null && params.height == CoordinatorLayout.LayoutParams.MATCH_PARENT){
            child.layout(0,0, parent.getWidth(), parent.getHeight());
            return true;
        }
        return super.onLayoutChild(parent, child, layoutDirection);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
                                       @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes) {
        return (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedScrollAccepted(@NonNull CoordinatorLayout coordinatorLayout,
                                       @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes) {
        isScroll = false;
        overScroller.abortAnimation();
        super.onNestedScrollAccepted(coordinatorLayout, child, directTargetChild, target, axes);
    }

    //处理向上滑动
    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout,
                                  @NonNull View child, @NonNull View target, int dx, int dy, @NonNull int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        if(dy < 0){
            return;
        }
        View dependencyView = getDependencyView();
        float transY = dependencyView.getTranslationY() - dy;
        if(transY < 0 && -transY < getToolbarSpreadHeight()){

            dependencyView.setTranslationY(transY);
            consumed[1] = dy;

        }
    }

    //处理向下滑动
    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
                               @NonNull View child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        if(dyUnconsumed > 0){

            return;

        }
        View dependencyView = getDependencyView();
        float transY = dependencyView.getTranslationY() - dyUnconsumed;
        if(transY < 0){

            dependencyView.setTranslationY(transY);

        }
    }

    @Override
    public boolean onNestedPreFling(@NonNull CoordinatorLayout coordinatorLayout,
                                    @NonNull View child, @NonNull View target, float velocityX, float velocityY) {
        return onUserStopDragging(velocityY);
    }


    private boolean onUserStopDragging(float velocity){

        View dependentView = getDependencyView();
        float translateY = dependentView.getTranslationY();
        float minHeaderTranslate = -(dependentView.getY() + getToolbarSpreadHeight());

        if (translateY == 0 || translateY == -getToolbarSpreadHeight()) {

            return false;

        }

        boolean targetState;
        if(Math.abs(velocity) <= 800){

            if(Math.abs(translateY ) < Math.abs(translateY - minHeaderTranslate)){

                targetState = false;

            }else{

                targetState = true;

            }

            velocity = 800;

        }else{

            if(velocity > 0){

                targetState = true;

            }else{

                targetState = false;

            }

        }

        float targetTranslateY = targetState ? minHeaderTranslate : -dependentView.getY();
        overScroller.startScroll(0, (int) translateY, 0, (int) (targetTranslateY), (int)(1000000 / Math.abs(velocity)));
        handler.post(flingRunnable);
        isScroll = true;

        return true;

    }

    private Runnable flingRunnable = new Runnable() {
        @Override
        public void run() {
            if(overScroller.computeScrollOffset()){

                getDependencyView().setTranslationY(overScroller.getCurrY());
                handler.post(this);

            }else{

                isScroll = false;

            }
        }
    };

    private int getToolbarSpreadHeight(){
        return context.getResources().getDimensionPixelOffset(R.dimen.actionbar_size_offset);
    }

    private View getDependencyView(){
        return depencyView.get();
    }

    private TabLayout getTabLayoutView(){
        return tabLayout.get();
    }

    private int getColor(int id){
        return ContextCompat.getColor(context, id);
    }

}
