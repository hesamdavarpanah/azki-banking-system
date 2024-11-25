# Azki Banking System Application

This is a simple banking system application built using Java and Spring Boot. The application allows users to create accounts, perform transactions (deposit, withdraw, transfer), and check balances through both a RESTful API and a console-based user interface.

![Azki Banking System Application](./azki.png)

## Features

- Create bank accounts
- Deposit money into accounts
- Withdraw money from accounts
- Transfer money between accounts
- Check account balances
- Console-based user interface for interaction
- RESTful API for programmatic access

## Prerequisites

- Java 23
- Maven
- Docker (optional, for containerization)

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/hesamdavarpanah/azki-banking-system.git
cd banking-system
```

## Build the Project
You can build the project using Maven. Run the following command in the root directory of the project:

```bash
mvn clean package
```

## Run the Application
You can run the application in two ways: using the console interface or through the REST API.

### Console Interface
To run the console interface, execute the following command:

```bash
java -jar target/my-banking-app-0.0.1-SNAPSHOT.jar
```

Follow the prompts in the console to interact with the banking system.

### REST API
To run the application as a REST API, you can also use the same command as above. Once the application is running, you can use tools like curl or Postman to interact with the API.

#### Example API Endpoints:

* Create Account: POST /api/bank/create-account/
* Deposit Money: POST /api/bank/deposit/
* Withdraw Money: POST /api/bank/withdraw/
* Transfer Money: POST /api/bank/transfer-fund/
* Check Balance: POST /api/bank/display-balance/

## Docker Support

To build and run the application using Docker, follow this step:

```bash
docker compose up -d
```

This will start the application, and you can access the REST API at http://localhost:8080.

## Contributing
Contributions are welcome! If you have suggestions for improvements or new features, feel free to open an issue or submit a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](./LICENSE) file for details.

## Acknowledgments

* [Spring Boot](https://spring.io/projects/spring-boot) for the framework
* [Maven](https://maven.apache.org/) for project management
* [Docker](https://www.docker.com/) for containerization
