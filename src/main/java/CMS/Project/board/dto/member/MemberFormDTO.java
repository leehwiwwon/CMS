package CMS.Project.board.dto.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberFormDTO {
    private String email;
    private String username;
    private String password;
}
