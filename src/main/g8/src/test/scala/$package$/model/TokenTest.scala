package $package$.model

import org.scalatest.{FunSpec, Matchers}

class TokenTest extends FunSpec with Matchers {
  describe("Token") {
    describe("random") {
      it("returns a random token every time") {
        Token.random() should not equal Token.random()
        Token.random() should not equal Token.random()
        Token.random() should not equal Token.random()
      }
    }
  }
}
