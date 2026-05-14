package com.my.member_app.controller;

import com.my.member_app.dto.MemberDto;
import com.my.member_app.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;


    @GetMapping("/view")
    public String showAllMember(Model model){
        List<MemberDto> dtoList = memberService.findAll();
        model.addAttribute("title","회원정보");
        model.addAttribute("lists", "dtoList");
         return "showMember";

    }

    @GetMapping("/insertForm")
    public String insertForm(Model model) {
    //모델에 빈 Dto를 생성해서 보낸다.
        model.addAttribute("dto", new MemberDto());
        return "insertMember";


    }
    @PostMapping("/insert")
    //리다이 렉트용 1회성 모델 에 담아 보내는 기능  :RedirectAttributes
    public String insertmember(@ModelAttribute("dto") MemberDto dto,
                               RedirectAttributes redirectAttributes) {
        //redirect:/member/view 는  /member/view 를 Get 으로 다시호출
        log.info("result : " + dto);
        //insert 서비스를 호출
        memberService.insert(dto);
        //성공 메세지를 RedirectAttributes 에 담아 보낸다.
        redirectAttributes.addFlashAttribute("message","등록이 완료 되었습니다. ");
        return "redirect:/member/view";

    }
    @PostMapping("/delete")
    public String delete( @RequestParam("deleteid")Long deleteid,
            RedirectAttributes redirectAttributes {
    log.info("======== deleteid : " + deleteid);
    memberService.delete(deleteid);
    redirectAttributes.addFlashAttribute(redirectAttributes: "message",
        redirectAttributes "정상적으로 삭제되었습니다.");
    return "redirect:/member/view";

    }

    }
}

