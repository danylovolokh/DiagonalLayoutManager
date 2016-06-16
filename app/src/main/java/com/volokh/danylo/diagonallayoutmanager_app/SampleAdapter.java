package com.volokh.danylo.diagonallayoutmanager_app;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class SampleAdapter extends RecyclerView.Adapter<SampleAdapter.SampleViewHolder> {

    private static final String TAG = SampleAdapter.class.getSimpleName();

    private final FragmentActivity mActivity;
    private final List<String> mList;

    public SampleAdapter(FragmentActivity activity, List<String> list) {
        mActivity = activity;
        mList = list;
    }

    @Override
    public SampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.v(TAG, "onCreateViewHolder, viewType " + viewType);

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_item_layout, parent, false);
//        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
//        layoutParams.height = mActivity.getResources().getDisplayMetrics().widthPixels/3;
//        layoutParams.width = layoutParams.height;
        return new SampleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SampleViewHolder holder, int position) {
        Log.v(TAG, "onBindViewHolder, position " + position);
        holder.mTextView.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class SampleViewHolder extends RecyclerView.ViewHolder{

        private final TextView mTextView;

        public SampleViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.item_sample_text);
        }
    }

}
