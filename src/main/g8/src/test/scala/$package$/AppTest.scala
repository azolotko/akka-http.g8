package $package$

import org.scalatest._

class AppTest extends AsyncFunSpec with Matchers {
  override def withFixture(test: NoArgAsyncTest): FutureOutcome = {
    App.main(Array.empty)
    test()
  }
  // noinspection FieldFromDelayedInit

  describe("App") {
    it("starts an HTTP server") {
      App.httpBinding.map(_.localAddress should not be null)
    }
  }
}
