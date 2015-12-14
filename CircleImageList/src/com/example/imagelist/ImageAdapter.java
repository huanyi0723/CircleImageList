/*
 * ImageAdapter.java
 * classes : com.example.imagelist.ImageAdapter
 * @author  张奕
 * V 1.0.0
 * Create at 2015年8月28日 下午5:22:48
 */
package com.example.imagelist;

import com.bumptech.glide.Glide;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * com.example.imagelist.ImageAdapter
 * 
 * @author 张奕 <br/>
 *         create at 2015年8月28日 下午5:22:48
 */
public class ImageAdapter extends BaseAdapter {

  private Context context;
  private String[] imageUrls;
  ArrayList<String> fileNames;
  private LinearLayout.LayoutParams mImageViewLayoutParams;

  /*
   * public ImageAdapter(Context context, String[] imageUrls) { super();
   * this.context = context; this.imageUrls = imageUrls; }
   */

  public ImageAdapter(Context context, ArrayList<String> fileNames) {
    super();
    this.context = context;
    this.fileNames = fileNames;
    
    DisplayMetrics dm = context.getResources().getDisplayMetrics();  
    int wh = dm.widthPixels;

    int w = (wh - context.getResources().getDimensionPixelSize(R.dimen.test) * 2) / 3;
    mImageViewLayoutParams = new LinearLayout.LayoutParams(w, w);
  }

  @Override
  public int getCount() {
    return fileNames.size();
  }

  @Override
  public Object getItem(int position) {
    return null;
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    
    final ViewHolder holder;
    
    if (convertView == null) {
      convertView = LayoutInflater.from(context).inflate(R.layout.item, null);
      holder = new ViewHolder();

      holder.image = (CircleImageView) convertView.findViewById(R.id.image);

      convertView.setTag(holder);
    } else {
      holder = (ViewHolder) convertView.getTag();
    }

    CircleImageView image = (CircleImageView) convertView.findViewById(R.id.image);
    image.setLayoutParams(mImageViewLayoutParams);

    String string = fileNames.get(position);
    Glide.with(context).load(string).placeholder(R.color.test).into(image);
    

    return convertView;
  }
  
  class ViewHolder {
    
    CircleImageView image;  //圆图
    
  }
  
  
}
