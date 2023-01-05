package controllers

import dao.UserDAO
import models.User
import play.api.http.ContentTypes
import play.api.mvc._
import services.UserService
import utils.{JsonUtils, UUIDUtils}

import javax.inject.Inject

class UsersController @Inject()(users: UserService, uuidUtils: UUIDUtils) extends InjectedController {

  def create = Action(parse.json) { implicit request =>
    val u = JsonUtils.parse[User](request.body)
    users.create(u)
    Ok("user is created successfully")
  }

  def get(userId: String) = Action {
    val u = users.get(userId)
    u match {
      case Some(u) =>
        Ok(JsonUtils.render(u)).as(ContentTypes.JSON)
      case None => NotFound
    }
  }

  def authenticate = Action(parse.json) { implicit request =>
    val username = (request.body \ "username").asOpt[String]
    val password = (request.body \ "password").asOpt[String]
    if (username.isDefined && password.isDefined) {
      if (users.authenticate(username.get, password.get)) {
        Ok("Authenticated")
      }
      else {
        Unauthorized("Unauthenticated")
      }
    } else {
      Unauthorized("Unauthenticated")
    }
  }
}
