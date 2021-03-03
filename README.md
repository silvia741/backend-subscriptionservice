# backend-subscriptionservice

4 microservices have been created with spring-boot:

- adidas-publicservice
- adidas-subscriptionservice
- adidas-emailservice
- eurekaserver

I have used Eureka as service registration and discovery:
  - Eureka server --> is the spring boot app eurekaserver
  - Eureka clients --> adidas-publicservice, adidas-subscriptionservice, adidas-emailservice

adidas-publicservice is the public api, it calls the rest-services of the other microservices through Feign.
adidas-subscriptionservice is a jwt securized spring boot app. The following operations can be done:
  * login
  * register
  * get one subscription
  * get all subscriptions
  * create subscription
  * delete subscription

Only login and register do not need user authentication. The rest need a token authentication in header request. If you are not authenticated you will receive a forbidden status from server.

The token has a fairly high lifetime, in a real situation it would not be like that. Also users are created with admin role, it would not be like that too in the real life.

All microservices have in 'src/main/resources' his own postman collection of each rest call with parameters, headers, etc.

In adidas-subscriptionservice I have used MySql as database. It is created automatically when the application starts. The only thing to do to make everything work is (after db is created) to insert a 'campaign' (insert into campaign (id, name, desc) values (1, 'campaign1', 'campaign1') in table 'Campaing' in order to be able to create subscriptions. Because each subscription belongs to one campaign.

adidas-emailservice only have one method to show the html email that would be sent if there was an email delivery system implemented. This microservice is not securized but in a real situation it should be.

To run the application, it's necessary to start the 4 (or each service independently) microservices and test through postman or other program each endpoint, starting from register, login, (insert campaign) create subscription ...

