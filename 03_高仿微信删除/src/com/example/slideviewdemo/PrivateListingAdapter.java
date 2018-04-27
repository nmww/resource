package com.example.slideviewdemo;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.slideviewdemo.SlideView.OnSlideListener;

public class PrivateListingAdapter extends BaseAdapter implements
		OnSlideListener {
	private static final String TAG = "SlideAdapter";
	private Context mContext;
	private LayoutInflater mInflater;

	private List<MessageBean> mMessageItems = new ArrayList<MessageBean>();
	private SlideView mLastSlideViewWithStatusOn;

	PrivateListingAdapter(Context context) {
		mContext = context;
		mInflater = LayoutInflater.from(mContext);
	}

	public void setmMessageItems(List<MessageBean> mMessageItems) {
		this.mMessageItems = mMessageItems;
	}

	@Override
	public int getCount() {
		if (mMessageItems == null) {
			mMessageItems = new ArrayList<MessageBean>();
		}
		return mMessageItems.size();
	}

	@Override
	public Object getItem(int position) {
		return mMessageItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		SlideView slideView = (SlideView) convertView;
		if (slideView == null) {//在linelayout中加载布局
			View itemView = mInflater.inflate(R.layout.privatelisting_item,
					null);

			slideView = new SlideView(mContext);
			slideView.setContentView(itemView);

			holder = new ViewHolder(slideView);
			slideView.setOnSlideListener(this);
			slideView.setTag(holder);
		} else {
			holder = (ViewHolder) slideView.getTag();
		}
		MessageBean item = mMessageItems.get(position);
		item.slideView = slideView;
		item.slideView.shrink();//记录每项的初始值

		holder.icon.setImageResource(item.iconRes);
		holder.title.setText(item.title);
		holder.msg.setText(item.msg);
		holder.time.setText(item.time);
		holder.deleteHolder.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mMessageItems.remove(position);
				notifyDataSetChanged();
			}
		});

		return slideView;
	}

	private static class ViewHolder {
		public ImageView icon;
		public TextView title;
		public TextView msg;
		public TextView time;
		public ViewGroup deleteHolder;

		ViewHolder(View view) {
			icon = (ImageView) view.findViewById(R.id.icon);
			title = (TextView) view.findViewById(R.id.title);
			msg = (TextView) view.findViewById(R.id.msg);
			time = (TextView) view.findViewById(R.id.time);
			deleteHolder = (ViewGroup) view.findViewById(R.id.holder);
		}
	}

	@Override
	public void onSlide(View view, int status) {//判断slideview
		if (mLastSlideViewWithStatusOn != null
				&& mLastSlideViewWithStatusOn != view) {
			mLastSlideViewWithStatusOn.shrink();
		}

		if (status == SLIDE_STATUS_ON) {//开始状态
			mLastSlideViewWithStatusOn = (SlideView) view;
		}
	}
}
