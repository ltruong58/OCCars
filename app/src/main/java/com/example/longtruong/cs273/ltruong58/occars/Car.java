package com.example.longtruong.cs273.ltruong58.occars;

/**
 * Created by ltruong58 on 9/15/2016.
 */
public class Car {
    private double mPrice;
    private double mDownPayment;
    private int mLoanTerm;
    private static final  double TAX_RATE = 0.08;
    private static final  double THREE_YEARS_INTEREST_RATE = 0.0462;
    private static final  double FOUR_YEARS_INTEREST_RATE = 0.0416;
    private static final  double FIVE_YEARS_INTEREST_RATE = 0.0419;

    public Car() {
        mPrice = 0.0;
        mDownPayment = 0.0;
        mLoanTerm = 3;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double mPrice) {
        this.mPrice = mPrice;
    }

    public double getDownPayment() {
        return mDownPayment;
    }

    public void setDownPayment(double mDownPayment) {
        this.mDownPayment = mDownPayment;
    }

    public String getLoanTerm() {
        return " " + String.valueOf(mLoanTerm) + " years.";
    }

    public void setLoanTerm(int mLoanTerm) {
        this.mLoanTerm = mLoanTerm;
    }

    public double calculateBorrowedAmount()
    {
        return mPrice - mDownPayment + calculateTaxAmount();
    }
    public double calculateInterestAmount()
    {
        double borrowedAmount;
        borrowedAmount = calculateBorrowedAmount();
        if(mLoanTerm == 3)
            return borrowedAmount * THREE_YEARS_INTEREST_RATE;
        else if(mLoanTerm == 4)
            return borrowedAmount * FOUR_YEARS_INTEREST_RATE;
        else
            return borrowedAmount * FIVE_YEARS_INTEREST_RATE;
    }
    public double calculateMonthlyPayment()
    {
        return (calculateBorrowedAmount() + calculateInterestAmount()) / (12 * mLoanTerm);
    }
    public double calculateTaxAmount()
    {
        return mPrice * TAX_RATE;
    }

    public double calculateTotalAmount()
    {
        return mPrice + calculateTaxAmount();
    }

}
