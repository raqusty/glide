package com.bumptech.glide.samples.gallery;

import android.Manifest.permission;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.MemoryCategory;
import java.io.File;

/** Displays a {@link HorizontalGalleryFragment}. */
@RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
public class MainActivity extends FragmentActivity {

  private static final int REQUEST_READ_STORAGE = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main_activity);
//    GlideApp.get(this).setMemoryCategory(MemoryCategory.HIGH);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
        && ContextCompat.checkSelfPermission(this, permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
      requestStoragePermission();
    } else {
      replaceFragment();
    }
  }

  private void requestStoragePermission() {
    ActivityCompat.requestPermissions(
        this, new String[] {permission.READ_EXTERNAL_STORAGE}, REQUEST_READ_STORAGE);
  }


  ImageView imageView1,imageView2;
  private void replaceFragment() {
    imageView1 = findViewById(R.id.image_view1);
    imageView2 = findViewById(R.id.image_view2);

    imageView1.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });

    imageView2.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {

        String url2 = "https://media1.giphy.com/media/LOu2MMZgOPItHK6QJd/giphy.gif?cid=e1bb72fffdbf40a7f71e87f837f333fbea9ed8145bc8e181&rid=giphy.gif";
        String url3 = "http://pim.ycw.com/chat/pic/201909/29/eea6e2432c338c43ef4b583c17e0d0ef";
        String url4 = "https://p-meet.oss-cn-shenzhen.aliyuncs.com/test/common/pic/202008/14/d7a84ebe6c4e4608c42eaeff74aaed30";
//        GlideApp.with(MainActivity.this).load(new GlideUrl(url2)).into(imageView2);
        GlideApp.with(MainActivity.this).load(new GlideUrl(url4,"1234560000000000")).into(imageView2);
      }
    });



//    Fragment fragment = new HorizontalGalleryFragment();
//    getSupportFragmentManager()
//        .beginTransaction()
//        .replace(R.id.fragment_container, fragment)
//        .commit();
  }

  @Override
  public void onRequestPermissionsResult(
      int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    switch (requestCode) {
      case REQUEST_READ_STORAGE:
        {
          // If request is cancelled, the result arrays are empty.
          if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            replaceFragment();
          } else {
            Toast.makeText(this, "Storage permission is required", Toast.LENGTH_LONG).show();
            requestStoragePermission();
          }
        }
    }
  }
}
