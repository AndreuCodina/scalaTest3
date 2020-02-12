import eu.timepit.refined.api.{RefType, Refined, Validate}
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

object Program {
  // ----- CustomerName codecs -----
  // Uncomment this line to get a refined error
//  implicit val customerNameDecoder: Decoder[CustomerName] =
//    Decoder.forProduct1[CustomerName, String]("name")(name => CustomerName(Refined.unsafeApply(name)))

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
  implicit val customerDecoder: Decoder[Customer] =
    Decoder.forProduct1[Customer, String]("name")(name => Customer(CustomerName(Refined.unsafeApply(name))))

  implicit val customerEncoder: Encoder[Customer] = deriveEncoder[Customer]

  def main(args: Array[String]): Unit = {
    val customer = Customer(CustomerName("Alex")) // Alex -> 4 characters
    val customerWithLongName: Customer = customer.copy(name = CustomerName(Refined.unsafeApply("Alex Mendoza"))) // Alex Mendoza -> More than 4 characters
    val json = customerWithLongName.asJson.noSpaces
    println("Customer json: " + json)
    val decodedJson = decode[Customer](json)
    println(decodedJson)
    ()
  }
}
