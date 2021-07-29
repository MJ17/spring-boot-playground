package com.playground.springboot

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

@SpringBootTest(classes = Application.class)
@ContextConfiguration
class BookControllerSpec extends Specification {

    @Autowired
    WebApplicationContext webApplicationContext

    MockMvc mvc
    ObjectMapper mapper

    def setup() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build()
        mapper = new ObjectMapper()
    }

    def "when get is performed then the response has status 200 and content is 'hello'"() {
        given:

        when:
        def result = mvc.perform(
                MockMvcRequestBuilders.get("/books/123")
        ).andReturn().getResponse()

        then:
        result.status == HttpStatus.OK.value()
        result.contentAsString == "Book id is 123"
    }

    def "when post is performed then the response has status 200 and content is bookId"() {
        given:
        HttpHeaders headers = new HttpHeaders()
        headers.put(HttpHeaders.AUTHORIZATION, Arrays.asList("Bearer token"))

        CreateBookRequest request = new CreateBookRequest()
        request.title = "The Old Man and the Sea"
        request.author = "Hemingway";
        request.publisher = "Good Books"

        when:
        def result = mvc.perform(
                MockMvcRequestBuilders.post("/books")
                        .headers(headers)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request))
        ).andReturn().getResponse()

        then:
        result.status == HttpStatus.OK.value()
        result.contentAsString.contains(request.getTitle())
        result.contentAsString.contains(request.getAuthor())
        result.contentAsString.contains(request.getPublisher())
    }

}
