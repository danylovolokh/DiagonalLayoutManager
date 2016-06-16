package com.volokh.danylo.diagonallayoutmanager_app;

import android.support.v7.widget.RecyclerView;

/**
 * Created by danylo.volokh on 6/16/16.
 */
public class DiagonalLayoutManager extends RecyclerView.LayoutManager {

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return null;
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        //We have nothing to show for an empty data set but clear any existing views
        int itemCount = getItemCount();
        if (itemCount == 0) {
            removeAllViews();
            return;
        }
    }
}
