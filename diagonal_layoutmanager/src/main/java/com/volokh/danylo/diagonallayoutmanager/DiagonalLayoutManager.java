package com.volokh.danylo.diagonallayoutmanager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Created by danylo.volokh on 6/16/16.
 */
public class DiagonalLayoutManager extends RecyclerView.LayoutManager {

    private static final String TAG = DiagonalLayoutManager.class.getSimpleName();

    private final int mStepSize;
    private int mLastVisiblePosition;

    private int mFirstVisiblePosition;

    public DiagonalLayoutManager(Context context){
        mStepSize = context.getResources().getDimensionPixelSize(R.dimen.step_size);
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        //We have nothing to show for an empty data set but clear any existing views
        int itemCount = getItemCount();
        if (itemCount == 0) {
            removeAllViews();
            return;
        }

        mLastVisiblePosition = 0;
        mFirstVisiblePosition = 0;

        // TODO: add this to a step
        // TODO: add padding
        int viewTop = getPaddingTop();
        int viewLeft = getPaddingLeft();

        Log.v(TAG, "onLayoutChildren");

        boolean isLastLaidOutView;
        do{
            Log.v(TAG, "onLayoutChildren, do, mLastVisiblePosition " + mLastVisiblePosition);

            View view = recycler.getViewForPosition(mLastVisiblePosition);
            addView(view);
            measureChildWithMargins(view, 0, 0);

            int measuredWidth = getDecoratedMeasuredWidth(view);
            int measuredHeight = getDecoratedMeasuredHeight(view);

            Log.v(TAG, "onLayoutChildren, do, viewLeft " + viewLeft);
            Log.v(TAG, "onLayoutChildren, do, viewTop " + viewTop);
            Log.v(TAG, "onLayoutChildren, do, measuredWidth " + measuredWidth);
            Log.v(TAG, "onLayoutChildren, do, measuredHeight " + measuredHeight);


            layoutDecorated(view, viewLeft, viewTop, viewLeft + measuredWidth, viewTop + measuredHeight);

            // this assumes that all views are the same height
            viewLeft += mStepSize;
            viewTop += measuredHeight;

            mLastVisiblePosition++;

            isLastLaidOutView = isLastLaidOutView(view);

        } while(!isLastLaidOutView && mLastVisiblePosition < itemCount);

    }

    /**
     * TODO: question:
     * How to deal if device is in landscape.
     * This way view will reach the down edge before the right.
     *
     * @param view
     * @return
     */
    private boolean isLastLaidOutView(View view) {
        int left = view.getLeft(); // space from left edge of RecyclerView to left side of the view
        Log.v(TAG, "isLastLaidOutView, left " + left);
        Log.v(TAG, "isLastLaidOutView, mStepSize " + mStepSize);

        int nextViewLeft = left + mStepSize;
        Log.v(TAG, "isLastLaidOutView, width " + getWidth());
        Log.v(TAG, "isLastLaidOutView, leftPadding " + getPaddingLeft());
        Log.v(TAG, "isLastLaidOutView, rightPadding " + getPaddingRight());

        int recyclerViewRightEdge = getWidth() - getPaddingRight();

        if(nextViewLeft > recyclerViewRightEdge){
            return true;
        }

        return false;
    }

    @Override
    public boolean canScrollVertically() {
        return true;
    }

    @Override
    public boolean canScrollHorizontally() {
        return false;
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        Log.v(TAG, "scrollVerticallyBy dy " + dy);
        int childCount = getChildCount();
        Log.v(TAG, "scrollVerticallyBy childCount " + childCount);

        if (childCount == 0) {
            // we cannot scroll if we don't have views
            return 0;
        }

        boolean isFirstItemReached = isFirstItemReached();
        boolean isLastItemReached = isLastItemReached();

        View firstView = getChildAt(0);
        View lastView = getChildAt(
                getChildCount() - 1
        );

        /** scroll views*/
        int delta;

        if (dy > 0) { // Contents are scrolling up
            //Check against bottom bound
            if (isLastItemReached) {
                //If we've reached the last row, enforce limits
                int recyclerViewRightEdge = getWidth() - getPaddingRight();

                int rightOffset = recyclerViewRightEdge - lastView.getRight();
                delta = Math.max(-dy, rightOffset);
            } else {

                delta = -dy;
            }
        } else { // Contents are scrolling down
            //Check against top bound
            int leftOffset = firstView.getLeft();
            Log.v(TAG, "checkBoundsReached, leftOffset " + leftOffset);
            Log.v(TAG, "checkBoundsReached, dy " + dy);

            if (isFirstItemReached) {
                delta = -Math.max(dy, leftOffset);
            } else {
                delta = -dy;
            }
        }

        for (int indexOfView = 0; indexOfView < childCount; indexOfView++) {
            View view = getChildAt(indexOfView);

            view.offsetTopAndBottom(delta);
            view.offsetLeftAndRight(delta);
        }
        /** scroll views*/

        /** perform recycling*/
        if (delta < 0) {
            /** Scroll down*/
            recycleTopIfNeeded(firstView, recycler);
            addToBottomIfNeeded(lastView, recycler);

        } else {
            /** Scroll up*/
//            recycleBottomIfNeeded(lastView, recycler);
//            addTopIfNeeded(firstView, recycler);
        }
        /** perform recycling*/

        return delta;
    }

    private void addToBottomIfNeeded(View lastView, RecyclerView.Recycler recycler) {

        int recyclerViewRightEdge = getWidth() - getPaddingRight();

        int rightOffset = recyclerViewRightEdge - lastView.getRight();
        Log.v(TAG, "addToBottomIfNeeded, rightOffset " + rightOffset);

        if (rightOffset > 0) {
            int itemCount = getItemCount();

            Log.v(TAG, "addToBottomIfNeeded, itemCount " + itemCount);
            int nextPosition = mLastVisiblePosition + 1;
            Log.v(TAG, "addToBottomIfNeeded, nextPosition " + nextPosition);

            if (nextPosition <= itemCount) {
                Log.i(TAG, "addToBottomIfNeeded, add new view to bottom");

                View newLastView = recycler.getViewForPosition(nextPosition - 1);

                addView(newLastView);
                measureChildWithMargins(newLastView, 0, 0);

                int measuredWidth = getDecoratedMeasuredWidth(newLastView);
                int measuredHeight = getDecoratedMeasuredHeight(newLastView);
                // TODO: add this to a step
                // TODO: add padding
                int viewTop = lastView.getBottom();
                int viewLeft = lastView.getLeft() + mStepSize;

                layoutDecorated(newLastView, viewLeft, viewTop, viewLeft + measuredWidth, viewTop + measuredHeight);

                mLastVisiblePosition++;
            } else {
                // last view is the last item. Do nothing
            }
        }
    }

    private void recycleTopIfNeeded(View firstView, RecyclerView.Recycler recycler) {
        int recyclerViewLeftEdge = getPaddingLeft();
        boolean needRecycling = firstView.getRight() < recyclerViewLeftEdge;

        Log.v(TAG, "recycleTopIfNeeded, needRecycling " + needRecycling);

        if (needRecycling) {
            // first view is hidden
            Log.i(TAG, "recycleTopIfNeeded, recycling first view");

            removeView(firstView);
            mFirstVisiblePosition++;
            recycler.recycleView(firstView);
        }
    }

    private boolean isFirstItemReached() {
        boolean isFirstItemReached = mFirstVisiblePosition == 0;
        Log.v(TAG, "isFirstItemReached, " + isFirstItemReached);
        return isFirstItemReached;
    }

    private boolean isLastItemReached() {
        boolean isLastItemReached = mLastVisiblePosition == getItemCount();
        Log.v(TAG, "isLastItemReached " + isLastItemReached);
        return isLastItemReached;
    }


}
