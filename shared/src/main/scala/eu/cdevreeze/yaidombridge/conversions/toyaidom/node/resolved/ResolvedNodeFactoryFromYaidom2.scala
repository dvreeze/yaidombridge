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

package eu.cdevreeze.yaidombridge.conversions.toyaidom.node.resolved

import scala.collection.immutable.ListMap

import eu.cdevreeze.yaidom
import eu.cdevreeze.yaidom2
import eu.cdevreeze.yaidombridge.conversions.toyaidom.ENameConversionsToYaidom

/**
 * Resolved node factories for yaidom from yaidom2 Clark elements.
 *
 * @author Chris de Vreeze
 */
object ResolvedNodeFactoryFromYaidom2 {

  def fromYaidom2ClarkNode(node: yaidom2.queryapi.ClarkNodes.Node): yaidom.resolved.Node = {
    node match {
      case e: yaidom2.queryapi.ClarkNodes.Elem => fromYaidom2ClarkElem(e)
      case t: yaidom2.queryapi.ClarkNodes.Text => yaidom.resolved.Text(t.text)
    }
  }

  def fromYaidom2ClarkElem(elem: yaidom2.queryapi.ClarkNodes.Elem): yaidom.resolved.Elem = {
    val ename = ENameConversionsToYaidom.convertEName(elem.name)
    val attrs =
      elem.attributes
        .to(IndexedSeq)
        .map {
          case (attrEName, attrValue) => ENameConversionsToYaidom.convertEName(attrEName) -> attrValue
        }
        .to(ListMap)

    // Recursive calls (mutual recursion with method fromYaidom2ClarkNode).
    val children = elem.children.map(fromYaidom2ClarkNode).to(IndexedSeq)

    yaidom.resolved.Elem(ename, attrs, children)
  }
}
