package com.yang.designsupportdemo;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 为了使得Toolbar有滑动效果，必须做到如下三点:
 1. CoordinatorLayout作为布局的父布局容器。
 2. 给需要滑动的组件设置 app:layout_scrollFlags=”scroll|enterAlways” 属性。
 3. 给滑动的组件设置app:layout_behavior属性
 */
public class CollapsingToolbarLayoutActivity extends AppCompatActivity {

    @Bind(R.id.tool_bar)
    Toolbar toolBar;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    CollapsingToolbarLayoutState state;
    @Bind(R.id.title_parent)
    LinearLayout linearLayout;
    @Bind(R.id.expanded_parent)
    LinearLayout expendParent;
    @Bind(R.id.rl)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_collapsing_toolbar_layout);
        ButterKnife.bind(this);

        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setHomeAsUpIndicator(0);
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        toolBar.setTitle("");
//        collapsingToolbar.setTitleEnabled(false);
//        toolBar.setSubtitle("副标题");
//        collapsingToolbar.setTitle("ss");
//        collapsingToolbar.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
//        collapsingToolbar.setCollapsedTitleTextColor(Color.GREEN);//设置收缩后Toolbar上字体的颜色
        AppBarLayout app_bar = (AppBarLayout) findViewById(R.id.app_bar);
        app_bar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (verticalOffset == 0) {
                    if (state != CollapsingToolbarLayoutState.EXPANDED) {
                        /**
                         * 展开状态============
                         */

                        state = CollapsingToolbarLayoutState.EXPANDED;//修改状态标记为展开
                        collapsingToolbar.setTitle("");//设置title为EXPANDED
                        linearLayout.setVisibility(View.GONE);
                        expendParent.setVisibility(View.VISIBLE);
                    }
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                        /**
                         * 滚动超过title距离=============
                         */
                        expendParent.setVisibility(View.GONE);
                        linearLayout.setVisibility(View.VISIBLE);
                        collapsingToolbar.setTitle("");//设置title不显示
                        state = CollapsingToolbarLayoutState.COLLAPSED;//修改状态标记为折叠
                    }
                } else {
                    /**
                     * 正在滚动中============中间状态
                     */
                    if (state != CollapsingToolbarLayoutState.MIDDLE) {
                        collapsingToolbar.setTitle("");//设置title不显示

                        linearLayout.setVisibility(View.GONE);
                        expendParent.setVisibility(View.VISIBLE);
                        state = CollapsingToolbarLayoutState.MIDDLE;//修改状态标记为中间
                    }
                }
            }
        });

        MyAdapter myAdapter = new MyAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);
    }

    enum CollapsingToolbarLayoutState {
        EXPANDED,
        COLLAPSED,
        MIDDLE
    }
}
