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

package eu.cdevreeze.yaidombridge.test

import java.io.File

import eu.cdevreeze.yaidom
import eu.cdevreeze.yaidom2
import org.scalacheck.Properties
import org.scalacheck.Prop.forAll
import eu.cdevreeze.yaidombridge.sharedconversions._
import eu.cdevreeze.yaidombridge.saxonconversions._
import scala.util.chaining._

import eu.cdevreeze.yaidom.utils.saxon.SaxonElemToSimpleElemConverter
import net.sf.saxon.s9api.Processor
import org.scalacheck.Arbitrary
import org.scalacheck.Gen

object ConversionSpecification extends Properties("conversions") {

  property("Conversion of Saxon doc to yaidom2 retains equality") = forAll { (doc: yaidom.saxon.SaxonDocument) =>
    val convertedDoc = doc.toYaidom2

    yaidom2.node.resolved.Elem.from(convertedDoc.documentElement) == yaidom.resolved.Elem.from(doc.documentElement).pipe(_.toYaidom2)
  }

  property("Conversion of Saxon doc to yaidom retains equality") = forAll { (doc: yaidom2.node.saxon.SaxonDocument) =>
    val convertedDoc = doc.toYaidom

    yaidom.resolved.Elem.from(convertedDoc.documentElement) == yaidom2.node.resolved.Elem.from(doc.documentElement).pipe(_.toYaidom)
  }

  property("Conversion of Saxon doc to yaidom2 and back retains equality") = forAll { (doc: yaidom.saxon.SaxonDocument) =>
    val convertedDoc = doc.toYaidom2.toYaidom

    yaidom.resolved.Elem.from(convertedDoc.documentElement) == yaidom.resolved.Elem.from(doc.documentElement)
  }

  property("Conversion of Saxon doc to yaidom and back retains equality") = forAll { (doc: yaidom2.node.saxon.SaxonDocument) =>
    val convertedDoc = doc.toYaidom.toYaidom2

    yaidom2.node.resolved.Elem.from(convertedDoc.documentElement) == yaidom2.node.resolved.Elem.from(doc.documentElement)
  }

  property("Conversion of indexed doc to yaidom2 retains equality") = forAll { (doc: yaidom.saxon.SaxonDocument) =>
    val doc1 = yaidom.indexed.Document(SaxonElemToSimpleElemConverter.convertSaxonDocument(doc))
    val convertedDoc = doc1.toYaidom2

    yaidom2.node.resolved.Elem.from(convertedDoc.documentElement) == yaidom.resolved.Elem.from(doc1.documentElement).pipe(_.toYaidom2)
  }

  property("Conversion of indexed doc to yaidom retains equality") = forAll { (doc: yaidom2.node.saxon.SaxonDocument) =>
    val doc1 = yaidom2.node.indexed.Document.from(doc)
    val convertedDoc = doc1.toYaidom

    yaidom.resolved.Elem.from(convertedDoc.documentElement) == yaidom2.node.resolved.Elem.from(doc1.documentElement).pipe(_.toYaidom)
  }

  property("Conversion of indexed doc to yaidom2 and back retains equality") = forAll { (doc: yaidom.saxon.SaxonDocument) =>
    val doc1 = yaidom.indexed.Document(SaxonElemToSimpleElemConverter.convertSaxonDocument(doc))
    val convertedDoc = doc1.toYaidom2.toYaidom

    yaidom.resolved.Elem.from(convertedDoc.documentElement) == yaidom.resolved.Elem.from(doc1.documentElement)
  }

  property("Conversion of indexed doc to yaidom and back retains equality") = forAll { (doc: yaidom2.node.saxon.SaxonDocument) =>
    val doc1 = yaidom2.node.indexed.Document.from(doc)
    val convertedDoc = doc1.toYaidom.toYaidom2

    yaidom2.node.resolved.Elem.from(convertedDoc.documentElement) == yaidom2.node.resolved.Elem.from(doc1.documentElement)
  }

  private val processor: Processor = new Processor(false)

  private val xmlFiles: Seq[File] = {
    val rootDir: File = new File(classOf[ConversionSpecification].getResource("/xml-files/cars.xml").toURI).getParentFile
    rootDir.listFiles().toSeq
  }

  implicit private val anyYaidomSaxonDoc: Arbitrary[yaidom.saxon.SaxonDocument] = {
    val docBuilder = processor.newDocumentBuilder()

    val docs: Seq[yaidom.saxon.SaxonDocument] = xmlFiles
      .map(f => docBuilder.build(f))
      .map(xdmNode => yaidom.saxon.SaxonDocument.wrapDocument(xdmNode.getUnderlyingNode.getTreeInfo))

    val result: Gen[yaidom.saxon.SaxonDocument] =
      for {
        i <- Gen.oneOf(0.until(docs.size))
      } yield docs(i)

    Arbitrary(result)
  }

  implicit private val anyYaidom2SaxonDoc: Arbitrary[yaidom2.node.saxon.SaxonDocument] = {
    val docBuilder = processor.newDocumentBuilder()

    val docs: Seq[yaidom2.node.saxon.SaxonDocument] = xmlFiles
      .map(f => docBuilder.build(f))
      .map(xdmNode => yaidom2.node.saxon.SaxonDocument(xdmNode))

    val result: Gen[yaidom2.node.saxon.SaxonDocument] =
      for {
        i <- Gen.oneOf(0.until(docs.size))
      } yield docs(i)

    Arbitrary(result)
  }
}

class ConversionSpecification
