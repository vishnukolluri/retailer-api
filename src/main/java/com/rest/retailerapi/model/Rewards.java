package com.rest.retailerapi.model;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
public class Rewards {
    private int custId;
    private int rewardsMonth1;
    private int rewardsMonth2;
    private int rewardsMonth3;
    private int totalRewards;

    public Rewards() {
    }

    public Rewards(int custId, int rewardsMonth1, int rewardsMonth2, int rewardsMonth3, int totalRewards) {
        this.custId = custId;
        this.rewardsMonth1 = rewardsMonth1;
        this.rewardsMonth2 = rewardsMonth2;
        this.rewardsMonth3 = rewardsMonth3;
        this.totalRewards = totalRewards;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public int getRewardsMonth1() {
        return rewardsMonth1;
    }

    public void setRewardsMonth1(int rewardsMonth1) {
        this.rewardsMonth1 = rewardsMonth1;
    }

    public int getRewardsMonth2() {
        return rewardsMonth2;
    }

    public void setRewardsMonth2(int rewardsMonth2) {
        this.rewardsMonth2 = rewardsMonth2;
    }

    public int getRewardsMonth3() {
        return rewardsMonth3;
    }

    public void setRewardsMonth3(int rewardsMonth3) {
        this.rewardsMonth3 = rewardsMonth3;
    }

    public int getTotalRewards() {
        return totalRewards;
    }

    public void setTotalRewards(int totalRewards) {
        this.totalRewards = totalRewards;
    }

    @Override
    public String toString() {
        return "Rewards{" +
                "custId=" + custId + System.lineSeparator() +
        ", rewardsMonth1=" + rewardsMonth1 + System.lineSeparator() +
                ", rewardsMonth2=" + rewardsMonth2 + System.lineSeparator() +
                ", rewardsMonth3=" + rewardsMonth3 +
                ", totalRewards=" + totalRewards +
                '}';
    }
}
