package com.nny.Demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

//	@Autowired
//	private UserPasswordMapper mapper;
//
//	@Test
//	public void contextLoads() {
//		Map<String, String> cg = mapper.selectByUsername("cg");
//		System.out.println(cg.size());
//		System.out.println(cg.get("token"));
//		System.out.println(cg.get("openId"));
//	}

    @Autowired
    private PerInfoService service;

    @Test
    public void test1() {
        int i = service.addEvaluationByPid(1, 2, "3");
        System.out.println(i);
    }
}
