package services.day01

import java.io.InputStream
import java.time.Instant

import scala.io.Source

case class KeyLog(keyPresses: Seq[KeyPress]) {

  def keysAndDelays: Seq[(Long, Int)] = {
    val first = keyPresses.head
    val delays = keyPresses.foldLeft(Seq[(Long, Long, Int)]((first.instant.toEpochMilli, 0L, first.keyCode))) { (l, p) =>
      val next = (p.instant.toEpochMilli, p.instant.toEpochMilli - l.last._1, p.keyCode)
      l :+ next
    }
    delays drop 1 map(p => (p._2, p._3))
  }
}

object KeyLog {
  def fromFileName(fileName: String): KeyLog = {
    val stream: InputStream = getClass.getResourceAsStream(s"/$fileName.csv")
    val lines: Seq[String] = Source.fromInputStream( stream ).getLines.toSeq
    val keyPresses = lines.map { line =>
      val parts = line split ","
      val date = Instant.ofEpochMilli(parts(0).toLong)
      val keyCode = parts(1).toInt
      KeyPress(date, keyCode)
    }
    KeyLog(keyPresses)
  }
}