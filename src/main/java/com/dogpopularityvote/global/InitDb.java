package com.dogpopularityvote.global;

import com.dogpopularityvote.domain.Dog;
import com.dogpopularityvote.domain.DogImage;
import com.dogpopularityvote.domain.DogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final DogRepository dogRepository;
        public void dbInit() {
            for (int i = 1; i <= 20; i++) {
                dogRepository.save(new Dog("강아지" + i,
                        String.valueOf(i), new DogImage(String.valueOf(i), String.valueOf(i)), i));
            }
        }
    }
}
