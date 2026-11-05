package zzyzzy.spring.massai_mara_park.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import zzyzzy.spring.massai_mara_park.model.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

    // 조회 수 +1 : 변경된 행 수를 반환합니다 (0이면 해당 id가 없다는 뜻)
    @Modifying
    @Query("update Animal a set a.viewCount = a.viewCount + 1 where a.id = :id")
    int increaseViewCount(@Param("id") Long id);
}
