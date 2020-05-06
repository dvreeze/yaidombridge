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

package eu.cdevreeze.yaidombridge.conversions.toyaidom.node.indexed

import eu.cdevreeze.yaidom
import eu.cdevreeze.yaidom2
import eu.cdevreeze.yaidombridge.conversions.toyaidom.ENameConversionsToYaidom
import eu.cdevreeze.yaidombridge.conversions.toyaidom.node.simple.SimpleNodeFactoryFromYaidom2

/**
 * Indexed node factories for yaidom from yaidom2 backing elements.
 *
 * @author Chris de Vreeze
 */
object IndexedNodeFactoryFromYaidom2 {

  def fromYaidom2BackingNode(node: yaidom2.queryapi.BackingNodes.Node): yaidom.indexed.IndexedScopedNode.Node = {
    node match {
      case e: yaidom2.queryapi.BackingNodes.Elem    => fromYaidom2BackingElem(e)
      case t: yaidom2.queryapi.BackingNodes.Text    => yaidom.indexed.IndexedScopedNode.Text(t.text, isCData = false)
      case c: yaidom2.queryapi.BackingNodes.Comment => yaidom.indexed.IndexedScopedNode.Comment(c.text)
      case pi: yaidom2.queryapi.BackingNodes.ProcessingInstruction =>
        yaidom.indexed.IndexedScopedNode.ProcessingInstruction(pi.target, pi.data)
    }
  }

  def fromYaidom2BackingElem(elem: yaidom2.queryapi.BackingNodes.Elem): yaidom.indexed.Elem = {
    val ownPath: yaidom.core.Path = getOwnYaidomPath(elem)

    yaidom.indexed.Elem(elem.docUriOption, SimpleNodeFactoryFromYaidom2.fromYaidom2ScopedElem(elem.rootElem), ownPath)
  }

  private def getOwnYaidomPath(elem: yaidom2.queryapi.BackingNodes.Elem): yaidom.core.Path = {
    if (elem.ownNavigationPathRelativeToRootElem.isEmpty) {
      yaidom.core.Path.Empty
    } else {
      val ename: yaidom2.core.EName = elem.name
      val cnt = elem.findAllPrecedingSiblingElems.count(_.name == elem.name)
      val pathEntry = yaidom.core.Path.Entry(ENameConversionsToYaidom.convertEName(ename), cnt)

      // Recursive call
      getOwnYaidomPath(elem.findParentElem.ensuring(_.nonEmpty).get).append(pathEntry)
    }
  }
}
