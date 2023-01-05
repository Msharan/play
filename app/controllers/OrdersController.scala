package controllers

import models.Order
import play.api.mvc._
import services.OrderService
import utils.JsonUtils

import javax.inject.Inject

class OrdersController @Inject()(orderService: OrderService) extends InjectedController {

  def create = Action(parse.json) { implicit request =>
    val o = JsonUtils.parse[Order](request.body)
    orderService.create(o)
    Ok("order is created")
  }

  def cancel(userId: String, orderId: String) = Action {
    orderService.cancel(userId, orderId)
    Ok("Order is cancelled")
  }

  def get(userId: String) = Action {
    val o = orderService.get(userId)
    Ok(JsonUtils.render(o))
  }

}
