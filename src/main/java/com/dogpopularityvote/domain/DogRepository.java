package com.dogpopularityvote.domain;

import com.dogpopularityvote.exception.NoSuchDogException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {

    Slice<Dog> findAllBy(Pageable pageable);

    default Dog getById(Long id) {
        return findById(id)
                .orElseThrow(NoSuchDogException::new);
    }
}
