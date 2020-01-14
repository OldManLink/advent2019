package services.day01

case class TelephoneNumber(digitString: String) {
  assert(digitString.matches("[0-9]+"), "Invalid characters in telephone number")
}
