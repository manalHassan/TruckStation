package truckstationsa.truckstation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by wafaa7maD on 05/03/18.
 */

import com.google.firebase.auth.FirebaseAuth;
public class Publicownerforcustmer extends AppCompatActivity {

        /**
         * The {@link ViewPager} that will host the section contents.
         */
        private ViewPager mViewPager;
       // private FirebaseAuth mAuth;
       // private FirebaseAuth.AuthStateListener mAuthListener;
     public static String user1="";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
           Bundle b = getIntent().getExtras();
            user1 = b.getString("id");
            Toast.makeText(this, "ID="+user1 ,Toast.LENGTH_SHORT).show();
            //Bundle bundle = new Bundle();
            //bundle.putString("id", user1);
           // set Fragmentclass Arguments
         // publicTab1profileforcustmer fragobj = new publicTab1profileforcustmer();
          //  fragobj.setArguments(bundle);
            //
            setContentView(R.layout.activity_public_ownerforcustomer);

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            // Create the adapter that will return a fragment for each of the three
            // primary sections of the activity.
        /*
      The {@link android.support.v4.view.PagerAdapter} that will provide
      fragments for each of the sections. We use a
      {@link FragmentPagerAdapter} derivative, which will keep every
      loaded fragment in memory. If this becomes too memory intensive, it
      may be best to switch to a
      {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
            SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
// SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
            // Set up the ViewPager with the sections adapter.
            mViewPager = (ViewPager) findViewById(R.id.container);
            mViewPager.setAdapter(mSectionsPagerAdapter);

            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

            mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
            contextOfApplication = getApplicationContext();
           // mAuth = FirebaseAuth.getInstance();

            /*mAuthListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    if (firebaseAuth.getCurrentUser() == null) {
                        Intent loginIntent = new Intent(truckstationsa.truckstation.Publicownerforcustmer.this, publicOnerLogIn.class);
                        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(loginIntent);
                    }
                }
            };*/

        }



       // @Override
       // protected void onStart() {
         //   super.onStart();
        //    mAuth.addAuthStateListener(mAuthListener);

      //  }

        public static Context contextOfApplication;
        public static Context getContextOfApplication()
        {
            return contextOfApplication;
        }
        /*
            @Override
            public boolean onCreateOptionsMenu(Menu menu) {
                // Inflate the menu; this adds items to the action bar if it is present.
                getMenuInflater().inflate(R.menu.menu_public_main, menu);
                return true;
            }
        */




        /**
         * A placeholder fragment containing a simple view.
         */
        public static class PlaceholderFragment extends Fragment {
            /**
             * The fragment argument representing the section number for this
             * fragment.
             */
            private static final String ARG_SECTION_NUMBER = "section_number";

            public PlaceholderFragment() {
            }

            /**
             * Returns a new instance of this fragment for the given section
             * number.
             */
            public static PlaceholderFragment newInstance(int sectionNumber) {
                PlaceholderFragment fragment = new PlaceholderFragment();
                Bundle args = new Bundle();
                args.putInt(ARG_SECTION_NUMBER, sectionNumber);
                args.putString("id",user1);
                fragment.setArguments(args);
                return fragment;
            }

            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                     Bundle savedInstanceState) {
               // user1 = getArguments().getString("id");
               // String user1 = getArguments().getString("id");
                View rootView = inflater.inflate(R.layout.public_tab1_profileforcustomer, container, false);
                TextView textView = (TextView) rootView.findViewById(R.id.section_label_public);
                textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
                return rootView;

            }
        }

        /**
         * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
         * one of the sections/tabs/pages.
         */

        public class SectionsPagerAdapter extends FragmentPagerAdapter {

            public SectionsPagerAdapter(FragmentManager fm) {
                super(fm);
            }

            @Override
            public Fragment getItem(int position) {
                // getItem is called to instantiate the fragment for the given page.
                // Return a PlaceholderFragment (defined as a static inner class below).
                switch (position) {
                    case 0:
                        publicTab1profileforcustmer tab1 = new publicTab1profileforcustmer();
                        return tab1;
                    case 1:
                        publicTab2postsforcustomer tab2 = new publicTab2postsforcustomer();
                        return tab2;
                    case 2:
                        publicTab3menuforcustomer tab3 = new publicTab3menuforcustomer();
                        return tab3;
                }

                return null;
            }

            @Override
            public int getCount() {
                // Show 3 total pages.
                return 3;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "معلومات شخصية";
                    case 1:
                        return "صور";
                    case 2:
                        return "قائمة الطعام";
                }
                return null;
            }
        }
    public String getuser1() {
        return user1;
         }
    }
