import eu.timepit.refined.api.Refined
import eu.timepit.refined.auto._
import eu.timepit.refined.collection.MaxSize
import io.circe._
import io.circe.generic.extras.Configuration
import io.circe.generic.extras.decoding.UnwrappedDecoder
import io.circe.generic.semiauto._
import io.circe.syntax._
import io.circe.parser.decode
import io.circe.refined._
import io.estatico.newtype.Coercible
import io.estatico.newtype.ops._
import models.user.Customer
import models.user.Customer.CustomerName
//import main.scala.models.user.User
//import main.scala.models.user.User.UserName
import io.circe.generic.extras.defaults._
import io.estatico.newtype.macros.newtype
//import shapeless.Unwrapped
import cats.Applicative

object Program {
//  implicit val configuration: Configuration =
//    Configuration.default.withDiscriminator("type")
  //
  //  implicit val userNameDecoder: Decoder[UserName] = Decoder.forProduct1[UserName, String]("value")(name => UserName(Refined.unsafeApply(name)))
  //  implicit val userNameEncoder: Encoder[UserName] = Encoder.forProduct1[UserName, String Refined MaxSize[4]]("value")(_.value)
  //
  //  implicit val userDecoder: Decoder[User] = deriveDecoder[User]
  //  implicit val userEncoder: Encoder[User] = deriveEncoder[User]


  /////////////////

//  implicit def decodeAnyVal[T, U](
//                                   implicit
//                                   ev: T <:< AnyVal,
//                                   unwrapped: Unwrapped.Aux[T, U],
//                                   decoder: Decoder[U]): Decoder[T] = Decoder.instance[T] { cursor =>
//
//    decoder(cursor).map(value => unwrapped.wrap(value))
//  }
//
//  implicit def encodeAnyVal[T, U](
//                                   implicit
//                                   ev: T <:< AnyVal,
//                                   unwrapped: Unwrapped.Aux[T, U],
//                                   encoder: Encoder[U]): Encoder[T] = Encoder.instance[T] { value =>
//
//    encoder(unwrapped.unwrap(value))
//  }

//  case class UserName(value: String Refined MaxSize[4]) extends AnyVal
//
//  case class User(name: UserName)
//
//  implicit val userNameDecoder: Decoder[UserName] = deriveDecoder[UserName]
//  implicit val userNameEncoder: Encoder[UserName] = deriveEncoder[UserName]
//
//  implicit val userDecoder: Decoder[User] = deriveDecoder[User]
//  implicit val userEncoder: Encoder[User] = deriveEncoder[User]

  // ----- Coercible codecs -----
  implicit def coercibleDecoder[A: Coercible[B, *], B: Decoder]: Decoder[A] =
    Decoder[B].map(_.coerce[A])

  implicit def coercibleEncoder[A: Coercible[B, *], B: Encoder]: Encoder[A] =
    Encoder[B].contramap(_.repr.asInstanceOf[B])

  implicit def coercibleKeyDecoder[A: Coercible[B, *], B: KeyDecoder]: KeyDecoder[A] =
    KeyDecoder[B].map(_.coerce[A])

  implicit def coercibleKeyEncoder[A: Coercible[B, *], B: KeyEncoder]: KeyEncoder[A] =
    KeyEncoder[B].contramap[A](_.repr.asInstanceOf[B])

  // ----- Domain codecs -----
  implicit val customerDecoder: Decoder[Customer] = deriveDecoder[Customer]
  implicit val customerEncoder: Encoder[Customer] = deriveEncoder[Customer]

  def main(args: Array[String]): Unit = {

    val customer = Customer(CustomerName("Alex"))



//    customer.copy(name = customer.name.copy())

    val customerWithLongName: Customer = customer.copy(name = CustomerName(Refined.unsafeApply("Alex Mendoza")))
    val json = customerWithLongName.asJson.noSpaces
    println("json: " + json)
    val decodedJson = decode[Customer](json)
    println(decodedJson)

//    val name = User(UserName("Alex"))
//    val json = name.asJson.noSpaces
//    println("json: " + json)
//    val decodedJson = decode[User](json)
//    println(decodedJson)

    //////////
    //    val user = User(UserName("Alex"))
    //    val json = user.asJson.noSpaces
    //    println("json: " + json)
    //    val decodedJson = decode[User](json)
    //    println(decodedJson)
    ()
  }
}


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
