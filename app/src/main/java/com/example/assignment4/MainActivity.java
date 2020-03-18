package com.example.assignment4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    public static final String SHARED_PREF = "SHARED_PREFRENCES";
    public static final String HISTORY = "history";
    public static final String BALANCE = "balance";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button add = findViewById(R.id.addTransaction);
        Button sub = findViewById(R.id.subtractTransaction);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAddTransaction();
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSubTransaction();
            }
        });
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        float balance = sharedPreferences.getFloat(BALANCE, 0);
        String history = sharedPreferences.getString(HISTORY, "");
        EditText balanceET = findViewById(R.id.currentBalanceAmt);
        balanceET.setText(Double.toString(balance));
        EditText ll = findViewById(R.id.transactionLayout);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        EditText historyET = new EditText(this);
        historyET.setLayoutParams(p);
        historyET.setText(history);
        ll.setText(historyET.getText().toString());

    }

    public void saveAddTransaction(){
        EditText amountET = findViewById(R.id.transactionAmount);
        EditText dateET = findViewById(R.id.dateOfTransaction);
        EditText balanceET = findViewById(R.id.currentBalanceAmt);
        EditText reasonET = findViewById(R.id.reasonForTransaction);
        Double amount = Double.parseDouble(amountET.getText().toString());
        Double balance = Double.parseDouble(balanceET.getText().toString());
        String date = dateET.getText().toString();
        String reason = reasonET.getText().toString();
        Transaction transaction = new Transaction(amount, date, reason, true);
        EditText ll = findViewById(R.id.transactionLayout);
        EditText newTransaction = new EditText(this);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        newTransaction.setLayoutParams(p);
        newTransaction.setText(transaction.toString());
        ll.setText(ll.getText() + "\n" + newTransaction.getText());
        balance += transaction.amount;
        balanceET.setText(Double.toString(balance));
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(HISTORY, ll.getText().toString());
        editor.putFloat(BALANCE, balance.floatValue());
        editor.apply();
    }

    public void saveSubTransaction(){
        EditText amountET = findViewById(R.id.transactionAmount);
        EditText dateET = findViewById(R.id.dateOfTransaction);
        EditText balanceET = findViewById(R.id.currentBalanceAmt);
        EditText reasonET = findViewById(R.id.reasonForTransaction);
        Double amount = Double.parseDouble(amountET.getText().toString());
        Double balance = Double.parseDouble(balanceET.getText().toString());
        String date = dateET.getText().toString();
        String reason = reasonET.getText().toString();
        Transaction transaction = new Transaction(amount, date, reason, false);
        EditText ll = findViewById(R.id.transactionLayout);
        EditText newTransaction = new EditText(this);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        newTransaction.setLayoutParams(p);
        newTransaction.setText(transaction.toString());
        ll.setText(ll.getText() + "\n" + newTransaction.getText());
        balance -= transaction.amount;
        balanceET.setText(Double.toString(balance));
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(HISTORY, ll.getText().toString());
        editor.putFloat(BALANCE, balance.floatValue());
        editor.apply();
    }
}
