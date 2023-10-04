package com.example.iot_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.iot_app.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences prefs = getSharedPreferences("MyApp", MODE_PRIVATE);
        String json = prefs.getString("rooms", "[]");

        SharedViewModel viewModel = new ViewModelProvider(this).get(SharedViewModel.class);
        viewModel.jsonToRooms(json);
    }

    // ActivityMainBinding cho phép người dùng truy cập và tương tác với các view trong file XML
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ActivityMainBinding.inflate nạp các layout của file XML vào binding để dể gọi đến và sữ dụng
        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        Để có thể thay đổi nhiều fragment khác nhau nên setContentView bằng getRoot()
        setContentView(binding.getRoot());

//        thực hiện thay thế màn hình Home ngay khi khởi động
//        new là tạo một fragment mới giống fragment gốc và mọi thay đổi trên new fragment không ảnh hưởng tới fragment góc
        replaceFragment(new HomeFragment());

        setUpViewPager();
        BottomNavigationView navigationView = findViewById(R.id.bottom_nav);

//      kiểm tra trạng thái mỗi khi nhấn vào item
        binding.bottomNav.setOnItemSelectedListener(item -> {
//lấy id của item đc nhận vào
            int id = item.getItemId();
//       ứng với mỗi id của item nhấn vào sẽ hiển thị fragment tương ứng
            if (id == R.id.home) {
                replaceFragment(new HomeFragment());
            } else if (id == R.id.web) {
                replaceFragment(new WebFragment());
            } else if (id == R.id.account) {
                replaceFragment(new AccountFragment());
            }
            return true;
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences prefs = getSharedPreferences("MyApp", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        SharedViewModel viewModel = new ViewModelProvider(this).get(SharedViewModel.class);
        String json = viewModel.roomsToJson();

        editor.putString("rooms", json);
        editor.apply();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getSupportFragmentManager().popBackStack();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpViewPager() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);

    }

    private void replaceFragment(Fragment fragment){
//       FragmentManager quản lý các Fragment trong trong layout
//        getSupportFragmentManager trả về các tương tác của fragment đã kết hợp với activy hiện tại
        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction thực hiện việc thay đổi fragment
//        fragmentManager.beginTransaction() trình quản lý Fragment cho phép bắt đầu việc thay thế fragment
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        replace(): thực hiện thay thế FrameLayout ban đầu bằng một fragment truyền vào
        fragmentTransaction.replace(R.id.frameLayout, fragment);
//        commit() xác nhận việc thay thế và kế thúc thay thế fragment
        fragmentTransaction.commit();
    }
}