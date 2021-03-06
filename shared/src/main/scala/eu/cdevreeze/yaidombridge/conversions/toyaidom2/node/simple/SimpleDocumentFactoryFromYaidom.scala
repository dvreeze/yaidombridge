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

import eu.cdevreeze.yaidom
import eu.cdevreeze.yaidom2

/**
 * Simple document factories for yaidom2 from yaidom scoped documents.
 *
 * @author Chris de Vreeze
 */
object SimpleDocumentFactoryFromYaidom {

  /**
   * Creates a yaidom2 simple document from the given yaidom document (of any type, as long as the document element is a scoped element).
   */
  def fromYaidomDocument(doc: yaidom.queryapi.DocumentApi.Aux[_, _ <: yaidom.queryapi.ScopedNodes.Elem]): yaidom2.node.simple.Document = {
    val docElem: yaidom2.node.simple.Elem =
      SimpleNodeFactoryFromYaidom.fromYaidomScopedElem(doc.documentElement)

    yaidom2.node.simple.SimpleDocument(doc.uriOption, Seq(docElem))
  }
}
