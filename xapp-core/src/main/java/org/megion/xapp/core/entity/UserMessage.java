package org.megion.xapp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "user_message")
public class UserMessage implements Serializable {

	private static final long serialVersionUID = 3618641671794290651L;

	@Id
	@GeneratedValue
	private Long id;

	@NotEmpty(message = "Message cannot be empty")
	@Column(nullable = false, length = 255)
	private String message;

	@ManyToOne(optional = false)
	@JoinColumn(name = "user_id", nullable = false, updatable = false)
	private final User user;

	public UserMessage() {
		this.user = null;
	}

	public UserMessage(User user) {
		this.user = user;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	@Override
	public String toString() {
		return "UserMessage [id=" + id + ", message=" + message + "]";
	}

}
