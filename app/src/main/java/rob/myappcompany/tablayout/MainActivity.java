package rob.myappcompany.tablayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.Log;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Initialize variable
    TabLayout tabLayout;
    ViewPager viewPager;

    public static String Tag = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);

        ArrayList<String> arrayList = new ArrayList<>();

        //Add title
        arrayList.add("Basic");
        arrayList.add("Advance");
        arrayList.add("Pro");

        tabLayout.setupWithViewPager(viewPager);
        prepareViewPager(viewPager, arrayList);
    }

    private void prepareViewPager(ViewPager viewPager, ArrayList<String> arrayList) {
        MainAdapter adapter = new MainAdapter(getSupportFragmentManager());


        //inti main fragment
        MainFragment mainFragment = new MainFragment();
        for (int i =0; i< arrayList.size(); i++){
            Bundle bundle = new Bundle();

            //put title
            bundle.putString("title", arrayList.get(i));
            //set Argument
            mainFragment.setArguments(bundle);
            //Add fragment
            adapter.addFragment(mainFragment, arrayList.get(i));
            mainFragment = new MainFragment();
        }
        viewPager.setAdapter(adapter);
    }

    private class MainAdapter extends FragmentPagerAdapter {
        //init array list
        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        ArrayList<String> stringArrayList = new ArrayList<>();
        int[]image ={R.drawable.ic_balance, R.drawable.ic_photo, R.drawable.ic_screen};

        //Create constructor

        public void addFragment(Fragment fragment, String s){
            //Adda fragment
            fragmentArrayList.add(fragment);
            //add title
            stringArrayList.add(s);
        }

        public MainAdapter(FragmentManager supportFragmentManager){
            super(supportFragmentManager);

        }

        @Override
        public int getCount() {
            //Return fragment position

            return fragmentArrayList.size();
        }

        @Override
        public Fragment getItem(int position) {
            //Return fragment position


            return fragmentArrayList.get(position);
        }


        @Override
        public CharSequence getPageTitle(int position) {

            Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), image[position]);
            //Set bound
            drawable.setBounds(0,0,drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            //Init spannable string
            SpannableString spannableString = new SpannableString("       "+ stringArrayList.get(position));

            ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BOTTOM);

            //set span
            spannableString.setSpan(imageSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            //Return spannable string

            return spannableString;
        }
    }
}