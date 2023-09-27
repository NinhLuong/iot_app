package com.example.iot_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.adapter.FragmentStateAdapter;


import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.iot_app.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
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