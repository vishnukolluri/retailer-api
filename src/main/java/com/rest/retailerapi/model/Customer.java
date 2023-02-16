package com.rest.retailerapi.model;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;

@EntityScan
public class Customer {
    private int transid;
    private int custid;
    private double purchaseamt;
    private Date transdate;

    public Customer() {
    }

    public Customer(int transid, int custid, double purchaseamt, Date transdate) {
        this.transid = transid;
        this.custid = custid;
        this.purchaseamt = purchaseamt;
        this.transdate = transdate;
    }

    public int getTransid() {
        return transid;
    }

    public void setTransid(int transid) {
        this.transid = transid;
    }

    public int getCustid() {
        return custid;
    }

    public void setCustid(int custid) {
        this.custid = custid;
    }

    public double getPurchaseamt() {
        return purchaseamt;
    }

    public void setPurchaseamt(double purchaseamt) {
        this.purchaseamt = purchaseamt;
    }

    public Date getTransdate() {
        return transdate;
    }

    public void setTransdate(Date transdate) {
        this.transdate = transdate;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "transid=" + transid +
                ", custid=" + custid +
                ", purchaseamt=" + purchaseamt +
                ", transdate=" + transdate +
                '}';
    }

}
