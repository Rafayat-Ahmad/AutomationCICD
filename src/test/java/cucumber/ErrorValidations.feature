Feature: Error validation

	@ErrorValidation
	Scenario Outline: Error validation scenario
		Given I landed on Ecoomerce Page
		When Logged in with username <username> and password <password>
		Then "Incorrect email or password." message is displayed
		
		Examples:
			| username				| password		|
			| abcd2026@gmail.com	| Abcd@12345	|