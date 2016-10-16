package com.byteslounge.slickrepo.test

import com.typesafe.slick.driver.oracle.OracleDriver
import slick.driver.{H2Driver, JdbcProfile, MySQLDriver}

case class Config(driver: JdbcProfile, dbConfig: String, rollbackTxError: Int, rowLockTimeoutError: Int)

abstract class DatabaseConfig {
  def config: Config
}

object H2Config extends DatabaseConfig {
  override def config: Config = Config(H2Driver, "h2", 23505, 50200)
}

object MySQLConfig extends DatabaseConfig {
  override def config: Config = Config(MySQLDriver, "mysql", 1062, 1213)
}

object OracleConfig extends DatabaseConfig {
  override def config: Config = Config(OracleDriver, "oracle", 1, 60)
}
