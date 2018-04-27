package com.slidingmenu.lib.app;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.slidingmenu.lib.R;
import com.slidingmenu.lib.SlidingFragmentActivity;
import com.slidingmenu.lib.SlidingMenu;

public class MainActivity extends SlidingFragmentActivity {

	/**
	 * 滑动的位置变量
	 */
    private int lastX;
    /**
     * 滑动的大小
     */
    private int temp;
    /**
     * 主页中的页面内容 
     */
    private ViewPager viewPager = null;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSlidingMenu().setMode(SlidingMenu.LEFT_RIGHT);
        getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        
        viewPager = (ViewPager) findViewById(R.id.vp_content);
        
        viewPager.setAdapter(new ColorPagerAdapter(getSupportFragmentManager()));
        //设置ViewPager的监听事件
        viewPager.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
            	//获取当前用户的操作的动作
                int action=event.getAction();
                
                switch(action) {
                	//按下时记录当前点击的x轴的内容
                    case MotionEvent.ACTION_DOWN:
                        lastX=(int)event.getX();
                        break;
                    case MotionEvent.ACTION_MOVE:
                    	//判断x轴移动的位置
                        temp=(int)(event.getX() - lastX);
                        lastX=(int)event.getX();
                        //如果判断当前的滑动的方向是向右滑动，并且当前停留在第一个页面，那么判断是想要显示抽屉
                        if(temp > 0 && viewPager.getCurrentItem() == 0) {
                            getSlidingMenu().setSlidingEnabled(true);
                        //如果判断当前的滑动的方向是向左滑动，并且当前停留在最后一个页面，那么判断是想要显示抽屉
                        } else if(temp < 0 && viewPager.getCurrentItem() == (viewPager.getAdapter().getCount() - 1)) {
                            getSlidingMenu().setSlidingEnabled(true);
                        } else {
                        //不符合要求屏蔽抽屉的事件
                        	getSlidingMenu().setSlidingEnabled(false);
                        }
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        break;
                }
                return false;
            }
        });
        //创建事务
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        
        //设置左侧内容
        setBehindContentView(R.layout.menu_frame);
        //左侧菜单添加内容
        ft.replace(R.id.menu_frame, new SampleListFragment());
        //设置宽度（页面内容的宽度，非抽屉宽度）
		getSlidingMenu().setBehindOffset(800);
		//滑动时渐变的程度
		getSlidingMenu().setFadeDegree(0.35f);
        //设置滑动屏幕的范围
        getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //设置右侧菜单的内容
        getSlidingMenu().setSecondaryMenu(R.layout.right_menu_frame);
        //为右侧菜单添加内容
        ft.replace(R.id.right_menu_frame, new SampleListFragment());
        //提交事务
		ft.commit();
    }
    
    /**
     * 显示左侧的内容
     */
    public void showLeft(){
        getSlidingMenu().setSlidingEnabled(true);
        if(getSlidingMenu().isMenuShowing()){
            getSlidingMenu().showContent();
        }else{
            getSlidingMenu().showMenu();
        }

       
    }
    
    /**
     * 显示右侧的内容
     */
    public void showRight(){
        getSlidingMenu().setSlidingEnabled(true);
        if(getSlidingMenu().isMenuShowing()){
            getSlidingMenu().showContent();
        }else{
            getSlidingMenu().showSecondaryMenu();
        }

    }
    
    /**
     * 创建颜色不用的Fragment进行加载
     * 
     * @author a
     *
     */
    public class ColorPagerAdapter extends FragmentPagerAdapter {

        private ArrayList<Fragment> mFragments;

        private final int[] DRAWABLES=new int[]{R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4, R.drawable.image5};

        public ColorPagerAdapter(FragmentManager fm) {
            super(fm);
            mFragments=new ArrayList<Fragment>();
            for(int drawable: DRAWABLES)
                mFragments.add(new DawableFragment(drawable));
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

    }


}
