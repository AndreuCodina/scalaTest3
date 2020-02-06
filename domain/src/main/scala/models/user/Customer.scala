package models.user

import eu.timepit.refined.api.Refined
import eu.timepit.refined.collection.MaxSize
import io.estatico.newtype.macros.newtype
import models.user.Customer.CustomerName

object Customer {
  @newtype case class CustomerName(value: String Refined MaxSize[4])
}

case class Customer(name: CustomerName)
