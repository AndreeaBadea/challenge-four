package com.playtika.repositories;

import com.playtika.models.SpaceInvader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpaceInvaderRepository extends JpaRepository<SpaceInvader, Long> {
}
