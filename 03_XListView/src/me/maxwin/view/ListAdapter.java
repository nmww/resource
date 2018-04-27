package me.maxwin.view;

import java.util.List;

import me.maxwin.R;
import me.maxwin.view.ImageLoader.ImageCallback;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {
    private ImageLoader imageLoader;
	private Context context;
	private List<City> citys;
	private Drawable drawable;
    public ListAdapter(Context context,List<City> citys){
    	this.context = context;
    	this.citys = citys;
    	imageLoader = new ImageLoader();
    }
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.citys.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return this.citys.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Log.i("convertView", convertView+"     =====   1   =====  " + position );
		convertView = loadView(convertView,position);  //优化方法调用
		//convertView = getView(convertView,position); //未优化方法调用
		Log.i("convertView", convertView+"     =====   2   =====" + position);
		
		return convertView;
	}

	//未优化的
	private View getView(View convertView,int position){
		convertView = LayoutInflater.from(context).inflate(R.layout.list_item_too, null);
		TextView tv = (TextView)convertView.findViewById(R.id.list_item_textview);
		ImageView imv = (ImageView)convertView.findViewById(R.id.list_item_imageview);
		City city = this.citys.get(position);
		tv.setText(city.getCityName());
        
        return convertView;
	}
	
	
	//已经优化
	private View loadView(View convertView,int position){
		ViewHolder holder = null;
        if (convertView == null) {
        	
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_too, null);
            
            holder = new ViewHolder();
            holder.textView = (TextView)convertView.findViewById(R.id.list_item_textview);
            holder.imv = (ImageView)convertView.findViewById(R.id.list_item_imageview);
            
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        
        City city = this.citys.get(position);
        holder.textView.setText(city.getCityName());
        
        imageLoader.loadImage(city.getPath(), holder.imv);
        
        return convertView;
	}
	
	public static class ViewHolder {
		public TextView textView;
	    public ImageView imv;
	}
	
}
