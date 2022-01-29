package com.example.jbdl24swagger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @PostMapping("/movie")
    ResponseEntity create(@RequestBody MovieRequest movieRequest){
        return ResponseEntity.ok(new MovieResponse());
    }

    @GetMapping("/movie")
    ResponseEntity create(@RequestParam("name") String  name){
        return ResponseEntity.ok(new MovieResponse());
    }


}
