package com.tp.chat.entity;

import java.time.Instant;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "messages")
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "canal_id")
	private Canal canal;

	@Column(name = "content")
	private String content;

	@Column(name = "date")
	private Date date;

	@ManyToOne
	@JoinColumn(name = "response_quote")
	private Message responseQuote;

	public Message(User user, Canal canal, String content, Date date, Message responseQuote) {
		super();
		this.user = user;
		this.canal = canal;
		this.content = content;
		this.date = Date.from(Instant.now());
		this.responseQuote = responseQuote;
	}
	public Message(User user, Canal canal, String content, Date date) {
		super();
		this.user = user;
		this.canal = canal;
		this.content = content;
		this.date = Date.from(Instant.now());
		
	}

	public Message() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Canal getCanal() {
		return canal;
	}

	public void setCanal(Canal canal) {
		this.canal = canal;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Message getResponseQuote() {
		return responseQuote;
	}

	public void setResponseQuote(Message responseQuote) {
		this.responseQuote = responseQuote;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", user=" + user + ", canal=" + canal + ", content=" + content + ", date=" + date
				+ "]";
	}

}
