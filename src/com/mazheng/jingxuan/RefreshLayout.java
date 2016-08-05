package com.mazheng.jingxuan;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

public class RefreshLayout extends SwipeRefreshLayout implements OnScrollListener {

	/**
	 * 滑动到最下面时的上拉操作
	 */

	private int mTouchSlop;
	/**
	 * listview实例
	 */
	private ListView mListView;
	/**
	 * 上拉监听器, 到了最底部的上拉加载操作
	 */
	private OnLoadListener mOnLoadListener;

	/**
	 * ListView的加载中footer
	 */
	private View mListViewFooter;
	/**
	 * 按下时的y坐标
	 */
	private int mYDown;
	/**
	 * 抬起时的y坐标, 与mYDown一起用于滑动到底部时判断是上拉还是下拉
	 */
	private int mLastY;
	/**
	 * 是否在加载中 ( 上拉加载更多 )
	 */
	private boolean isLoading = false;

	public RefreshLayout(Context context) {
		this(context, null);
	}

	public RefreshLayout(Context context, AttributeSet attrs) {
		super(context, attrs);

		mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
		mListViewFooter = LayoutInflater.from(context).inflate(R.layout.listview_footer, null, false);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		if (mListView == null) {
			getListView();
		}
	}

	/**
	 * 获取ListView对象
	 */

	private void getListView() {
		int childs = getChildCount();
		if (childs > 0) {
			View childView = getChildAt(0);
			if (childView instanceof ListView) {
				mListView = (ListView) childView;
				mListView.setOnScrollListener(this);
			}
		}

	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		final int action = event.getAction();
		switch (action) {

		// 按下
		case MotionEvent.ACTION_DOWN:
			mYDown = (int) event.getRawY();

			break;
		// 移动
		case MotionEvent.ACTION_MOVE:
			mLastY = (int) event.getRawY();
			break;
		// 抬起
		case MotionEvent.ACTION_UP:
			if (canLoad()) {
				loadData();
			}
			break;

		}
		return super.dispatchTouchEvent(event);

	}

	/**
	 * 是否可以加载更多, 条件是到了最底部, listview不在加载中, 且为上拉操作.
	 * 
	 * @return
	 */
	private boolean canLoad() {
		return isBottom() && !isLoading && isPullUp();

	}

	/**
	 * 是否是上拉操作
	 * 
	 * @return
	 */
	private boolean isPullUp() {

		return (mYDown - mLastY) >= mTouchSlop;
	}

	/**
	 * 如果到了最底部,而且是上拉操作.那么执行onLoad方法
	 */
	private void loadData() {
		if (mOnLoadListener != null) {
			setLoading(true);
			mOnLoadListener.onLoad();

		}

	}

	public void setLoading(boolean loading) {
		isLoading = loading;
		if (isLoading) {
			mListView.addFooterView(mListViewFooter);

		} else {
			mListView.removeFooterView(mListViewFooter);
			mYDown = 0;
			mLastY = 0;
		}

	}

	/**
	 * 判断是否到了最底部
	 */
	private boolean isBottom() {
		if (mListView != null && mListView.getAdapter() != null) {
			return mListView.getLastVisiblePosition() == (mListView.getAdapter().getCount() - 1);

		}
		return false;
	}

	public void setOnLoadListener(OnLoadListener loadListener) {
		mOnLoadListener = loadListener;
	}

	/**
	 * 加载更多的监听器
	 * 
	 * @author mrsimple
	 */
	public interface OnLoadListener {
		public void onLoad();
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		// 滚动时到了最底部也可以加载更多
		if (canLoad()) {
			loadData();
		}
		// TODO Auto-generated method stub

	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub

	}

}
