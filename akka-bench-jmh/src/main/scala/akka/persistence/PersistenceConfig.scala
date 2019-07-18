package akka.persistence

import com.typesafe.config.{ Config, ConfigFactory }

object PersistenceSpec {

  def config(plugin: String, test: String, serialization: String = "on", extraConfig: Option[String] = None) =
    extraConfig.map(ConfigFactory.parseString(_)).getOrElse(ConfigFactory.empty()).withFallback(
      ConfigFactory.parseString(
        s"""
      akka.actor.serialize-creators = ${serialization}
      akka.actor.serialize-messages = ${serialization}
      akka.actor.warn-about-java-serializer-usage = off
      akka.persistence.publish-plugin-commands = on
      akka.persistence.journal.plugin = "akka.persistence.journal.${plugin}"
      akka.persistence.journal.leveldb.dir = "target/journal-${test}"
      akka.persistence.snapshot-store.plugin = "akka.persistence.snapshot-store.local"
      akka.persistence.snapshot-store.local.dir = "target/snapshots-${test}/"
      akka.test.single-expect-default = 10s
    """))
}
