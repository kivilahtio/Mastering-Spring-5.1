package fi.solita.kivilahtio.controller

import fi.solita.kivilahtio.Application

/*
 * Copyright 2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate

import spock.lang.*

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Unroll
class SpockTest extends Specification {

    @Autowired
    private TestRestTemplate rest

    def "when context is loaded then all expected beans are created"() {
        expect: "the Rest client is created"
        rest
    }

    def "maximum of two numbers"() {
        expect:
        Math.max(a, b) == c

        where:
        a << [3, 5, 9]
        b << [7, 4, 9]
        c << [7, 5, 9]
    }

    def "minimum of #a and #b is #c"() {
        expect:
        Math.min(a, b) == c

        where:
        a | b || c
        3 | 7 || 3
        5 | 4 || 4
        9 | 9 || 9
    }

    def "#person.name is a #sex.toLowerCase() person"() {
        expect:
        person.getSex() == sex

        where:
        person                    || sex
        new Person(name: "Fred")  || "Male"
        new Person(name: "Wilma") || "Female"
    }

    static class Person {
        String name
        String getSex() {
            name == "Fred" ? "Male" : "Female"
        }
    }
}