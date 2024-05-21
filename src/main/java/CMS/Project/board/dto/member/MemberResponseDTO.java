package CMS.Project.board.dto.member;

import CMS.Project.board.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResponseDTO {
    private String email;
    private String username;

    @Builder
    public MemberResponseDTO(Member member) {
        this.email = member.getEmail();
        this.username = member.getUsername();
    }
}
