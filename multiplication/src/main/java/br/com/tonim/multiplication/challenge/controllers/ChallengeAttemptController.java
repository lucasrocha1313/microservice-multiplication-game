package br.com.tonim.multiplication.challenge.controllers;

import br.com.tonim.multiplication.challenge.DTOs.ChallengeAttemptDTO;
import br.com.tonim.multiplication.challenge.domain.ChallengeAttempt;
import br.com.tonim.multiplication.challenge.services.ChallengeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/attempts")
public class ChallengeAttemptController {
    private final ChallengeService challengeService;

    public ChallengeAttemptController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @PostMapping
    ResponseEntity<ChallengeAttempt> postResult(@RequestBody @Valid ChallengeAttemptDTO
                                                        challengeAttemptDTO) {
        return ResponseEntity.ok(challengeService.verifyAttempt
                (challengeAttemptDTO));
    }

    @GetMapping
    ResponseEntity<List<ChallengeAttempt>> getStatistics(@RequestParam("alias") String alias) {
        return ResponseEntity.ok(challengeService.getStatsForUser(alias));
    }
}
