package com.example.web;

import com.example.domain.Member;
import com.example.service.MemberService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class MemberController {

    private final MemberService memberService;


    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signin(@RequestBody Member member) {
        try {
            Member foundMember = memberService.signin(member.getLoginId(), member.getMbPw());
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"message\":\"로그인 성공\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"message\":\"로그인 실패: 아이디 또는 비밀번호가 일치하지 않습니다.\"}");
        }
    }


    @PostMapping("/check-duplicate")
    public ResponseEntity<?> checkDuplicateId(@RequestBody Map<String, String> payload) {
        String id = payload.get("id");
        boolean isDuplicate = memberService.checkForDuplicateId(id);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"isDuplicate\":" + isDuplicate + "}");
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody Member newMember) {
        try {
            Member registered = memberService.join(newMember);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"message\":\"회원가입 성공\"}");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"message\":\"회원가입 실패: 중복된 사용자 또는 유효하지 않은 데이터\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"message\":\"회원가입 실패: 서버 오류\"}");
        }
    }
    @GetMapping("/validate-login-id")
    public ResponseEntity<?> validateLoginId(@RequestParam String loginId) {
        boolean exists = memberService.doesLoginIdExist(loginId);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"exists\":" + exists + "}");
    }
}
