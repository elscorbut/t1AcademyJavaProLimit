package ru.academy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.academy.domain.Limit;

import java.util.Optional;

@Repository
public interface LimitRepository extends JpaRepository<Limit, Long> {

    Optional<Limit> findByUserId(Long userId);

    @Modifying
    @Query("UPDATE Limit l SET l.value = :defaultLimit")
    void resetAllLimits(double defaultLimit);
}
