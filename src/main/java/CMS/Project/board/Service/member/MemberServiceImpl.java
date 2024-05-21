package CMS.Project.board.Service.member;

import CMS.Project.board.Member;
import CMS.Project.board.repository.MemberRepository;
import CMS.Project.board.dto.member.MemberFormDTO;
import CMS.Project.board.dto.member.MemberPasswordUpdateDTO;
import CMS.Project.board.dto.member.MemberResponseDTO;
import CMS.Project.board.dto.member.MemberUsernameUpdateDTO;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.lang.Long;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<MemberResponseDTO> findMembers() {
        List<Member> all = memberRepository.findAll();
        List<MemberResponseDTO> members = new ArrayList<>();

        for (Member member: all) {
            MemberResponseDTO build = MemberResponseDTO.builder()
                    .member(member)
                    .build();
            members.add(build);
        }

        return members;
    }

    @Override
    public MemberResponseDTO findMember(String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("이메일이 존재하지 않습니다."));

        MemberResponseDTO result = MemberResponseDTO.builder()
                .member(member)
                .build();

        return result;
    }

    @Override
    public Long updateMemberUsername(MemberUsernameUpdateDTO memberUsernameUpdateDTO) {
        Member member = memberRepository.findByEmail(memberUsernameUpdateDTO.getEmail()).orElseThrow(() -> new UsernameNotFoundException("이메일이 존재하지 않습니다."));

        member.updateUsername(memberUsernameUpdateDTO.getUsername());
        memberRepository.save(member);

        return member.getId();
    }

    @Override
    public Long updateMemberPassword(MemberPasswordUpdateDTO memberPasswordUpdateDTO, String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("이메일이 존재하지 않습니다."));

        if (!passwordEncoder.matches(memberPasswordUpdateDTO.getCurrentPassword(), member.getPassword())) {
            return null;
        } else {
            memberPasswordUpdateDTO.setNewPassword(passwordEncoder.encode(memberPasswordUpdateDTO.getNewPassword()));
            member.updatePassword(memberPasswordUpdateDTO.getNewPassword());
            memberRepository.save(member);
        }

        return member.getId();
    }

    @Override
    public boolean withdrawal(String email, String password) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("이메일이 존재하지 않습니다."));

        if (passwordEncoder.matches(password, member.getPassword())) {
            memberRepository.delete(member);
            return true;
        } else {
            return false;
        }
    }
}