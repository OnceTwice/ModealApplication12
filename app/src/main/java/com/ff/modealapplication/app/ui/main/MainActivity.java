package com.ff.modealapplication.app.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ff.modealapplication.R;
import com.ff.modealapplication.app.ui.join.JoinActivity;
import com.ff.modealapplication.app.ui.login.LoginActivity;
import com.ff.modealapplication.app.ui.mypage.MypageFragment;
import com.ff.modealapplication.app.ui.search.SearchActivity;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private Fragment myPageFragment;
    private Fragment mainFragment;
//    private Fragment joinFragment;

    private DrawerLayout drawer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        startActivity(new Intent(this, SplashActivity.class)); // 스플래시 화면 (170131/상욱추가)

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myPageFragment = new MypageFragment();
        mainFragment = new MainFragment();

        // 프래그먼트
        // 프래그먼트의 commit은 여러번 하면 에러가 뜨므로... commit이 필요할때마다 프래그먼트트랜잭션을 만들어서 사용한다
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.activity_content, mainFragment).commit();

        // 툴바 추가 (170123/상욱추가)
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false); // 타이틀에 있는 앱이름 숨기기

        // 네비게이션 추가(170123/상욱추가)
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle); // setDrawerListener가 addDrawerListener로 바뀜 (170131/준현수정)
        toggle.syncState();

        // 로그인 클릭시
        ((NavigationView) findViewById(R.id.nav_view)).getHeaderView(0).findViewById(R.id.login_button).setOnClickListener(this);

        // 회원가입 클릭시
        ((NavigationView) findViewById(R.id.nav_view)).getHeaderView(0).findViewById(R.id.register_button).setOnClickListener(this);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    // 단순히 액션바부터 네비게이션 돋보기 추가하기 위해서는 여기서 부터~~~
    // 뒤로가기 버튼 누를때... (170123/상욱추가)
    // 네비게이션 드로어가 켜져 있을때 뒤로가기 누르면 드로어가 꺼지고 드로어가 켜져 있지 않을때 뒤로가기 누르면 진짜로 뒤로 감 (170124/상욱추가)
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View view) {
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (view.getId()) {
            case R.id.login_button: {
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
//                drawer.closeDrawer(GravityCompat.START);
                break;
            }
            case R.id.register_button: {
                Intent intent1 = new Intent(this, JoinActivity.class);
                startActivity(intent1);
//                transaction.remove(mainFragment);
//                joinFragment = new JoinFragment();
//                transaction.add(R.id.activity_content, new JoinFragment());
                break;
            }
        }

//        transaction.addToBackStack(null);
//        transaction.commit();

        drawer.closeDrawer(GravityCompat.START);
    }

    // 액션바 맨 오른쪽 돋보기 추가 (170123/상욱추가)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // 돋보기 눌렀을때 효과... (170123/상욱추가)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // 이곳에 돋보기 눌렀을때 검색액티비티로 이동할 코드 구현하시오!!!
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    // 네비게이션 메뉴 선택시 일어날 작업을 입력하시오 (170123/상욱추가)
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_myPage) {
            transaction.replace(R.id.activity_content, myPageFragment);
        } else if (id == R.id.nav_bookmark) {

        } else if (id == R.id.nav_setup) {

        } else if (id == R.id.nav_help) {

        } else if (id == R.id.nav_manage) {

        }

        transaction.addToBackStack(null);
        transaction.commit();

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}
