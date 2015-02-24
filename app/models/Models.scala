package models

import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._

case class Article(id: Pk[Int] = NotAssigned, caption: String, content: String)

object Article {

  /**
   * Parse a Employee from a ResultSet
   */
  val simple = {
    get[Pk[Int]]("articles.article_id") ~
      get[String]("articles.caption") ~
      get[String]("articles.content") map {
      case id ~ caption ~ content => Article(id, caption, content)
    }
  }

  /**
   * Find Employee Via Employee Id
   *
   * @param id the employee  id.
   */
  def findByArticleId(id: Int) = {
    DB.withConnection { implicit connection =>
      val articleFound = SQL(
        """
          select * from articles
          where article_id = {id}
        """).on(
          'id -> id).as(Article.simple.singleOpt)
      articleFound
    }
  }
}
