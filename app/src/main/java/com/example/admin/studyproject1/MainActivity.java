package com.example.admin.studyproject1;

import com.google.android.gms.ads.AdRequest;

import com.google.android.gms.ads.AdView;


import android.app.Fragment;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.util.ArraySet;
import android.view.SubMenu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);



        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /*

        AdView mAdView = (AdView) findViewById(R.id.adView);

        //테스트 에뮬
        //AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();

        // 실제용
        //AdRequest adRequest = new AdRequest.Builder().build();


        mAdView.loadAd(adRequest);

        */

        //메인화면 나오기
        FragmentMain mm = new FragmentMain();
        getFragmentManager().beginTransaction().replace(R.id.fragment_container,mm).commit();

        //쿨엔조이 게시판 숨기기 코드
        SharedPreferences setRefer = PreferenceManager.getDefaultSharedPreferences(this);
        Set<String> defArraySet = new HashSet<String>();
        defArraySet.add("0");
        defArraySet.add("1");
        defArraySet.add("2");
        defArraySet.add("3");
        defArraySet.add("4");
        defArraySet.add("5");
        Set<String> arraySet = setRefer.getStringSet("multi_select_list_preference_coolN",defArraySet);
        Object[] array = arraySet.toArray();

        DrawerLayout drawer2 = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView n = (NavigationView)drawer2.findViewById(R.id.nav_view);
        Menu m = n.getMenu();
        MenuItem aa = m.getItem(1);
        SubMenu s = aa.getSubMenu();
        int subSize = s.size();
        for(int j=0;j<subSize;j++)
        {
            s.getItem(j).setVisible(false); //쿨엔조이 게시판 일단 전부 가리기
        }
        for(int j=0;j<array.length;j++)
        {
            s.getItem(Integer.parseInt((String)array[j])).setVisible(true);
        }

        //메인게시판 메뉴 설정에따른 visible 조절 코드 onPostResume에도 똑같은 코드 넣어둬야 ... 실시간적용됨
        Set<String> arraySet2 = setRefer.getStringSet("multi_select_list_preference_boards",defArraySet);
        Object[] array2 = arraySet2.toArray();

        MenuItem boardMenu = m.getItem(0);
        SubMenu board_sub = boardMenu.getSubMenu();
        int board_subSize = board_sub.size();
        for(int j=0;j<board_subSize;j++)
        {
            board_sub.getItem(j).setVisible(false); // 메인 게시판 일단 전부 가리기
        }
        for(int j=0;j<array2.length;j++)
        {
            board_sub.getItem(Integer.parseInt((String)array2[j])).setVisible(true);
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_setting) {
            //Toast.makeText(getApplicationContext(),"settings",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, PrefActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        int group = -1;
        int index = -1;

        if (id == R.id.nav_coolnjoy)
        {
            group=0;
            index=0;
        } else if (id == R.id.nav_ruli)
        {
            group=0;
            index=1;
        } else if (id == R.id.nav_ppom1)
        {
            group=0;
            index=2;
        } else if (id == R.id.nav_ppom2)
        {
            group=0;
            index=3;
        } else if (id == R.id.nav_slr1)
        {
            group=0;
            index=4;
        } else if (id == R.id.nav_slr2)
        {
            group=0;
            index=5;
        } else if(id == R.id.nav_coolnjoy_board_0)
        {
            group=1;
            index=0;
        }
        else if(id == R.id.nav_coolnjoy_board_0)
        {
            group=1;
            index=0;
        }
        else if(id == R.id.nav_coolnjoy_board_1)
        {
            group=1;
            index=1;
        }
        else if(id == R.id.nav_coolnjoy_board_2)
        {
            group=1;
            index=2;
        }
        else if(id == R.id.nav_coolnjoy_board_3)
        {
            group=1;
            index=3;
        }
        else if(id == R.id.nav_coolnjoy_board_4)
        {
            group=1;
            index=4;
        }
        else if(id == R.id.nav_coolnjoy_board_5)
        {
            group=1;
            index=5;
        }
        else if(id == R.id.nav_coolnjoy_board_6)
        {
            group=1;
            index=6;
        }
        else if(id == R.id.nav_coolnjoy_board_7)
        {
            group=1;
            index=7;
        }
        else if(id == R.id.nav_coolnjoy_board_8)
        {
            group=1;
            index=8;
        }
        else if(id == R.id.nav_coolnjoy_board_9)
        {
            group=1;
            index=9;
        }
        else if(id == R.id.nav_coolnjoy_board_10)
        {
            group=1;
            index=10;
        }
        else if(id == R.id.nav_coolnjoy_board_11)
        {
            group=1;
            index=11;
        }
        else if(id == R.id.nav_coolnjoy_board_12)
        {
            group=1;
            index=12;
        }
        else if(id == R.id.nav_coolnjoy_board_13)
        {
            group=1;
            index=13;
        }
        else if(id == R.id.nav_coolnjoy_board_14)
        {
            group=1;
            index=14;
        }
        else if(id == R.id.nav_coolnjoy_board_15)
        {
            group=1;
            index=15;
        }
        else if(id == R.id.nav_coolnjoy_board_16)
        {
            group=1;
            index=16;
        }
        else if(id == R.id.nav_coolnjoy_board_17)
        {
            group=1;
            index=17;
        }
        else if(id == R.id.nav_coolnjoy_board_18)
        {
            group=1;
            index=18;
        }
        else if(id == R.id.nav_coolnjoy_board_19)
        {
            group=1;
            index=19;
        }
        else if(id == R.id.nav_coolnjoy_board_20)
        {
            group=1;
            index=20;
        }
        else if(id == R.id.nav_coolnjoy_board_21)
        {
            group=1;
            index=21;
        }
        else if(id == R.id.nav_coolnjoy_board_22)
        {
            group=1;
            index=22;
        }
        else if(id == R.id.nav_coolnjoy_board_23)
        {
            group=1;
            index=23;
        }
        else if(id == R.id.nav_coolnjoy_board_24)
        {
            group=1;
            index=24;
        }
        else if (id ==R.id.nav_help)
        {
            Toast.makeText(getApplicationContext(),"추후 추가예정 : 추가 게시판, 카톡 및 문자 공유" +
                    "\n추가 하고싶은 기능 : 새 게시물 알림 기능\n\n많은 후기 부탁드립니다",Toast.LENGTH_LONG).show();
        }else if (id == R.id.nav_kakao) {
            String subject = "특가 게시판 모음 무료 앱을 추천합니다\n";
            String text = "https://play.google.com/store/apps/details?id=com.csh8130.admin.studyproject1";

            Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, text);
            Intent chooser = Intent.createChooser(intent, "타이틀");
            startActivity(chooser);
        }

        refreshFragment(group,index);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    protected void onPostResume() {
        super.onPostResume();
        //환경설정 끝낸후 색상 변경 등을 위한 초기화 작업
       DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView nn = (NavigationView)drawer.findViewById(R.id.nav_view);
        Menu mm = nn.getMenu();
        int size = mm.size();
        for(int i=0;i<size;i++)
        {
            MenuItem aa = mm.getItem(i);
            SubMenu s = aa.getSubMenu();
            int subSize = s.size();
            for(int j=0;j<subSize;j++)
            {
                if( s.getItem(j).isChecked())
                {
                    refreshFragment(i,j);
                }
            }
        }
            //쿨엔조이 게시판 숨기기 코드
            SharedPreferences setRefer = PreferenceManager.getDefaultSharedPreferences(this);
            Set<String> defArraySet = new HashSet<String>();
            defArraySet.add("0");
            defArraySet.add("1");
            defArraySet.add("2");
            defArraySet.add("3");
            defArraySet.add("4");
            defArraySet.add("5");
            Set<String> arraySet = setRefer.getStringSet("multi_select_list_preference_coolN",defArraySet);
            Object[] array = arraySet.toArray();


            DrawerLayout drawer2 = (DrawerLayout) findViewById(R.id.drawer_layout);
            NavigationView n = (NavigationView)drawer2.findViewById(R.id.nav_view);
            Menu m = n.getMenu();
            MenuItem aa = m.getItem(1);
            SubMenu s = aa.getSubMenu();
            int subSize = s.size();
            for(int j=0;j<subSize;j++)
            {
                s.getItem(j).setVisible(false);
            }
            for(int j=0;j<array.length;j++)
            {
                s.getItem(Integer.parseInt((String)array[j])).setVisible(true);
            }

            //메인게시판 메뉴 설정에따른 visible 조절 코드 onCreate에도 똑같은 코드 넣어둬야 ... 앱 실행시
            Set<String> arraySet2 = setRefer.getStringSet("multi_select_list_preference_boards",defArraySet);
            Object[] array2 = arraySet2.toArray();

            MenuItem boardMenu = m.getItem(0);
            SubMenu board_sub = boardMenu.getSubMenu();
            int board_subSize = board_sub.size();
            for(int j=0;j<board_subSize;j++)
            {
                board_sub.getItem(j).setVisible(false); // 메인 게시판 일단 전부 가리기
            }
            for(int j=0;j<array2.length;j++)
            {
                board_sub.getItem(Integer.parseInt((String)array2[j])).setVisible(true);
            }

    }

    void refreshFragment(int group,int index)
    {
        FragmentFirst fragmentFirst;
        FragmentSecond fragmentSecond;
        FragmentThird fragmentThird;
        FragmentForth fragmentForth;
        FragmentFifth fragmentFifth;
        FragmentSixth fragmentSixth;

        if(group == 0)
        {
            if(index == 0)
            {
                fragmentFirst = new FragmentFirst();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentFirst).commit();
            }
            if(index == 1)
            {
                fragmentSecond = new FragmentSecond();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentSecond).commit();
            }
            if(index == 2)
            {
                fragmentThird = new FragmentThird();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentThird).commit();
            }
            if(index == 3)
            {
                fragmentForth = new FragmentForth();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentForth).commit();
            }
            if(index == 4)
            {
                fragmentFifth = new FragmentFifth();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentFifth).commit();
            }
            if(index == 5)
            {
                fragmentSixth = new FragmentSixth();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentSixth).commit();
            }
        }

        if(group == 1)
        {
            if(index == 0)
            {
                fragmentFirst = new FragmentFirst();
                fragmentFirst.setUrl("http://www.coolenjoy.net/bbs/38");
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentFirst).commit();
            }
            if(index == 1)
            {
                fragmentFirst = new FragmentFirst();
                fragmentFirst.setUrl("http://www.coolenjoy.net/bbs/37");
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentFirst).commit();
            }
            if(index == 2)
            {
                fragmentFirst = new FragmentFirst();
                fragmentFirst.setUrl("http://www.coolenjoy.net/bbs/pds");
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentFirst).commit();
            }
            if(index == 3)
            {
                fragmentFirst = new FragmentFirst();
                fragmentFirst.setUrl("http://www.coolenjoy.net/bbs/cooling");
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentFirst).commit();
            }
            if(index == 4)
            {
                fragmentFirst = new FragmentFirst();
                fragmentFirst.setUrl("http://www.coolenjoy.net/bbs/water_cooling");
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentFirst).commit();
            }
            if(index == 5)
            {
                fragmentFirst = new FragmentFirst();
                fragmentFirst.setUrl("http://www.coolenjoy.net/bbs/hardcore_cooling");
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentFirst).commit();
            }
            if(index == 6)
            {
                fragmentFirst = new FragmentFirst();
                fragmentFirst.setUrl("http://www.coolenjoy.net/bbs/case_tuning");
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentFirst).commit();
            }
            if(index == 7)
            {
                fragmentFirst = new FragmentFirst();
                fragmentFirst.setUrl("http://www.coolenjoy.net/bbs/overclock");
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentFirst).commit();
            }
            if(index == 8)
            {
                fragmentFirst = new FragmentFirst();
                fragmentFirst.setUrl("http://www.coolenjoy.net/bbs/27");
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentFirst).commit();
            }
            if(index == 9)
            {
                fragmentFirst = new FragmentFirst();
                fragmentFirst.setUrl("http://www.coolenjoy.net/bbs/28");
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentFirst).commit();
            }
            if(index == 10)
            {
                fragmentFirst = new FragmentFirst();
                fragmentFirst.setUrl("http://www.coolenjoy.net/bbs/hdd");
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentFirst).commit();
            }
            if(index == 11)
            {
                fragmentFirst = new FragmentFirst();
                fragmentFirst.setUrl("http://www.coolenjoy.net/bbs/35");
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentFirst).commit();
            }
            if(index == 12)
            {
                fragmentFirst = new FragmentFirst();
                fragmentFirst.setUrl("http://www.coolenjoy.net/bbs/31");
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentFirst).commit();
            }
            if(index == 13)
            {
                fragmentFirst = new FragmentFirst();
                fragmentFirst.setUrl("http://www.coolenjoy.net/bbs/40");
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentFirst).commit();
            }
            if(index == 14)
            {
                fragmentFirst = new FragmentFirst();
                fragmentFirst.setUrl("http://www.coolenjoy.net/bbs/34");
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentFirst).commit();
            }
            if(index == 15)
            {
                fragmentFirst = new FragmentFirst();
                fragmentFirst.setUrl("http://www.coolenjoy.net/bbs/tip");
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentFirst).commit();
            }
            if(index == 16)
            {
                fragmentFirst = new FragmentFirst();
                fragmentFirst.setUrl("http://www.coolenjoy.net/bbs/30");
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentFirst).commit();
            }
            if(index == 17)
            {
                fragmentFirst = new FragmentFirst();
                fragmentFirst.setUrl("http://www.coolenjoy.net/bbs/45");
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentFirst).commit();
            }
            if(index == 18)
            {
                fragmentFirst = new FragmentFirst();
                fragmentFirst.setUrl("http://www.coolenjoy.net/bbs/diy");
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentFirst).commit();
            }
            if(index == 19)
            {
                fragmentFirst = new FragmentFirst();
                fragmentFirst.setUrl("http://www.coolenjoy.net/bbs/42");
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentFirst).commit();
            }
            if(index == 20)
            {
                fragmentFirst = new FragmentFirst();
                fragmentFirst.setUrl("http://www.coolenjoy.net/bbs/25");
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentFirst).commit();
            }
            if(index == 21)
            {
                fragmentFirst = new FragmentFirst();
                fragmentFirst.setUrl("http://www.coolenjoy.net/bbs/qa");
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentFirst).commit();
            }
            if(index == 22)
            {
                fragmentFirst = new FragmentFirst();
                fragmentFirst.setUrl("http://www.coolenjoy.net/bbs/32");
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentFirst).commit();
            }
            if(index == 23)
            {
                fragmentFirst = new FragmentFirst();
                fragmentFirst.setUrl("http://www.coolenjoy.net/bbs/33");
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentFirst).commit();
            }
            if(index == 24)
            {
                fragmentFirst = new FragmentFirst();
                fragmentFirst.setUrl("http://www.coolenjoy.net/bbs/group");
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentFirst).commit();
            }
        }

    }
}
