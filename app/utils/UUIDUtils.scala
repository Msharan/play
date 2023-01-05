package utils

import java.util.UUID
import javax.inject.Inject

class UUIDUtils @Inject()() {

  def getRandomUUID() = UUID.randomUUID()

  def validate(str: String): Boolean =
    try {
      UUID.fromString(str)
      true
    } catch {
      case ex: IllegalArgumentException => false
    }

  def validate(str: Option[String]): Boolean = str match {
    case None => false
    case Some(s) => validate(s)
  }


}