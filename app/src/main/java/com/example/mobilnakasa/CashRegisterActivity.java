package com.example.mobilnakasa;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CashRegisterActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ReceiptProductListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<ProductItem> productList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_register);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        //przycisk "Home"
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final cashRegisterFragment firstFragment = new cashRegisterFragment();
        final cashRegisterFragment2 secondFragment = new cashRegisterFragment2();
        getSupportFragmentManager().beginTransaction().replace(R.id.cashRegisterFragmentContainerView, firstFragment, "firstFragment").commit();
        Button priceBtn = findViewById(R.id.button_numprice);
        Button codeBtn = findViewById(R.id.button_numcode);
        productList = new ArrayList<>();

        priceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.cashRegisterFragmentContainerView, firstFragment, "firstFragment").commit();
            }
        });
        codeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.cashRegisterFragmentContainerView, secondFragment, "secondFragment").commit();
            }
        });


    }
    public void click(View view){
        int id = view.getId();
        if(id==R.id.button_num0){
            //zero
            Fragment test = getSupportFragmentManager().findFragmentByTag("secondFragment");
            if (test != null && test.isAdded() && test.isVisible()) {
                TextView text = findViewById(R.id.cashRegisterEditCode);
                text.append("0");
            }
        }
        else if(id==R.id.button_num1){
            //jeden
            Fragment test = getSupportFragmentManager().findFragmentByTag("secondFragment");
            if (test != null && test.isAdded() && test.isVisible()) {
                TextView text = findViewById(R.id.cashRegisterEditCode);
                text.append("1");
            }
        }
        else if(id==R.id.button_num2){
            //dwa
            Fragment test = getSupportFragmentManager().findFragmentByTag("secondFragment");
            if (test != null && test.isAdded() && test.isVisible()) {
                TextView text = findViewById(R.id.cashRegisterEditCode);
                text.append("2");
            }
        }
        else if(id==R.id.button_num3){
            //trzy
            Fragment test = getSupportFragmentManager().findFragmentByTag("secondFragment");
            if (test != null && test.isAdded() && test.isVisible()) {
                TextView text = findViewById(R.id.cashRegisterEditCode);
                text.append("3");
            }
        }
        else if(id==R.id.button_num4){
            //cztery
            Fragment test = getSupportFragmentManager().findFragmentByTag("secondFragment");
            if (test != null && test.isAdded() && test.isVisible()) {
                TextView text = findViewById(R.id.cashRegisterEditCode);
                text.append("4");
            }
        }
        else if(id==R.id.button_num5){
            //pięć
            Fragment test = getSupportFragmentManager().findFragmentByTag("secondFragment");
            if (test != null && test.isAdded() && test.isVisible()) {
                TextView text = findViewById(R.id.cashRegisterEditCode);
                text.append("5");
            }
        }
        else if(id==R.id.button_num6){
            //sześć
            Fragment test = getSupportFragmentManager().findFragmentByTag("secondFragment");
            if (test != null && test.isAdded() && test.isVisible()) {
                TextView text = findViewById(R.id.cashRegisterEditCode);
                text.append("6");
            }
        }
        else if(id==R.id.button_num7){
            //siedem
            Fragment test = getSupportFragmentManager().findFragmentByTag("secondFragment");
            if (test != null && test.isAdded() && test.isVisible()) {
                TextView text = findViewById(R.id.cashRegisterEditCode);
                text.append("7");
            }
        }
        else if(id==R.id.button_num8){
            //osiem
            Fragment test = getSupportFragmentManager().findFragmentByTag("secondFragment");
            if (test != null && test.isAdded() && test.isVisible()) {
                TextView text = findViewById(R.id.cashRegisterEditCode);
                text.append("8");
            }
        }
        else if(id==R.id.button_num9){
            //dziewięć
            Fragment test = getSupportFragmentManager().findFragmentByTag("secondFragment");
            if (test != null && test.isAdded() && test.isVisible()) {
                TextView text = findViewById(R.id.cashRegisterEditCode);
                text.append("9");
            }
        }
        else if(id==R.id.button_numcomma){
            //przecinek
            Fragment test = getSupportFragmentManager().findFragmentByTag("secondFragment");
            if (test != null && test.isAdded() && test.isVisible()) {
                TextView text = findViewById(R.id.cashRegisterEditCode);
                text.append(",");
            }
        }
        else if(id==R.id.button_numreturn){
            //cofnij
            Fragment test = getSupportFragmentManager().findFragmentByTag("secondFragment");
            if (test != null && test.isAdded() && test.isVisible()) {
                TextView text = findViewById(R.id.cashRegisterEditCode);
                if(text.getText().length()!=0)
                text.setText(text.getText().subSequence(0, text.getText().length()-1));
            }
        }
        else if(id==R.id.button_numexit){
            //wyjscie
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.action_transaction_exit);
            builder.setMessage(R.string.prompt_confirm);
            builder.setPositiveButton(R.string.prompt_yes, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    //wyjscie
                    finish();
                }
            });
            builder.setNegativeButton(R.string.prompt_no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
        else if(id==R.id.button_numx){
            //ilosc
        }
        else if(id==R.id.button_numscan){
            //skan
        }
        else if(id==R.id.button_numok){
            //ok-zatwierdz
            //wyjscie
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.action_transaction_finish);
            builder.setMessage(R.string.prompt_confirm);
            builder.setPositiveButton(R.string.prompt_yes, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    //wyjscie
                }
            });
            builder.setNegativeButton(R.string.prompt_no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    public void dodajProdukt(String id){
        Intent intencja = new Intent(getApplicationContext(),Server.class);
        PendingIntent pendingResult = createPendingResult(1, new Intent(),0);
        intencja.putExtra(Server.ACTION,Server.ACTION_PRODUCT_GET_ONE);
        intencja.putExtra(Server.PRODUCT_CODE, id);
        intencja.putExtra(Server.RETURN, pendingResult);
        startService(intencja);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == Server.RESPONSE_SUCCESS){
            //Toast.makeText(this, String.valueOf(data.getStringExtra(Server.RESPONSE)), Toast.LENGTH_LONG).show();
            try {
                JSONObject obj = new JSONObject(data.getStringExtra(Server.RESPONSE));
                JSONObject productDetail = obj.getJSONObject("product");
                productList.add(new ProductItem(productDetail.getString("_id"), productDetail.getString("nazwa"),productDetail.getDouble("netto"), null, null, productDetail.getString("jm")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            buildRecyclerView();


        }
        else{
            Toast.makeText(this, "Nie znaleziono produktu", Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    public void buildRecyclerView(){
        //wypełnianie listy produktów
        mRecyclerView = findViewById(R.id.cashRegisterRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ReceiptProductListAdapter(productList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new ReceiptProductListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position){
            }
        });
    }
}
