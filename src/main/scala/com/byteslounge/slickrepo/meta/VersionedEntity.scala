/*
 * Copyright 2016 byteslounge.com (Gonçalo Marques).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.byteslounge.slickrepo.meta

import com.byteslounge.slickrepo.version.VersionGenerator

/**
 * Versioned business entity that is mapped to a database record.
 */
abstract class VersionedEntity[T <: VersionedEntity[T, ID, V], ID, V : VersionGenerator](val version: Option[V] = None) extends Entity[T, ID] {
  val generator: VersionGenerator[V] = implicitly[VersionGenerator[V]]

  /**
  * Sets the version for this versioned entity instance.
  */
  def withVersion(version: V): T

  def withNewVersion(version: Option[V]): T = withVersion(version.map(v => generator.nextVersion(v)).getOrElse(generator.initialVersion()))

}
