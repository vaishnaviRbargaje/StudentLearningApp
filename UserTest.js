import React, { useEffect, useState } from 'react';
import axios from 'axios';

const UserTest = () => {
  const [userId, setUserId] = useState(null);
  const [tests, setTests] = useState([]);
  const [selectedTest, setSelectedTest] = useState(null);
  const [answers, setAnswers] = useState({});
  const [result, setResult] = useState(null);
  const [showResultPopup, setShowResultPopup] = useState(false);

  useEffect(() => {
    const user = JSON.parse(localStorage.getItem('user'));
    if (user?.id) {
      setUserId(user.id);
    } else {
      console.error('User ID not found in localStorage!');
    }
    fetchAllTests();
  }, []);

  const fetchAllTests = async () => {
    try {
      const res = await axios.get('http://localhost:8080/api/users/test/getTest');
      setTests(res.data || []);
    } catch (err) {
      console.error('Error fetching tests:', err);
    }
  };

  const handleTestSelect = (test) => {
    setSelectedTest(test);
    setAnswers({});
    setResult(null);
    setShowResultPopup(false);
  };

  const handleAnswerChange = (questionId, selectedOptionKey, question) => {
    const selectedOptionValue = question[`option${selectedOptionKey}`];
    setAnswers(prev => ({ ...prev, [questionId]: selectedOptionValue }));
  };

  const submitTest = async () => {
    if (!userId || !selectedTest?.id) {
      console.error("Missing test ID or user ID");
      return;
    }

    const payload = {
      testId: selectedTest.id,
      userId: userId,
      answers: answers
    };

    try {
      const res = await axios.post(
        `http://localhost:8080/api/users/test/submit`,
        payload,
        { headers: { 'Content-Type': 'application/json' } }
      );

      setResult(res.data);
      setShowResultPopup(true);
    } catch (err) {
      console.error('Test submission failed:', err);
      alert("Failed to submit the test. Please try again.");
    }
  };

  const renderOptions = (q) => {
    return ['A', 'B', 'C', 'D'].map(opt => {
      const selectedValue = answers[q.id];
      const actualOptionValue = q[`option${opt}`];
      const isSelected = selectedValue === actualOptionValue;

      const qResult = result?.questionResults?.find(r => r.questionId === q.id);
      const isCorrect = qResult?.correctAnswer === actualOptionValue;
      const isUserCorrect = result && isSelected && isCorrect;
      const isUserWrong = result && isSelected && !isCorrect;

      return (
        <div
          className={`form-check ${isUserCorrect ? 'text-success' : ''} ${isUserWrong ? 'text-danger' : ''}`}
          key={opt}
        >
          <input
            className="form-check-input"
            type="radio"
            name={`q-${q.id}`}
            value={actualOptionValue}
            checked={isSelected}
            onChange={() => handleAnswerChange(q.id, opt, q)}
            id={`q-${q.id}-${opt}`}
            disabled={!!result}
          />
          <label className="form-check-label" htmlFor={`q-${q.id}-${opt}`}>
            {opt}. {actualOptionValue}
            {result && isCorrect && (
              <span className="ms-2 badge bg-success">Correct</span>
            )}
          </label>
        </div>
      );
    });
  };

  return (
    <div className="container py-4">
      <h2 className="text-center mt-5 mb-4">📘 Available Tests</h2>

      <div className="d-flex flex-wrap gap-2 mb-4 justify-content-center">
        {tests.map(test => (
          <button
            key={test.id}
            onClick={() => handleTestSelect(test)}
            className="btn btn-outline-primary"
          >
            {test.title}
          </button>
        ))}
      </div>

      {selectedTest && (
        <div className="card shadow p-4 mb-5">
          <h4 className="mb-3">{selectedTest.title}</h4>
          <p className="text-muted">{selectedTest.description}</p>

          {selectedTest.questions.map((q, idx) => (
            <div className="mb-4" key={q.id}>
              <strong>{idx + 1}. {q.question}</strong>
              <div className="ms-3 mt-2">{renderOptions(q)}</div>
            </div>
          ))}

          {!result && (
            <div className="text-center">
              <button
                onClick={submitTest}
                className="btn btn-success mt-3"
              >
                Submit Test
              </button>
            </div>
          )}
        </div>
      )}

      {showResultPopup && result && (
        <div className="modal show d-block" tabIndex="-1" style={{ backgroundColor: "rgba(0,0,0,0.5)" }}>
          <div className="modal-dialog modal-dialog-centered">
            <div className="modal-content">
              <div className="modal-header">
                <h5 className="modal-title">🎉 Test Completed</h5>
                <button type="button" className="btn-close" onClick={() => setShowResultPopup(false)}></button>
              </div>
              <div className="modal-body text-center">
                <h4 className="mb-2 text-primary">
                  🎯 Score: {result.score ?? 0} / {result.totalQuestion ?? selectedTest?.questions?.length ?? 0}
                </h4>
                <p>Total Questions: {result.totalQuestion ?? selectedTest?.questions?.length ?? 0}</p>
                <p>Correct Answers: {result.correctQuestion ?? 0}</p>
                <p className={`fw-bold ${
                  result.score >= (result.totalQuestion / 2)
                    ? 'text-success'
                    : result.score >= (result.totalQuestion / 4)
                    ? 'text-warning'
                    : 'text-danger'
                }`}>
                  {result.feedback ?? "No feedback"}
                </p>
              </div>
              <div className="modal-footer">
                <button className="btn btn-secondary" onClick={() => setShowResultPopup(false)}>
                  Close
                </button>
              </div>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default UserTest;
