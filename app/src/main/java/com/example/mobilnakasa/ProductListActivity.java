package com.example.mobilnakasa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ProductAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //przycisk "Home"
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //zapytanie o listę produktów do serwera
        Intent intencja = new Intent(getApplicationContext(), Server.class);
        PendingIntent pendingResult = createPendingResult(1, new Intent(),0);
        intencja.putExtra(Server.ACTION,Server.ACTION_PRODUCT_GET_ALL);
        intencja.putExtra(Server.RETURN, pendingResult);
        startService(intencja);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ArrayList<ProductItem> productList = new ArrayList<>();
        try {
            JSONObject obj = new JSONObject(data.getStringExtra(Server.RESPONSE));
            JSONArray productArray = obj.getJSONArray("products");
            for(int i=0; i<productArray.length(); i++){
                JSONObject productDetail = productArray.getJSONObject(i);
                productList.add(new ProductItem(productDetail.getString("_id"), productDetail.getString("nazwa"),productDetail.getDouble("netto"), null, null, productDetail.getString("jm")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        buildRecyclerView(productList);
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void buildRecyclerView(final ArrayList<ProductItem> productList){

        //wypełnianie listy produktów
        mRecyclerView = findViewById(R.id.productListRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ProductAdapter(productList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position){
            }

            @Override
            public void onDeleteClick(int position) {
                //TODO usuwanie produktów
            }
            @Override
            public void onEditClick(int position) {
                //TODO edycja produktów

            }
        });
    }
}
