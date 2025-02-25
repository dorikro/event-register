# My Event Registration App

This project is a Spring Boot application that allows users to register for events. Users can provide their full name, work email, phone number, and an optional car number for parking.

## Project Structure

```
my-event-registration-app
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── eventregistration
│   │   │               ├── Application.java
│   │   │               ├── controller
│   │   │               │   └── RegistrationController.java
│   │   │               ├── model
│   │   │               │   └── Registration.java
│   │   │               └── service
│   │   │                   └── RegistrationService.java
│   │   └── resources
│   │       └── application.properties
├── pom.xml
├── Dockerfile
└── README.md
```

## Steps to Deploy the App to Azure Kubernetes Service (AKS)

1. **Build the Docker Image**:
   - Navigate to the project directory.
   - Run `mvn clean package` to build the project.
   - Build the Docker image using `docker build -t my-event-registration-app .`.

2. **Push the Docker Image to Azure Container Registry (ACR)**:
   - Log in to ACR: `az acr login --name <your-acr-name>`.
   - Tag the image: `docker tag my-event-registration-app <your-acr-name>.azurecr.io/my-event-registration-app:latest`.
   - Push the image: `docker push <your-acr-name>.azurecr.io/my-event-registration-app:latest`.

3. **Create AKS Cluster**:
   - Create an AKS cluster: `az aks create --resource-group <your-resource-group> --name <your-aks-name> --node-count 1 --enable-addons monitoring --generate-ssh-keys`.

4. **Connect to AKS**:
   - Connect to the AKS cluster: `az aks get-credentials --resource-group <your-resource-group> --name <your-aks-name>`.

5. **Deploy the Application**:
   - Create a Kubernetes deployment YAML file (e.g., deployment.yaml) for the application.
   - Apply the deployment: `kubectl apply -f deployment.yaml`.

6. **Expose the Application**:
   - Create a service YAML file (e.g., service.yaml) to expose the application.
   - Apply the service: `kubectl apply -f service.yaml`.

7. **Access the Application**:
   - Get the external IP of the service: `kubectl get services`.
   - Access the application using the external IP.