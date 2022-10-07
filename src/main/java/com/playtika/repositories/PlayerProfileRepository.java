package com.playtika.repositories;

import com.playtika.models.PlayerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerProfileRepository extends JpaRepository<PlayerProfile, Long> {

    @Query("SELECT p FROM players p WHERE p.gameSessions.size > :min AND p.gameSessions.size < :max")
    List<PlayerProfile> findAllByNumberOfPlayedGamesBetween(@Param("min") int min, @Param("max") int max);

    @Query("SELECT p FROM players p WHERE p.isNewsletterSubscriber = TRUE ORDER BY p.userName DESC")
    List<PlayerProfile> findAllRegisteredForNewsletterOrderByNameDesc();

    @Query("SELECT p FROM players p WHERE p.gameSessions.size > :limit")
    List<PlayerProfile> findAllByNumberOfPlayedGamesIsLessThan(@Param("limit") int limit);


    List<PlayerProfile> findAllByOrderByUserNameAsc();

    @Query("SELECT p FROM players p ORDER BY p.gameSessions.size DESC")
    List<PlayerProfile> findAllByNumberOfPlayedGamesDesc();

}
