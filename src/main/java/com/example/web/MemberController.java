package com.example.web;

import com.example.domain.Member;
import com.example.service.MemberService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


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


    @GetMapping("/check-duplicate")
    public ResponseEntity<?> checkDuplicateId(@RequestParam("loginId") String loginId) {
        boolean isDuplicate = memberService.checkForDuplicateId(loginId);
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
    @PostMapping("/validate-login-id")
    public ResponseEntity<?> validateLoginId(@RequestParam String loginId) {
        boolean exists = memberService.doesLoginIdExist(loginId);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"exists\":" + exists + "}");
    }

    @GetMapping("/api/members/all")
    public ResponseEntity<List<Member>> listAllMembers() {
        List<Member> members = memberService.findAllMembers();
        return ResponseEntity.ok().body(members); // 회원 목록을 JSON 형태로 반환
    }

    @PutMapping("/api/members/updateByLoginId/{loginId}")
    public ResponseEntity<?> updateMember(@PathVariable String loginId, @RequestBody Member updatedMemberInfo) {
        // loginId를 통해 회원정보를 찾아서 수정
        try {
            Member member = memberService.updateMemberByLoginId(loginId, updatedMemberInfo);
            return ResponseEntity.ok().body(member);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"message\":\"" + e.getMessage() + "\"}");
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("{\"message\":\"회원정보 수정 실패: 서버 오류\"}");
        }
    }

    @GetMapping("/api/members/getByLoginId/{loginId}")
    public ResponseEntity<Optional<Member>> getMemberByLoginId(@PathVariable String loginId) {
        Optional<Member> member = memberService.findByLoginId(loginId);
        if (member.isPresent()) {
            return ResponseEntity.ok(member);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @DeleteMapping("/api/members/deleteByLoginId/{loginId}")
    public ResponseEntity<?> deleteMemberByLoginId(@PathVariable String loginId) {
        try {
            memberService.deleteMemberByLoginId(loginId);
            return ResponseEntity.ok().body("{\"message\":\"회원정보 삭제 성공\"}");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"message\":\"" + e.getMessage() + "\"}");
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("{\"message\":\"회원정보 삭제 실패: 서버 오류\"}");
        }
    }


}
