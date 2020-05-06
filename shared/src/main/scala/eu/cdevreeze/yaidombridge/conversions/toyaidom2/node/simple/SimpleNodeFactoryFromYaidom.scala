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

package eu.cdevreeze.yaidombridge.conversions.toyaidom2.node.simple

import scala.collection.immutable.ListMap

import eu.cdevreeze.yaidom
import eu.cdevreeze.yaidom2
import eu.cdevreeze.yaidombridge.conversions.toyaidom2.QNameConversionsToYaidom2
import eu.cdevreeze.yaidombridge.conversions.toyaidom2.ScopeConversionsToYaidom2

/**
 * Simple node factories for yaidom2 from yaidom scoped elements.
 *
 * @author Chris de Vreeze
 */
object SimpleNodeFactoryFromYaidom {

  def fromYaidomScopedNode(node: yaidom.queryapi.ScopedNodes.Node): yaidom2.node.simple.Node = {
    node match {
      case e: yaidom.queryapi.ScopedNodes.Elem                   => fromYaidomScopedElem(e)
      case t: yaidom.queryapi.ScopedNodes.Text                   => yaidom2.node.simple.Text(t.text, isCData = false)
      case c: yaidom.queryapi.ScopedNodes.Comment                => yaidom2.node.simple.Comment(c.text)
      case pi: yaidom.queryapi.ScopedNodes.ProcessingInstruction => yaidom2.node.simple.ProcessingInstruction(pi.target, pi.data)
      case er: yaidom.queryapi.ScopedNodes.EntityRef             => sys.error(s"Cannot convert entity reference to yaidom2")
    }
  }

  def fromYaidomScopedElem(elem: yaidom.queryapi.ScopedNodes.Elem): yaidom2.node.simple.Elem = {
    val qname = QNameConversionsToYaidom2.convertQName(elem.qname)
    val attrs =
      elem.attributes.map { case (attrQName, attrValue) => QNameConversionsToYaidom2.convertQName(attrQName) -> attrValue }.to(ListMap)
    val scope = ScopeConversionsToYaidom2.convertScope(elem.scope)

    // Recursive calls (mutual recursion with method fromYaidomScopedNode).
    val children = elem.children.filterNot(_.isInstanceOf[yaidom.simple.EntityRef]).map(fromYaidomScopedNode)

    new yaidom2.node.simple.Elem(qname, attrs, scope, children.to(Vector))
  }
}
