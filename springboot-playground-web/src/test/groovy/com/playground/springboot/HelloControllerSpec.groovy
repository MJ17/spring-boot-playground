package com.playground.springboot


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

@SpringBootTest(classes = Application.class)
@ContextConfiguration
class HelloControllerSpec extends Specification {

    MockMvc mvc
    @Autowired
    WebApplicationContext webApplicationContext

    def setup() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build()
    }

    def "when get is performed then the response has status 200 and content is 'hello'"() {
        given:

        when:
        def result = mvc.perform(
                MockMvcRequestBuilders.get("/hello")
        ).andReturn().getResponse()

        then:
        result.status == HttpStatus.OK.value()
        result.contentAsString == "Hello world!"
    }

}
