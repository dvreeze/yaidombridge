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

package eu.cdevreeze

/**
 * Conversions between yaidom and yaidom2 for ENames, QNames, simple/indexed/resolved elements, etc. Also views as an alternative
 * to (deep) conversions of element implementations. Also generic variants of several conversions.
 *
 * To use toYaidom and toYaidom2 "extension methods", just import "eu.cdevreeze.yaidombridge.sharedconversions._".
 * There are similar imports for Saxon (JVM-only) and JS-Dom (JS-only), namely for saxonconversions and jsdomconversions.
 *
 * @author Chris de Vreeze
 */
package object yaidombridge
