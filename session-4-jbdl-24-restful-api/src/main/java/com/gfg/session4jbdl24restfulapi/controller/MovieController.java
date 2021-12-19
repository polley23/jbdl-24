package com.gfg.session4jbdl24restfulapi.controller;
import com.gfg.session4jbdl24restfulapi.model.Movie;
import com.gfg.session4jbdl24restfulapi.model.MovieCreationRequest;
import com.gfg.session4jbdl24restfulapi.model.MovieUpdationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;


/*
Backend service for Movie Store
We will have api to create, get , update and delete movie resource.
 */

@RestController
@Slf4j
public class MovieController {

    Map<String,Movie> movies = new HashMap<>();

    @PostMapping("/v1/movie")
    ResponseEntity create(@RequestBody MovieCreationRequest movieCreationRequest){
        movies.put(movieCreationRequest.getMovieName(),new Movie(movieCreationRequest.getMovieName(),
                movieCreationRequest.getRating()));
        log.info("movie is created");
        return ResponseEntity.status(HttpStatus.CREATED).body("movie is created");
    }


    @GetMapping("/v1/movie")
    ResponseEntity get(@RequestParam("name") String name){
        if(!movies.containsKey(name)){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        Movie movie = movies.get(name);
        return ResponseEntity.status(HttpStatus.OK).body(movie);

    }

    @PatchMapping("/v1/movie/{name}")
    ResponseEntity update(@PathVariable("name") String name,
                          @RequestBody MovieUpdationRequest movieUpdationRequest){
        if(!movies.containsKey(name)){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        Movie movie = movies.get(name);
        movie.setRating(movieUpdationRequest.getRating());
        movies.put(name,movie);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @DeleteMapping("/v1/movie/{name}")
    ResponseEntity delete(@PathVariable("name") String name){
        if(!movies.containsKey(name)){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        movies.remove(name);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
