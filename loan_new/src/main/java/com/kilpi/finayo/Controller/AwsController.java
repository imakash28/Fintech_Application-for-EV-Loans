package com.kilpi.finayo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kilpi.finayo.Service.AwsService;

@RestController
@RequestMapping("send")
public class AwsController {

	@Autowired
	AwsService awsService;
	
	@GetMapping("/")
	public String send(@RequestParam String recipient) {
		awsService.sendEmail(recipient);
		return "Email sent successfully";
	}
}
