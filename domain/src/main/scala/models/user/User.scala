package main.scala.models.user

import eu.timepit.refined._
import eu.timepit.refined.api.{Refined, Validate}
import eu.timepit.refined.auto._
import eu.timepit.refined.numeric._
import eu.timepit.refined.collection.{MaxSize, Size}
import io.circe.{Decoder, Encoder}
import io.circe.generic.JsonCodec
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
//import io.estatico.newtype.macros.newtype
import eu.timepit.refined.types.all
import io.estatico.newtype.macros._
//import scala.language.implicitConversions /////////////
import io.circe.generic.auto._
import io.circe.syntax._

object User {

  @newtype case class UserName(value: String Refined MaxSize[4])

  //  implicit val userNameDecoder: Decoder[UserName] = deriveDecoder[UserName]
  //  implicit val userNameEncoder: Encoder[UserName] = deriveEncoder[UserName]

  case class User(name: UserName)
}

//@newtype case class Foo(name: String)

//case class User()
