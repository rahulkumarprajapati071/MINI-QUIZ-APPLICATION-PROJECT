package in.glootech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.glootech.entity.Question;
import in.glootech.service.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestionsController {
	
	@Autowired
	private QuestionService questionService;
	
	@GetMapping("/allQuestions")
	public ResponseEntity<List<Question>> getAllQuestion() {
		return (ResponseEntity<List<Question>>) questionService.getAllQuestions();
	}
	
	@GetMapping("/category/{category}")
	public ResponseEntity<List<Question>> getByCategory(@PathVariable String category) {
		return (ResponseEntity<List<Question>>) questionService.getByCategory(category);
	}
	
	@PostMapping("/addQuestions")
	public ResponseEntity<String> addQuestions(@RequestBody List<Question> question) {
		return questionService.addQuestions(question);
	}

	
	@PostMapping("/addQuestion")
	public ResponseEntity<String> addQuestion(@RequestBody Question question) {
		return questionService.addQuestion(question);
	}
}
