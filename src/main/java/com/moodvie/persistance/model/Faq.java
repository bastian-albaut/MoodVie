package com.moodvie.persistance.model;

import java.util.ArrayList;
import java.util.List;

public class Faq {
    private int faqID;
    private List<QuestionAndAnswer> questionAndAnswer;

    public Faq() {
        this.questionAndAnswer = new ArrayList<>();
    }

    // Getters et Setters

    /**
     * @return the faqID
     */
    public int getFaqID() {
        return faqID;
    }
    
    /**
     * @param faqID the faqID to set
     */
    public void setFaqID(int faqID) {
        this.faqID = faqID;
    }
    
    /**
     * @return the questionAndAnswer
     */
    public List<QuestionAndAnswer> getQuestionAndAnswer() {
        return questionAndAnswer;
    }

    /**
     * @param questionAndAnswer the questionAndAnswer to add
     */
    public void addQuestionAndAnswer(QuestionAndAnswer questionAndAnswer) {
        if (!this.questionAndAnswer.contains(questionAndAnswer)) {
            this.questionAndAnswer.add(questionAndAnswer);
        }
    }


    /**
     * @param questionAndAnswer the questionAndAnswer to remove
     */
    public void removeQuestionAndAnswer(QuestionAndAnswer questionAndAnswer) {
        this.questionAndAnswer.remove(questionAndAnswer);
    }
}
