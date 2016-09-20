package com.example.longtruong.cs273.ltruong58.occars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoanSummaryActivity extends Activity {

    private TextView monthlyPaymentTextView;
    private TextView loanSummaryTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_summary);

        //
        monthlyPaymentTextView = (TextView) findViewById(R.id.monthlyPaymentTextView);
        loanSummaryTextView = (TextView) findViewById(R.id.loanReportTextView);

        // Call intent
        Intent intentFromPurchaseActivity = getIntent();

        String monthlyPaymentText = intentFromPurchaseActivity.getStringExtra("MonthlyPayment");
        String loanSummaryText = intentFromPurchaseActivity.getStringExtra("LoanSummary");

        monthlyPaymentTextView.setText(monthlyPaymentText.toString());
        loanSummaryTextView.setText(loanSummaryText.toString());

    }

    public void returnToDataEntry(View view)
    {
        this.finish();
    }
}
