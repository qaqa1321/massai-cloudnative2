package zzyzzy.spring.massai_mara_park.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import zzyzzy.spring.massai_mara_park.model.Animal;
import zzyzzy.spring.massai_mara_park.repository.AnimalRepository;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;

    // 생성자 주입: 의존성이 final로 고정되고, 테스트에서 가짜 객체를 넣기 쉽습니다.
    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<Animal> findAll() {
        return animalRepository.findAll();
    }

    public Animal findById(Long id) {
        return animalRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "animal not found: " + id));
    }

    // 행 개수를 하드코딩하지 않고, 실제 데이터에서 무작위로 고릅니다.
    public Animal findRandom() {
        List<Animal> all = animalRepository.findAll();
        if (all.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "no animals in database");
        }
        return all.get(ThreadLocalRandom.current().nextInt(all.size()));
    }

    @Transactional
    public void increaseView(Long id) {
        if (animalRepository.increaseViewCount(id) == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "animal not found: " + id);
        }
    }
}
