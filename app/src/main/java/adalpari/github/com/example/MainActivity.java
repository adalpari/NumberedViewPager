package adalpari.github.com.example;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import adalpari.github.com.numberedviewpager.NumberedViewPagerHandler;

public class MainActivity extends AppCompatActivity {

    NumberedViewPagerHandler numberedViewPagerHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberedViewPagerHandler = findViewById(R.id.numbered_view_page_handler);

        List<String> imageUrl = new ArrayList<>();
        imageUrl.add("https://www.extremetech.com/wp-content/uploads/2017/03/smiling-android-640x353.jpg");
        imageUrl.add("https://i2.wp.com/hipertextual.com/files/2016/06/android-nougat-2.png");
        imageUrl.add("https://images.techhive.com/images/article/2016/09/android-old-habits-100682662-primary.idge.jpg");
        imageUrl.add("http://s3.amazonaws.com/poderpda/2017/07/Android-O-Logo.jpg");
        imageUrl.add("https://www.ayudacelular.com/wp-content/uploads/2018/01/Trucos-para-Android.jpg");

        numberedViewPagerHandler.setAdapter(new CustomPagerAdapter(imageUrl, this));
    }

    @Override
    protected void onDestroy() {
        numberedViewPagerHandler.onDestroy();
        super.onDestroy();
    }

    class CustomPagerAdapter extends PagerAdapter {

        private List<String> imageUrls;
        private Context context;

        CustomPagerAdapter(List<String> imageUrls, Context context){
            this.imageUrls = imageUrls;
            this.context = context;
        }

        @Override
        public int getCount() {
            return imageUrls.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            LayoutInflater inflater = LayoutInflater.from(context);
            ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.pager_item, container, false);

            ImageView itemImage = layout.findViewById(R.id.item_image);
            String imageUrl = imageUrls.get(position);
            Picasso
                    .with(context)
                    .load(imageUrl)
                    .fit()
                    .centerCrop()
                    .into(itemImage);

            container.addView(layout);
            return layout;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
