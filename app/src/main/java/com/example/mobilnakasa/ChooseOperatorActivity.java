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

public class ChooseOperatorActivity extends AppCompatActivity {

    private RecyclerView opRecyclerView;
    private OperatorAdapter opAdapter;
    private RecyclerView.LayoutManager opLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_operator);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //przycisk "Home"
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_home_black_18dp);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //wypełnianie listy operatorów
        // TODO pobranie listy z serwera

        Intent intencja = new Intent(getApplicationContext(), Server.class);
        PendingIntent pendingResult = createPendingResult(1, new Intent(),0);
        intencja.putExtra(Server.ACTION,Server.ACTION_USER_LIST);
        intencja.putExtra(Server.RETURN, pendingResult);
        startService(intencja);
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Toast.makeText(this, String.valueOf(resultCode),Toast.LENGTH_SHORT).show();
        Toast.makeText(this, String.valueOf(requestCode),Toast.LENGTH_SHORT).show();
        Toast.makeText(this, String.valueOf(data.getStringExtra(Server.RESPONSE)), Toast.LENGTH_SHORT).show();


        ArrayList<OperatorItem> operatorList = new ArrayList<>();
        try {
            JSONArray operatorArray = new JSONArray(data.getStringExtra(Server.RESPONSE));
            for(int i=0; i<operatorArray.length(); i++){
                JSONObject operatorDetail = operatorArray.getJSONObject(i);
                operatorList.add(new OperatorItem(R.drawable.ic_android_black_24dp,operatorDetail.getString("imie")+" "+operatorDetail.getString("nazwisko"),operatorDetail.getString("stanowisko")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        buildRecyclerView(operatorList);
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void buildRecyclerView(final ArrayList<OperatorItem> operatorList){
        opRecyclerView = findViewById(R.id.operatorRecyclerView);
        opRecyclerView.setHasFixedSize(true);
        opLayoutManager = new LinearLayoutManager(this);
        opAdapter = new OperatorAdapter(operatorList);
        opRecyclerView.setLayoutManager(opLayoutManager);
        opRecyclerView.setAdapter(opAdapter);
        opAdapter.setOnItemClickListener(new OperatorAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position){
                operatorList.get(position).test("test");
                opAdapter.notifyItemChanged(position);
            }
        });
    }

}
