package in.glootech.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import in.glootech.entity.Quiz;

public interface QuizDao extends JpaRepository<Quiz, Integer>{
}
