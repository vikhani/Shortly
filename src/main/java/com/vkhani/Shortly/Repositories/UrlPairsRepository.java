package com.vkhani.Shortly.Repositories;

import com.vkhani.Shortly.Models.UrlPair;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UrlPairsRepository extends MongoRepository<UrlPair, String> {
    @Query("{longUrl:'?0'}")
    UrlPair findPairByLongURL(String longUrl);

    // @Query("{shortUrl:'?0'}")
    UrlPair findByShortenedUrl(String shortUrl);

//    @Query(fields = "{'longUrl':1,'shortenedUrl':1}")
//    List<UrlPair> findAll();
}
