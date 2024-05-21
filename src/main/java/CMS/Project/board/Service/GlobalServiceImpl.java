package CMS.Project.board.Service;
import CMS.Project.board.dto.member.MemberSaveRequestDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import CMS.Project.board.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import CMS.Project.board.Member;
import CMS.Project.board.Role;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GlobalServiceImpl implements GlobalService{

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional()
    @Override
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        /* 유효성 및 중복 검사에 실패한 필드 목록을 받음 */
        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }

        return validatorResult;
    }

    @Override
    public void messageHandling(Errors errors, Model model) {
        Map<String, String> validatorResult = validateHandling(errors);
        for (String key : validatorResult.keySet()) {
            model.addAttribute(key, validatorResult.get(key));
        }
    }


    @Override
    @Transactional
    public Long join(MemberSaveRequestDTO memberSaveRequestDTO) {
        memberSaveRequestDTO.setPassword(passwordEncoder.encode(memberSaveRequestDTO.getPassword()));

        Member member = Member.builder()
                .email(memberSaveRequestDTO.getEmail())
                .username(memberSaveRequestDTO.getUsername())
                .password(memberSaveRequestDTO.getPassword())
                .role(Role.ROLE_USER)
                .build();


        Long id = memberRepository.save(member).getId();

        return id;
    }
}