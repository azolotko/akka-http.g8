package $package$.http

import akka.event.Logging
import akka.http.scaladsl.model._
import akka.http.scaladsl.model.headers.Location
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.directives.DebuggingDirectives
import akka.http.scaladsl.server.{Route, _}
import akka.stream.ActorMaterializer
import com.typesafe.scalalogging.StrictLogging
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._
import io.circe.syntax._

import scala.util.control.NonFatal

object Routes extends Auth with StrictLogging {
  implicit private val exceptionHandler: ExceptionHandler = ExceptionHandler {
    case NonFatal(e) =>
      extractUri { uri =>
        logger.error(s"could not handle a request to \$uri", e)
        complete(HttpResponse(StatusCodes.InternalServerError))
      }
  }

  def api()(implicit materializer: ActorMaterializer): Route =
    DebuggingDirectives.logRequestResult("http", Logging.DebugLevel) {
      pathPrefix("api") {
        authenticated(tokenAuthenticator) { userId =>
          path("test") {
            get {
              complete("test")
            }
          }
        }
      }
    }
}
