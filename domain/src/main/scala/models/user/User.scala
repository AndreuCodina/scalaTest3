package main.scala.models.user

import eu.timepit.refined.api.{Refined, Validate}
import eu.timepit.refined.collection.{MaxSize, Size}
import io.circe.generic.extras.Configuration
import io.estatico.newtype.macros.newtype
//import main.scala.models.user.User.UserName

object User {
//  @newtype case class UserName(value: String Refined MaxSize[4])
//  implicit val userNameonfig: Configuration =
//    Configuration.default.withDefaults
//   @newtype class UserName(value: String)
}

//case class User(name: UserName)
