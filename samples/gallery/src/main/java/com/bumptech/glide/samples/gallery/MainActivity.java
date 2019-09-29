package com.bumptech.glide.samples.gallery;

import android.Manifest.permission;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
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
import com.bumptech.glide.MemoryCategory;

/** Displays a {@link HorizontalGalleryFragment}. */
@RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
public class MainActivity extends FragmentActivity {

  private static final int REQUEST_READ_STORAGE = 0;
  private  GlideRequests glideRequests;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main_activity);
    GlideApp.get(this).setMemoryCategory(MemoryCategory.HIGH);
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

  ImageView imageView;
  private void replaceFragment() {
    imageView = findViewById(R.id.image_view);
    glideRequests= GlideApp.with(this);
    imageView.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {


        GlideRequest<Drawable> requestBuilder =glideRequests.asDrawable().fitCenter();
        String url = "http://pic25.nipic.com/20121112/9252150_150552938000_2.jpg";
        String url2 = "https://media1.giphy.com/media/LOu2MMZgOPItHK6QJd/giphy.gif?cid=e1bb72fffdbf40a7f71e87f837f333fbea9ed8145bc8e181&rid=giphy.gif";
        String url3 = "http://pim.ycw.com/chat/pic/201909/29/eea6e2432c338c43ef4b583c17e0d0ef";
        String url4 = "http://pim.ycw.com/chat/pic/201909/29/0643d50ddc179d1723ba5ce1fd8e021c";

        requestBuilder.clone().load(url4).into(imageView);
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
