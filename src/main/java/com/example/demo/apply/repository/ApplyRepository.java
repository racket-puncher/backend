package com.example.demo.apply.repository;

import com.example.demo.entity.Apply;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Matching;
import com.example.demo.entity.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplyRepository extends JpaRepository<Apply, Long> {
    boolean existsBySiteUser_IdAndMatching_Id(long userId, long matchId);

    Optional<Apply> findBySiteUser_IdAndMatching_Id(long userId, long matchId);

    List<Apply> findApplyBySiteUser(SiteUser siteUser);
}
