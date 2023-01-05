package controllers

import play.api.mvc._

import javax.inject.Inject


class AppController @Inject()() extends InjectedController {

  def ping = Action {
    Ok("pong")
  }


}
