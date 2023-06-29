package com.donghaeng.dev.controller;

import com.donghaeng.dev.domain.Apply;
import com.donghaeng.dev.domain.Crew;
import com.donghaeng.dev.domain.User;
import com.donghaeng.dev.dto.ApplyRequestDto;
import com.donghaeng.dev.dto.ApplyResDto;
import com.donghaeng.dev.repository.CrewRepository;
import com.donghaeng.dev.service.ApplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/apply")
@RequiredArgsConstructor
public class ApplyController {

    private final ApplyService applyService;

    @PostMapping("/{crewId}")
    public ResponseEntity<?> apply(@PathVariable Long crewId, @RequestBody ApplyRequestDto applyRequestDto, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<String> answers = applyRequestDto.getAnswers();
        return applyService.apply(user, crewId, answers);
    }
    @GetMapping("/{crewId}/applicants")
    public ResponseEntity<List<ApplyResDto>> getApplicants(@PathVariable Long crewId) {
        List<ApplyResDto> applicants = applyService.getApplicantsByCrew(crewId);
        return ResponseEntity.ok(applicants);
    }


}
