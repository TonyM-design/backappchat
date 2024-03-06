package com.tp.chat.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Fetch;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "canals")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class Canal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	private String name;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "canal_user", joinColumns = @JoinColumn(name = "canal_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> users = new ArrayList<>();

	@Column(name = "public")
	private boolean isPublic;

	@Column(name = "description")
	private String description;

	@Column(name = "version")
	private int version;

	 @OneToMany(mappedBy = "canal", cascade = CascadeType.ALL)
    private List<Message> messages;

	@Override
	public String toString() {
		return "Canal [id=" + id + ", name=" + name + ", users=" + users + ", isPublic=" + isPublic
				+ ", description=" + description + ", version=" + version + "]";
	}



	public Canal(String name, List<User> users, boolean isPublic, String description, int version) {
		this.name = name;
		this.users = users;
		this.isPublic = isPublic;
		this.description = description;
		this.version = version;
	}



	public Canal(String name, boolean isPublic, String description, int version) {
		this.name = name;
		this.isPublic = isPublic;
		this.description = description;
		this.version = version;
	}



	public Canal() {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public boolean getIsPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}



	public List<Message> getMessages() {
		return messages;
	}



	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

}
