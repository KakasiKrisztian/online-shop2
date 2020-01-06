package org.fasttrackit.onlineshop.transfer;

import javax.validation.constraints.NotNull;

public class AddProductToCartRequest {

    @NotNull
    private Long productId;
    @NotNull
    private Long customerId;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "AddProductToCartRequest{" +
                "productId=" + productId +
                ", customerId=" + customerId +
                '}';
    }
}
