package CMS.Project.board.dto.image;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageUploadDTO {

    private MultipartFile file;
}