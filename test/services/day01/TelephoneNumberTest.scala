package services.day01

import org.specs2.mutable.Specification

class TelephoneNumberTest extends Specification {

  "TelephoneNumber" should {

    "accept a purely numerical string" in {
      TelephoneNumber("0708797753") must not throwA
    }

    "not accept a not-purely numerical string" in {
      TelephoneNumber("+46708797753") must throwA[AssertionError]
    }
  }
}
