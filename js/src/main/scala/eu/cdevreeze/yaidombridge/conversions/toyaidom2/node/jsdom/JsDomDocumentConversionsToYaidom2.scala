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

package eu.cdevreeze.yaidombridge.conversions.toyaidom2.node.jsdom

import eu.cdevreeze.yaidom
import eu.cdevreeze.yaidom2

/**
 * JsDom document conversions from yaidom to yaidom2.
 *
 * @author Chris de Vreeze
 */
object JsDomDocumentConversionsToYaidom2 {

  def convertDocument(doc: yaidom.jsdom.JsDomDocument): yaidom2.node.jsdom.JsDomDocument = {
    yaidom2.node.jsdom.JsDomDocument(doc.wrappedDocument)
  }
}
