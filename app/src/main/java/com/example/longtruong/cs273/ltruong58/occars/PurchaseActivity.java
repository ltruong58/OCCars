package com.example.longtruong.cs273.ltruong58.occars;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class PurchaseActivity extends AppCompatActivity {

    private EditText carPriceEditText;
    private EditText downPaymentEditText;
    private RadioButton threeYearsRadioButton;
    private RadioButton fourYearsRadioButton;
    private RadioButton fiveYearsRadioButton;

    private Car currentCar;

    private String monthlyPaymentText, loanSummaryText;
    DecimalFormat myNumber = new DecimalFormat("0.00");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        carPriceEditText = (EditText) findViewById(R.id.priceEditText);
        downPaymentEditText = (EditText) findViewById(R.id.paymentEditText);
        threeYearsRadioButton = (RadioButton) findViewById(R.id.threeYearsRadioButton);
        fourYearsRadioButton = (RadioButton) findViewById(R.id.fourYearsRadioButton);
        fiveYearsRadioButton = (RadioButton) findViewById(R.id.fiveYearsRadioButton);

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

        constructLoanSummaryText();

        // Create the Intent to share data with LoanSummaryActivity
        Intent loanSummaryIntent = new Intent(this, LoanSummaryActivity.class);
        loanSummaryIntent.putExtra("MonthlyPayment", monthlyPaymentText);
        loanSummaryIntent.putExtra("LoanSummary", loanSummaryText);

        // Start the new activity with the intent data
        startActivity(loanSummaryIntent);
    }

    private void constructLoanSummaryText(){
        monthlyPaymentText = getString(R.string.report_line1) + myNumber.format(currentCar.calculateMonthlyPayment());
        loanSummaryText = String.format(getString(R.string.report_line2) +  myNumber.format(currentCar.getPrice()))
        + getString(R.string.report_line3) + myNumber.format(currentCar.getDownPayment())
        + getString(R.string.report_line5) + myNumber.format(currentCar.calculateTaxAmount())
        + getString(R.string.report_line6) + myNumber.format(currentCar.calculateTotalAmount())
        + getString(R.string.report_line7) + myNumber.format(currentCar.calculateBorrowedAmount())
        + getString(R.string.report_line8) + myNumber.format(currentCar.calculateInterestAmount())
        + getString(R.string.report_line4) + currentCar.getLoanTerm()
        + getString(R.string.report_line9)
        + getString(R.string.report_line10)
        + getString(R.string.report_line11)
        + getString(R.string.report_line12);
    }
}
