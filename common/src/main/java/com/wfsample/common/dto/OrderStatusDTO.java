package com.wfsample.common.dto;

/**
 * DTO for a beachshirts order status.
 *
 * @author Srujan Narkedamalli (snarkedamall@wavefront.com).
 */
public class OrderStatusDTO {
  String orderId;
  String status;
  double amount;

  public OrderStatusDTO() {
  }

  public OrderStatusDTO(String orderId, String status, double amount) {
    this.orderId = orderId;
    this.status = status;
    this.amount = amount;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String statusMessage) {
    this.status = statusMessage;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }
}
