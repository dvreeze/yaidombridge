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
import eu.cdevreeze.yaidombridge.conversions.toyaidom.node.simple.SimpleDocumentFactoryFromYaidom2

/**
 * Indexed document factories for yaidom from yaidom2 backing documents.
 *
 * @author Chris de Vreeze
 */
object IndexedDocumentFactoryFromYaidom2 {

  /**
   * Creates a yaidom indexed document from the given yaidom2 backing document.
   */
  def fromYaidom2Document(doc: yaidom2.queryapi.BackingDocumentApi): yaidom.indexed.Document = {
    val simpleDoc: yaidom.simple.Document = SimpleDocumentFactoryFromYaidom2.fromYaidom2Document(doc)

    yaidom.indexed.Document.from(simpleDoc)
  }
}
