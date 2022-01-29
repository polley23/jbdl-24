package com.example.jbdl24urlshortener.repository;
import com.example.jbdl24urlshortener.entity.CachedUrl;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CachedUrlRepository extends CrudRepository<CachedUrl,String> {
}
