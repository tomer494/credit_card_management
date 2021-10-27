package com.ob1tech.creditCardManager;

import com.ob1tech.creditCardManager.model.Deposit;
import com.ob1tech.creditCardManager.model.DepositResponse;
import com.ob1tech.creditCardManager.services.DepositService;
import com.ob1tech.creditCardManager.transactions.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
public class CreditCardManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditCardManagerApplication.class, args);
	}

	@Autowired
	private DepositService depositService;

	/**
	 * Api access pint for performing a deposit
	 * @param deposit - Deposit object that contains all the relevant data, like, customer id,
	 *                authentication token, card number to credit and amount
	 * @return CCMResponse with confirmation or error message
	 */
	@PostMapping("/api/deposit")
	public DepositResponse deposit(@RequestBody Deposit deposit) {
		deposit.setType(TransactionService.TransactionType.DEPOSIT.toString());
		return (DepositResponse) depositService.execute(deposit);
	}

	@GetMapping("/")
	public String home() {
		return "Welcome!";
	}

	@GetMapping("/error")
	public String error() {
		return "Hoo Noo!";
	}

}
