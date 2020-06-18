package com.example.mobilnakasa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //przycisk "Home"
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //ustawianie imienia
        Intent intent = getIntent();
        TextView name = findViewById(R.id.loginName);
        OperatorItem operator = intent.getParcelableExtra("operator");
        name.setText(operator.getName());
    }

    public void login(View view){
        Intent intencja = new Intent(getApplicationContext(), Server.class);
        PendingIntent pendingResult = createPendingResult(1, new Intent(),0);
        intencja.putExtra(Server.ACTION,Server.ACTION_USER_LOGIN);
        Intent intent = getIntent();
        TextView password = findViewById(R.id.editPassword);
        OperatorItem operator = intent.getParcelableExtra("operator");
        String credentials = "id=" + operator.getId() +"&haslo=" + password.getText();
        //String credentials = "id=5ee6121067995521d80df6f0&haslo=1Testowy!";
        intencja.putExtra(Server.PARAMS, credentials);
        intencja.putExtra(Server.RETURN, pendingResult);
        startService(intencja);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Intent intent = getIntent();
        OperatorItem operator = intent.getParcelableExtra("operator");
        //Sprawdzenie czy logowanie się udało
        if(resultCode == Server.RESPONSE_SUCCESS){
            MobilnaKasaApplication app = (MobilnaKasaApplication) getApplicationContext();
            app.setLoggedOperator(operator);
            Toast.makeText(this, "Zalogowano jako " + app.getLoggedOperator().getName(), Toast.LENGTH_SHORT).show();
        } else {
                Toast.makeText(this, "Niepowodzenie(nieznany operator lub hasło)",Toast.LENGTH_SHORT).show();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
