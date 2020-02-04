import main.scala.models.user.User.{User, UserName}
import eu.timepit.refined.api.Refined
import eu.timepit.refined.auto._
import eu.timepit.refined.numeric._
import main.scala.models.user.User.UserName
import io.circe._
import io.circe.generic.auto._
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.parser._
import io.circe.syntax._
import io.circe.generic.extras.auto._
//import JsonCodecs._
import io.circe.generic.auto._
import io.circe.syntax._

object Program {
  implicit val userDecoder: Decoder[User] = deriveDecoder[User]
  implicit val userEncoder: Encoder[User] = deriveEncoder[User]

  //  implicit def validateSizeN[N <: Int, R](implicit w: ValueOf[N]): Validate.Plain[R, Size[N]] =
  //    Validate.fromPredicate[R, Size[N]](_.toString.size == w.value, _ => s"Must have ${w.value} digits", Size[N](w.value))

//  def algo(x: Option[Int]): Unit = {
//    x match {
//      case Some(v) => ()
//    }
//  }

  def main(args: Array[String]): Unit = {
    val user = User(UserName("Alex"))
    val json = user.asJson.noSpaces
    println("json: " + json)
    val decodedJson = decode[User](json)
    println(decodedJson)

//    sealed trait Customer
//    case class RegularCustomer(xs: Vector[String]) extends Customer
//    case class PremiumCustomer(i: Int, d: Option[Double]) extends Customer
//
//    val customer: Customer = PremiumCustomer(13, Some(14.0))
//
//    val json = customer.asJson.noSpaces
//    println(json)
//
//    val decodedJson = decode[Customer](json)
//    println(decodedJson)
//
//    decodedJson
//      .map {
//        case regularCustomer: RegularCustomer => println("Regular!!!")
//        case premiumCustomer: PremiumCustomer => println("Premium!!!")
//      }
    ()
  }
}
