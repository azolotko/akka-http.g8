package $package$.model

import java.util.concurrent.atomic.AtomicLong

case class UserId(id: Long) extends AnyVal

object UserId {
  private val serial = new AtomicLong(0)

  def next(): UserId = UserId(serial.incrementAndGet())
}
