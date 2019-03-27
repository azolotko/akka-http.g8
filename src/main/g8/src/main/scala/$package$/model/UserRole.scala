package $package$.model

sealed trait UserRole

case object TariffAuthorityRole extends UserRole

case object ChargePointRole extends UserRole

case object EVDriverRole extends UserRole
