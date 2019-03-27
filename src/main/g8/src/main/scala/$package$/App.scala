package $package$

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

object App extends scala.App {
  implicit val system: ActorSystem = ActorSystem("$name$-system")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContext = system.dispatcher

  // TODO: move HTTP server settings to a config file
  val httpServerInterface = "localhost"
  val httpServerPort = 8080

  val httpBinding = Http().bindAndHandle(http.Routes.api,
                                         interface = httpServerInterface,
                                         port = httpServerPort)

  Runtime.getRuntime.addShutdownHook(new Thread(() => {
    httpBinding.foreach(_.terminate(10.seconds))
  }))
}
