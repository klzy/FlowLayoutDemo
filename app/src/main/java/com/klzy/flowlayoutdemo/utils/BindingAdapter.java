package com.klzy.flowlayoutdemo.utils;

import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Spanned;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import java.io.InputStream;
import java.net.URL;

public class BindingAdapter {

    // 默认加载自带图片，当存在网络上的图片时则加载网络图片
    @android.databinding.BindingAdapter({"imageUrl", "android:src"})
    public static void loadImageFromUrl(ImageView view, String url, Drawable drawable) {
        Glide.with(view.getContext())
                .load(url)
                .placeholder(drawable)
                .into(view);
    }

    // TextView 加载 富文本格式的内容
    @android.databinding.BindingAdapter({"htmlTxt"})
    public static void loadText(TextView view, String statebg) {
        if (statebg!=null) {
            Spanned sp = Html.fromHtml(statebg, new Html.ImageGetter() {
                @Override
                public Drawable getDrawable(String source) {
                    InputStream is = null;
                    try {
                        is = (InputStream) new URL(source).getContent();
                        Drawable d = Drawable.createFromStream(is, "src");
                        d.setBounds(0, 0, d.getIntrinsicWidth(),
                                d.getIntrinsicHeight());
                        is.close();
                        return d;
                    } catch (Exception e) {
                        return null;
                    }
                }
            }, null);
            view.setText(sp);
        }
    }

    // WebView 加载 富文本格式的内容
    @android.databinding.BindingAdapter({"htmlWeb"})
    public static void loadText(WebView view, String statebg) {
        String a = "<div style=\"font-size:14px;\">"+statebg+"</div>";
        view.loadDataWithBaseURL(null,a,"text/html","UTF-8", null);

    }
}
