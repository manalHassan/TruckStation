package truckstationsa.truckstation;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.design.widget.TabLayout;
import android.widget.TableLayout;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity implements TabLayout.OnTabSelectedListener,ViewPager.OnPageChangeListener {
    private static final String TAG = "Main2Activity";
    private SectionsPagerAdapter sectionsPagerAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Log.d(TAG,"onCreate: starting");
        sectionsPagerAdapter=new SectionsPagerAdapter(getSupportFragmentManager());

        //set up the viweer page with the section adapter
        viewPager=(ViewPager)findViewById(R.id.container);
        setUpViewPager(viewPager);

        TabLayout tableLayout=(TabLayout) findViewById(R.id.tabs);
        tableLayout.setupWithViewPager(viewPager);



    }
private void setUpViewPager(ViewPager viewPager){//to creat sections pager adapter and add fragments to section pager adapter
        SectionsPagerAdapter adapter= new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1FragmentPrivateFollow(),"عربات الطعام الخاصة");
        adapter.addFragment(new Tab2FragmentPublicFollow(),"عربات الطعام العامة");
        viewPager.setAdapter(adapter);
}


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
