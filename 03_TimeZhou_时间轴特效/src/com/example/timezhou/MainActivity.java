package com.example.timezhou;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends Activity {

	  // ʱ�����б�
    private ListView lvList;
    // ����list
    private List<DateText> list;
    // �б�������
    private DateAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            // ��ʼ��listview�ؼ�
            lvList = (ListView) findViewById(R.id.lv_list);
            list = new ArrayList<DateText>();
            // ��Ӳ�������
            addData();
            // �����ݰ���ʱ������
            DateComparator comparator = new DateComparator();
            Collections.sort(list, comparator);
            // listview��������
            adapter = new DateAdapter(this, list);
            lvList.setAdapter(adapter);
    }

    private void addData() {
            DateText date1 = new DateText("20140710", "��������");
            DateText date2 = new DateText("20140709", "������");
            DateText date3 = new DateText("20140726", "����ADS");
            DateText date4 = new DateText("20140710", "���ﵽ����������");
            DateText date5 = new DateText("20140711", "����ɪ�������");
            DateText date6 = new DateText("20140713", "��������");
            DateText date7 = new DateText("20140712", "�����۸��M��");
            DateText date8 = new DateText("20140714", "�������ʰ�����˹��");
            DateText date9 = new DateText("20140709", "�����װ��������Ƕ���");
            DateText date10 = new DateText("20140705", "�� �������ʵ�");
            DateText date11 = new DateText("20140729", "�����ʶ���ȷ������");
            DateText date12 = new DateText("20140725", "����ʱ����");
            DateText date13 = new DateText("20140716", "�����ϴ�����");
            DateText date14 = new DateText("20140711", "����ζ�����");
            DateText date15 = new DateText("20140710", "�����������ڴ���");
            DateText date16 = new DateText("20140711", "����������");
            DateText date17 = new DateText("20140712", "��˹��");
            DateText date18 = new DateText("20140711", "������������");
            DateText date19 = new DateText("20140715", "��˹��������23 ");
            DateText date20 = new DateText("20140723", "����������");
            DateText date21 = new DateText("20140718", "�����·���");
            DateText date22 = new DateText("20140706", "���������");
            DateText date23 = new DateText("20140714", "������");
            DateText date24 = new DateText("20140726", "��΢�ĳ��еķ�ʽ");
            DateText date25 = new DateText("20140725", "��˹�ﰢ˹������");
            DateText date26 = new DateText("20140723", "�����Ѷ������г�");
            DateText date27 = new DateText("20140721", "����������ʱ����");
            DateText date28 = new DateText("20140716", "���ϴ����ذ���");
            DateText date29 = new DateText("20140712", "��˹�ٷҵ���ʦ��");
            DateText date30 = new DateText("20140710", "������������");
            list.add(date1);
            list.add(date2);
            list.add(date3);
            list.add(date4);
            list.add(date5);
            list.add(date6);
            list.add(date7);
            list.add(date8);
            list.add(date9);
            list.add(date10);
            list.add(date11);
            list.add(date12);
            list.add(date13);
            list.add(date14);
            list.add(date15);
            list.add(date16);
            list.add(date17);
            list.add(date18);
            list.add(date19);
            list.add(date20);
            list.add(date21);
            list.add(date22);
            list.add(date23);
            list.add(date24);
            list.add(date25);
            list.add(date26);
            list.add(date27);
            list.add(date28);
            list.add(date29);
            list.add(date30);
    }
}
