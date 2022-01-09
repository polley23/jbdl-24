package com.gfg.session4jbdl24restfulapi.repository;
import com.gfg.session4jbdl24restfulapi.entity.Cast;
import com.gfg.session4jbdl24restfulapi.entity.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends CrudRepository<Movie,Long> {
    Optional<Movie> findByName(String name);


}
