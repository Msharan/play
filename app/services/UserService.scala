package services

import dao.UserDAO
import models.User

import javax.inject.Inject
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UserService @Inject()(users: UserDAO) {

  def create(user: User) = {
    users.create(user)
  }

  def authenticate(username: String, password: String) = {
    users.authenticate(username, password)
  }

  def get(userId: String) = {
    users.get(userId)
  }

}


