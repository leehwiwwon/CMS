package CMS.Project.board.controller;

import CMS.Project.board.dto.image.ImageUploadDTO;
import CMS.Project.board.Service.image.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/upload")
    public String upload(@ModelAttribute ImageUploadDTO imageUploadDTO, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        imageService.upload(imageUploadDTO, userDetails.getUsername());

        return "redirect:/member/info";
    }
}