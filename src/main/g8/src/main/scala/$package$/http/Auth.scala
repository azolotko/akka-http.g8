package $package$.http

import akka.http.scaladsl.model.headers.HttpChallenges
import akka.http.scaladsl.server.AuthenticationFailedRejection.{
  CredentialsMissing,
  CredentialsRejected
}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.directives.{
  AuthenticationDirective,
  AuthenticationResult
}
import akka.http.scaladsl.server.{
  AuthenticationFailedRejection,
  Directive0,
  Directive1
}
import $package$.model.{Token, UserId, UserRole}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait Auth {
  private def realm = "$name$"

  def tokenAuthenticator(token: Token): Future[AuthenticationResult[UserId]] =
    Future.successful(???.asInstanceOf[Option[UserId]]).map {
      case Some(userId: UserId) =>
        AuthenticationResult.success(userId)
      case None =>
        AuthenticationResult.failWithChallenge(HttpChallenges.oAuth2(realm))
    }

  def authenticated[T](authenticator: Token => Future[AuthenticationResult[T]])
    : AuthenticationDirective[T] =
    optionalHeaderValueByName("X-Auth-Token").flatMap {
      case Some(token) =>
        onSuccess(authenticator(Token(token))).flatMap {
          case Right(s) => provide(s)
          case Left(challenge) =>
            reject(AuthenticationFailedRejection(CredentialsRejected,
                                                 challenge)): Directive1[T]
        }
      case None =>
        reject(
          AuthenticationFailedRejection(
            CredentialsMissing,
            HttpChallenges.oAuth2(realm))): Directive1[T]
    }

  def authorizeByRole(userId: UserId, userRole: UserRole): Directive0 =
    authorize((???.asInstanceOf[Option[UserRole]]).contains(userRole))
}
