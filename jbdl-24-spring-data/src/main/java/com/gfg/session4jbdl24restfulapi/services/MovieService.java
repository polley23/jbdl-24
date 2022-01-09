package com.gfg.session4jbdl24restfulapi.services;
import com.gfg.session4jbdl24restfulapi.model.MovieCreationRequest;
import com.gfg.session4jbdl24restfulapi.model.MovieResponse;

public interface MovieService {
    void create(MovieCreationRequest request);
    MovieResponse get(String name);

}
