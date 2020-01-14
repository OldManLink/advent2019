package services.day01

import java.io.InputStream
import java.time.ZoneOffset.UTC
import java.time.format.DateTimeFormatter
import java.time.{Instant, LocalDateTime}

import scala.io.Source

case class Message(date: Instant, to: TelephoneNumber, text: String)

object Message {
  def fromFileName(fileName: String): Message = {
    val stream: InputStream = getClass.getResourceAsStream(s"/$fileName.txt")
    val lines: Seq[String] = Source.fromInputStream( stream ).getLines.toSeq
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    val date: Instant = LocalDateTime.from(formatter.parse(lines.head.substring(6))).toInstant(UTC)
    val to: TelephoneNumber = TelephoneNumber(lines(1).substring(4))
    val text: String = lines(2).substring(6)

    Message(date, to, text)
  }
}
