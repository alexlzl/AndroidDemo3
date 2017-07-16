
package weeho.com.petim.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import weeho.com.petim.R;
import weeho.com.petim.adapter.AchievementAdapter;
import weeho.com.petim.base.BaseFragment;
import weeho.com.petim.modle.AchievementBean.CorrectBean;


/**
 * Created by wangkui on 2017/4/18.
 *
 *
 */

public class AchievementFragment  extends BaseFragment{


    private RecyclerView recycleView;

    @Override
    protected boolean isShowLeftIcon() {
        return false;
    }

    @Override
    protected View setContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.achieve_view, container,
                true);
        initView(rootView);
        initData();
        return rootView;
    }

    private void initView(View rootView) {
        recycleView = (RecyclerView) rootView.findViewById(R.id.id_recyclerview);
        StaggeredGridLayoutManager stagger = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recycleView.setLayoutManager(stagger);
    }

    private void initData() {
        ArrayList<CorrectBean> list  = new ArrayList<CorrectBean>();
        for(int i=0;i<5;i++){
            CorrectBean  correctBean = new CorrectBean();
            if(i==0)
            correctBean.setConame("随地大小便");
            else if(i==1){
                correctBean.setConame("扑人");}
            else if(i==2){
                correctBean.setConame("乱咬东西");}
            else if(i==3){
                correctBean.setConame("翻垃圾箱");}
            else if(i==4){
                correctBean.setConame("爱打架");
            }
            list.add(correctBean);
        }
        AchievementAdapter achievementAdapter = new AchievementAdapter(list,getActivity());
        recycleView.setAdapter(achievementAdapter);
    }


    @Override
    protected void getNetData() {

    }

    @Override
    protected void setViewListener() {

    }

    @Override
    protected boolean hasTitle() {
        return true;
    }

    @Override
    protected boolean hasTitleIcon() {
        return false;
    }

    @Override
    protected boolean hasDownIcon() {
        return false;
    }

    @Override
    protected void reloadCallback() {

    }

    @Override
    protected String setTitleName() {
        return "成就";
    }

    @Override
    protected String setRightText() {
        return null;
    }

    @Override
    protected int setLeftImageResource() {
        return 0;
    }

    @Override
    protected int setMiddleImageResource() {
        return 0;
    }

    @Override
    protected int setRightImageResource() {
        return 0;
    }
}

