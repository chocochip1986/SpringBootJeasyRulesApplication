package bespoke.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {
    @GetMapping()
    public ResponseEntity<String> trigger() {
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
