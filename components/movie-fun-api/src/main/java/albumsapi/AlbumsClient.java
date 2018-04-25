package albumsapi;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestOperations;

import java.util.List;

public class AlbumsClient {

    private final RestOperations restOperations;
    private final String albumsUrl;

    public AlbumsClient(String albumsUrl, RestOperations restOperations) {
        this.restOperations = restOperations;
        this.albumsUrl = albumsUrl;
    }


    public void addAlbum(AlbumInfo album) {
        restOperations.postForEntity(albumsUrl+"/rest", album, AlbumInfo.class);
    }

    public List<AlbumInfo> getAlbums() {
        return restOperations.exchange(albumsUrl+"/rest", HttpMethod.GET, null, new ParameterizedTypeReference<List<AlbumInfo>>() {
        }).getBody();
    }

    public AlbumInfo find(long albumId) {
        String test =  restOperations.getForEntity(albumsUrl+"/rest/"+albumId, String.class).getBody();

        System.out.println(test);

        return restOperations.getForEntity(albumsUrl+"/rest/"+albumId, AlbumInfo.class).getBody();

//        ResponseEntity<MovieInfo> entity = this.restOperations.getForEntity(moviesUrl, MovieInfo.class, id);
//        return entity.getBody();
    }
}
