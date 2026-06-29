package org.dmitri.App.config;

import org.springframework.beans.factory.annotation.Value;

public class AccountProperties {
    @Value("${account.default-amount}")
    private Integer defaultAmount;

    @Value("${account.transfer-commission}")
    private Double transferCommission;

    public Integer getDefaultAmount() {
        return defaultAmount;
    }

    public Double getTransferCommission() {
        return transferCommission;
    }
}
