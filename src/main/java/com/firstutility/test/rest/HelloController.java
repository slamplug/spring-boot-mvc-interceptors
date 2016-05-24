package com.firstutility.test.rest;

import com.firstutility.test.model.Greeting;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private static final Logger log = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping(method = RequestMethod.POST, path = "/hello", produces = "application/json")
    public ResponseEntity<Greeting> sayHello(@RequestBody String jsonString) {

        log.info("/hello, request body : " + jsonString);
        
        JSONObject jsonObject = new JSONObject(jsonString);

        return new ResponseEntity<Greeting>(
                new Greeting(1, "test"), HttpStatus.OK);
    }
}
