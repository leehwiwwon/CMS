package CMS.Project.board.Service.image;

import CMS.Project.board.dto.image.ImageResponseDTO;
import CMS.Project.board.dto.image.ImageUploadDTO;

public interface ImageService {

    void upload(ImageUploadDTO imageUploadDTO, String email);

    ImageResponseDTO findImage(String email);
}