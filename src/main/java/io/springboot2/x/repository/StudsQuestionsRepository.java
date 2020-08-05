package io.springboot2.x.repository;

import io.springboot2.x.domain.StudsQuestions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



import java.util.List;

import javax.transaction.Transactional;

@Repository
public interface StudsQuestionsRepository extends JpaRepository<StudsQuestions,Long> {

@Transactional
@Modifying
    @Query(value="UPDATE StudsQuestions SET type=:type WHERE questionId=:questionId")
    public Integer updateQuestions(@Param("questionId")long questionId,@Param("type")String type);

@Query("select n from StudsQuestions n order by RAND()")
public List<StudsQuestions> findRandamQuestions();

}
  