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
import eu.cdevreeze.yaidombridge.conversions.toyaidom.DeclarationsConversionsToYaidom
import eu.cdevreeze.yaidombridge.conversions.toyaidom.ENameConversionsToYaidom
import eu.cdevreeze.yaidombridge.conversions.toyaidom.QNameConversionsToYaidom
import eu.cdevreeze.yaidombridge.conversions.toyaidom.ScopeConversionsToYaidom
import eu.cdevreeze.yaidombridge.conversions.toyaidom.node.indexed.IndexedDocumentConversionsToYaidom
import eu.cdevreeze.yaidombridge.conversions.toyaidom.node.indexed.IndexedNodeConversionsToYaidom
import eu.cdevreeze.yaidombridge.conversions.toyaidom.node.resolved.ResolvedNodeConversionsToYaidom
import eu.cdevreeze.yaidombridge.conversions.toyaidom.node.simple.SimpleDocumentConversionsToYaidom
import eu.cdevreeze.yaidombridge.conversions.toyaidom.node.simple.SimpleNodeConversionsToYaidom
import eu.cdevreeze.yaidombridge.conversions.toyaidom2.DeclarationsConversionsToYaidom2
import eu.cdevreeze.yaidombridge.conversions.toyaidom2.ENameConversionsToYaidom2
import eu.cdevreeze.yaidombridge.conversions.toyaidom2.QNameConversionsToYaidom2
import eu.cdevreeze.yaidombridge.conversions.toyaidom2.ScopeConversionsToYaidom2
import eu.cdevreeze.yaidombridge.conversions.toyaidom2.node.indexed.IndexedDocumentConversionsToYaidom2
import eu.cdevreeze.yaidombridge.conversions.toyaidom2.node.indexed.IndexedNodeConversionsToYaidom2
import eu.cdevreeze.yaidombridge.conversions.toyaidom2.node.resolved.ResolvedNodeConversionsToYaidom2
import eu.cdevreeze.yaidombridge.conversions.toyaidom2.node.simple.SimpleDocumentConversionsToYaidom2
import eu.cdevreeze.yaidombridge.conversions.toyaidom2.node.simple.SimpleNodeConversionsToYaidom2

/**
 * Implicit classes extending ENames, QNames, simple/indexed/resolved elements with toYaidom or toYaidom2 methods.
 *
 * @author Chris de Vreeze
 */
package object conversions {

  implicit class ToYaidom2EName(val ename: yaidom.core.EName) extends AnyVal {

    def toYaidom2: yaidom2.core.EName = ENameConversionsToYaidom2.convertEName(ename)
  }

  implicit class ToYaidomEName(val ename: yaidom2.core.EName) extends AnyVal {

    def toYaidom: yaidom.core.EName = ENameConversionsToYaidom.convertEName(ename)
  }

  implicit class ToYaidom2QName(val qname: yaidom.core.QName) extends AnyVal {

    def toYaidom2: yaidom2.core.QName = QNameConversionsToYaidom2.convertQName(qname)
  }

  implicit class ToYaidomQName(val qname: yaidom2.core.QName) extends AnyVal {

    def toYaidom: yaidom.core.QName = QNameConversionsToYaidom.convertQName(qname)
  }

  implicit class ToYaidom2Scope(val scope: yaidom.core.Scope) extends AnyVal {

    def toYaidom2: yaidom2.core.Scope = ScopeConversionsToYaidom2.convertScope(scope)
  }

  implicit class ToYaidomScope(val scope: yaidom2.core.Scope) extends AnyVal {

    def toYaidom: yaidom.core.Scope = ScopeConversionsToYaidom.convertScope(scope)
  }

  implicit class ToYaidom2Declarations(val declarations: yaidom.core.Declarations) extends AnyVal {

    def toYaidom2: yaidom2.core.Declarations = DeclarationsConversionsToYaidom2.convertDeclarations(declarations)
  }

  implicit class ToYaidomDeclarations(val declarations: yaidom2.core.Declarations) extends AnyVal {

    def toYaidom: yaidom.core.Declarations = DeclarationsConversionsToYaidom.convertDeclarations(declarations)
  }

  implicit class ToYaidom2SimpleElem(val elem: yaidom.simple.Elem) extends AnyVal {

    def toYaidom2: yaidom2.node.simple.Elem = SimpleNodeConversionsToYaidom2.convertElem(elem)
  }

  implicit class ToYaidomSimpleElem(val elem: yaidom2.node.simple.Elem) extends AnyVal {

    def toYaidom: yaidom.simple.Elem = SimpleNodeConversionsToYaidom.convertElem(elem)
  }

  implicit class ToYaidom2SimpleDocument(val doc: yaidom.simple.Document) extends AnyVal {

    def toYaidom2: yaidom2.node.simple.Document = SimpleDocumentConversionsToYaidom2.convertDocument(doc)
  }

  implicit class ToYaidomSimpleDocument(val doc: yaidom2.node.simple.Document) extends AnyVal {

    def toYaidom: yaidom.simple.Document = SimpleDocumentConversionsToYaidom.convertDocument(doc)
  }

  implicit class ToYaidom2IndexedElem(val elem: yaidom.indexed.Elem) extends AnyVal {

    def toYaidom2: yaidom2.node.indexed.Elem = IndexedNodeConversionsToYaidom2.convertElem(elem)
  }

  implicit class ToYaidomIndexedElem(val elem: yaidom2.node.indexed.Elem) extends AnyVal {

    def toYaidom: yaidom.indexed.Elem = IndexedNodeConversionsToYaidom.convertElem(elem)
  }

  implicit class ToYaidom2IndexedDocument(val doc: yaidom.indexed.Document) extends AnyVal {

    def toYaidom2: yaidom2.node.indexed.Document = IndexedDocumentConversionsToYaidom2.convertDocument(doc)
  }

  implicit class ToYaidomIndexedDocument(val doc: yaidom2.node.indexed.Document) extends AnyVal {

    def toYaidom: yaidom.indexed.Document = IndexedDocumentConversionsToYaidom.convertDocument(doc)
  }

  implicit class ToYaidom2ResolvedElem(val elem: yaidom.resolved.Elem) extends AnyVal {

    def toYaidom2: yaidom2.node.resolved.Elem = ResolvedNodeConversionsToYaidom2.convertElem(elem)
  }

  implicit class ToYaidomResolvedElem(val elem: yaidom2.node.resolved.Elem) extends AnyVal {

    def toYaidom: yaidom.resolved.Elem = ResolvedNodeConversionsToYaidom.convertElem(elem)
  }
}
