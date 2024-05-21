package CMS.Project.board.controller;

import CMS.Project.board.Service.member.MemberService;
import CMS.Project.board.dto.member.MemberPasswordUpdateDTO;
import CMS.Project.board.dto.member.MemberResponseDTO;
import CMS.Project.board.dto.member.MemberUsernameUpdateDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;


    @GetMapping("/list")
    public String members(Model model) {
        List<MemberResponseDTO> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "/members/memberList";
    }


    @GetMapping("/info")
    public String memberInfo(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        MemberResponseDTO member = memberService.findMember(userDetails.getUsername());

        model.addAttribute("member", member);

        return "/members/info";
    }

    @GetMapping("/update/username")
    public String updateUsernameForm(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        MemberResponseDTO member = memberService.findMember(userDetails.getUsername());
        model.addAttribute("member", member);

        return "/members/updateUsername";
    }

    @PostMapping("/update/username")
    public String updateUsername(@Valid MemberUsernameUpdateDTO memberUsernameUpdateDTO, Errors errors, Model model, Authentication authentication) {
        if (errors.hasErrors()) {
            model.addAttribute("member", memberUsernameUpdateDTO);
            return "/members/updateUsername";
        }

        memberService.updateMemberUsername(memberUsernameUpdateDTO);

        return "redirect:/members/info";
    }

    @GetMapping("/update/password")
    public String updatePasswordForm() {
        return "/members/updatePassword";
    }

    @PostMapping("/update/password")
    public String updatePassword(@Valid MemberPasswordUpdateDTO memberPasswordUpdateDTO, Model model, Authentication authentication) {
        // new password 비교
        if (!Objects.equals(memberPasswordUpdateDTO.getNewPassword(), memberPasswordUpdateDTO.getConfirmPassword())) {
            model.addAttribute("dto", memberPasswordUpdateDTO);
            model.addAttribute("differentPassword", "비밀번호가 같지 않습니다.");
            return "/members/updatePassword";
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Long result = memberService.updateMemberPassword(memberPasswordUpdateDTO, userDetails.getUsername());

        // 현재 비밀번호가 틀렸을 경우
        if (result == null) {
            model.addAttribute("dto", memberPasswordUpdateDTO);
            model.addAttribute("wrongPassword", "비밀번호가 맞지 않습니다.");
            return "/members/updatePassword";
        }

        return "redirect:/members/info";
    }

    @GetMapping("/withdrawal")
    public String memberWithdrawalForm() {
        return "/members/withdrawal";
    }

    @PostMapping("/withdrawal")
    public String memberWithdrawal(@RequestParam String password, Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        boolean result = memberService.withdrawal(userDetails.getUsername(), password);

        if (result) {
            return "redirect:/logout";
        } else {
            model.addAttribute("wrongPassword", "비밀번호가 맞지 않습니다.");
            return "/members/withdrawal";
        }
    }
}