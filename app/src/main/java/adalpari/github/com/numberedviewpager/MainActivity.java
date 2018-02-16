package adalpari.github.com.numberedviewpager;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NumberedViewPagerHandler numberedViewPagerHandler = findViewById(R.id.numbered_view_page_handler);

        List<String> imageUrl = new ArrayList<>();
        imageUrl.add("1");
        imageUrl.add("2");
        imageUrl.add("3");
        imageUrl.add("4");
        imageUrl.add("5");

        ViewPager viewPager = numberedViewPagerHandler.getViewPager();
        viewPager.setAdapter(new CustomPagerAdapter(imageUrl, this));
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

            TextView imageText = layout.findViewById(R.id.image_text);
            String imageUrl = imageUrls.get(position);
            imageText.setText(imageUrl);

            return layout;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
