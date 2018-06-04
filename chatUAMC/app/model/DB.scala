package model

import java.io.File
import java.nio.file.Paths
import java.security.MessageDigest

import play.api.libs.Files
import slick.jdbc.H2Profile.api._
import play.api.mvc.MultipartFormData
import play.libs.Files.TemporaryFile
//import play.libs.Files.TemporaryFile


import scala.concurrent.ExecutionContext.Implicits.global

object DB{

  val salt = "JATdev"
  def getDatabase() =  {
    Database.forConfig("h2mem1")
  }
}
/*
object GenToken{
  def apply(u:Usuario) : Usuario = u.copy( token = SHA256( u.nombre + DB.salt ) )
}
*/
//Cifrados
object SHA256{
  def apply(s:String):String = { MessageDigest.getInstance("SHA-256").digest(s.getBytes).map("%02x".format(_)).mkString }
}

object SHA512{
  def apply(s:String):String = { MessageDigest.getInstance("SHA-512").digest(s.getBytes).map("%02x".format(_)).mkString }
}

object getExtension{
  def apply(name:String):String = {
    name.substring(name.lastIndexOf("."))
  }
}

object FileUserUploader{

  val currDir = Paths.get(System.getProperty("user.home"))
  def path(u:Usuario,foto:MultipartFormData.FilePart[Files.TemporaryFile]):String = currDir.resolve( s"u${u.id}${getExtension(foto.filename)}" ).toString


  def url(u:Usuario,foto:MultipartFormData.FilePart[Files.TemporaryFile]):String = {
    foto.ref.moveTo( new File(path(u,foto)) , replace = true )
    s"${u.id}${getExtension(foto.filename)}"
  }

  def apply(u:Usuario,img:MultipartFormData.FilePart[Files.TemporaryFile]) = {
    for{
      x <- UsuarioOps.update(u,url(u,img))
    } yield {
      x
    }
  }
}