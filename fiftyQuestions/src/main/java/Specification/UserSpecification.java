package Specification;

import org.springframework.data.jpa.domain.Specification;

import com.MyLearning.fiftyQuestions.Model.Users;

public class UserSpecification {

	public static Specification<Users> hasName(String name){
		return (root,query,cb) -> {
			if(name==null) {
				return cb.conjunction();
			}
			return cb.equal(root.get("userName"), name);
		};
	}
	
	public static Specification<Users> hasEmail(String email){
		return (root,query,cb) ->{
			if(email==null) {
				return cb.conjunction();
			}
			return cb.equal(root.get("email"), email);
		};
	}
}
