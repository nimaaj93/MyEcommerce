package com.nimaaj.ecommerce.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "product_qa")
public class ProductQa extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;
    private String answer;
    @ManyToOne
    private User askUser;
    @ManyToOne
    private User replyUser;
    @Temporal(TemporalType.TIMESTAMP)
    private Date replyDateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public User getAskUser() {
        return askUser;
    }

    public void setAskUser(User askUser) {
        this.askUser = askUser;
    }

    public User getReplyUser() {
        return replyUser;
    }

    public void setReplyUser(User replyUser) {
        this.replyUser = replyUser;
    }

    public Date getReplyDateTime() {
        return replyDateTime;
    }

    public void setReplyDateTime(Date replyDateTime) {
        this.replyDateTime = replyDateTime;
    }
}
