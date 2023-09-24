package in.glootech.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import in.glootech.dao.QuestionDao;
import in.glootech.dao.QuizDao;
import in.glootech.entity.Question;
import in.glootech.entity.Quiz;
import in.glootech.wrapper.QuestionWrapper;
import in.glootech.wrapper.Response;

@Service
public class QuizService {
	
	@Autowired
	private QuizDao quizDao;
	@Autowired
	private QuestionDao questionDao;
	

	public ResponseEntity<String> createQuiz(String category, Integer numQ, String title) {
		Quiz quiz = new Quiz();
		quiz.setQuizTitle(title);
		
		List<Question> questions = questionDao.findRandomQuestionsByCategory(category,numQ);
		quiz.setQuestions(questions);
		
		quizDao.save(quiz);
		
		return new ResponseEntity<>("Quiz Created Succefully",HttpStatus.CREATED);
	}


	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		Optional<Quiz> quiz = quizDao.findById(id);
		List<Question> questionFromDB = quiz.get().getQuestions();
		List<QuestionWrapper> questionsForUser = new ArrayList<>();
		
		for(Question question : questionFromDB) {
		    QuestionWrapper questionWrapper = new QuestionWrapper(); // Create a new instance here

		    // Set properties as before
		    questionWrapper.setQuestionId(question.getQuestionId());
		    questionWrapper.setQuestion(question.getQuestion());
		    questionWrapper.setOption1(question.getOption1());
		    questionWrapper.setOption2(question.getOption2());
		    questionWrapper.setOption3(question.getOption3());
		    questionWrapper.setOption4(question.getOption4());

		    questionsForUser.add(questionWrapper);
		}

		
		return new ResponseEntity<>(questionsForUser,HttpStatus.OK);
	}


	public ResponseEntity<Integer> setScore(Integer id, List<Response> responses) {
		Quiz quiz = quizDao.findById(id).get();
		List<Question> questions = quiz.getQuestions();
		Integer right = 0;
		int i = 0;
		for(Response res : responses) {
			if(res.getResponse().equals(questions.get(i).getAnswer())) {
				right++;
			}
			i++;
		}
		return new ResponseEntity<Integer>(right,HttpStatus.OK);
	}

}
