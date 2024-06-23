package in.ashokit.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import in.ashokit.dto.StudentOrder;
import in.ashokit.service.PaymentService;

@Controller
@CrossOrigin
public class PaymentController {

	@Autowired
	private PaymentService service;

	@GetMapping("/")
	public String init() {
		return "index";
	}

	@PostMapping(value = "/create-order", produces = "application/json", consumes = "application/json")
	@ResponseBody
	public ResponseEntity<StudentOrder> purchaseCourse(@RequestBody StudentOrder studentOrder) throws Exception {

		StudentOrder orderResp = service.createOrder(studentOrder);

		return new ResponseEntity<>(orderResp, HttpStatus.OK);
	}

	@PostMapping("/payment-callback")
	public String handlePaymentCallback(@RequestParam Map<String, String> respPayload, Model model) {
		System.out.println(respPayload);
		StudentOrder updatedOrder = service.verifyPaymentAndUpdateOrderStatus(respPayload);
		model.addAttribute("order", updatedOrder);
		return "success";
	}

}
