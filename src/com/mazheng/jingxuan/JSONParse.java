package com.mazheng.jingxuan;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class JSONParse {
	public static List<NewsList> parseSearch(String json) throws JSONException {
		JSONObject obj = new JSONObject(json);
		JSONArray ary = obj.getJSONArray("newslist");
		List<NewsList> newsLists = new ArrayList<NewsList>();
		for (int i = 0; i < ary.length(); i++) {
			JSONObject m = ary.getJSONObject(i);
			NewsList n = new NewsList();
			n.setTitle(m.getString("title"));
			n.setDescription(m.getString("description"));
			n.setPicUrl(m.getString("picUrl"));
			n.setUrl(m.getString("url"));
			n.setCtime(m.getString("ctime"));
			newsLists.add(n);
		}
		return newsLists;

	}

}
