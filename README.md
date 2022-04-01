# TopBuses Demo

This is a demo project to display the top 10 bus lines in Stockholm region using Spring Boot and Next.js as backend and frontend, respectively.

- Client (app): <http://localhost:3000>
- Server: <http://localhost:8080/api/buses/top>

## Running Locally

### Prerequisites

This project is created with Java 11+ and Node.js 16+. In order to run the project, you need to have these both Java and JavaScript runtimes.

The easy way to install them.

```.
brew install java11
brew install node
```

Check their version,

```.
java -version
openjdk version "11.0.15" 2022-04-19

node -v
v16.14.2
```

### Running

Open your favorite terminal and run the server,

```.
cd server
./mvnw spring-boot:run
```

With another terminal, run the client,

```.
cd client
yarn install
yarn dev
```

## Running Using Docker

If you have docker installed, you can run the project with a single command,

```.
docker-compose up
```

You can perform clean up with,

```.
docker-compose down --rmi local
```

## Screenshot of the App

Welcome to the top 10 bus lines in Stockholm! Happy riding! 😀 🚌

!["screenshot"](resources/screenshot.png)
