/*
 * This program is licensed to you under the Apache License Version 2.0,
 * and you may not use this file except in compliance with the Apache License Version 2.0.
 * You may obtain a copy of the Apache License Version 2.0 at http://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the Apache License Version 2.0 is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Apache License Version 2.0 for the specific language governing permissions and limitations there under.
 */
package me.soulmachine

import com.twitter.scalding._
import org.scalatest.{ Matchers, WordSpec }

class WordCountTest extends WordSpec with Matchers {
  "A WordCount job" should {
    JobTest(new WordCountJob(_))
      .arg("input", "inputFile")
      .arg("output", "outputFile")
      .source(TextLine("inputFile"), List((0, "hack hack hack and hack")))
      .sink[(String, Int)](TypedTsv[(String, Long)]("outputFile")){ outputBuffer =>
        val outMap = outputBuffer.toMap
        "count words correctly" in {
          outMap("hack") shouldBe 4
          outMap("and") shouldBe 1
        }
      }
      .run
      .finish
  }
}
