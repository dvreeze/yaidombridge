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

package eu.cdevreeze.yaidombridge

import eu.cdevreeze.yaidom
import eu.cdevreeze.yaidom2
import eu.cdevreeze.yaidombridge.conversions.toyaidom.node.jsdom.JsDomDocumentConversionsToYaidom
import eu.cdevreeze.yaidombridge.conversions.toyaidom2.node.jsdom.JsDomDocumentConversionsToYaidom2

/**
 * Implicit classes extending JS-Dom wrapper documents with toYaidom or toYaidom2 methods.
 *
 * Add "import eu.cdevreeze.yaidombridge.jsdomconversions._" to application code for using these "extension functions".
 *
 * @author Chris de Vreeze
 */
object jsdomconversions {

  implicit class ToYaidom2JsDomDocument(val doc: yaidom.jsdom.JsDomDocument) extends AnyVal {

    def toYaidom2: yaidom2.node.jsdom.JsDomDocument = JsDomDocumentConversionsToYaidom2.convertDocument(doc)
  }

  implicit class ToYaidomJsDomDocument(val doc: yaidom2.node.jsdom.JsDomDocument) extends AnyVal {

    def toYaidom: yaidom.jsdom.JsDomDocument = JsDomDocumentConversionsToYaidom.convertDocument(doc)
  }
}
