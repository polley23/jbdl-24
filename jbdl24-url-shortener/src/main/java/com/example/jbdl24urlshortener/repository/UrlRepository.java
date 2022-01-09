package com.example.jbdl24urlshortener.repository;
import com.example.jbdl24urlshortener.entity.Url;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UrlRepository extends CrudRepository<Url,Long> {
}
