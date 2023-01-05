package utils

import org.json4s.{DefaultFormats, _}
import org.json4s.jackson.Serialization.write
import play.api.libs.json.{JsNull, JsValue}


object JsonUtils {

  private implicit val formats = (new DefaultFormats {
    override def dateFormatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss")
  }).preservingEmptyValues

  def parse[A <: AnyRef](reqJson: JsValue)(implicit m: Manifest[A]): A = {
    org.json4s.jackson.JsonMethods.parse(reqJson.toString()).extract[A]
  }

  def render[T <: AnyRef](response: T): String = write(response)
}
