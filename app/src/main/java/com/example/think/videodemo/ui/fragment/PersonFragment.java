package com.example.think.videodemo.ui.fragment;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.think.videodemo.R;
import com.example.think.videodemo.base.BaseFragment;
import com.example.think.videodemo.ui.activity.MoreActivity;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

public class PersonFragment extends BaseFragment {

    private Unbinder unbinder;

    private Uri imageUri;

    //九个选项卡
    @BindView(R.id.item_1)
    LinearLayout tab_item_1;

    @BindView(R.id.item_2)
    LinearLayout tab_item_2;

    @BindView(R.id.item_3)
    LinearLayout tab_item_3;

    @BindView(R.id.item_4)
    LinearLayout tab_item_4;

    @BindView(R.id.item_5)
    LinearLayout tab_item_5;





    @BindView(R.id.item_text_1)
    TextView item_text_1;

    @BindView(R.id.item_text_2)
    TextView item_text_2;

    @BindView(R.id.item_text_3)
    TextView item_text_3;

    @BindView(R.id.item_text_4)
    TextView item_text_4;

    @BindView(R.id.item_text_5)
    TextView item_text_5;


    @BindView(R.id.item_image_1)
    ImageView item_image_1;

    @BindView(R.id.item_image_2)
    ImageView item_image_2;

    @BindView(R.id.item_image_3)
    ImageView item_image_3;

    @BindView(R.id.item_image_4)
    ImageView item_image_4;

    @BindView(R.id.item_image_5)
    ImageView item_image_5;


    //头像
    @BindView(R.id.user_head)
    CircleImageView user_head;

    //昵称
    @BindView(R.id.user_name)
    TextView user_name;

    //详情
    @BindView(R.id.more)
    TextView more;


    //



    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_person, null);

        unbinder = ButterKnife.bind(this,view);

        //初始化选项卡内容
        initTabItem();

        //点击事件
        clickAction();

        return view;
    }

    private void initTabItem() {
        //头像设置
        user_head.setImageResource(R.drawable.user_head);

        //昵称
        user_name.setText("Boomerrr");

        //选项卡内容设定
        item_text_1.setText("我的资讯");

        item_text_2.setText("历史记录");

        item_text_3.setText("我的收藏");

        item_text_4.setText("意见反馈");

        item_text_5.setText("关于我们");

        item_image_1.setImageResource(R.drawable.item_info);

        item_image_2.setImageResource(R.drawable.item_history);

        item_image_3.setImageResource(R.drawable.item_collect);

        item_image_4.setImageResource(R.drawable.item_advice);

        item_image_5.setImageResource(R.drawable.item_about_us);

    }



    //默认选项卡有9项   没有内容的以空白存在  每个对应点击方法
    private void clickAction() {


        //五个选项卡的点击事件
        tab_item_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item_1_Function();
            }
        });

        tab_item_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item_2_Function();
            }
        });

        tab_item_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item_3_Function();
            }
        });

        tab_item_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item_4_Function();
            }
        });

        tab_item_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item_5_Function();
            }
        });




        //头像的点击事件   可以更换本地图片更换头像
        user_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_head_change_Function();
            }
        });


        //更多详情点击事件
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moreFunction();
            }
        });

    }

    //更换头像逻辑
    private void user_head_change_Function() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{ Manifest.permission. WRITE_EXTERNAL_STORAGE }, 1);
        } else {
            openAlbum();
        }
    }

    private void openAlbum() {

        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, 2); // 打开相册

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(getActivity(), "你拒绝了权限请求", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    try {
                        // 将拍摄的照片显示出来
                        Bitmap bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(imageUri));
                        user_head.setImageBitmap(bitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    // 判断手机系统版本号
                    if (Build.VERSION.SDK_INT >= 19) {
                        // 4.4及以上系统使用这个方法处理图片
                        handleImageOnKitKat(data);
                    } else {
                        // 4.4以下系统使用这个方法处理图片
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            default:
                break;
        }
    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        Log.d("TAG", "handleImageOnKitKat: uri is " + uri);
        if (DocumentsContract.isDocumentUri(getActivity(), uri)) {
            // 如果是document类型的Uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1]; // 解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果是content类型的Uri，则使用普通方式处理
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            // 如果是file类型的Uri，直接获取图片路径即可
            imagePath = uri.getPath();
        }
        displayImage(imagePath); // 根据图片路径显示图片
    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        // 通过Uri和selection来获取真实的图片路径
        Cursor cursor = getActivity().getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath) {

        Dialog dialog = new Dialog(getContext(),R.style.AppTheme_NoActionBar);
        dialog.setContentView(R.layout.dialog_makesure);
        Button sure = dialog.findViewById(R.id.sure);
        Button cancel = dialog.findViewById(R.id.cancel);
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imagePath != null) {
                    Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
                    user_head.setImageBitmap(bitmap);
                } else {
                    Toast.makeText(getActivity(), "获取图片失败", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.setCancelable(true);

    }

    //更多内容
    private void moreFunction() {

    }

    private void item_1_Function() {
        toast("你好");
    }

    private void item_2_Function() {

    }

    private void item_3_Function() {

    }

    private void item_4_Function() {

    }

    private void item_5_Function() {
        startActivity(new Intent(getActivity(), MoreActivity.class));
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
