package com.example.longtruong.cs273.ltruong58.occars;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class PurchaseActivity extends AppCompatActivity {

    private EditText carPriceEditText;
    private EditText downPaymentEditText;
    private RadioButton threeYearsRadioButton;
    private RadioButton fourYearsRadioButton;
    private RadioButton fiveYearsRadioButton;

    private Car currentCar;

    private String monthlyPaymentText, loanSummaryText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        carPriceEditText = (EditText) findViewById(R.id.priceEditText);
        downPaymentEditText = (EditText) findViewById(R.id.paymentEditText);
        threeYearsRadioButton = (RadioButton) findViewById(R.id.threeYearsRadioButton);
        threeYearsRadioButton = (RadioButton) findViewById(R.id.threeYearsRadioButton);
        threeYearsRadioButton = (RadioButton) findViewById(R.id.threeYearsRadioButton);

        currentCar = new Car();
    }

    // When associating the button with an event, set the onClock property
    // Define the method as public void (with one parameter View view)
    public void activateLoanReport(View view)
    {
        double price, downPayment;

        try {
            price = Double.parseDouble(carPriceEditText.getText().toString());
            downPayment = Double.parseDouble(downPaymentEditText.getText().toString());
        }
        catch(NumberFormatException e) {
            price = 0.0;
            downPayment = 0.0;
        }

        int loanTerm;

        if(fiveYearsRadioButton.isChecked())
            loanTerm = 5;
        else if (fourYearsRadioButton.isChecked())
            loanTerm = 4;
        else
            loanTerm = 3;

        currentCar.setPrice(price);
        currentCar.setDownPayment(downPayment);
        currentCar.setLoanTerm(loanTerm);

        // Create the Intent to share data with LoanSummaryActivity
        Intent loanSummaryIntent = new Intent(this, LoanSummaryActivity.class);
        loanSummaryIntent.putExtra("MonthlyPayment", monthlyPaymentText);
        loanSummaryIntent.putExtra("LoanSummary", loanSummaryText);

        // Start the new activity with the intent data
        startActivity(loanSummaryIntent);
    }

    private void constructLoanSummaryText(){
        monthlyPaymentText = getString(R.string.report_line1) + currentCar.monthlyPayment();
        loanSummaryText = getString(R.string.report_line2) + currentCar.getPrice();
    }
}
