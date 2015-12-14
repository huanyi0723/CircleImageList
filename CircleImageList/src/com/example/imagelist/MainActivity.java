package com.example.imagelist;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore.Images.Media;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity {

  ArrayList<String> fileNames = new ArrayList<String>(); // 本地图片路径
  ImageAdapter imageAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    initData();

    GridView listView = (GridView) findViewById(R.id.gridview);
    // ImageAdapter imageAdapter = new
    // ImageAdapter(getApplicationContext(),Images.imageUrls);
    imageAdapter = new ImageAdapter(getApplicationContext(), fileNames);

    listView.setAdapter(imageAdapter);

    new Handler().postDelayed(new Runnable() {
      public void run() {
        imageAdapter.notifyDataSetInvalidated();
      }
    }, 1000); // 5秒

  }

  private void initData() {

    fileNames.clear();
    Cursor cursor = getContentResolver().query(Media.EXTERNAL_CONTENT_URI, null, null, null, null);
    while (cursor.moveToNext()) {
      byte[] data = cursor.getBlob(cursor.getColumnIndex(Media.DATA)); // 图片的保存位置的数据
      fileNames.add(new String(data, 0, data.length - 1));
    }
  }

}
