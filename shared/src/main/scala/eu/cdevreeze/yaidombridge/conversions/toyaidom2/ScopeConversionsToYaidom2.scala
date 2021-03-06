/*
 * Copyright 2020-2020 Chris de Vreeze
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.cdevreeze.yaidombridge.conversions.toyaidom2

import scala.collection.immutable.ListMap

import eu.cdevreeze.yaidom
import eu.cdevreeze.yaidom2

/**
 * Scope conversions from yaidom to yaidom2. Note that the input scope does not promise to keep its insertion order, so
 * the resulting scope may be "incorrect" from the perspective of the insertion order.
 *
 * @author Chris de Vreeze
 */
object ScopeConversionsToYaidom2 {

  def convertScope(scope: yaidom.core.Scope): yaidom2.core.Scope = {
    yaidom2.core.Scope.from(scope.prefixNamespaceMap.to(ListMap))
  }
}
