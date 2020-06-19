package com.example.mobilnakasa;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class cashRegisterFragment2 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cash_register2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button dodajProdukt = getView().findViewById(R.id.buttonCashRegisterNewProduct);
        dodajProdukt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CashRegisterActivity activity = (CashRegisterActivity) getActivity();
                TextView text = getView().findViewById(R.id.cashRegisterEditCode);
                activity.dodajProdukt(text.getText().toString());
            }
        });
    }
}
