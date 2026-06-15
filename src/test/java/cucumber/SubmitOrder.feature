Feature: Purchase the order from Ecommerce Website


	Background:
		Given I landed on Ecoomerce Page
	
	@Regression
	Scenario Outline: Positive test of Submitting the order
		Given Logged in with username <username> and password <password>
		When I add product <productName> to cart
		And Checkout <productName> and submit the order
		Then "Thankyou for the order." message is displayed on confirmation page
		
		Examples:
			| username				| password	| productName |
			| abcd2026@gmail.com	| Abcd@123	| ZARA COAT 3 |