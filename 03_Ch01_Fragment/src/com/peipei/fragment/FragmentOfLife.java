package com.peipei.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @date：2014-12-16
 * @describe：Fragment的生命周期。
 */
@SuppressLint("NewApi")
public class FragmentOfLife extends Fragment {

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Log.e("FragmentOfLife",
				"-------------------------------------->onAttach");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		Log.e("FragmentOfLife",
				"-------------------------------------->onCreate");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.e("FragmentOfLife",
				"-------------------------------------->onCreateView");
		return LayoutInflater.from(this.getActivity()).inflate(
				R.layout.fragment_main, null);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.e("FragmentOfLife",
				"-------------------------------------->onActivityCreated");
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.e("FragmentOfLife",
				"-------------------------------------->onActivityResult");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.e("FragmentOfLife",
				"-------------------------------------->onDestroy");
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.e("FragmentOfLife",
				"-------------------------------------->onDestroyView");
	}

	@Override
	public void onDetach() {
		super.onDetach();
		Log.e("FragmentOfLife",
				"-------------------------------------->onDetach");
	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
		Log.e("FragmentOfLife",
				"-------------------------------------->onHiddenChanged");
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
		Log.e("FragmentOfLife",
				"-------------------------------------->onLowMemory");
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.e("FragmentOfLife",
				"-------------------------------------->onPause");
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.e("FragmentOfLife",
				"-------------------------------------->onResume");
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Log.e("FragmentOfLife",
				"-------------------------------------->onSaveInstanceState");
	}

	@Override
	public void onStart() {
		super.onStart();
		Log.e("FragmentOfLife",
				"-------------------------------------->onStart");
	}

	@Override
	public void onStop() {
		super.onStop();
		Log.e("FragmentOfLife", "-------------------------------------->onStop");
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		Log.e("FragmentOfLife",
				"-------------------------------------->onViewCreated");
	}

	@Override
	public void onViewStateRestored(Bundle savedInstanceState) {
		super.onViewStateRestored(savedInstanceState);
		Log.e("FragmentOfLife",
				"-------------------------------------->onViewStateRestored");
	}

}
