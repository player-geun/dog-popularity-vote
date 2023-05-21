package com.dogpopularityvote.presentation;

import com.dogpopularityvote.application.DogService;
import com.dogpopularityvote.dto.response.DogInfiniteScrollResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/dogs")
@RequiredArgsConstructor
@RestController
public class DogController {

    private final DogService dogService;

    @GetMapping
    @Cacheable(cacheNames = "dogs")
    public DogInfiniteScrollResponse findAllWithPage(@PageableDefault(sort = "updatedAt",
            direction = Sort.Direction.DESC, size = 5) Pageable pageable) {
        return dogService.findAllWithPage(pageable);
    }
}
