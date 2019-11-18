package com.nimaaj.ecommerce.web.rest.v1;

import com.nimaaj.ecommerce.dto.MediaDTO;
import com.nimaaj.ecommerce.exception.FileNotFoundException;
import com.nimaaj.ecommerce.model.input.MediaUploadModel;
import com.nimaaj.ecommerce.service.MediaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.net.URLConnection;
import java.nio.charset.Charset;

@RestController
@RequestMapping("/api/v1/media")
public class MediaResource {

    private final MediaService mediaService;

    public MediaResource(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @PostMapping
    public ResponseEntity<MediaDTO> upload(@Valid @RequestBody MediaUploadModel model) {
        return ResponseEntity.ok(mediaService.upload(model));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MediaDTO> getMedia(@PathVariable("id") Long id) {
        return ResponseEntity.ok(mediaService.getById(id));
    }

    @GetMapping("/download/{uuid}/{name}")
    public void download(HttpServletResponse response,
                 @PathVariable("uuid") String uuid,
                 @PathVariable("name") String name) throws IOException {
        File file = mediaService.download(uuid, name);

        if (!file.exists()) {
            String errorMessage = "Sorry. The file you are looking for does not exist";
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
            outputStream.close();
            throw new FileNotFoundException();
        }

        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }

        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
        response.setContentLength((int) file.length());
        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        mediaService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}