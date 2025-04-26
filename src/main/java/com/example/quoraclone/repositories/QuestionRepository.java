package com.example.quoraclone.repositories;

import com.example.quoraclone.models.Question;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {

  @Query("SELECT q FROM Question q JOIN q.tags t WHERE t.id IN :tagIds")
  Page<Question> findQuestionByTags(Set<Long> tagIds, Pageable pageable);
}
