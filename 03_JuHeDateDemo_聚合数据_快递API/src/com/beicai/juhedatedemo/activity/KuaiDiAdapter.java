package com.beicai.juhedatedemo.activity;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class KuaiDiAdapter extends BaseAdapter{
	List<KuaiDiList> mKuaiDiList;
    Context context;
    
    public KuaiDiAdapter(Context context,List<KuaiDiList> mKuaiDiList){
    	this.mKuaiDiList=mKuaiDiList;
    	this.context=context;
    }
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mKuaiDiList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHold hodler = null;
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.kuaidiitem, null);
			hodler = new ViewHold();
			hodler.time=(TextView) convertView.findViewById(R.id.tv_time);
			hodler.quyu=(TextView) convertView.findViewById(R.id.tv_difang);
			hodler.state=(TextView) convertView.findViewById(R.id.tv_state);
			convertView.setTag(hodler);
		} else {
			hodler = (ViewHold) convertView.getTag();
		}
	     hodler.time.setText(mKuaiDiList.get(position).getDatetime().toString());
		 hodler.state.setText(mKuaiDiList.get(position).getRemark()+"");
		 hodler.quyu.setText(mKuaiDiList.get(position).getZone()+"");
		 return convertView;
	}
	static class ViewHold {
		TextView quyu;
		TextView time;
		TextView state;
	}

}
