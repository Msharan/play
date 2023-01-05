package dao

import models.User
import org.mindrot.jbcrypt.BCrypt

import java.sql.ResultSet
import javax.inject.Inject

class UserDAO @Inject()() {

  def create(user: User) = {
    get(user.userId) match {
      case Some(u) => throw new RuntimeException("user already exists")
      case None =>
        val conn = Datasources.getConnection
        val statement = conn.prepareStatement("INSERT INTO users (userid, username, password, email, status) VALUES(?, ?, ?, ?, ?)")
        try {
          statement.setString(1, user.userId)
          statement.setString(2, user.username)
          statement.setString(3, BCrypt.hashpw(user.password, BCrypt.gensalt()))
          statement.setString(4, user.email)
          statement.setString(5, user.status)
          statement.executeUpdate()
        } finally {
          statement.close()
          conn.close()
        }
    }
  }

  def authenticate(username: String, password: String) = {
    getByUsername(username) match {
      case Some(u) => BCrypt.checkpw(password, u.password)
      case None => false
    }
  }

  def updateProfile(userId: String) = {
  }

  def updatePassword(userId: String) = {
  }

  def get(userId: String) = {
    val conn = Datasources.getConnection
    val statement = conn.prepareStatement("SELECT userid, username, password, email, status FROM users WHERE userid = ? ")
    var rs: ResultSet = null
    var u: Option[User] = None
    try {
      statement.setString(1, userId)
      rs = statement.executeQuery()
      while (rs.next()) {
        u = Option(User(rs.getString("userid"), rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getString("status")))
      }
     u
    } finally {
      rs.close()
      statement.close()
      conn.close()
    }
  }

  def getByUsername(username: String) = {
    val conn = Datasources.getConnection
    val statement = conn.prepareStatement("SELECT userid, username, password, email, status FROM users WHERE username = ? ")
    var rs: ResultSet = null
    var u: Option[User] = None
    try {
      statement.setString(1, username)
      rs = statement.executeQuery()
      while (rs.next()) {
        u = Option(User(rs.getString("userid"), rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getString("status")))
      }
      u
    } finally {
      rs.close()
      statement.close()
      conn.close()
    }
  }

}
