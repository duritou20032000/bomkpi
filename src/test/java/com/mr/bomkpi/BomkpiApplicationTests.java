package com.mr.bomkpi;

import com.mr.bomkpi.entity.AsnVo;
import com.mr.bomkpi.entity.SubjectGroup;
import com.mr.bomkpi.entity.User;
import com.mr.bomkpi.repository.AsnVoRepository;
import com.mr.bomkpi.service.PolicyService;
import com.mr.bomkpi.service.UserService;
import com.mr.bomkpi.util.PasswordUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BomkpiApplicationTests {


	@Autowired
	private UserService userService;

	@Autowired
	private PolicyService policyService;

	@Autowired
	private AsnVoRepository asnVoRepository;

	/**
	 * 密码加密
	 */
	@Test
	public void contextLoads() {
//		User user = userService.findByUsername("admin");
//		String passsword = new PasswordUtil().encode("admin123");

		User user = userService.findByUsername("zhangxu");
		String passsword = new PasswordUtil().encode("123456");

		user.setPassword(passsword);
		user.setLastModifyDate(new Date(System.currentTimeMillis()));
		user.setLastChangePwdTime(new Date(System.currentTimeMillis()));
		userService.save(user);
	}
	/**
	 * 授权，资源测试
	 */

    @Test
	public void resource(){
		User user = userService.findByUsername("zhangxu");
		String sbn = user.getSubject().getSubjectName();
		List<SubjectGroup> lists = user.getSubject().getSubjectGroups();
		for (SubjectGroup s : lists) {
			String name = s.getSubjectGroupName();
			System.out.println("[admin==="+sbn+"===="+name+"]");
		}

		//得到相应的policy_id 来resource_id
	}

	// 判断是否已经将订单数量分配完
	@Test
	public void  isAllocationOver(){
    	String[] str =new String[]{"1","2","3","4"};
		boolean flag =false;
		Double d = 0.0d;
		Double t =0.0d;
		for (String s : str) {
			t = Double.valueOf((s));
			d=d+t;
		}
		System.out.println(d);
	}
	@Test
	public  void asnDesc(){
		Sort sort = new Sort(Sort.Direction.DESC, "lastModifyDate");
		List<AsnVo> asns = asnVoRepository.findAll(sort);
		for (AsnVo asn : asns) {
			System.out.println("ASN-->>>>> "+ asn.getAsnNbr() +"====" + asn.getLastModifyDate());
		}
	}
}
