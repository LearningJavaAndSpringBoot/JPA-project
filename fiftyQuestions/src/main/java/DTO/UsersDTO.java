package DTO;

import java.util.ArrayList;
import java.util.List;

import com.MyLearning.fiftyQuestions.Model.Orders;

public class UsersDTO {

	private Long id;
	
	private String userName;
	
	

	public UsersDTO(Long id, String userName) {
		super();
		this.id = id;
		this.userName = userName;
		
	}

	public UsersDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	

	@Override
	public String toString() {
		return "UsersDTO [id=" + id + ", userName=" + userName + "]";
	}
	
	
}
