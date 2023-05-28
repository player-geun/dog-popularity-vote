package com.dogpopularityvote.application;

import com.dogpopularityvote.domain.Dog;
import com.dogpopularityvote.domain.DogRepository;
import com.dogpopularityvote.dto.response.DogDetailResponse;
import com.dogpopularityvote.dto.response.DogInfiniteScrollResponse;
import com.dogpopularityvote.dto.response.DogSimpleResponse;
import com.dogpopularityvote.exception.NoSuchDogImageException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
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

        List<DogSimpleResponse> responses = dogs.getContent().stream()
                .map(DogSimpleResponse::new)
                .collect(Collectors.toList());
        return new DogInfiniteScrollResponse(responses, dogs.hasNext(), dogs.getNumber());
    }

    public DogDetailResponse findDetailById(Long id) {
        Dog dog = dogRepository.getById(id);
        return new DogDetailResponse(dog);
    }

    public Resource findImageById(Long id) {
        Dog dog = dogRepository.getById(id);
        String fullPath = dog.getImage().makeFullPath();

        Resource resource = new FileSystemResource(fullPath);
        if(!resource.exists()){
            throw new NoSuchDogImageException();
        }
        return resource;
    }
}
