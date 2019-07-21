/*
 * Copyright (C) 2018 Lightbend Inc. <https://www.lightbend.com>
 */

package akka.cluster.sharding.typed.scaladsl

import akka.persistence.typed.scaladsl.Effect
import akka.persistence.typed.scaladsl.PersistentBehavior

object PersistentEntity {

  /**
   * Create a `Behavior` for a persistent actor that is used with Cluster Sharding.
   *
   * Any [[Behavior]] can be used as a sharded entity actor, but the combination of sharding and persistent
   * actors is very common and therefore this `PersistentEntity` is provided as convenience.
   *
   * It is a [[PersistentBehavior]] and is implemented in the same way. It selects the `persistenceId`
   * automatically from the [[EntityTypeKey]] and `entityId` constructor parameters by using
   * [[EntityTypeKey.persistenceIdFrom]].
   */
  def apply[Command, Event, State](
    entityTypeKey:  EntityTypeKey[Command],
    entityId:       String,
    emptyState:     State,
    commandHandler: (State, Command) ⇒ Effect[Event, State],
    eventHandler:   (State, Event) ⇒ State): PersistentBehavior[Command, Event, State] =
    PersistentBehavior(entityTypeKey.persistenceIdFrom(entityId), emptyState, commandHandler, eventHandler)
}
