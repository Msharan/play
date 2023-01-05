
import play.api.http.{ContentTypes, HeaderNames, HttpErrorHandler}
import play.api.mvc.Results._
import play.api.mvc._
import utils.{JsonUtils, UUIDUtils}

import javax.inject.{Inject, Singleton}
import scala.concurrent.Future

@Singleton
class ErrorHandler @Inject() (uuidUtils: UUIDUtils) extends HttpErrorHandler {
  /* Client related error */

  override def onClientError(request: RequestHeader, statusCode: Int, message: String): Future[Result] = {
    val errorId = uuidUtils.getRandomUUID()
    Future.successful(InternalServerError(JsonUtils.render(Map(("error" -> Map(
      ("message" -> s"Something went wrong, Please contact Support with failure Id: $errorId"),
      ("causes" -> message)))))).withHeaders((HeaderNames.CONTENT_TYPE, ContentTypes.JSON)))
  }

  override def onServerError(request: RequestHeader, exception: Throwable): Future[Result] = {
    val errorId = uuidUtils.getRandomUUID()
    exception.printStackTrace()
    Future.successful(InternalServerError(JsonUtils.render(Map(("error" -> Map(
      ("message" -> s"Something went wrong, Please contact Support with failure Id: $errorId"),
      ("causes" -> exception.getMessage)))))).withHeaders((HeaderNames.CONTENT_TYPE, ContentTypes.JSON)))
  }

}
