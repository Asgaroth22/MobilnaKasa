package com.example.mobilnakasa;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class CashRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_register);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        //przycisk "Home"
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void click(View view){
        int id = view.getId();
        if(id==R.id.button_num0){
            Toast.makeText(this, "przycisk", Toast.LENGTH_SHORT).show();
        }
        else if(id==R.id.button_num1){
            //jeden
        }
        else if(id==R.id.button_num2){
            //dwa
        }
        else if(id==R.id.button_num3){
            //trzy
        }
        else if(id==R.id.button_num4){
            //cztery
        }
        else if(id==R.id.button_num5){
            //pięć
        }
        else if(id==R.id.button_num6){
            //sześć
        }
        else if(id==R.id.button_num7){
            //siedem
        }
        else if(id==R.id.button_num8){
            //osiem
        }
        else if(id==R.id.button_num9){
            //dziewięć
        }
        else if(id==R.id.button_numcomma){
            //przecinek
        }
        else if(id==R.id.button_numreturn){
            //cofnij
        }
        else if(id==R.id.button_numexit){
            //wyjscie
            Toast.makeText(this, "przycisk", Toast.LENGTH_SHORT).show();
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
        else if(id==R.id.button_numcode){
            //kod
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
}
