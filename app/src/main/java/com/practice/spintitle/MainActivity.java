package com.practice.spintitle;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.mainRv)
    RecyclerView mainRv;
    @BindView(R.id.titleOverlay)
    View titleOverlay;
    MyAdapter myAdapter;
    LinearLayoutManager linearLayoutManager;
    @BindView(R.id.titleTv)
    TextView titleTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        myAdapter = new MyAdapter();
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mainRv.setLayoutManager(linearLayoutManager);
        mainRv.setAdapter(myAdapter);
        mainRv.setOnScrollListener(new RecyclerView.OnScrollListener() {

            public View findNextTitleView(int startIndex) {
                int upLimit = myAdapter.getItemCount();
                for (int i = startIndex; i < upLimit; ++i) {
                    if (myAdapter.getItem(i).getDataType() == MyAdapter.BaseData.TITLE_TYPE) {
                        return linearLayoutManager.findViewByPosition(i);
                    }
                }
                return null;
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (titleOverlay.getVisibility() != View.VISIBLE) {
                    titleOverlay.setVisibility(View.VISIBLE);
                }
                int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                Log.e(TAG, "onScrolled:" + titleOverlay.getMeasuredHeight());

                MyAdapter.BaseData baseData = myAdapter.getItem(firstVisibleItemPosition);
                String title = "";
                switch (baseData.getDataType()) {
                    case MyAdapter.BaseData.NORMAL_TYPE:
                        title = ((MyAdapter.NormalData) baseData).getTitle();
                        break;
                    case MyAdapter.BaseData.TITLE_TYPE:
                        title = ((MyAdapter.TitleData) baseData).getTitle();
                        break;
                }
                titleTv.setText(title);
                View titleView = findNextTitleView(firstVisibleItemPosition + 1);
                if (titleView.getTop() <= titleOverlay.getMeasuredHeight()) {
                    titleOverlay.setTranslationY(titleView.getTop() - titleOverlay.getMeasuredHeight());
                    titleTv.setScaleY(titleView.getTop() / (float) titleOverlay.getMeasuredHeight());
                } else {
                    titleOverlay.setTranslationY(0);
                    titleTv.setScaleY(1);
                }
            }
        });
        myAdapter.add(new MyAdapter.TitleData("分组1"));
        myAdapter.add(new MyAdapter.NormalData("项目1", "分组1"));
        myAdapter.add(new MyAdapter.NormalData("项目2", "分组1"));
        myAdapter.add(new MyAdapter.NormalData("项目3", "分组1"));
        myAdapter.add(new MyAdapter.NormalData("项目4", "分组1"));
        myAdapter.add(new MyAdapter.NormalData("项目5", "分组1"));
        myAdapter.add(new MyAdapter.NormalData("项目6", "分组1"));
        myAdapter.add(new MyAdapter.NormalData("项目7", "分组1"));
        myAdapter.add(new MyAdapter.TitleData("分组2"));
        myAdapter.add(new MyAdapter.NormalData("项目1", "分组2"));
        myAdapter.add(new MyAdapter.NormalData("项目2", "分组2"));
        myAdapter.add(new MyAdapter.NormalData("项目3", "分组2"));
        myAdapter.add(new MyAdapter.NormalData("项目4", "分组2"));
        myAdapter.add(new MyAdapter.NormalData("项目5", "分组2"));
        myAdapter.add(new MyAdapter.NormalData("项目6", "分组2"));
        myAdapter.add(new MyAdapter.NormalData("项目7", "分组2"));
        myAdapter.add(new MyAdapter.TitleData("分组3"));
        myAdapter.add(new MyAdapter.NormalData("项目1", "分组3"));
        myAdapter.add(new MyAdapter.NormalData("项目2", "分组3"));
        myAdapter.add(new MyAdapter.NormalData("项目3", "分组3"));
        myAdapter.add(new MyAdapter.NormalData("项目4", "分组3"));
        myAdapter.add(new MyAdapter.NormalData("项目5", "分组3"));
        myAdapter.add(new MyAdapter.NormalData("项目6", "分组3"));
        myAdapter.add(new MyAdapter.NormalData("项目7", "分组3"));
        myAdapter.add(new MyAdapter.TitleData("分组4"));
        myAdapter.add(new MyAdapter.NormalData("项目1", "分组4"));
        myAdapter.add(new MyAdapter.NormalData("项目2", "分组4"));
        myAdapter.add(new MyAdapter.NormalData("项目3", "分组4"));
        myAdapter.add(new MyAdapter.NormalData("项目4", "分组4"));
        myAdapter.add(new MyAdapter.NormalData("项目5", "分组4"));
        myAdapter.add(new MyAdapter.NormalData("项目6", "分组4"));
        myAdapter.add(new MyAdapter.NormalData("项目7", "分组4"));
        myAdapter.add(new MyAdapter.TitleData("分组5"));
        myAdapter.add(new MyAdapter.NormalData("项目1", "分组5"));
        myAdapter.add(new MyAdapter.NormalData("项目2", "分组5"));
        myAdapter.add(new MyAdapter.NormalData("项目3", "分组5"));
        myAdapter.add(new MyAdapter.NormalData("项目4", "分组5"));
        myAdapter.add(new MyAdapter.NormalData("项目5", "分组5"));
        myAdapter.add(new MyAdapter.NormalData("项目6", "分组5"));
        myAdapter.add(new MyAdapter.NormalData("项目7", "分组5"));
        myAdapter.add(new MyAdapter.TitleData("分组6"));
        myAdapter.add(new MyAdapter.NormalData("项目1", "分组6"));
        myAdapter.add(new MyAdapter.NormalData("项目2", "分组6"));
        myAdapter.add(new MyAdapter.NormalData("项目3", "分组6"));
        myAdapter.add(new MyAdapter.NormalData("项目4", "分组6"));
        myAdapter.add(new MyAdapter.NormalData("项目5", "分组6"));
        myAdapter.add(new MyAdapter.NormalData("项目6", "分组6"));
        myAdapter.add(new MyAdapter.NormalData("项目7", "分组6"));
    }
}
