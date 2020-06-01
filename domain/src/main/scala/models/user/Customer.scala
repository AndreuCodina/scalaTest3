package models.user

import eu.timepit.refined.api.Refined
import eu.timepit.refined.collection.MaxSize
import io.estatico.newtype.macros.newtype
import models.user.Customer.{CustomerEmail, CustomerName}

object Customer {
  @newtype case class CustomerName(value: String Refined MaxSize[4])

  sealed trait CustomerEmail
  case class UnvalidatedEmail(value: String) extends CustomerEmail
  case class ValidatedEmail(value: String) extends CustomerEmail
}

case class Customer(
  name: CustomerName,
  email: CustomerEmail
)
