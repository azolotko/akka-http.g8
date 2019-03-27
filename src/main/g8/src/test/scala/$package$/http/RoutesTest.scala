package $package$.http

import akka.http.scaladsl.model.{ContentTypes, HttpRequest}
import akka.http.scaladsl.model.headers.{HttpChallenges, RawHeader}
import akka.http.scaladsl.server.AuthenticationFailedRejection.{
  CredentialsMissing,
  CredentialsRejected
}
import akka.http.scaladsl.server.{
  AuthenticationFailedRejection,
  AuthorizationFailedRejection
}
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{FunSpec, Matchers}

class RoutesTest extends FunSpec with Matchers with ScalatestRouteTest {
  describe("/api") {
    val apiRoute = Routes.api

    it("requires authentication by token") {
      Get("/api") ~> apiRoute ~> check {
        rejection shouldEqual AuthenticationFailedRejection(
          CredentialsMissing,
          HttpChallenges.oAuth2("$name$"))
      }
    }

    it("requires authentication token to be valid") {
      Get("/api").withHeaders(RawHeader("X-Auth-Token", "invalid token") :: Nil) ~> apiRoute ~> check {
        rejection shouldEqual AuthenticationFailedRejection(
          CredentialsRejected,
          HttpChallenges.oAuth2("$name$"))
      }
    }
  }
}
