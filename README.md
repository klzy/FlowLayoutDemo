当前这个Demo是基于'com.zhy:flowlayout-lib:1.0.3'进行的单选、多选功能进行的展示。
![](https://github.com/klzy/FlowLayoutDemo/blob/master/screen/1.png)
![](https://github.com/klzy/FlowLayoutDemo/blob/master/screen/2.png)
通过这个Demo可学到如下的技术点：
1 此项目使用dataBinding技术
2 在BindingAdapter类中，展示了几个自定义BindingAdapter的方法

    // 默认加载自带图片，当存在网络上的图片时则加载网络图片
    @android.databinding.BindingAdapter({"imageUrl", "android:src"})
    public static void loadImageFromUrl(ImageView view, String url, Drawable drawable) 

    // TextView 加载 富文本格式的内容
    @android.databinding.BindingAdapter({"htmlTxt"})
    public static void loadText(TextView view, String statebg) 

    // WebView 加载 富文本格式的内容
    @android.databinding.BindingAdapter({"htmlWeb"})
    public static void loadText(WebView view, String statebg)

3 通过Convert类，把json字符串转成对象类、和对象类数组的方法
    QuestionItemEntity questionItem = Convert.fromJson(sData, QuestionItemEntity.class);
    QuestionItemEntity[] list = Convert.fromJson(sData, QuestionItemEntity[].class);

    此处特别注意  xxx[]的用法

4 在初始化题目类时，可看到对答案选项的处理方法
    String[] zimu = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
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

5 通过BindingViewHolder实现 RecyclerView.Adapter 的适配，此技术可在QuestionAdapter单元中了解

6 在QuestionAdapter单元的 public void addAll(List<QuestionItemEntity> datas) 方法中实现了数据加载、数据预处理，
根据题目的类型设定了项选的个数、布局文件ID、选项布局ID
    if (itemEntity.getQuestiontype().equals("normal")) {
        itemEntity.setMaxSelect(-1);
        itemEntity.setLayoutID(R.layout.item_question_main);
        itemEntity.setLayoutItem(R.layout.tv);
    } else if (itemEntity.getQuestiontype().equals("judge")) {
        itemEntity.setMaxSelect(1);
        itemEntity.setLayoutID(R.layout.item_question_choice);
        itemEntity.setLayoutItem(R.layout.tv_single);
    }
在此处理数据后，在onBindViewHolder方法中可以直接使用相关属性，不必每次在判断处理，极大的优化数据展示速度

7 BindingViewHolder是可以强制进行转意，使用布局控件的，参见下面的代码
  ((ItemQuestionChoiceBinding) holder.getBinding()).flowLayout.setAdapter(tagAdapter);

8 TagFlowLayout设置预选中项、及监听选择的方法，参见

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
    这里需要注意Set<Integer>的用法

9 需要特别注意TagAdapter的创建方法，<String>是关键所在
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
    这里需要特别注意设置选中项的方法  public boolean setSelected(int position, String s) ，不然在列表上下刷新时会看不到之前的选中项，这要与  questionEntity.setSelected(selectPosSet);   连用

10通过布局文件实现控件属性的选中、不选中的不同样式，看过下面的布局文件及可明白

tv_single.xml

<?xml version="1.0" encoding="utf-8"?>
<TextView xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_margin="5px"
    android:background="@drawable/tag_bg"
    android:gravity="center"
    android:text="Helloworld"
    android:textSize="@dimen/asr_size_3small"
    android:textColor="@drawable/text_color"
    android:drawablePadding="5px"
    android:drawableLeft="@drawable/tag_bg_single"
    />

text_color.xml

<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:color="#ffffff" android:state_checked="true"/>
    <item android:color="#666666"/>
</selector>

tag_bg_single.xml

<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:drawable="@mipmap/asr_d_checked" android:state_checked="true" />
    <item android:drawable="@mipmap/asr_d_checkedno"/>
</selector>

这里需要注意item中的属性名，只可以用drawable，不必再使用要设置的属性名
