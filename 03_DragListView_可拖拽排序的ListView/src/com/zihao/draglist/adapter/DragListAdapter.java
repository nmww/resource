package com.zihao.draglist.adapter;

import java.util.ArrayList;
import java.util.List;

import com.zihao.draglist.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.TextView;

/***
 * �Զ������קListView������
 */
public class DragListAdapter extends BaseAdapter {

	private Context mContext; // ������
	private ArrayList<String> copyList = new ArrayList<String>();
	private List<String> dataList;// ����Դ
	private int mInvisilePosition = -1;// ������ǲ��ɼ�Item��λ��
	private boolean isChanged = true;// �Ƿ����ı��ʶ
	private boolean mShowItem = false;// �Ƿ���ʾ��קItem�����ݱ�ʶ
	private int mLastFlag = -1;
	private int mHeight;
	@SuppressWarnings("unused")
	private int mDragPosition = -1;
	@SuppressWarnings("unused")
	private boolean isSameDragDirection = true;// �Ƿ�Ϊ��ͬ�����϶��ı��

	/** DragListAdapter���췽�� */
	public DragListAdapter(Context context, ArrayList<String> dataList) {
		this.mContext = context;
		this.dataList = dataList;
	}

	/** ��ȡItem���� */
	@Override
	public int getCount() {
		return dataList.size();
	}

	/** ��ȡListView��Item�� */
	@Override
	public Object getItem(int position) {
		return dataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	/**
	 * �����Ƿ���ʾ�½���Item
	 * 
	 * @param showItem
	 */
	public void showDropItem(boolean showItem) {
		this.mShowItem = showItem;
	}

	/**
	 * ���ò��ɼ����λ�ñ��
	 * 
	 * @param position
	 */
	public void setInvisiblePosition(int position) {
		mInvisilePosition = position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		/***
		 * �����ﾡ����ÿ�ζ�����ʵ�����µģ���������קListView��ʱ�򲻻���ִ���.
		 * ����ԭ���������������������ԣ�Ŀǰû�з��ִ��ҡ���˵Ч�ʲ��ߣ���������קLisView�㹻�ˡ�
		 */
		convertView = LayoutInflater.from(mContext).inflate(
				R.layout.drag_list_item, null);

		initItemView(position, convertView);// ��ʼ��Item��ͼ

		TextView titleTv = (TextView) convertView
				.findViewById(R.id.drag_item_title_tv);
		titleTv.setText(dataList.get(position));

		if (isChanged) {// �ж��Ƿ����˸ı�

			if (position == mInvisilePosition) {

				if (!mShowItem) {// ����ק���̲�������ʾ��״̬�£�����Item��������

					// ��Ϊitem����Ϊ��ɫ���ʶ�������Ҫ����Ϊȫ͸��ɫ��ֹ�а�ɫ�ڵ����⣨������ק��
					convertView.findViewById(R.id.drag_item_layout)
							.setBackgroundColor(0x0000000000);

					// ����Item���������
					int invis = View.INVISIBLE;
					convertView.findViewById(R.id.drag_item_image)
							.setVisibility(invis);
					convertView.findViewById(R.id.drag_item_close_layout)
							.setVisibility(invis);
					titleTv.setVisibility(invis);
				}
			}

			if (mLastFlag != -1) {

				if (mLastFlag == 1) {

					if (position > mInvisilePosition) {
						Animation animation;
						animation = getFromSelfAnimation(0, -mHeight);
						convertView.startAnimation(animation);// ��������
					}

				} else if (mLastFlag == 0) {

					if (position < mInvisilePosition) {
						Animation animation;
						animation = getFromSelfAnimation(0, mHeight);
						convertView.startAnimation(animation);// ��������
					}
				}
			}
		}
		return convertView;
	}

	/**
	 * 
	 * ��ʼ��Item��ͼ
	 * 
	 * @param convertView
	 */
	private void initItemView(final int position, final View convertView) {

		if (convertView != null) {
			// ���ö�Ӧ��ɾ������
			convertView.findViewById(R.id.drag_item_close_layout)
					.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							removeItem(position);// ɾ��ָ����Item
						}
					});
		}
	}

	/**
	 * ɾ��ָ����Item
	 * 
	 * @param pos
	 *            // Ҫɾ�����±�
	 */
	private void removeItem(int pos) {
		if (dataList != null && dataList.size() > pos) {
			dataList.remove(pos);
			this.notifyDataSetChanged();// ˢ��������
		}
	}

	/***
	 * ��̬�޸�ListView�ķ�λ.
	 * 
	 * @param startPosition
	 *            ����ƶ���position
	 * @param endPosition
	 *            �ɿ�ʱ���position
	 */
	public void exchange(int startPosition, int endPosition) {
		
		Object startObject = getItem(startPosition);
		if (startPosition < endPosition) {// ������ק
			dataList.add(endPosition + 1, (String) startObject);
			dataList.remove(startPosition);
		} else {// ������ק���߲���
			dataList.add(endPosition, (String) startObject);
			dataList.remove(startPosition + 1);
		}
		isChanged = true;
	}

	/**
	 * ��̬�޸�Item����
	 * 
	 * @param startPosition
	 *            // ��ʼ��λ��
	 * @param endPosition
	 *            // ��ǰͣ����λ��
	 */
	public void exchangeCopy(int startPosition, int endPosition) {
		Object startObject = getCopyItem(startPosition);

		if (startPosition < endPosition) {// ������ק
			copyList.add(endPosition + 1, (String) startObject);
			copyList.remove(startPosition);
		} else {// ������ק���߲���
			copyList.add(endPosition, (String) startObject);
			copyList.remove(startPosition + 1);
		}
		isChanged = true;
	}

	/**
	 * ��ȡ����(��ק)Item��
	 * 
	 * @param position
	 * @return
	 */
	public Object getCopyItem(int position) {
		return copyList.get(position);
	}

	/**
	 * ����϶���
	 * 
	 * @param start
	 *            // Ҫ������ӵ�λ��
	 * @param obj
	 */
	public void addDragItem(int start, Object obj) {
		dataList.remove(start);// ɾ������
		dataList.add(start, (String) obj);// ���ɾ����
	}

	public void copyList() {
		copyList.clear();// ��ռ���
		for (String str : dataList) {// ��������Դ
			copyList.add(str);// �������������
		}
	}

	public void pastList() {
		dataList.clear();// ��ռ���
		for (String str : copyList) {// ������������
			dataList.add(str);// ���������Դ����
		}
	}

	/**
	 * �����Ƿ�Ϊ��ͬ�����϶��ı��
	 * 
	 * @param value
	 */
	public void setIsSameDragDirection(boolean value) {
		isSameDragDirection = value;
	}

	/**
	 * �����϶�������
	 * 
	 * @param flag
	 */
	public void setLastFlag(int flag) {
		mLastFlag = flag;
	}

	/**
	 * ���ø߶�
	 * 
	 * @param value
	 */
	public void setHeight(int value) {
		mHeight = value;
	}

	/**
	 * ���õ�ǰ�϶�λ��
	 * 
	 * @param position
	 */
	public void setCurrentDragPosition(int position) {
		mDragPosition = position;
	}

	/**
	 * ��������ֵĶ���
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private Animation getFromSelfAnimation(int x, int y) {
		TranslateAnimation translateAnimation = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0, Animation.ABSOLUTE, x,
				Animation.RELATIVE_TO_SELF, 0, Animation.ABSOLUTE, y);// ƽ�ƶ���
		translateAnimation
				.setInterpolator(new AccelerateDecelerateInterpolator());// ���ò�ֵ��
		translateAnimation.setFillAfter(true);
		translateAnimation.setDuration(100);
		translateAnimation.setInterpolator(new AccelerateInterpolator());// ���ò�ֵ��
		return translateAnimation;
	}

}
