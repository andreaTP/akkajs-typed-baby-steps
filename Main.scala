import akka.actor.typed._
import akka.actor.typed.scaladsl.Actor
import akka.actor.typed.scaladsl.AskPattern._
import scala.concurrent.Future
import scala.concurrent.duration._
// import scala.concurrent.Await
import akka.util.Timeout

object HelloWorld {
  final case class Greet(whom: String, replyTo: ActorRef[Greeted])
  final case class Greeted(whom: String)

  val greeter = Actor.immutable[Greet] { (_, msg) ⇒
    println(s"Hello ${msg.whom}!")
    msg.replyTo ! Greeted(msg.whom)
    Actor.same
  }
}

object Main extends App {

  implicit val timeout = Timeout(5 seconds)

  import HelloWorld._

  val system: ActorSystem[Greet] = ActorSystem(greeter, "hello")

  import system.executionContext
  implicit val scheduler = system.scheduler

  val future: Future[Greeted] = system ? (Greet("world", _))

  for {
    greeting ← future.recover { case ex ⇒ ex.getMessage }
    done ← {
      println(s"result: $greeting")
      system.terminate()
    }
  } println("system terminated")

}
