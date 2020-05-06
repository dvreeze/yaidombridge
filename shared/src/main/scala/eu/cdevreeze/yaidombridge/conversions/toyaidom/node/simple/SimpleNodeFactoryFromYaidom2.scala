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

package eu.cdevreeze.yaidombridge.conversions.toyaidom.node.simple

import eu.cdevreeze.yaidom
import eu.cdevreeze.yaidom2
import eu.cdevreeze.yaidombridge.conversions.toyaidom.QNameConversionsToYaidom
import eu.cdevreeze.yaidombridge.conversions.toyaidom.ScopeConversionsToYaidom

/**
 * Simple node factories for yaidom from yaidom2 scoped elements.
 *
 * @author Chris de Vreeze
 */
object SimpleNodeFactoryFromYaidom2 {

  def fromYaidom2ScopedNode(node: yaidom2.queryapi.ScopedNodes.Node): yaidom.simple.Node = {
    node match {
      case e: yaidom2.queryapi.ScopedNodes.Elem                   => fromYaidom2ScopedElem(e)
      case t: yaidom2.queryapi.ScopedNodes.Text                   => yaidom.simple.Text(t.text, isCData = false)
      case c: yaidom2.queryapi.ScopedNodes.Comment                => yaidom.simple.Comment(c.text)
      case pi: yaidom2.queryapi.ScopedNodes.ProcessingInstruction => yaidom.simple.ProcessingInstruction(pi.target, pi.data)
    }
  }

  def fromYaidom2ScopedElem(elem: yaidom2.queryapi.ScopedNodes.Elem): yaidom.simple.Elem = {
    val qname = QNameConversionsToYaidom.convertQName(elem.qname)
    val attrs =
      elem.attributesByQName.to(IndexedSeq).map {
        case (attrQName, attrValue) => QNameConversionsToYaidom.convertQName(attrQName) -> attrValue
      }

    val scope = ScopeConversionsToYaidom.convertScope(elem.scope)

    // Recursive calls (mutual recursion with method fromYaidom2ScopedNode).
    val children = elem.children.map(fromYaidom2ScopedNode).to(IndexedSeq)

    new yaidom.simple.Elem(qname, attrs, scope, children)
  }
}
