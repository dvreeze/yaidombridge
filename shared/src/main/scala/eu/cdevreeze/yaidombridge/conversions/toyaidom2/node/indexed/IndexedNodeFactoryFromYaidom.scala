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

package eu.cdevreeze.yaidombridge.conversions.toyaidom2.node.indexed

import scala.collection.immutable.ArraySeq

import eu.cdevreeze.yaidom
import eu.cdevreeze.yaidom2
import eu.cdevreeze.yaidombridge.conversions.toyaidom2.node.simple.SimpleNodeFactoryFromYaidom

/**
 * Indexed node factories for yaidom2 from yaidom backing elements.
 *
 * @author Chris de Vreeze
 */
object IndexedNodeFactoryFromYaidom {

  def fromYaidomBackingNode(node: yaidom.queryapi.BackingNodes.Node): yaidom2.node.indexed.Node = {
    node match {
      case e: yaidom.queryapi.BackingNodes.Elem                   => fromYaidomBackingElem(e)
      case t: yaidom.queryapi.BackingNodes.Text                   => yaidom2.node.indexed.Text(t.text, isCData = false)
      case c: yaidom.queryapi.BackingNodes.Comment                => yaidom2.node.indexed.Comment(c.text)
      case pi: yaidom.queryapi.BackingNodes.ProcessingInstruction => yaidom2.node.indexed.ProcessingInstruction(pi.target, pi.data)
      case er: yaidom.queryapi.BackingNodes.EntityRef             => sys.error(s"Cannot convert entity reference to yaidom2")
    }
  }

  def fromYaidomBackingElem(elem: yaidom.queryapi.BackingNodes.Elem): yaidom2.node.indexed.Elem = {
    val ownYaidom2Path: ArraySeq[Int] = getOwnYaidom2Path(elem)

    yaidom2.node.indexed.Elem.of(elem.docUriOption, SimpleNodeFactoryFromYaidom.fromYaidomScopedElem(elem.rootElem), ownYaidom2Path)
  }

  private def getOwnYaidom2Path(elem: yaidom.queryapi.BackingNodes.Elem): ArraySeq[Int] = {
    def relativeNavigationPath(e: yaidom.queryapi.BackingNodes.Elem): Seq[Int] = {
      e.parentOption
        .map { pe =>
          // Recursive call
          relativeNavigationPath(pe).appended(findAllPrecedingSiblingElems(e).size)
        }
        .getOrElse(IndexedSeq.empty)
    }

    relativeNavigationPath(elem).to(ArraySeq)
  }

  private def findAllPrecedingSiblingElems(elem: yaidom.queryapi.BackingNodes.Elem): Seq[yaidom.queryapi.BackingNodes.Elem] = {
    val parentElemOption = elem.parentOption

    if (parentElemOption.isEmpty) {
      ArraySeq.empty
    } else {
      parentElemOption.get.findAllChildElems.takeWhile(_ != elem).reverse
    }
  }
}
