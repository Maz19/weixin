package com.mazheng.jingxuan;

import java.util.List;

import com.bumptech.glide.Glide;
import com.mazheng.jingxuan.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	private Context context;
	private List<NewsList> newLists;
	private LayoutInflater layoutInflater;

	public MyAdapter(Context context, List<NewsList> newLists) {
		super();
		this.context = context;
		this.newLists = newLists;
		this.layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return newLists.size();
	}

	@Override
	public NewsList getItem(int position) {
		return newLists.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		NewsList newList = getItem(position);
		ViewHolder holder;

		if (convertView == null) {
			holder = new ViewHolder();
			convertView = layoutInflater.inflate(R.layout.item_listview, null);
			holder.tvTitle = (TextView) convertView
					.findViewById(R.id.tv_item_title);
			holder.tvTitle2 = (TextView) convertView
					.findViewById(R.id.tv_item_title2);
			holder.tvTime = (TextView) convertView.findViewById(R.id.tv_item_time);
			holder.ivPic = (ImageView) convertView.findViewById(R.id.iv_pic);

			convertView.setTag(holder);

		}
		holder = (ViewHolder) convertView.getTag();
		holder.tvTitle.setText(newList.getTitle());
		holder.tvTitle2.setText(newList.getDescription());
		holder.tvTime.setText(newList.getCtime());
		Glide.with(context).load(newList.getPicUrl()).into(holder.ivPic);


		return convertView;
	}

	class ViewHolder {
		TextView tvTitle;
		TextView tvTitle2;
		TextView tvTime;
		ImageView ivPic;
		

	}

}
