package $package$.model

import org.scalatest.{FunSpec, Matchers}

class UserIdTest extends FunSpec with Matchers {
  describe("UserId") {
    describe("next") {
      it("returns a fresh id every time") {
        UserId.next() should not equal UserId.next()
        UserId.next() should not equal UserId.next()
        UserId.next() should not equal UserId.next()
      }
    }
  }
}
