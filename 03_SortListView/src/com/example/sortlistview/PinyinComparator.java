package com.example.sortlistview;

import java.util.Comparator;

/**
 * ����ƴ��������ListView�����������
 */
public class PinyinComparator implements Comparator<SortModel> {

	public int compare(SortModel o1, SortModel o2) {
		if (o1.getSortLetters().equals("@") || o2.getSortLetters().equals("#")) {
			return -1;
		} else if (o1.getSortLetters().equals("#")
				|| o2.getSortLetters().equals("@")) {
			return 1;
		} else {
			System.out.println("----PinyinComparator------------------->"
					+ o1.getSortLetters().compareTo(o2.getSortLetters()));
			return o1.getSortLetters().compareTo(o2.getSortLetters());
		}
	}

}
