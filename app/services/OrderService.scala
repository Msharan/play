package services

import dao.OrderDAO
import models.Order

import javax.inject.Inject

class OrderService @Inject()(orders: OrderDAO) {

  def create(order: Order) = {
    orders.create(order)
  }

  def cancel(userId: String, orderId: String) = {
    orders.cancel(userId, orderId)
  }

  def get(userId: String) = {
    orders.getAll(userId)
  }

}


