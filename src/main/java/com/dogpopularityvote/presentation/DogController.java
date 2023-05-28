package com.dogpopularityvote.presentation;

import com.dogpopularityvote.application.DogService;
import com.dogpopularityvote.dto.response.DogDetailResponse;
import com.dogpopularityvote.dto.response.DogInfiniteScrollResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/dogs")
@RequiredArgsConstructor
@RestController
public class DogController {

    private final DogService dogService;

    @GetMapping
    @Cacheable(cacheNames = "dogs", cacheManager = "userCacheManager")
    public DogInfiniteScrollResponse findAllWithPage(@PageableDefault(sort = "updatedAt",
            direction = Sort.Direction.DESC, size = 5) Pageable pageable) {
        return dogService.findAllWithPage(pageable);
    }

    @GetMapping("/{id}/details")
    @Cacheable(cacheNames = "dog", key = "#id", cacheManager = "userCacheManager")
    public DogDetailResponse findDetailById(@PathVariable Long id) {
        return dogService.findDetailById(id);
    }

    @GetMapping("/{id}/images")
    public ResponseEntity<Resource> findImageById(@PathVariable Long id) {
        Resource resource = dogService.findImageById(id);
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "image/jpeg");
        return new ResponseEntity<>(resource, header, HttpStatus.OK);
    }
}
