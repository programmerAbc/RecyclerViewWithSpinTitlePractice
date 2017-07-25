package com.practice.spintitle;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhuyakun on 2017/7/25.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<BaseData> baseDataList = new LinkedList<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case BaseData.NORMAL_TYPE:
                holder = new NormalViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.normal_item_view, parent, false));
                break;
            case BaseData.TITLE_TYPE:
                holder = new TitleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.title_item_view, parent, false));
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (baseDataList.get(position).getDataType()) {
            case BaseData.NORMAL_TYPE:
                ((NormalViewHolder) holder).bindData((NormalData) baseDataList.get(position));
                break;
            case BaseData.TITLE_TYPE:
                ((TitleViewHolder) holder).bindData((TitleData) baseDataList.get(position));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return baseDataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return baseDataList.get(position).getDataType();
    }

    public BaseData getItem(int position) {
        try {
            return baseDataList.get(position);
        } catch (Exception e) {
            return null;
        }
    }

    public void add(BaseData baseData) {
        baseDataList.add(baseData);
        notifyItemInserted(baseDataList.size() - 1);
    }

    public void clear() {
        baseDataList.clear();
        notifyDataSetChanged();
    }

    public static class NormalViewHolder extends RecyclerView.ViewHolder {
        NormalData data = null;
        TextView normalTv = null;

        public NormalViewHolder(View itemView) {
            super(itemView);
            normalTv = (TextView) itemView.findViewById(R.id.normalTv);
        }

        public void bindData(NormalData v) {
            data = v;
            normalTv.setText(data.getNormalValue());
        }
    }

    public static class TitleViewHolder extends RecyclerView.ViewHolder {
        TitleData data = null;
        TextView titleTv = null;

        public TitleViewHolder(View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.titleTv);
        }

        public void bindData(TitleData v) {
            data = v;
            titleTv.setText(data.getTitle());
        }
    }

    public interface BaseData {
        int NORMAL_TYPE = 1;
        int TITLE_TYPE = 2;

        int getDataType();
    }


    public static class TitleData implements BaseData {
        private String title;

        public TitleData(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public int getDataType() {
            return BaseData.TITLE_TYPE;
        }
    }

    public static class NormalData implements BaseData {
        private String normalValue;
        private String title;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public NormalData(String normalValue, String title) {
            this.normalValue = normalValue;
            this.title = title;
        }

        public String getNormalValue() {
            return normalValue;
        }

        public void setNormalValue(String normalValue) {
            this.normalValue = normalValue;
        }

        @Override
        public int getDataType() {
            return BaseData.NORMAL_TYPE;
        }
    }

}
