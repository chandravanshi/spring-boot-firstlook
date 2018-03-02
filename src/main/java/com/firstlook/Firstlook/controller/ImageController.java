package com.firstlook.Firstlook.controller;

import com.firstlook.Firstlook.model.*;
import com.firstlook.Firstlook.repository.FemaleActiveImageRepository;
import com.firstlook.Firstlook.repository.MaleActiveImageRepository;
import com.firstlook.Firstlook.repository.UserImageRepository;
import com.firstlook.Firstlook.repository.UserRepository;
import com.firstlook.Firstlook.service.ImageSelectionService;
import com.firstlook.Firstlook.utility.Gender;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class ImageController {
    private static String UPLOAD_FOLDER = "C:\\Users\\navcha\\spring-boot-firstlook\\src\\main\\resources\\images\\" ;
    @Autowired
    private UserImageRepository imageRepository;
    @Autowired
    private MaleActiveImageRepository maleActiveImageRepository;
    @Autowired
    private FemaleActiveImageRepository femaleActiveImageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ImageSelectionService imageSelectionService;

    @PostMapping("/upload")
    public @ResponseBody String uploadImage(@RequestParam("file")MultipartFile file, boolean currentImage, RedirectAttributes redirectAttributes) {
        if(file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return redirectAttributes.getFlashAttributes().get("message").toString();
        }
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_FOLDER+ file.getOriginalFilename());
            Files.write(path, bytes);
            User user = new User();
            user.setName("new user");
            userRepository.save(user);
            UserImage userImage = new UserImage();
            userImage.setUser(user);
            userImage.setUrl("/images/" + file.getOriginalFilename());
            imageRepository.save(userImage);
            if(currentImage) {
                if(user.isGender()) {
                    MaleActiveImage activeImage = new MaleActiveImage();
                    activeImage.setUserImage(userImage);
                    maleActiveImageRepository.save(activeImage);
                } else  {
                    FemaleActiveImage femaleActiveImage = new FemaleActiveImage();
                    femaleActiveImage.setUserImage(userImage);
                    femaleActiveImageRepository.save(femaleActiveImage);
                }

            }
            redirectAttributes.addFlashAttribute("message", "You successfully uploaded '" + file.getOriginalFilename() + "'");



        } catch(IOException exception) {
            exception.printStackTrace();
        }

        return redirectAttributes.getFlashAttributes().get("message").toString();
    }

    @GetMapping(path = "/fetchActiveImages")
    public @ResponseBody ResponseEntity<List<ActiveImage>> fetchActiveImages(@RequestParam Gender gender) throws IOException {
        List<ActiveImage> activeImages = imageSelectionService.getImagesBasedOnCriteria(gender);

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(activeImages);
    }

    @GetMapping(path = "getImage")
    public @ResponseBody ResponseEntity getImage(@RequestParam String url) throws IOException {
        ClassPathResource resource = new ClassPathResource(url);
        byte [] bytes = StreamUtils.copyToByteArray(resource.getInputStream());

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }
}
