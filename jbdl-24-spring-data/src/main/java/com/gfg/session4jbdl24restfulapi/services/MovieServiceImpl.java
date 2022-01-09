package com.gfg.session4jbdl24restfulapi.services;
import com.gfg.session4jbdl24restfulapi.entity.Cast;
import com.gfg.session4jbdl24restfulapi.entity.Movie;
import com.gfg.session4jbdl24restfulapi.model.MovieCreationRequest;
import com.gfg.session4jbdl24restfulapi.model.MovieResponse;
import com.gfg.session4jbdl24restfulapi.repository.CastRepository;
import com.gfg.session4jbdl24restfulapi.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository repository;

    @Autowired
    private CastRepository castRepository;

    @Override
    public void create(final MovieCreationRequest request) {
        List<Cast> casts = new ArrayList<>();
        for(String cast: request.getCasts()){
            Optional<Cast> castOptional =castRepository.findByName(cast);
            if(castOptional.isPresent()){
                casts.add(castOptional.get());
                continue;
            }
            Cast newCast = Cast.builder().name(cast).build();
            casts.add(newCast);

        }

        Movie movie = Movie.builder()
                .name(request.getMovieName())
                .castList(casts).build();
        repository.save(movie);
    }

    @Override
    public MovieResponse get(final String  name) {
        Optional<Movie> movieOptional = repository.findByName(name);
        if(movieOptional.isPresent()){
            List<String> casts = new ArrayList<>();
            for(Cast cast : movieOptional.get().getCastList()){
                casts.add(cast.getName());
            }
            MovieResponse movieResponse = new MovieResponse(movieOptional.get().getName(),
                    movieOptional.get().getRating(),casts);
            return movieResponse;
        }
        return new MovieResponse();
    }
}
