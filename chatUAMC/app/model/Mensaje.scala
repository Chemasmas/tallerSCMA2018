package model

import slick.jdbc.H2Profile.api._
import scala.concurrent.ExecutionContext.Implicits.global


case class Mensaje(
                    remitente:Long,
                    destinatario:Long,
                    mensaje:String
                  )

class MensajeTable(tag:Tag) extends Table [Mensaje] (tag,"Mensaje"){
  def remitente = column[Long]("remitente")
  def destinatario = column[Long]("destinatario")
  def mensaje = column[String]("mensaje")

  def * = (remitente,destinatario,mensaje) <> (Mensaje.tupled,Mensaje.unapply)
}
