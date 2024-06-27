#!/bin/bash

# Update package index
sudo apt update

# Install Java Development Kit (JDK)
sudo apt install -y openjdk-11-jdk

# Install Maven
sudo apt install -y maven

# Install Docker
sudo apt install -y apt-transport-https ca-certificates curl software-properties-common
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"
sudo apt update
sudo apt install -y docker-ce

# Install Docker Compose
sudo curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose

# Install Kubernetes Tools (kubectl, minikube)
sudo apt install -y apt-transport-https
sudo curl -s https://packages.cloud.google.com/apt/doc/apt-key.gpg | sudo apt-key add -
echo "deb https://apt.kubernetes.io/ kubernetes-xenial main" | sudo tee -a /etc/apt/sources.list.d/kubernetes.list
sudo apt update
sudo apt install -y kubectl
curl -Lo minikube https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64
chmod +x minikube
sudo mv minikube /usr/local/bin/

# Start Minikube
sudo minikube start --driver=none

# Install Git
sudo apt install -y git

# Clone Your Project Repository
git clone https://github.com/adidiac/awbd.git
cd note-app

# Set Docker Hub repository
DOCKER_REPO="your-dockerhub-username"

# Build and Package the Applications

# Build Config Server
cd config-server
mvn clean package
docker build -t $DOCKER_REPO/config-server .
cd ..

# Build Eureka Server
cd eureka-server
mvn clean package
docker build -t $DOCKER_REPO/eureka-server .
cd ..

# Build Note Service
cd cafetaria
mvn clean package
docker build -t $DOCKER_REPO/cafetaria .
cd ..

# Build Gateway Service
cd gateway-service
mvn clean package
docker build -t $DOCKER_REPO/gateway-service .
cd ..

# Push Docker Images to Docker Hub
docker login
docker push $DOCKER_REPO/config-server
docker push $DOCKER_REPO/eureka-server
docker push $DOCKER_REPO/cafeteria
docker push $DOCKER_REPO/gateway-service

# Create Docker Compose file
cat <<EOL > docker-compose.yml
version: '3'
services:
  config-server:
    image: $DOCKER_REPO/config-server
    ports:
      - "8888:8888"
    networks:
      - note-app-network

  eureka-server:
    image: $DOCKER_REPO/eureka-server
    ports:
      - "8761:8761"
    networks:
      - note-app-network

  cafetaria:
    image: $DOCKER_REPO/cafetaria
    ports:
      - "8081:8081"
    networks:
      - note-app-network
    environment:
      - SPRING_PROFILES_ACTIVE=default

  gateway-service:
    image: $DOCKER_REPO/gateway-service
    ports:
      - "8080:8080"
    networks:
      - note-app-network

  zipkin:
    image: openzipkin/zipkin
    ports:
      - "9411:9411"
    networks:
      - note-app-network

networks:
  note-app-network:
    driver: bridge
EOL

# Deploy Applications using Docker Compose
docker-compose up -d

# Verify Deployments
docker-compose ps
