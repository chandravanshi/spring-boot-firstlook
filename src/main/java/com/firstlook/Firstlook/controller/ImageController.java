package com.firstlook.Firstlook.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ImageController {
    private static String UPLOAD_FOLDER = "C://ftemp";

    @PostMapping("/upload")
    public @ResponseBody String uploadImage(@RequestParam("file")MultipartFile file, RedirectAttributes redirectAttributes) {
        if(file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
            redirectAttributes.addFlashAttribute("message", "You successfully uploaded '" + file.getOriginalFilename() + "'");
        } catch(IOException exception) {
            exception.printStackTrace();
        }

        return "redirect:uploadStatus";
    }

    @GetMapping(path = "/uploadStatus")
    public @ResponseBody String uploadStatus() {
        return "uploadStatus";
    }

    @GetMapping(path = "/fetchImage")
    public @ResponseBody ResponseEntity fetchImage() throws IOException {
        ClassPathResource imgFile = new ClassPathResource("images/test1.jpeg");
        byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }
}
