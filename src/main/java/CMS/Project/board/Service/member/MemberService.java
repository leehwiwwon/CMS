package CMS.Project.board.Service.member;

import CMS.Project.board.dto.member.MemberFormDTO;
import CMS.Project.board.dto.member.MemberPasswordUpdateDTO;
import CMS.Project.board.dto.member.MemberResponseDTO;
import CMS.Project.board.dto.member.MemberUsernameUpdateDTO;

import java.util.List;

public interface MemberService {
    List<MemberResponseDTO> findMembers();
    MemberResponseDTO findMember(String email);
    Long updateMemberUsername(MemberUsernameUpdateDTO memberUsernameUpdateDTO);
    Long updateMemberPassword(MemberPasswordUpdateDTO memberPasswordUpdateDTO, String email);
    boolean withdrawal(String email, String password);
}
