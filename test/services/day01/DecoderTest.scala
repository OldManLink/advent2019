package services.day01

import org.specs2.mutable.Specification

class DecoderTest extends Specification {

  "DecoderTest" should {

    "decode the first message" in {
      val keyLog = KeyLog.fromFileName("sms1")
      val plainText = Message.fromFileName("sms1")
      val decoder = Decoder(keyLog, 2592402216L)
      val message = decoder.plainTextMessage

      message.date mustEqual plainText.date
      message.to mustEqual plainText.to
      message.text mustEqual plainText.text
    }

    "decode the second message" in {
      val keyLog = KeyLog.fromFileName("sms2")
      val plainText = Message.fromFileName("sms2")
      val decoder = Decoder(keyLog, 2592905514L)
      val message = decoder.plainTextMessage

      message.date mustEqual plainText.date
      message.to mustEqual plainText.to
      message.text mustEqual plainText.text
    }

    "decode the third message" in {
      val keyLog = KeyLog.fromFileName("sms3")
      val plainText = Message.fromFileName("sms3")
      val decoder = Decoder(keyLog, 2593322502L)
      val message = decoder.plainTextMessage

      message.date mustEqual plainText.date
      message.to mustEqual plainText.to
      message.text mustEqual plainText.text
    }

    "decode the fourth message" in {
      val keyLog = KeyLog.fromFileName("sms4")
      val plainText = Message.fromFileName("sms3")
      val decoder = Decoder(keyLog, 2594289734L)
      val message = decoder.plainTextMessage

      message.date mustEqual plainText.date
      message.to mustEqual plainText.to
      message.text mustEqual plainText.text
    }
  }
}
