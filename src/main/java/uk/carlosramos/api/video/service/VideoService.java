package uk.carlosramos.api.video.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class VideoService {

    private static final Path VIDEO_UPLOAD_DIR = Paths.get("videos");
    private static final Path PARTS_DIR = Paths.get("videos/partes");

    public VideoService() throws IOException {
        Files.createDirectories(VIDEO_UPLOAD_DIR);
        Files.createDirectories(PARTS_DIR);
    }

    public Mono<String> handleUpload(FilePart filePart) {
        String originalFileName = filePart.filename();
        if (!originalFileName.endsWith(".mp4")) {
            return Mono.error(new IllegalArgumentException("Solo se permiten archivos .mp4"));
        }

        Path targetPath = VIDEO_UPLOAD_DIR.resolve(originalFileName);

        return filePart.transferTo(targetPath)
                .then(Mono.fromRunnable(() -> dividirVideo(targetPath)))
                .thenReturn("Video subido y dividido exitosamente");
    }

    private void dividirVideo(Path videoPath) {
        String input = videoPath.toAbsolutePath().toString();
        String output = PARTS_DIR.resolve("parte_%03d.mp4").toAbsolutePath().toString();

        try {
            ProcessBuilder pb = new ProcessBuilder(
                    "ffmpeg", "-i", input,
                    "-c", "copy",
                    "-map", "0",
                    "-segment_time", "180",
                    "-f", "segment",
                    output
            );
            pb.redirectErrorStream(true);
            Process process = pb.start();
            if (process.waitFor() != 0) {
                throw new RuntimeException("Error al dividir el video con ffmpeg");
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Fallo en la división del video", e);
        }
    }

    public Mono<Resource> getVideoPart(String nombre) {
        Path file = PARTS_DIR.resolve(nombre);
        if (!Files.exists(file)) {
            return Mono.error(new FileNotFoundException("No existe: " + nombre));
        }

        return Mono.just(new FileSystemResource(file));
    }
}

