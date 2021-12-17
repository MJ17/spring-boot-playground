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
class PlayerControllerSpec extends Specification {

    @Autowired
    WebApplicationContext webApplicationContext

    MockMvc mvc
    ObjectMapper mapper

    def setup() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build()
        mapper = new ObjectMapper()
    }

    def "when get is performed then the response has status 200"() {
        given:

        when:
        def result = mvc.perform(
                MockMvcRequestBuilders.get("/players/1")
        ).andReturn().getResponse()

        then:
        result.status == HttpStatus.OK.value()
    }

    def "when post is performed then the response has status 200"() {
        given:
        HttpHeaders headers = new HttpHeaders()
        headers.put(HttpHeaders.AUTHORIZATION, Arrays.asList("Bearer token"))

        CreatePlayerRequest request = new CreatePlayerRequest()
        request.name = "Son"
        request.teamId = 1

        when:
        def result = mvc.perform(
                MockMvcRequestBuilders.post("/players")
                        .headers(headers)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request))
        ).andReturn().getResponse()

        then:
        result.status == HttpStatus.OK.value()
        result.contentAsString.contains(request.getName())
        result.contentAsString.contains(request.getTeamId())
    }

}
