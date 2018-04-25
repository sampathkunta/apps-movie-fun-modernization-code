package org.superbiz.moviefun.moviesapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public class MoviesClient {

    private final RestOperations restOperations;
    private final String moviesUrl;

    public MoviesClient(String moviesUrl, RestOperations restOperations) {
        this.restOperations = restOperations;
        this.moviesUrl = moviesUrl;
    }

    public MovieInfo find(Long id) {
        ResponseEntity<MovieInfo> entity = this.restOperations.getForEntity(moviesUrl, MovieInfo.class, id);
        return entity.getBody();
    }

    public void addMovie(MovieInfo movie) {
        this.restOperations.postForEntity(moviesUrl, movie, MovieInfo.class);
    }

    public void deleteMovieId(long id) {
        restOperations.delete(moviesUrl + "/" +id);
    }

    public List<MovieInfo> getMovies() {
        ResponseEntity<List<MovieInfo>> entity =
                this.restOperations.exchange(moviesUrl,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<MovieInfo>>() {
                        });
        return entity.getBody();
    }

    public List<MovieInfo> findAll(int firstResult, int maxResults) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(moviesUrl)
                .queryParam("firstResult", firstResult)
                .queryParam("maxResults", maxResults);


        ResponseEntity<List<MovieInfo>> entity =
                this.restOperations.exchange(builder.toUriString(),
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<MovieInfo>>() {
                        });
        return entity.getBody();
    }

    public int countAll() {
        System.out.println("Movies URL::: "+ moviesUrl);
        return this.restOperations.getForObject(moviesUrl+"/count", Integer.class);
    }

    public int count(String field, String searchTerm) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(moviesUrl)
                .path("count")
                .queryParam("field", field)
                .queryParam("searchTerm", searchTerm);
        return this.restOperations.getForObject(builder.toUriString(), Integer.class);
    }

    public List<MovieInfo> findRange(String field, String searchTerm, int firstResult, int maxResults) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(moviesUrl)
                .queryParam("field", field)
                .queryParam("searchTerm", searchTerm)
                .queryParam("firstResult", firstResult)
                .queryParam("maxResults", maxResults);

        ResponseEntity<List<MovieInfo>> entity =
                this.restOperations.exchange(builder.toUriString(),
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<MovieInfo>>() {
                        });
        return entity.getBody();
    }
}
