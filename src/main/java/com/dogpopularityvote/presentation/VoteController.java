package com.dogpopularityvote.presentation;

import com.dogpopularityvote.application.VoteProducerService;
import com.dogpopularityvote.dto.request.DogVoteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/api/votes")
@RequiredArgsConstructor
@RestController
public class VoteController {

    private final VoteProducerService voteProducerService;

    @PostMapping
    public ResponseEntity<Void> sendVote(@RequestBody DogVoteRequest request) {
        request.setClientIp(getClientIp());
        voteProducerService.sendVote(request);
        return ResponseEntity.noContent().build();
    }

    private String getClientIp() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();

        String ip = request.getHeader("X-FORWARDED-FOR");
        if (ip == null) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
