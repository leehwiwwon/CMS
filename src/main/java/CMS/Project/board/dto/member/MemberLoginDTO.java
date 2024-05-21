package CMS.Project.board.dto.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginDTO {
    @NotBlank(message = "이메일을 입력하세요")
    @Email(message = "올바른 이메일 주소를 입력하세요")
    private String email;

    @NotBlank
    private String password;
}
