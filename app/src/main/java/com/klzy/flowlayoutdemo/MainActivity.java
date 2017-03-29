package com.klzy.flowlayoutdemo;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.klzy.flowlayoutdemo.Entity.QuestionEntity;
import com.klzy.flowlayoutdemo.Entity.QuestionItemEntity;
import com.klzy.flowlayoutdemo.databinding.ActivityMainBinding;
import com.klzy.flowlayoutdemo.utils.Convert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public class Presenter {

        //提交
        public void onClickCommit(View v) {
/*            String ss = "";
            for(QuestionItemEntity item : mListQuestion){
                if(item.getListType()==2) {
                    ss +=  item.getOrderID() + " : " + item.getAnswer() + "\n";
                }
            }
            //Toasty.error(mContext, ss, Toast.LENGTH_SHORT, true).show();

            mListAnswer = new ArrayList<>();
            AnswerEntity answerEntity = new AnswerEntity();
            int iLast = 0;
            int iPosition = 0;
            for(QuestionItemEntity item : mListQuestion){
                if(item.getListType()==1) {
                    if(iLast==2){
                        answerEntity.setAnswertype(2);
                        mListAnswer.add(answerEntity);
                        answerEntity = new AnswerEntity();
                    }
                    answerEntity.getTitle().add(item.getContent());
                    answerEntity.getLayouts().add(R.layout.tv_card_normal);
                    answerEntity.getPosition().add(iPosition++);
                    answerEntity.setAnswertype(1);
                    mListAnswer.add(answerEntity);

                    answerEntity = new AnswerEntity();
                    iLast = 1;
                }
                else if(item.getListType()==2) {
                    answerEntity.getTitle().add(item.getOrderID());
                    answerEntity.getLayouts().add(item.getAnswer()!=null && item.getAnswer().length()>0 ? R.layout.tv_card_ok : R.layout.tv_card_none);
                    answerEntity.getPosition().add(iPosition++);

                    iLast = 2;
                }
            }
            if(iLast==2) {
                answerEntity.setAnswertype(2);
                mListAnswer.add(answerEntity);
            }

            final TestpaperPopup dialog = new TestpaperPopup(mContext, mListAnswer);
            dialog
                    .anchorView(mBinding.imgWrite)
                    .offset(-10, 20)
                    .showAnim(new BounceTopEnter())
                    .dismissAnim(new SlideTopExit())
                    .dimEnabled(true)
                    .show();
            dialog.show();
            dialog.setCanceledOnTouchOutside(true);*/
        }

        public void onClick1(View v){
        }
    }

    ActivityMainBinding mBinding;
    private Context mContext;
    private List<QuestionItemEntity> mListItem;
    private QuestionAdapter mQuestionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = MainActivity.this;

        mBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        mQuestionAdapter = new QuestionAdapter(mContext);
        mBinding.rvData.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.rvData.setAdapter(mQuestionAdapter);

        mBinding.setPresenter(new Presenter());

        initQuestion();
    }

    private void initQuestion() {
        String sData = "[{\"title\":\"一、单选题\",\"count\":6,\"questiontype\":\"single\",\"question\":[{\"id\":\"1101\",\"questiontype\":\"single\",\"title\":\"<p>1+1=？请选择：</p><p>A.2</p><p>B.1</p><p>C.3</p><p>D.0<br/></p>\",\"count\":4},{\"id\":\"1102\",\"questiontype\":\"single\",\"title\":\"<p>测试选择题1：</p><p>2017年2月21日是星期几？</p><p>A星期一</p><p>B星期二</p><p>C星期三</p><p>D星期四<br/></p>\",\"count\":4},{\"id\":\"1103\",\"questiontype\":\"single\",\"title\":\"<p>1-1=？请选择：</p><p>A.1</p><p>B.2</p><p>C.0</p><p>D.3<br/></p>\",\"count\":4},{\"id\":\"1104\",\"questiontype\":\"single\",\"title\":\"<p>图中画的是什么？</p>\",\"count\":4},{\"id\":\"1105\",\"questiontype\":\"single\",\"title\":\"<p>测试选择题1：</p><p>2017年2月21日是星期几？</p><p>A星期一</p><p>B星期二</p><p>C星期三</p><p>D星期四<br/></p>\",\"count\":4},{\"id\":\"1106\",\"questiontype\":\"single\",\"title\":\"<p>打点做错的题会进错题本吗？</p><p>A会</p><p>B不会</p><p>C不知道</p>\",\"count\":3}]},{\"title\":\"二、多选题\",\"count\":1,\"questiontype\":\"choice\",\"question\":[{\"id\":\"1201\",\"questiontype\":\"choice\",\"title\":\"<p>测试选择题1：</p><p>2017年1月21日是什么天气</p><p>A下雪</p><p>B很冷</p><p>C晴天</p><p>D打雷<br/></p>\",\"count\":4},{\"id\":\"1202\",\"questiontype\":\"choice\",\"title\":\"<p>测试选择题2：</p><p>2017年2月21日是什么天气</p><p>A下雪</p><p>B很冷</p><p>C晴天</p><p>D打雷<br/></p>\",\"count\":4},{\"id\":\"1203\",\"questiontype\":\"choice\",\"title\":\"<p>测试选择题3：</p><p>2017年3月21日是什么天气</p><p>A下雪</p><p>B很冷</p><p>C晴天</p><p>D打雷<br/></p>\",\"count\":4},{\"id\":\"1204\",\"questiontype\":\"choice\",\"title\":\"<p>测试选择题4：</p><p>2017年4月21日是什么天气</p><p>A下雪</p><p>B很冷</p><p>C晴天</p><p>D打雷<br/></p>\",\"count\":4}]},{\"title\":\"三、判断题\",\"count\":1,\"questiontype\":\"judge\",\"question\":[{\"id\":\"1301\",\"questiontype\":\"judge\",\"title\":\"<p>规定了原点、正方向、单位长度的直线叫数轴。<br/></p>\",\"count\":0},{\"id\":\"1302\",\"questiontype\":\"judge\",\"title\":\"<p>测试判断题1：</p><p>2017年2月21日是星期二。<br/></p>\",\"count\":0}]}]";

        QuestionEntity[] list = Convert.fromJson(sData, QuestionEntity[].class);
        List<QuestionEntity> listData = new ArrayList<>();
        for(int i=0;i<list.length;i++){
            listData.add(list[i]);
        }

        String[] zimu = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

        int iID = 1;
        mListItem = new ArrayList<>();
        for(QuestionEntity question : listData){
            QuestionItemEntity itemEntity = new QuestionItemEntity(question.getTitle(),question.getCount());
            mListItem.add(itemEntity);

            for(QuestionItemEntity item : question.getQuestion()){

                item.selector = new ArrayList<>();
                item.selected = new HashSet<>();
                if (item.getQuestiontype().equals("judge")){
                    item.selector.add("正确");
                    item.selector.add("错误");
                }
                else if(item.getQuestiontype().equals("single") || item.getQuestiontype().equals("choice")) {
                    int iCount = item.getCount() % zimu.length;
                    for (int i = 0; i < iCount; i++) {
                        item.selector.add(zimu[i]);
                    }
                }

                item.setItmeID(String.valueOf(iID++));
                mListItem.add(item);
            }
        }

        mQuestionAdapter.addAll(mListItem);
    }

/*
    //作业题目转换
    private void doClick1() {
        String sData = "{'id':'1101','questiontype':'single','title':'<p>1+1=？请选择：</p><p>A.2</p><p>B.1</p><p>C.3</p><p>D.0<br/></p>','count':4}";

        QuestionItemEntity questionItem = Convert.fromJson(sData, QuestionItemEntity.class);

        mBinding.txtTitle.setText(questionItem.getTitle());
    }

    //作业题目数组转换
    private void doClick2() {
        String sData = "[{\"id\":\"1101\",\"questiontype\":\"single\",\"title\":\"<p>1+1=？请选择：\",\"count\":4},{\"id\":\"1102\",\"questiontype\":\"single\",\"title\":\"<p>测试选择题22：</p>\",\"count\":4}]";

        QuestionItemEntity[] list = Convert.fromJson(sData, QuestionItemEntity[].class);

        mBinding.txtTitle.setText(String.valueOf(list.length));
    }

    //作业大题转换
    private void doClick3() {
        String sData = "{\"title\":\"一、单选题\",\"count\":6,\"questiontype\":\"single\",\"question\":[{\"id\":\"1101\",\"questiontype\":\"single\",\"title\":\"<p>1+1=？请选择：</p><p>A.2</p><p>B.1</p><p>C.3</p><p>D.0<br/></p>\",\"count\":4},{\"id\":\"1102\",\"questiontype\":\"single\",\"title\":\"<p>测试选择题1：</p><p>2017年2月21日是星期几？</p><p>A星期一</p><p>B星期二</p><p>C星期三</p><p>D星期四<br/></p>\",\"count\":4},{\"id\":\"1103\",\"questiontype\":\"single\",\"title\":\"<p>1-1=？请选择：</p><p>A.1</p><p>B.2</p><p>C.0</p><p>D.3<br/></p>\",\"count\":4},{\"id\":\"1104\",\"questiontype\":\"single\",\"title\":\"<p>图中画的是什么？</p>\",\"count\":4},{\"id\":\"1105\",\"questiontype\":\"single\",\"title\":\"<p>测试选择题1：</p><p>2017年2月21日是星期几？</p><p>A星期一</p><p>B星期二</p><p>C星期三</p><p>D星期四<br/></p>\",\"count\":4},{\"id\":\"1106\",\"questiontype\":\"single\",\"title\":\"<p>打点做错的题会进错题本吗？</p><p>A会</p><p>B不会</p><p>C不知道</p>\",\"count\":3}]}";

        QuestionEntity list = Convert.fromJson(sData, QuestionEntity.class);

        mBinding.txtTitle.setText(list.getTitle());

    }

    //作业大题数组转换
    private void doClick4() {
        String sData = "[{\"title\":\"一、单选题\",\"count\":6,\"questiontype\":\"single\",\"question\":[{\"id\":\"1101\",\"questiontype\":\"single\",\"title\":\"<p>1+1=？请选择：</p><p>A.2</p><p>B.1</p><p>C.3</p><p>D.0<br/></p>\",\"count\":4},{\"id\":\"1102\",\"questiontype\":\"single\",\"title\":\"<p>测试选择题1：</p><p>2017年2月21日是星期几？</p><p>A星期一</p><p>B星期二</p><p>C星期三</p><p>D星期四<br/></p>\",\"count\":4},{\"id\":\"1103\",\"questiontype\":\"single\",\"title\":\"<p>1-1=？请选择：</p><p>A.1</p><p>B.2</p><p>C.0</p><p>D.3<br/></p>\",\"count\":4},{\"id\":\"1104\",\"questiontype\":\"single\",\"title\":\"<p>图中画的是什么？</p>\",\"count\":4},{\"id\":\"1105\",\"questiontype\":\"single\",\"title\":\"<p>测试选择题1：</p><p>2017年2月21日是星期几？</p><p>A星期一</p><p>B星期二</p><p>C星期三</p><p>D星期四<br/></p>\",\"count\":4},{\"id\":\"1106\",\"questiontype\":\"single\",\"title\":\"<p>打点做错的题会进错题本吗？</p><p>A会</p><p>B不会</p><p>C不知道</p>\",\"count\":3}]},{\"title\":\"二、多选题\",\"count\":1,\"questiontype\":\"choice\",\"question\":[{\"id\":\"1201\",\"questiontype\":\"choice\",\"title\":\"<p>测试选择题1：</p><p>2017年1月21日是什么天气</p><p>A下雪</p><p>B很冷</p><p>C晴天</p><p>D打雷<br/></p>\",\"count\":4},{\"id\":\"1202\",\"questiontype\":\"choice\",\"title\":\"<p>测试选择题2：</p><p>2017年2月21日是什么天气</p><p>A下雪</p><p>B很冷</p><p>C晴天</p><p>D打雷<br/></p>\",\"count\":4},{\"id\":\"1203\",\"questiontype\":\"choice\",\"title\":\"<p>测试选择题3：</p><p>2017年3月21日是什么天气</p><p>A下雪</p><p>B很冷</p><p>C晴天</p><p>D打雷<br/></p>\",\"count\":4},{\"id\":\"1204\",\"questiontype\":\"choice\",\"title\":\"<p>测试选择题4：</p><p>2017年4月21日是什么天气</p><p>A下雪</p><p>B很冷</p><p>C晴天</p><p>D打雷<br/></p>\",\"count\":4}]},{\"title\":\"三、判断题\",\"count\":1,\"questiontype\":\"judge\",\"question\":[{\"id\":\"1301\",\"questiontype\":\"judge\",\"title\":\"<p>规定了原点、正方向、单位长度的直线叫数轴。<br/></p>\",\"count\":0},{\"id\":\"1302\",\"questiontype\":\"judge\",\"title\":\"<p>测试判断题1：</p><p>2017年2月21日是星期二。<br/></p>\",\"count\":0}]}]";

        QuestionEntity[] list = Convert.fromJson(sData, QuestionEntity[].class);

        mBinding.txtTitle.setText(String.valueOf(list.length));
    }
*/


}
