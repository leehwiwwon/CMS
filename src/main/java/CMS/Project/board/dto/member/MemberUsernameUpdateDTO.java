package CMS.Project.board.dto.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberUsernameUpdateDTO {
    @NotBlank(message = "이메일을 입력하세요")
    @Email(message = "올바른 이메일 주소 형식으로 입력하세요")
    private String email;

    @NotBlank(message = "이름을 입력하세요")
    @Size(min = 2, max = 15)
    private String username;

}
