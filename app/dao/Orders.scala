package dao

import models.Order

import java.sql.ResultSet
import javax.inject.Inject

class OrderDAO @Inject()() {

  def create(order: Order) = {
    val conn = Datasources.getConnection
    val statement = conn.prepareStatement("INSERT INTO orders (userid, orderid, status) VALUES(?, ?, ?)")
    try {
      statement.setString(1, order.userId)
      statement.setString(2, order.orderId)
      statement.setString(3, order.status)
      statement.executeUpdate()
    } finally {
      statement.close()
      conn.close()
    }

  }

  def cancel(userId: String, orderId: String) = {
    val allOrders = getAll(userId)
    val requiredOrder = allOrders.find(_.orderId == orderId)
    requiredOrder match {
      case Some(u) =>
        val conn = Datasources.getConnection
        val statement = conn.prepareStatement("UPDATE orders SET status = 'CANCELLED' WHERE userid = ? AND orderid = ?")
        try {
          statement.setString(1, userId)
          statement.setString(2, orderId)
          statement.executeUpdate()
        } finally {
          statement.close()
          conn.close()
        }
      case None =>
        throw new RuntimeException("Order not present")
    }


  }

  def getAll(userId: String) = {
    val conn = Datasources.getConnection
    val statement = conn.prepareStatement("SELECT userid, orderid, status FROM orders WHERE userId = ?")
    var rs: ResultSet = null
    var orders: List[Order] = List.empty
    try {
      statement.setString(1, userId)
      rs = statement.executeQuery()
      while (rs.next()) {
        orders = orders :+ (Order(rs.getString("userid"), rs.getString("orderid"), rs.getString("status")))
      }
      orders
    } finally {
      rs.close()
      statement.close()
      conn.close()
    }
  }

}
