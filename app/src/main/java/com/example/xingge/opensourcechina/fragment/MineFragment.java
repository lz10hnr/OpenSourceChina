package com.example.xingge.opensourcechina.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.xingge.opensourcechina.R;
import com.example.xingge.opensourcechina.base.BaseFragment;
import com.example.xingge.opensourcechina.config.XmlParserBuilder;
import com.example.xingge.opensourcechina.model.enitity.User;
import com.example.xingge.opensourcechina.model.http.HttpFactory;
import com.example.xingge.opensourcechina.model.http.biz.IMineModel;
import com.example.xingge.opensourcechina.model.http.biz.MineModelImpl;
import com.example.xingge.opensourcechina.model.http.callback.NetWorkCallBack;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import retrofit2.http.Url;

/**
 * Created by xingge on 2017/4/20.
 */

public class MineFragment extends BaseFragment {

    User user;
    private IMineModel mineModel;
    private Button openBtn;
    private File headImgFile;
    @Override
    protected int layoutId() {
        return R.layout.mine_fragment;
    }

    @Override
    protected void initView(View view) {

        openBtn = (Button) view.findViewById(R.id.openBtn);
    }

    @Override
    protected void initData() {

        mineModel = new MineModelImpl();
        headImgFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/head.jpg");
    }

    @Override
    protected void initListener() {

        openBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                searchPhotos();

            }
        });
    }


    //打开相册
    private void searchPhotos(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,100);
    }

    //打开裁剪框
    private void crop(Uri uri){
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri,"image/*");
        //允许裁剪
        intent.putExtra("crop",true);
        //设置裁剪框比例
        intent.putExtra("aspectX",1);
        intent.putExtra("aspectY",1);

        intent.putExtra("output",Uri.fromFile(headImgFile));

        //设置返回值
//        intent.putExtra("return-data",true);
        startActivityForResult(intent,200);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100){
            if(data != null){
                Uri uri = data.getData();
                crop(uri);
            }
        }else if(requestCode == 200){
            if(data != null){
                Bitmap bitmap = BitmapFactory.decodeFile(headImgFile.getAbsolutePath());
                //发送网络请求，上传图片
                Log.d("MineFragment", headImgFile.getAbsolutePath()+",bitmap = "+bitmap.getWidth());
            }

        }
    }

    @Override
    protected void loadData() {

        mineModel.login("18515410347","qwert12345++..","1",new NetWorkCallBack() {
            @Override
            public void onSuccess(String xmlData) {


                Log.d("MineFragment", xmlData);

                user = (User) XmlParserBuilder.getInstance().alias("oschina", User.class).build(xmlData);

                Map<String,String> params = new HashMap<>();
                params.put("uid",user.getUser().getUid());
                HttpFactory.create().get("http://www.oschina.net/action/api/my_information", params, new NetWorkCallBack() {
                    @Override
                    public void onSuccess(String xmlData) {
                        Log.d("MineFragment", xmlData);
                    }

                    @Override
                    public void onError(String errorMsg) {

                    }
                });

            }

            @Override
            public void onError(String errorMsg) {

            }
        });

    }


}
