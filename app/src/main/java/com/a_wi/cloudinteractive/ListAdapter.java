package com.a_wi.cloudinteractive;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {
    ArrayList<ListData> mArrayList;
    Context mContext;
    ViewHolder mViewHolder;
    LayoutInflater mLayoutInflater;

    //    定義重用物件
    class ViewHolder {
        ImageView img1;
        TextView id1;
        TextView title1;
    }

    public ListAdapter(Context context, ArrayList<ListData> arrayList) {
        this.mContext = context;
        this.mArrayList = arrayList;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) { //item未被創建時進行創建
            convertView = mLayoutInflater.inflate(R.layout.list_item, null);

            mViewHolder = new ViewHolder();
            mViewHolder.id1 = convertView.findViewById(R.id.tv_id1);

            mViewHolder.title1 = convertView.findViewById(R.id.tv_title1);

            mViewHolder.img1 = convertView.findViewById(R.id.imageView1);

            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        ListData listData = mArrayList.get(position);

        mViewHolder.id1.setText(listData.getId());
        mViewHolder.title1.setText(listData.getTitle());

        new DownloadImageTask(mViewHolder.img1,listData.getId())
                .execute(listData.getImgUrl());

        return convertView;
    }
}
