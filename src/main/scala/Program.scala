import eu.timepit.refined.api.Refined
import io.circe.generic.extras.auto._
import io.circe.generic.extras.Configuration
import io.circe.syntax._
import models.user.Customer.CustomerName
import io.circe.{Decoder, Encoder, KeyDecoder, KeyEncoder}
import io.circe.parser.decode
import io.estatico.newtype.Coercible
import io.estatico.newtype.ops._
import io.circe.refined._
import eu.timepit.refined.auto._
import models.user.Customer
import models.user.Customer.{ValidatedEmail}

object Program {
  implicit val customConfig: Configuration =
    Configuration.default.withSnakeCaseMemberNames.withDiscriminator("type")

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
  // implicit val customerDecoder: Decoder[Customer] =
  // Decoder.forProduct1[Customer, String]("name")(name => Customer(CustomerName(Refined.unsafeApply(name))))

  def main(args: Array[String]): Unit = {
    val customer = Customer(
      name = CustomerName("Alex"),
      email = ValidatedEmail(value = "alex@outlook.com")
    )

    var json = customer.asJson.noSpaces
    println("customer json: " + json)

    var decodedJson = decode[Customer](json)
    println(decodedJson)

    println("-----------------------------")

    val customerWithLongName: Customer = customer.copy(
      name = CustomerName(Refined.unsafeApply("Alex Mendoza")) // Alex Mendoza -> More than 4 characters
    )

    json = customerWithLongName.asJson.noSpaces
    println("customerWithLongName json: " + json)

    decodedJson = decode[Customer](json)
    println(decodedJson)
    ()
  }
}
