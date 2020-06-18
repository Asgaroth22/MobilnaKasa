package com.example.mobilnakasa;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    public void click(View view){

        //obsługa przycisków
        int id = view.getId();
        if(id == R.id.buttonHomeKasa){
            Toast.makeText(this, "przycisk1", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.buttonHomeScan){
            Toast.makeText(this, "przycisk2", Toast.LENGTH_SHORT).show();

        }
        else if (id == R.id.buttonHomeRaports){
            Toast.makeText(this, "przycisk3", Toast.LENGTH_SHORT).show();

        }
        else if (id == R.id.buttonHomeEmployees){
            Toast.makeText(this, "przycisk4", Toast.LENGTH_SHORT).show();

        }
        else if (id == R.id.buttonHomeProducts){
            Toast.makeText(this, "przycisk5", Toast.LENGTH_SHORT).show();

        }
        else if (id == R.id.buttonHomeReceipts){
            Toast.makeText(this, "przycisk6", Toast.LENGTH_SHORT).show();

        }
    }

    public void logout(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.action_logout);
        builder.setMessage(R.string.prompt_confirm);
        builder.setPositiveButton(R.string.prompt_yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                //logout
                Intent intencja = new Intent(getApplicationContext(), Server.class);
                PendingIntent pendingResult = createPendingResult(1, new Intent(),0);
                intencja.putExtra(Server.ACTION,Server.ACTION_USER_LOGOUT);
                intencja.putExtra(Server.RETURN, pendingResult);
                startService(intencja);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Sprawdzenie czy wylogowanie się udało
        if(resultCode == Server.RESPONSE_SUCCESS){
            MobilnaKasaApplication app = (MobilnaKasaApplication) getApplicationContext();
            if(app.getLoggedOperator()!=null) {
            Toast.makeText(this, "Wylogowano " + app.getLoggedOperator().getName(), Toast.LENGTH_SHORT).show();
            }
            app.setLoggedOperator(null);
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.setFlags(FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Błąd serwera",Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
