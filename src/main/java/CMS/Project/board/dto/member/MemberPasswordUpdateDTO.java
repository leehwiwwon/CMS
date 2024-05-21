package CMS.Project.board.dto.member;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberPasswordUpdateDTO {

    @NotBlank(message = "기존 비밀번호를 입력하세요")
    private String CurrentPassword;

    @NotBlank(message = "새 비밀번호를 입력하세요")
    private String newPassword;

    @NotBlank(message = "새 비밀번호를 재입력 하세요")
    private String confirmPassword;
}
