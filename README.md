# Invillia recruitment challenge

[![Build Status](https://travis-ci.org/shelsonjava/invillia.svg?branch=master)](https://travis-ci.org/shelsonjava/invillia)

![Invillia Logo](https://invillia.com/public/assets/img/logo-invillia.svg)
[Invillia](https://https://www.invillia.com/) - A transformação começa aqui.

The ACME company is migrating their monolithic system to a microservice architecture and you’re responsible to build their MVP (minimum viable product)  .
https://en.wikipedia.org/wiki/Minimum_viable_product

Your challenge is:
Build an application with those features described below, if you think the requirements aren’t detailed enough please leave a comment (portuguese or english) and proceed as best as you can.

You can choose as many features you think it’s necessary for the MVP,  IT’S NOT necessary build all the features, we strongly recommend to focus on quality over quantity, you’ll be evaluated by the quality of your solution.

If you think something is really necessary but you don’t have enough time to implement please at least explain how you would implement it.

## Tasks

Your task is to develop one (or more, feel free) RESTful service(s) to:
* Create a **Store**
* Update a **Store** information
* Retrieve a **Store** by parameters
* Create an **Order** with items
* Create a **Payment** for an **Order**
* Retrieve an **Order** by parameters
* Refund **Order** or any **Order Item**

Fork this repository and submit your code with partial commits.

## Business Rules

* A **Store** is composed by name and address
* An **Order** is composed by address, confirmation date and status
* An **Order Item** is composed by description, unit price and quantity.
* A **Payment** is composed by status, credit card number and payment date
* An **Order** just should be refunded until ten days after confirmation and the payment is concluded.

## Non functional requirements

Your service(s) must be resilient, fault tolerant, responsive. You should prepare it/them to be highly scalable as possible.

The process should be closest possible to "real-time", balancing your choices in order to achieve the expected
scalability.

## Nice to have features (describe or implement):
* **Asynchronous processing**
É possível utilizar programação Reativa no Spring utilizando, por exemplo o WebFlux. Porém, eu ainda não tive a oportunidade de trabalhar com programação reativa, por isso eu não saberia descrever aqui uma solução.
* **Database**
Utilizei Hibernate e Mysql
* **Docker**
O Docker trabalha com o conceito de Containers. Onde cada container é uma máquina virtual e é possível manter vários ambientes de trabalho, de forma que pode-se trabalhar em desenvolvimento com um container idêntico ao de produção.
* **AWS**
Neste caso eu não usaria o Spring Boot, eu criaria todos os resources com Functions Lambda e acessarias eles via API Gateway (também da AWS). Aproveitaria o baixo custo de manter a solução na Amazon e poderia utilizar outros servicos, com S3 buckets,
RDS entre outros dependendo do negócio do sistema.
* **Security**
Eu incluiria o Spring Security Rest na Api e aproveitaria as facilidades de criar um sistema oAuth completo, a partir das features que o Spring Security disponibiliza. 
* **Swagger**
Foi implementado
* **Clean Code**
Deixei o mais limpo possível.
