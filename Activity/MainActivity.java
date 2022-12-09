package com.example.baithick_thipchay_bino_quoc.Activity.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baithick_thipchay_bino_quoc.Activity.Adapter.CatagoryAdapter;
import com.example.baithick_thipchay_bino_quoc.Activity.Adapter.RecommendedAdapter;
import com.example.baithick_thipchay_bino_quoc.Activity.Domain.FoodDomain;
import com.example.baithick_thipchay_bino_quoc.Activity.Domain.RecommendedDomain;
import com.example.baithick_thipchay_bino_quoc.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter,adapter2;
    private RecyclerView recyclerViewCategoryList,recyclerViewPopularList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewCategory();
        recyclerViewPopular();
        bottomNavigation();
    }

    private void bottomNavigation(){
        LinearLayout homeBtn =findViewById(R.id.homeBtn);
        LinearLayout CartBtn=findViewById(R.id.CartBtn);



        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( MainActivity.this,MainActivity.class));
            }
        });

        CartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,CartActivity.class));
            }
        });
    }
    private  void recyclerViewPopular(){
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewPopularList=findViewById(R.id.view2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<FoodDomain> foodlist=new ArrayList<>();
        foodlist.add(new FoodDomain("Pepperoni pizza","pizza1","sloices pepperoni,mozzrella cheese,fresh oregano,ground black pepper,pizza sauce",13.0,5,20,1000));
        foodlist.add(new FoodDomain(" Cheese Burger","burger"," beef,gouda cheese,speccial sauce, Lettuce,tomato",15.20,4,18,1500));
        foodlist.add(new FoodDomain("Vagetable pizza","pizza3"," olive oil,vagetable oil,pitted Kalamata,cherry tomatoes,fresh oregano,basil",11.0,3,16,800));
        foodlist.add(new FoodDomain("Pepperoni pizza","pizza1","sloices pepperoni,mozzrella cheese,fresh oregano,ground black pepper,pizza sauce",13.0,5,20,1000));
        foodlist.add(new FoodDomain(" Cheese Burger","burger"," beef,gouda cheese,speccial sauce, Lettuce,tomato",15.20,4,18,1500));
        foodlist.add(new FoodDomain("Vagetable pizza","pizza3"," olive oil,vagetable oil,pitted Kalamata,cherry tomatoes,fresh oregano,basil",11.0,3,16,800));

        adapter2 =new RecommendedAdapter(foodlist);
        recyclerViewPopularList.setAdapter(adapter2);


    }
    private void recyclerViewCategory(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCategoryList =findViewById(R.id.view1);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<RecommendedDomain> cetegoryList=new ArrayList<>();
        cetegoryList.add(new RecommendedDomain(" Pizza","Cat_1"));
        cetegoryList.add(new RecommendedDomain(" Burger","Cat_2"));
        cetegoryList.add(new RecommendedDomain(" Hotdoc","Cat_3"));
        cetegoryList.add(new RecommendedDomain(" Drink","Cat_4"));
        cetegoryList.add(new RecommendedDomain(" Donut","Cat_5"));

        adapter =new CatagoryAdapter(cetegoryList);
        recyclerViewCategoryList.setAdapter(adapter);

    }



}