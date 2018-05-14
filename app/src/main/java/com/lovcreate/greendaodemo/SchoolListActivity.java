package com.lovcreate.greendaodemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.classic.common.MultipleStatusView;
import com.lovcreate.greendaodemo.adapter.SchoolListAdapter;
import com.lovcreate.greendaodemo.bean.School;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wanghaoyu on 2017/11/9.
 */

public class SchoolListActivity extends AppCompatActivity {

    @Bind(R.id.listview)
    ListView recycle;
    @Bind(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @Bind(R.id.status_view)
    MultipleStatusView statusView;

    private SchoolListAdapter adapter;
    private List<School> list = new ArrayList<>();
    private List<School> schoolList = new ArrayList<>();
    private int pageNo = 1;
    private int pageSize = 20;
    private QueryBuilder<School> queryBuilder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_list);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        int i = 100;
        for (int a = 0; a < i; a++) {
            School school = new School();
            school.setId(a + 1);
            school.setName("长春第" + (a + 1) + "高等中学");
            school.setAddress("长春" + (a + 1) + "马路，10" + (a + 1) + "号");
            school.setTel("0431-886991" + a);
            list.add(school);
        }
        GreenDaoManager.getInstance().getmDaoSession().getSchoolDao().insertOrReplaceInTx(list);

        queryBuilder = GreenDaoManager.getInstance().getmDaoSession().getSchoolDao().queryBuilder();

        statusView.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statusView.showContent();
                refreshLayout.autoRefresh();
            }
        });
    }

    private void showData() {
        List<School> schools = queryBuilder.limit(pageSize).offset((pageNo - 1) * pageSize).build().list();
        schoolList.addAll(schools);
        adapter.notifyDataSetHasChanged();
    }

    private void initView() {
        adapter = new SchoolListAdapter(this, schoolList, R.layout.item_school);
        recycle.setAdapter(adapter);
        refreshLayout.autoRefresh();//自动刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                pageNo = 1;
                schoolList.clear();
                showData();
                refreshLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        refreshlayout.finishRefresh(2000);
                    }
                });
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(final RefreshLayout refreshlayout) {
                pageNo++;
                refreshLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        statusView.showEmpty();
//                        showData();
                        refreshlayout.finishLoadmore(1000);
                    }
                });
            }
        });
        recycle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(SchoolListActivity.this, "点击了第" + i + "个", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
