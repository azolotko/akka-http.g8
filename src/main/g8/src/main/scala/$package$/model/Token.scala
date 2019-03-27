package $package$.model

import java.util.UUID

case class Token(token: String) extends AnyVal

object Token {
  def random(): Token = Token(UUID.randomUUID().toString)
}
