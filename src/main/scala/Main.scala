import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._

import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn

object Main {

  def main(args: Array[String]): Unit = {

    implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "system")
    implicit val context: ExecutionContextExecutor = system.executionContext

    val route = path("test") {
      get {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Works</h1>"))
      }
    }

    val bindingFuture = Http().newServerAt("localhost", 8080).bind(route)
    println("Server online. Press RETURN to stop...")
    StdIn.readLine()

    bindingFuture
      .flatMap(bindingFuture => bindingFuture.unbind())
      .onComplete(_ => system.terminate())
  }
}
