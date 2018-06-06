package model

import scala.util.Random

object DummyGenerator {
  def global():Seq[Mensaje] = {
    //Los mensajes al global tendran como destinatario el 0
    Range(1,70,1).map( _ => Mensaje(0,Random.nextInt(20)+1,randomMessage ) )
  }

  def fakeUsers():Seq[Usuario] = {
    Range(1,20,1).map( x =>  Usuario(x,s"$randomFirstName $randomLastName()","123456", randomImage() ) )
  }

  def randomFirstName():String = {
    Random.shuffle(
      Seq(
        "Jorge",
        "Ana",
        "Chema",
        "Carlos",
        "Juan",
        "Humberto",
        "Kira"
      )
    ).head
  }

  def randomLastName():String = {
    Random.shuffle(
      Seq(
        "Perez",
        "Gomez",
        "Sierra",
        "Gonzales",
        "Hernandez",
        "Bernardino"
      )
    ).head
  }

  def randomImage():String = {
    val imagen = Random.shuffle(
      Seq(
        "astudio.png",
        "scp.png",
        "butterknife.png",
        "java.jpg",
        "354px-Gnulinux.svg.png"
      )
    ).head
    s"http://play.chemasmas.com/assets/images/$imagen"
  }

  def randomString():String = {
    Random.shuffle(
      Seq(
        "Hola",
        "Saludos",
        "Casa",
        "Lorem",
        "Impsum",
        "Relleno"
      )
    ).head
  }

  def randomMessage():String = {
    Range(1,Random.nextInt(26)).map(_ => randomString() ).mkString(""," ",".").toString
  }

}
