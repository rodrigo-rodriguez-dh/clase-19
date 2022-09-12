package com.digitalhouse.movieservice.api.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.digitalhouse.movieservice.domain.models.Movie;
import com.digitalhouse.movieservice.domain.repositories.MovieRepository;

@Service
public class MovieService {

    private static final Logger LOG = LoggerFactory.getLogger(MovieService.class);

    private final MovieRepository repository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.repository = movieRepository;
    }

    public List<Movie> findByGenre(String genre) {
        return repository.findByGenre(genre);
    }

    public List<Movie> findByGenre(String genre, Boolean throwError) {
        LOG.info("se van a buscar las peliculas por género");
        if (throwError) {
            LOG.error("Hubo un error al buscar las películas");
            throw new RuntimeException();
        }
        return repository.findByGenre(genre);
    }

    @RabbitListener(queues = "${queue.movie.name}")
    public Movie save(Movie movie) {
        LOG.info("Se recibio una movie a través de rabbit " + movie.toString());
        return repository.save(movie);
    }

}
