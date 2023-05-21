package com.dogpopularityvote.application;

import com.dogpopularityvote.domain.Dog;
import com.dogpopularityvote.domain.DogRepository;
import com.dogpopularityvote.dto.response.DogInfiniteScrollResponse;
import com.dogpopularityvote.dto.response.DogResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class DogService {

    private final DogRepository dogRepository;

    public DogInfiniteScrollResponse findAllWithPage(Pageable pageable) {
        Slice<Dog> dogs = dogRepository.findAllBy(pageable);

        List<DogResponse> responses = dogs.getContent().stream()
                .map(DogResponse::new)
                .collect(Collectors.toList());
        return new DogInfiniteScrollResponse(responses, dogs.hasNext(), dogs.getNumber());
    }
}
