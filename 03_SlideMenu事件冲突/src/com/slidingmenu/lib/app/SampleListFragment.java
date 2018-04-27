package com.slidingmenu.lib.app;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.slidingmenu.lib.R;

public class SampleListFragment extends ListFragment {

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.main_menu_list, null);
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		SampleAdapter adapter = new SampleAdapter(getActivity());
		for (int i = 0; i < 20; i++) {
			adapter.add(new String("事件冲突"));
		}
		setListAdapter(adapter);
	}

	public class SampleAdapter extends ArrayAdapter<String> {

		public SampleAdapter(Context context) {
			super(context, 0);
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
			    convertView = new TextView(getContext());
			    convertView.setBackgroundResource(R.drawable.ic_launcher);
			}

			return convertView;
		}

	}
}
