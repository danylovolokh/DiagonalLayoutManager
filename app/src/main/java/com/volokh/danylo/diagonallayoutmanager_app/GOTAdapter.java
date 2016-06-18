package com.volokh.danylo.diagonallayoutmanager_app;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class GOTAdapter extends RecyclerView.Adapter<GOTAdapter.SampleViewHolder> {

    private static final String TAG = GOTAdapter.class.getSimpleName();

    private final FragmentActivity mActivity;
    private final List<GOT_hero> mList;

    public GOTAdapter(FragmentActivity activity, List<GOT_hero> list) {
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
        GOT_hero hero = mList.get(position);

        holder.mTextView.setText(hero.quote);
        holder.mAvatar.setImageResource(hero.imageResource);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class SampleViewHolder extends RecyclerView.ViewHolder{

        private final TextView mTextView;
        private final ImageView mAvatar;

        public SampleViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.quote);
            mAvatar = (ImageView) itemView.findViewById(R.id.avatar);
        }
    }

}
