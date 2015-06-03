/**
 * 
 */
package org.bunnyblue.apkautoInstaller;

import java.util.HashMap;
import java.util.LinkedList;

import org.bunnyblue.apkautoInstaller.utils.ApkItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author BunnyBlue
 *
 */

public class ApkAdapter extends BaseAdapter {
	// 填充数据的list
	private LinkedList<ApkItem> list;
	// 用来控制CheckBox的选中状况
	private HashMap<Integer, Boolean> isSelected = new HashMap<Integer, Boolean>();;
	// 上下文
	private Context context;
	// 用来导入布局
	private LayoutInflater inflater = null;

	// 构造器
	public ApkAdapter(LinkedList<ApkItem> list, Context context) {
		this.context = context;
		this.list = list;
		inflater = LayoutInflater.from(context);

		// 初始化数据
		initData();
	}

	// 初始化isSelected的数据
	private void initData() {
		for (int i = 0; i < list.size(); i++) {
			getIsSelected().put(i, false);
		}
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public ApkItem getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			// 获得ViewHolder对象
			holder = new ViewHolder();
			// 导入布局并赋值给convertview
			convertView = inflater.inflate(R.layout.item_apk, null);
			holder.apkName = (TextView) convertView.findViewById(R.id.apkName);
			holder.apkCheckBox = (CheckBox) convertView.findViewById(R.id.apkSelect);

			holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
			holder.tvPah = (TextView) convertView.findViewById(R.id.tvPah);
			holder.tvPkgName = (TextView) convertView.findViewById(R.id.tvPkgName);
			holder.tvVersionCode = (TextView) convertView.findViewById(R.id.tvVersionCode);
			holder.tvVersionName = (TextView) convertView.findViewById(R.id.tvVersionName);

			// 为view设置标签
			convertView.setTag(holder);
		} else {
			// 取出holder
			holder = (ViewHolder) convertView.getTag();
		}

		holder.apkName.setText(list.get(position).getAppName());
		holder.imageView.setImageDrawable(list.get(position).getIcon());
		ApkItem mApkItem = list.get(position);
		holder.tvPah.setText(mApkItem.getPath());
		holder.tvPkgName.setText(mApkItem.getPackageName());
		holder.tvVersionCode.setText(mApkItem.getVersionCode() + "");
		holder.tvVersionName.setText(mApkItem.getVersionName());
		holder.apkCheckBox.setChecked(getIsSelected().get(position));
		return convertView;
	}

	public HashMap<Integer, Boolean> getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(HashMap<Integer, Boolean> isSelected) {
		this.isSelected = isSelected;
	}

	static class ViewHolder {
		TextView apkName;
		CheckBox apkCheckBox;
		TextView tvPah;
		TextView tvPkgName;
		TextView tvVersionCode;
		TextView tvVersionName;
		ImageView imageView;

	}
}