package com.mr.bomkpi;

import com.mr.bomkpi.entity.SubjectGroup;
import com.mr.bomkpi.entity.User;
import com.mr.bomkpi.service.UserService;
import com.mr.bomkpi.util.DateUtil;
import com.mr.bomkpi.util.PasswordUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BomkpiApplicationTests {


	@Autowired
	private UserService userService;
	@Test
	public void contextLoads() {
		User user = userService.findByUsername("admin");
		String passsword = new PasswordUtil().encode("admin123");

		user.setPassword(passsword);
		user.setLastModifyDate(new Date(System.currentTimeMillis()));
		user.setLastChangePwdTime(new Date(System.currentTimeMillis()));
		userService.save(user);
	}

}
