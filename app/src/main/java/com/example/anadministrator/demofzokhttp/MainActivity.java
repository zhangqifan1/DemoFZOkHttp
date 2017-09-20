package com.example.anadministrator.demofzokhttp;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private String json_path = "http://publicobject.com/helloworld.txt";
    private String ImagePath = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3520210008,622099390&fm=27&gp=0.jpg";
    private String Login_path = "http://169.254.53.96:8080/web/LoginServlet";
    private String Picture_path = "https://10.url.cn/eth/ajNVdqHZLLAxibwnrOxXSzIxA76ichutwMCcOpA45xjiapneMZsib7eY4wUxF6XDmL2FmZEVYsf86iaw";
    private ImageView mImageView;
    private OkHttpManager okHttpManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = (ImageView) findViewById(R.id.imageView);
        okHttpManager = OkHttpManager.getInstance();
    }

    /**
     * 通过点击事件执行okhttp里封装的根据网址,获取字符串的逻辑操作.
     *
     * @param view
     */
    public void okhttp_json(View view) {
        okHttpManager.asyncJsonStringByURL(json_path, new OkHttpManager.Function1() {
            @Override
            public void onResponse(String result) {
                System.out.println(result);
                Toast.makeText(MainActivity.this,result,Toast.LENGTH_SHORT).show();
            }
        });
    }

    //像服务器提交账号及密码
    public void okhttp_table(View view) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("qq","10000");
        hashMap.put("pwd","abcde");
        okHttpManager.sendComplexForm(Login_path, hashMap, new OkHttpManager.Function1() {
            @Override
            public void onResponse(String result) {
                System.out.println(result);
            }
        });
    }

    //下载图片
    public void okhttp_picture(View view) {
        //IllegalStateException  非法状态异常
        //java.lang.reflect.InvocationTargetException  调用目标异常
        if(okHttpManager==null){

        }
        if(okHttpManager!=null){
            okHttpManager.downLoadImage(ImagePath, new OkHttpManager.Function4() {
                @Override
                public void onResponse(Bitmap result) {
                    if(result!=null){
                        mImageView.setImageBitmap(result);
                    }

                }
            });
        }

    }
}