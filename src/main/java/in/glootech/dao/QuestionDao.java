package in.glootech.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.glootech.entity.Question;

public interface QuestionDao extends JpaRepository<Question, Integer>{

	List<Question> findByCategory(String category);

    @Query(value = "select * from Question where category=:category order by rand() limit :numQ",nativeQuery = true)
	List<Question> findRandomQuestionsByCategory(String category, Integer numQ);
	
}
