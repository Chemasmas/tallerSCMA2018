package model

import slick.jdbc.H2Profile.api._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global


case class Usuario(
                    id:Long,
                    user:String,
                    token:String,
                    imagen:String
                  )
class UsuarioTable(tag:Tag) extends Table[Usuario](tag,"Usuarios")  {
  def id = column[Long]("id",O.PrimaryKey,O.AutoInc)
  def user = column[String]("user")
  def token = column[String]("token")
  def imagen = column[String]("imagen")

  def * = (id,user,token,imagen) <> (Usuario.tupled,Usuario.unapply)
}


object UsuarioOps{
  val usuariosTable = TableQuery[UsuarioTable]

  def crearTabla(): Future[Boolean] = {
    (for {
      x <- DB.getDatabase.run(usuariosTable.schema.create)
    } yield {
      true
    }) recover {
      case _ => false
    }
  }

  def insertar(usuario: Usuario): Future[Long] = {
    println(usuario)
    val a = (usuariosTable returning usuariosTable.map(_.id)) += usuario
    println(a.statements.mkString("\n"))
    (for {
      x <- DB.getDatabase.run(a)
    } yield {
      x //El id del nuevo
    }) recover {
      case t => {
        println(t)
        -1
      }
    }
  }

  def login(usuario:String,token:String):Future[Option[Usuario]] = {
    val a = usuariosTable.filter( _.user === usuario ).filter(_.token === token ).result
    (for {
      x <- DB.getDatabase().run(a)
    } yield {
      Some(x.head)
    }) recover {
      case _ => None
    }
  }

  def update(u: Usuario, foto: String): Future[Int] = {
    val a = usuariosTable.filter(_.id === u.id).map(x => x.imagen).update(foto)
    (for {
      x <- DB.getDatabase().run(a)
    } yield {
      x
    }) recover {
      case t => {
        println(t)
        0
      }
    }
  }

  def buscar(id: Long): Future[Option[Usuario]] = {
    val a = usuariosTable.filter(_.id === id).result
    (for {
      x <- DB.getDatabase().run(a)
    } yield {
      Some(x.head)
    }) recover {
      case _ => None
    }
  }

  def lista(): Future[Seq[Usuario]] = {
    val a = usuariosTable.result
    (for {
      x <- DB.getDatabase().run(a)
    } yield {
      x
    }) recover {
      case _ => Seq.empty[Usuario]
    }
  }
}