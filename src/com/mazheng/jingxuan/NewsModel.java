package com.mazheng.jingxuan;

import java.io.InputStream;
import java.util.List;

import android.os.AsyncTask;
import android.util.Log;

public class NewsModel {
	public void search(final String key, final Callback callback) {
		new AsyncTask<String, String, List<NewsList>>() {

			@Override
			protected List<NewsList> doInBackground(String... params) {
				try {
					String url = UrlFactory.getNewList(100, key);
					InputStream is = HttpUtil.get(url);
					String json = HttpUtil.isToString(is);
					List<NewsList> newsLists = JSONParse.parseSearch(json);
					return newsLists;

				} catch (Exception e) {
					e.printStackTrace();
				}

				return null;
			}

			protected void onPostExecute(java.util.List<NewsList> result) {
				callback.onNewsLoaded(result);
			};

		}.execute();
	}

	public interface Callback {
		void onNewsLoaded(List<NewsList> newlist);
	}

}
