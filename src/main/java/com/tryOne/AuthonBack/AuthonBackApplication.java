package com.tryOne.AuthonBack;

import com.tryOne.AuthonBack.DAO.security.RoleDao;
import com.tryOne.AuthonBack.DAO.security.UserDao;
import com.tryOne.AuthonBack.entity.complaint.Pincodes;
import com.tryOne.AuthonBack.entity.security.Role;
import com.tryOne.AuthonBack.entity.security.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Mathew F Vadakumchery
 * @see <a href = "https://www.youtube.com/watch?v=TeBt0Ike_Tk&t=4937s"> The video the code is based out of </a>
 * */

@SpringBootApplication
public class AuthonBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthonBackApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(UserDao userDao,PasswordEncoder passwordEncoder){
		return (args) -> {

			createUser(userDao, passwordEncoder);

		};
	}

	private void createUser(UserDao userDao,PasswordEncoder passwordEncoder){


		User mat = new User(
				"mathew",
				passwordEncoder.encode("test123"),
				1,
				"9921323",
				"mat@gsadsa.com",
				new HashSet<>(Set.of(new Role("ROLL_USER"))),
				new Pincodes("104")
		);

		try {
			userDao.saveUserWithPinCode(mat);
		}catch (Exception e){
			System.out.println(e);
			System.out.println("Exception caught");
		}
	}
}
