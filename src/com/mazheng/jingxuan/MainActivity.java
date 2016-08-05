package com.mazheng.jingxuan;

import java.util.List;

import com.mazheng.jingxuan.R;
import com.mazheng.jingxuan.RefreshLayout.OnLoadListener;
import com.mazheng.jingxuan.NewsModel.Callback;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ShowToast")
public class MainActivity extends Activity implements OnClickListener {
	private EditText etEdit;
	private Button btButton;
	private ListView listView;
	private TextView tvTitle;
	private TextView tvTitle2;
	private ImageView ivPic;
	private String text;
	private TextView tvTime;
	private NewsModel newsModel;
	private List<NewsList> newsList;
	private MyAdapter myAdapter;
	private RefreshLayout mRefreshListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		newsModel = new NewsModel();
		setContentView(R.layout.activity_main);

		tvTitle2 = (TextView) findViewById(R.id.tv_item_title2);
		tvTitle = (TextView) findViewById(R.id.tv_item_title);
		etEdit = (EditText) findViewById(R.id.et_edit);
		ivPic = (ImageView) findViewById(R.id.iv_pic);
		btButton = (Button) findViewById(R.id.bt_button);
		listView = (ListView) findViewById(R.id.listview);
		tvTime = (TextView) findViewById(R.id.tv_item_time);
		mRefreshListView = (RefreshLayout) findViewById(R.id.swipe_layout);

		ConnectivityManager cwjManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cwjManager.getActiveNetworkInfo();
		if (info != null && info.isAvailable()) {
			// do nothing
		} else {
			Toast.makeText(MainActivity.this, "无互联网连接", Toast.LENGTH_LONG).show();
		}
		mRefreshListView.setColorScheme(R.color.blue, R.color.green, R.color.red, R.color.yellow);

		setListeners();
	}

	public void onBackPressed() {
		new AlertDialog.Builder(this).setTitle("确认退出吗？").setIcon(android.R.drawable.ic_dialog_info)

				.setNegativeButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						finish();
						// 点击“返回”后的操作,这里不设置没有任何操作
					}
				}).setPositiveButton("返回", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// 点击“确认”后的操作

					}
				}).show();
	}

	private void setListeners() {
		btButton.setOnClickListener(this);
		

		mRefreshListView.setOnLoadListener(new OnLoadListener() {

			@Override
			public void onLoad() {
//				Toast.makeText(MainActivity.this, "load", Toast.LENGTH_SHORT).show();
				mRefreshListView.postDelayed(new Runnable() {

					@Override
					public void run() {
						
						newsList.add(new NewsList());
						myAdapter.notifyDataSetChanged();
						mRefreshListView.setLoading(false);

					}
				}, 2000);

			}
		});
		mRefreshListView.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
//				Toast.makeText(MainActivity.this, "refresh", Toast.LENGTH_SHORT).show();
				mRefreshListView.postDelayed(new Runnable() {
					public void run() {
						
						
						
						myAdapter.notifyDataSetChanged();
						mRefreshListView.setRefreshing(false);

					}

				}, 2000);

			}
		});

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long Id) {
				Intent i = new Intent(MainActivity.this, WebActivity.class);
				i.putExtra("url", newsList.get(position).getUrl());
				startActivity(i);
			}

		});

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.bt_button:
			find();
			break;
		}
	}

	private void find() {
		String key = etEdit.getText().toString();
		if (key.equals("")) {
			Toast.makeText(MainActivity.this, "你在逗我吗？？？", Toast.LENGTH_SHORT).show();
			return;

		}
		newsModel.search(key, new Callback() {

			@Override
			public void onNewsLoaded(List<NewsList> newslists) {

				newsList = newslists;

				if (newslists == null) {
					Toast.makeText(MainActivity.this, "呀！找不到唉………………", Toast.LENGTH_SHORT).show();
					return;
				} else {

					myAdapter = new MyAdapter(MainActivity.this, newslists);

					listView.setAdapter(myAdapter);

				}
			}
		});
		

	}
}
