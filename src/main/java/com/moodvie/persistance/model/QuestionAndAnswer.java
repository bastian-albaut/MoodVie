package com.moodvie.persistance.model;

public class QuestionAndAnswer {
    private String question;
    private String answer;

    public QuestionAndAnswer(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    // Getters et setters

    /**
     * @return the question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * @param question the question to set
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * @return the answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * @param answer the answer to set
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
