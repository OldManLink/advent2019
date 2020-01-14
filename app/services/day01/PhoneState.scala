package services.day01

import services.day01.PhoneState.{getChar, timeoutMillis}

import scala.collection.immutable.HashMap

case class PhoneState (display: List[Char], position: Int, lastPress: KeyPress) {

  def pressKey(keyPress: KeyPress): PhoneState = {
    val sameKey = keyPress.keyCode == lastPress.keyCode
    val timeout = (keyPress.instant.toEpochMilli - lastPress.instant.toEpochMilli) > timeoutMillis
    if (!sameKey || (sameKey && timeout)) PhoneState(display :+ getChar(keyPress), position + 1, keyPress)
    else PhoneState(display.dropRight(1) :+ getChar(keyPress, display.lastOption), position, keyPress)
  }

  def getDisplayString: String = {
    display.mkString("")
  }
}

object PhoneState {

  val timeoutMillis: Int = 1010

  val keyTabs: Map[Int, List[Char]] = HashMap(
    0 -> List(' ', '0'),
    1 -> List('.',',','\'','?','!','"','1','-','(',')','@','/',':'),
    2 -> List('a','b','c','2'),
    3 -> List('d','e','f','3'),
    4 -> List('g','h','i','4'),
    5 -> List('j','k','l','5'),
    6 -> List('m','n','o','6'),
    7 -> List('p','q','r','s','7'),
    8 -> List('t','u','v','8'),
    9 -> List('w','x','y','z','9'),
    10 -> List('@','/',':','_',';','+','&','%','*','[',']','{','}'),
    11 -> List('!'),
    100 -> List('<'),
    101 -> List('>'),
    102 -> List('^'),
    103 -> List('¨'),
    104 -> List('°'),
    105 -> List('§')
  )

  def getChar(keyPress: KeyPress, old: Option[Char] = None): Char = {
    val keyTab: List[Char] = keyTabs(keyPress.keyCode)
    old.map {c =>
      val index = (keyTab.indexOf(c) + 1) % keyTab.size
      keyTab(index)
    } getOrElse keyTab.head
  }

  def create(keyPress: KeyPress): PhoneState = {
    PhoneState(List(getChar(keyPress)), 0, keyPress)
  }

}