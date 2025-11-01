package com.tka.StudentLearningApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tka.StudentLearningApp.Repository.QuestionRepo;
import com.tka.StudentLearningApp.Repository.TestRepo;
import com.tka.StudentLearningApp.Repository.UserRepo;
import com.tka.StudentLearningApp.Repository.UserTestResultRepo;
import com.tka.StudentLearningApp.dto.QuestionDto;
import com.tka.StudentLearningApp.dto.QuestionResultDto;
import com.tka.StudentLearningApp.dto.TestDto;
import com.tka.StudentLearningApp.dto.TestSubmissionDto;
import com.tka.StudentLearningApp.dto.UserTestResultDto;
import com.tka.StudentLearningApp.entity.Question;
import com.tka.StudentLearningApp.entity.Test;
import com.tka.StudentLearningApp.entity.UserTestResult;
import com.tka.StudentLearningApp.entity.Users;

import jakarta.transaction.Transactional;

@Service
public class TestService {
	
	   @Autowired
	    private TestRepo testRepo;

	    @Autowired
	    private QuestionRepo questionRepo;

	    @Autowired
	    private UserRepo repo;

	    @Autowired
	    private UserTestResultRepo userTestResultRepo;

	    @Transactional
	    public List<TestDto> getAllTest() {
	        List<Test> tests = testRepo.findAll();

	        return tests.stream().map(test -> {
	            TestDto dto = new TestDto();
	            dto.setId(test.getId());
	            dto.setTitle(test.getTitle());
	            dto.setDescription(test.getDescription());

	            List<QuestionDto> questionDTOs = test.getQuestions().stream().map(q -> {
	                QuestionDto qdto = new QuestionDto();
	                qdto.setId(q.getId());
	                qdto.setQuestion(q.getQuestion());
	                qdto.setOptionA(q.getOptionA());
	                qdto.setOptionB(q.getOptionB());
	                qdto.setOptionC(q.getOptionC());
	                qdto.setOptionD(q.getOptionD());
	                qdto.setCorrectOption(q.getCorrectOption());
	                return qdto;
	            }).collect(Collectors.toList());

	            dto.setQuestions(questionDTOs);
	            return dto;
	        }).collect(Collectors.toList());
	    }

	    @Transactional
	    public Test addTest(TestDto testDto) {
	        Test test = new Test();
	        test.setTitle(testDto.getTitle());
	        test.setDescription(testDto.getDescription());

	        List<Question> questions = testDto.getQuestions().stream().map(q -> {
	            Question question = new Question();
	            question.setQuestion(q.getQuestion());
	            question.setOptionA(q.getOptionA());
	            question.setOptionB(q.getOptionB());
	            question.setOptionC(q.getOptionC());
	            question.setOptionD(q.getOptionD());
	            question.setCorrectOption(q.getCorrectOption());
	            question.setTest(test); // Link back
	            return question;
	        }).collect(Collectors.toList());

	        test.setQuestions(questions);
	        return testRepo.save(test);
	    }

	    @Transactional
	    public void deleteTest(Long id) {
	        testRepo.deleteById(id);
	    }

	  
	    @Transactional
	    public UserTestResultDto submitTest(TestSubmissionDto testdto) {

	        Long testId = testdto.getTestId();
	        Long userId = testdto.getUserId();
	        Map<Long, String> answers = testdto.getAnswers();

	        Test test = testRepo.findById(testId).orElseThrow(() -> new RuntimeException("Test Not Found"));
	        Users user = repo.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found"));

	        if (!"USERS".equalsIgnoreCase(user.getRole())) {
	            throw new RuntimeException("Only USERS can submit the test");
	        }

	        int total = test.getQuestions().size();
	        if (total == 0) {
	            throw new RuntimeException("Test has no questions.");
	        }

	        int correct = 0;
	        List<QuestionResultDto> questionResults = new ArrayList<>();

	        for (Question q : test.getQuestions()) {
	            String userAnswer = answers.get(q.getId());
	            String correctAnswer = q.getCorrectOption();

	            boolean isCorrect = userAnswer != null && userAnswer.trim().equalsIgnoreCase(correctAnswer.trim());
	            if (isCorrect) correct++;

	            QuestionResultDto qResult = new QuestionResultDto();
	            qResult.setQuestionId(q.getId());
	            qResult.setQuestion(q.getQuestion());
	            qResult.setSelectedAnswer(userAnswer);
	            qResult.setCorrectAnswer(correctAnswer);
	            qResult.setCorrect(isCorrect);

	            questionResults.add(qResult);
	        }

	        int score = correct;
	        int percent = (score * 100) / total;
	        String feedback;
	        if (percent >= 80) feedback = "Excellent!";
	        else if (percent >= 50) feedback = "Good effort!";
	        else feedback = "Needs improvement.";

	        UserTestResult result = new UserTestResult();
	        result.setUser(user);
	        result.setTest(test);
	        result.setTotalQuestion(total);
	        result.setCorrectQuestion(correct);
	        result.setScore(score);
	        result.setFeedback(feedback);

	        userTestResultRepo.save(result);

	        UserTestResultDto dto = new UserTestResultDto();
	        dto.setUserId(user.getId());
	        dto.setUsername(user.getUsername());
	        dto.setTestTitle(test.getTitle());
	        dto.setTotalQuestion(total);
	        dto.setCorrectQuestion(correct);
	        dto.setScore(score);
	        dto.setFeedback(feedback);
	        dto.setQuestionResults(questionResults);

	        return dto;
	    }


	 

	    @Transactional
	    public List<UserTestResultDto> getAllResults() {
	        List<UserTestResult> results = userTestResultRepo.findAll();
	        return results.stream().map(result -> new UserTestResultDto(
	       
	            null, result.getUser().getUsername(),
	            result.getTest().getTitle(),
	            result.getTotalQuestion(),
	            result.getCorrectQuestion(),
	            result.getScore(),
	            result.getFeedback(), null
	        )).collect(Collectors.toList());
	    }
	    
	    
	    @Transactional
	    public List<UserTestResultDto> getResultByUserName(String username) {
	        List<UserTestResult> results = userTestResultRepo.findByUserUsername(username);
	        return results.stream()
	                .map(result -> new UserTestResultDto(
	                    null,
	                    result.getUser().getUsername(),
	                    result.getTest().getTitle(),
	                    result.getTotalQuestion(),
	                    result.getCorrectQuestion(),
	                    result.getScore(),
	                    result.getFeedback(),
	                    null
	                ))
	                .collect(Collectors.toList());
	    }

	}