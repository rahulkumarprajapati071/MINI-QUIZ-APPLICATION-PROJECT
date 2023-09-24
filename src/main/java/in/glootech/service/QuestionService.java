package in.glootech.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import in.glootech.dao.QuestionDao;
import in.glootech.entity.Question;

@Service
public class QuestionService {
	
	@Autowired
	private QuestionDao questionDao;

	public ResponseEntity<String> addQuestions(List<Question> question) {
		questionDao.saveAll(question);
		return new ResponseEntity<String>("Saved Succesfully",HttpStatus.CREATED);
	}
	
	public ResponseEntity<List<Question>> getAllQuestions(){
		try {
			return new ResponseEntity<>(questionDao.findAll(),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>() ,HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> addQuestion(Question question) {
		questionDao.save(question);
		return new ResponseEntity<String>("Saved Succesfully",HttpStatus.CREATED);
	}

	public ResponseEntity<List<Question>> getByCategory(String category) {
		try {
			return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>() ,HttpStatus.BAD_REQUEST);
	}
	
	
}
