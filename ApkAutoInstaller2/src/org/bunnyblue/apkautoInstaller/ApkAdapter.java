/**
 * 
 */
package org.bunnyblue.apkautoInstaller;

import java.util.LinkedList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * @author BunnyBlue
 *
 */
public class ApkAdapter extends BaseAdapter {
	LinkedList<String> apkPaths;
	Context mContext;

	/**
 * 
 */
	public ApkAdapter(LinkedList<String> apkPaths, Context mContext) {
		this.apkPaths = apkPaths;
		this.mContext = mContext;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return apkPaths.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public String getItem(int position) {
		// TODO Auto-generated method stub
		return apkPaths.get(position);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder mHolder;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item_apk, null, false);
			mHolder = new ViewHolder();
			mHolder.textView = (TextView) convertView.findViewById(R.id.apkName);
		} else {
			mHolder = (ViewHolder) convertView.getTag();
		}
		mHolder.textView.setText(getItem(position));
		convertView.setTag(mHolder);
		return convertView;
	}

	static class ViewHolder {

		TextView textView;
	}

}
