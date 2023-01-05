package dao

import com.zaxxer.hikari.{HikariConfig, HikariDataSource}

object Datasources {

  private val source = {
    val config = new HikariConfig()
    config.setJdbcUrl("jdbc:mysql://localhost:3306/auth")
    config.setUsername("root")
    config.setPassword("")
    config.setMaximumPoolSize(10)
    config.setLeakDetectionThreshold(3000)
    config.setConnectionTimeout(120000)
    new HikariDataSource(config)
  }

  def getConnection = source.getConnection

}
