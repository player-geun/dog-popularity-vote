package com.dogpopularityvote.presentation;

import com.dogpopularityvote.application.DogProducerService;
import com.dogpopularityvote.application.DogService;
import com.dogpopularityvote.dto.request.DogVoteRequest;
import com.dogpopularityvote.dto.response.DogDetailResponse;
import com.dogpopularityvote.dto.response.DogInfiniteScrollResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/dogs")
@RequiredArgsConstructor
@RestController
public class DogController {

    private final DogService dogService;
    private final DogProducerService dogProducerService;

    @GetMapping
    @Cacheable(cacheNames = "dogs")
    public DogInfiniteScrollResponse findAllWithPage(@PageableDefault(sort = "updatedAt",
            direction = Sort.Direction.DESC, size = 5) Pageable pageable) {
        return dogService.findAllWithPage(pageable);
    }

    @GetMapping("/{id}/details")
    @Cacheable(cacheNames = "dog", key = "#id")
    public DogDetailResponse findDetailById(@PathVariable Long id) {
        return dogService.findDetailById(id);
    }

    @PostMapping("/votes")
    public ResponseEntity<Void> sendVote(@RequestBody DogVoteRequest request) {
        dogProducerService.sendVote(request);
        return ResponseEntity.noContent().build();
    }
}
