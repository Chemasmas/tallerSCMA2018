package controllers

import akka.actor.ActorSystem
import javax.inject._
import model.{Usuario, UsuarioOps}
import play.api.data.Forms._
import play.api.data._
import play.api.libs.Files
//import play.api.libs.json.{JsValue, Writes}
import play.api.mvc._
import play.api.mvc.{AbstractController, ControllerComponents}
//import play.libs.Json
import play.api.libs.json._

import scala.concurrent.{ExecutionContext, Future}


@Singleton
class ChatController @Inject()(cc: ControllerComponents, actorSystem: ActorSystem)(implicit exec: ExecutionContext) extends AbstractController(cc) {


  val loginForm = Form(
      tuple(
        "nombre" -> text,
        "token" -> text
      )
  )


  def registerUser():Action[AnyContent] = Action.async{
    implicit request:Request[AnyContent] => {
      //val (nombre,token) = loginForm.bindFromRequest.
      (for{
        c <- UsuarioOps.crearTabla()
        x <- {
          val (nombre,token) = loginForm.bindFromRequest().get
          UsuarioOps.insertar( Usuario(0,nombre,token,"" ))
        }
      }yield{
        println(c)
        println(x)
        Ok("Usuario Creado")
      }) recover {
        case t => {
          println(t)
          BadRequest("Fallo")
        }
      }
    }
  }
  def login():Action[AnyContent] = Action.async{
    implicit request:Request[AnyContent] => {
      //val (nombre,token) = loginForm.bindFromRequest.
      (for{
        x <- {
          val (nombre,token) = loginForm.bindFromRequest().get
          UsuarioOps.login( nombre,token )
        }
      }yield{
        println(x)
        if(x.orNull!=null)
          Ok(Json.obj(
            "id"->x.get.id,
            "nombre"->x.get.user,
            "token"->x.get.token,
            "imagen"->x.get.imagen
          ))
        else
          BadRequest("No Hallado")
      }) recover {
        case t => {
          println(t)
          BadRequest("Fallo")
        }
      }
    }
  }
}
