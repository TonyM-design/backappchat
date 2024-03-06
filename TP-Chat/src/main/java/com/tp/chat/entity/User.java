package com.tp.chat.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "nickname")
	private String nickname;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "badgeColor")
	private String badgeColor;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Message> messages;

	@ManyToMany(mappedBy = "users")
	@JsonIgnore
	private List<Canal> canals = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "user_message_lengths", joinColumns = @JoinColumn(name = "user_id"))
    @MapKeyColumn(name = "canal_id")
    @Column(name = "message_length")
    private Map<Long, Integer> userMessageLengths;

	public User(String name, String nickname, String email, String password, String badgeColor, List<Canal> canals) {
		this.name = name;
		this.nickname = nickname;
		this.email = email;
		this.password = password;
		this.badgeColor = badgeColor;
		this.canals = canals;
	}

	public User(String name, String nickname, String email, String password, String badgeColor) {
		this.name = name;
		this.nickname = nickname;
		this.email = email;
		this.password = password;
		this.badgeColor = badgeColor;
	}

	public User() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Canal> getCanals() {
		return canals;
	}

	public void setCanals(List<Canal> canals) {
		this.canals = canals;
	}

	public String getBadgeColor() {
		return badgeColor;
	}

	public void setBadgeColor(String badgeColor) {
		this.badgeColor = badgeColor;
	}

	

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public Map<Long, Integer> getUserMessageLengths() {
		return userMessageLengths;
	}

	public void setUserMessageLengths(Long canalId, Integer messageLength) {
		this.userMessageLengths.put(canalId, messageLength);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", nickname=" + nickname + ", email=" + email + ", password="
				+ password + ", badgeColor=" + badgeColor + ", messages=" + messages + ", canals=" + canals
				+ ", userMessageLengths=" + userMessageLengths + "]";
	}

	
}
