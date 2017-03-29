package com.klzy.flowlayoutdemo;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.klzy.flowlayoutdemo.Entity.QuestionItemEntity;
import com.klzy.flowlayoutdemo.databinding.ItemQuestionChoiceBinding;
import com.klzy.flowlayoutdemo.utils.BindingViewHolder;
import com.zhy.autolayout.attr.AutoAttr;
import com.zhy.autolayout.utils.AutoUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class QuestionAdapter extends RecyclerView.Adapter<BindingViewHolder> {

    private List<QuestionItemEntity> mDatas;
    private LayoutInflater mInflater;
    ViewDataBinding mBinding;
    private Context mContext;

    public class Presenter {

        //返回
        public void onClickItem() {

        }

    }

    public QuestionAdapter(Context context) {
        this.mDatas = new ArrayList<>();
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    /***
     * 0标题	    normal
     * 1判断题	judge
     * 2单选题	single
     * 3多选题	choice
     */
    public void addAll(List<QuestionItemEntity> datas) {
        mDatas.clear();
        for (QuestionItemEntity itemEntity : datas) {
            if (itemEntity.getQuestiontype().equals("normal")) {
                itemEntity.setMaxSelect(-1);
                itemEntity.setLayoutID(R.layout.item_question_main);
                itemEntity.setLayoutItem(R.layout.tv);
            } else if (itemEntity.getQuestiontype().equals("judge")) {
                itemEntity.setMaxSelect(1);
                itemEntity.setLayoutID(R.layout.item_question_choice);
                itemEntity.setLayoutItem(R.layout.tv_single);
            } else if (itemEntity.getQuestiontype().equals("single")) {
                itemEntity.setMaxSelect(1);
                itemEntity.setLayoutID(R.layout.item_question_choice);
                itemEntity.setLayoutItem(R.layout.tv_single);
            } else if (itemEntity.getQuestiontype().equals("choice")) {
                itemEntity.setMaxSelect(-1);
                itemEntity.setLayoutID(R.layout.item_question_choice);
                itemEntity.setLayoutItem(R.layout.tv_multi);
            }

        }
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return mDatas.get(position).getLayoutID();
    }

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mBinding = DataBindingUtil.inflate(mInflater, viewType, parent, false);

        AutoUtils.autoSize(mBinding.getRoot(), AutoAttr.BASE_WIDTH);
        return new BindingViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(final BindingViewHolder holder, final int position) {
        final QuestionItemEntity questionEntity = mDatas.get(position);
        if (questionEntity.getQuestiontype().equals("judge") || questionEntity.getQuestiontype().equals("single") || questionEntity.getQuestiontype().equals("choice")) {

            TagAdapter tagAdapter = new TagAdapter<String>(questionEntity.getSelector()) {

                @Override
                public View getView(FlowLayout parent, final int position, String s) {
                    TextView tv = (TextView) LayoutInflater.from(mContext).inflate(questionEntity.getLayoutItem(), ((ItemQuestionChoiceBinding) holder.getBinding()).flowLayout, false);
                    tv.setText(questionEntity.getSelector().get(position));
                    return tv;
                }

                @Override
                public boolean setSelected(int position, String s) {
                    return questionEntity.getSelected().contains(position);
                }
            };

            ((ItemQuestionChoiceBinding) holder.getBinding()).flowLayout.setAdapter(tagAdapter);
            ((ItemQuestionChoiceBinding) holder.getBinding()).flowLayout.setMaxSelectCount(questionEntity.getMaxSelect());
            ((ItemQuestionChoiceBinding) holder.getBinding()).flowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
                @Override
                public void onSelected(Set<Integer> selectPosSet) {
                    questionEntity.setSelected(selectPosSet);
                    if (selectPosSet.size() == 0) {
                        questionEntity.setAnswer("");
                    } else {
                        String sAnswer = "";

                        //实现选中项从小到大进行组合，设置成答案
                        for (int i = 0; i < questionEntity.getSelector().size(); i++) {
                            if (selectPosSet.contains(i)) {
                                sAnswer += questionEntity.getSelector().get(i) + ",";
                            }
                        }

                        sAnswer = sAnswer.substring(0, sAnswer.length() - 1);
                        questionEntity.setAnswer(sAnswer);
                    }
                }
            });
        }


        holder.getBinding().setVariable(com.klzy.flowlayoutdemo.BR.questionItem, questionEntity);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

}
