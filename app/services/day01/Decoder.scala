package services.day01

import java.time.Instant

case class Decoder(codedMessage: KeyLog, offset: Long) {

  def plainTextMessage: Message = {
    Message(getDate, getTelephoneNumber, getText)
  }

  def getDate: Instant = {
    codedMessage.keyPresses.last.instant.minusMillis(offset)
  }

  def getTelephoneNumber: TelephoneNumber = {
    val reversed = codedMessage.keyPresses.reverse.toList
    val menuOffsets = reversed.zipWithIndex.collect{case(p ,i) if p.keyCode == 100 => i}
    val numberReversed = reversed.slice(menuOffsets.head + 1, menuOffsets(1)).map (_.keyCode)
    TelephoneNumber(numberReversed.reverse.mkString(""))
  }

  def getText: String = {
    val finalState: PhoneState = codedMessage.keyPresses.drop(1).foldLeft(PhoneState.create(codedMessage.keyPresses.head))
    {(st, kp) => st.pressKey(kp)}

    println(s">>> $finalState")

    finalState.getDisplayString
  }
}
