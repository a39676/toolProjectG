package job_test.orange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import job_test.orange.service.ComplexService;

public class ComplexController {
	
	@Autowired
	ComplexService complexService;
	
	@GetMapping(value = "/test")
	public void test() {
		System.out.println(complexService.getLoanRecordByLoanId("2017041915330128201183568"));
	}

}
