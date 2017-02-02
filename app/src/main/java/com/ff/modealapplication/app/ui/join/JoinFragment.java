package com.ff.modealapplication.app.ui.join;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.ff.modealapplication.R;

public class JoinFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public JoinFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment JoinFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static JoinFragment newInstance(String param1, String param2) {
        JoinFragment fragment = new JoinFragment();
        Bundle args = new Bundle();

        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private ViewPager pager;
    private PagerSlidingTabStrip tabs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_join, container, false);

        Log.d("쪼인프레그먼트", "입니다!!");

        // View Pager 선언
        pager = (ViewPager) view.findViewById(R.id.pager);
        pager.setAdapter(new PagerAdapter(getActivity().getSupportFragmentManager()));
        pager.setOffscreenPageLimit(2);

        // 처음으로 0번째 Fragment를 보여줌
        pager.setCurrentItem(0);

        tabs = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
        tabs.setViewPager(pager);

        // Title 설정
        getActivity().setTitle("회원가입");

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onDestroyView() {       // View 리소스를 해제할 수 있도록 호출, backstack를 사용했다면 Fragment를 다시 돌아갈 때 onCreateView()가 호출됨
        getActivity().finish();
        Log.d("J===LifeCycle", "onDestroyView called!!!!!");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {           // fragment 상태를 완전히 종료할 수 있도록 호출
        Log.d("J===LifeCycle", "onDestroy called!!!!!");
        super.onDestroy();
    }

    @Override
    public void onDetach() {            // Fragment가 Activity와 연결이 완전히 끊기기 직전에 호출
        Log.d("J===LifeCycle", "onDetach called!!!!!");
        super.onDetach();
    }

    // 페이지마다 보여줄 타이틀 지정
    private String[] pageTitle = {"사용자", "사업자"};

    private class PagerAdapter extends FragmentPagerAdapter {
        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return pageTitle[position];
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0 : {
                    Log.d("getItem0", "0000000");
                    return new UserJoinFragment();
                }
                case 1 : {
                    Log.d("getItem1", "1111111111");
                    return new OwnerJoinFragment();
                }
                default: {
                    Log.d("default", "aasdfasdf");
                    return null;
                }
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
