package uk.carlosramos.api.video.controlador;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import uk.carlosramos.api.video.service.VideoService;

@RestController
public class VideoController {

    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<String> uploadVideo(@RequestPart("file") FilePart filePart) {
        return videoService.handleUpload(filePart);
    }

    @GetMapping("/partes/{nombre}")
    public Mono<Resource> download(@PathVariable String nombre) {
        return videoService.getVideoPart(nombre);
    }
}
