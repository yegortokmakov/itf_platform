package controllers

import java.util.UUID

import play.api._
import play.api.mvc._
import play.api.db._
import util.OAuth2
import models.Article

object Application extends Controller {
  val FLASH_MESSAGE_KEY: String = "message"
  val FLASH_ERROR_KEY: String = "error"

  def index() = Action {
    implicit request =>

      // catch exception
      val article = Article.findByArticleId(1).get

      val oauth2 = new OAuth2(Play.current)
      val callbackUrl = util.routes.OAuth2.callback(None, None).absoluteURL()
      val scope = "repo"   // github scope - request repo access
      val state = UUID.randomUUID().toString  // random confirmation string
      val redirectUrl = oauth2.getAuthorizationUrl(callbackUrl, scope, state)
      Ok(views.html.index("Your new application is ready.", redirectUrl, article.caption, article.content)).
        withSession("oauth-state" -> state)
  }

  def blog = Action { implicit request =>
    Ok(views.html.index("Your new application is ready.", "asd", "asd", "asd"))
  }

}