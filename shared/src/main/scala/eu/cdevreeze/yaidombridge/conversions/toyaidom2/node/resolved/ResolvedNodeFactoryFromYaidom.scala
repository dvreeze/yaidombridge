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

package eu.cdevreeze.yaidombridge.conversions.toyaidom2.node.resolved

import scala.collection.immutable.ListMap

import eu.cdevreeze.yaidom
import eu.cdevreeze.yaidom2
import eu.cdevreeze.yaidombridge.conversions.toyaidom2.ENameConversionsToYaidom2

/**
 * Resolved node factories for yaidom2 from yaidom Clark elements.
 *
 * @author Chris de Vreeze
 */
object ResolvedNodeFactoryFromYaidom {

  def fromYaidomClarkNode(node: yaidom.queryapi.ClarkNodes.Node): yaidom2.node.resolved.Node = {
    node match {
      case e: yaidom.queryapi.ClarkNodes.Elem => fromYaidomClarkElem(e)
      case t: yaidom.queryapi.ClarkNodes.Text => yaidom2.node.resolved.Text(t.text)
    }
  }

  def fromYaidomClarkElem(elem: yaidom.queryapi.ClarkNodes.Elem): yaidom2.node.resolved.Elem = {
    val ename = ENameConversionsToYaidom2.convertEName(elem.resolvedName)
    val attrs =
      elem.resolvedAttributes
        .map { case (attrEName, attrValue) => ENameConversionsToYaidom2.convertEName(attrEName) -> attrValue }
        .to(ListMap)

    // Recursive calls (mutual recursion with method fromYaidomClarkNode).
    val children = elem.children.map(fromYaidomClarkNode)

    new yaidom2.node.resolved.Elem(ename, attrs, children.to(Vector))
  }
}
