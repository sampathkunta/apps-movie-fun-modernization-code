package org.superbiz.moviefun.albums;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums/rest")
public class AlbumsRestController {

    private final AlbumsBean albumsBean;

    public AlbumsRestController(AlbumsBean albumsBean) {
        this.albumsBean = albumsBean;
    }

    @PostMapping()
    public void addAlbum(@RequestBody Album album) {
        albumsBean.addAlbum(album);
        return;
    }

    @GetMapping()
    public List<Album> listAlbums() {
        return albumsBean.getAlbums();
    }

    @GetMapping("/{albumId}")
    public Album findAlbum(@PathVariable Long albumId) {
        return albumsBean.find(albumId);
    }
}
