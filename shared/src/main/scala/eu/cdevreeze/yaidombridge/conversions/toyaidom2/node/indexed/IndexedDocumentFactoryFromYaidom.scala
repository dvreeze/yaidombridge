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

import eu.cdevreeze.yaidom
import eu.cdevreeze.yaidom2
import eu.cdevreeze.yaidombridge.conversions.toyaidom2.node.simple.SimpleDocumentFactoryFromYaidom

/**
 * Indexed document factories for yaidom2 from yaidom scoped documents.
 *
 * @author Chris de Vreeze
 */
object IndexedDocumentFactoryFromYaidom {

  /**
   * Creates a yaidom2 indexed document from the given yaidom document (of any type, as long as the document element is a backing element).
   */
  def fromYaidomDocument(doc: yaidom.queryapi.DocumentApi.Aux[_, _ <: yaidom.queryapi.BackingNodes.Elem]): yaidom2.node.indexed.Document = {
    val simpleDoc: yaidom2.node.simple.Document = SimpleDocumentFactoryFromYaidom.fromYaidomDocument(doc)

    yaidom2.node.indexed.IndexedDocument.of(simpleDoc)
  }
}
