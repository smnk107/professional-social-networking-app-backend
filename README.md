# **🌟 Professional Social Networking App Backend 🌟**  
🚀 *A LinkedIn-like platform to connect, post, and notify in real-time!*  

---

## **🌐 Overview**  
This backend project brings LinkedIn-like functionalities to life! Users can:  
- 📝 Post updates and engage with their connections.  
- 🤝 Send, accept, or reject connection requests seamlessly.  
- 🔔 Receive real-time notifications for likes, connection actions, and more!  

With a microservices architecture and robust integrations, this platform ensures smooth, secure, and scalable interactions.

---

## **🛠️ Tech Stack**  
### **Core Technologies**  
- **📚 Languages:** Java  
- **💾 Databases:** PostgreSQL, Neo4j  
- **🔍 Query Language:** Cypher Query Language  

### **Frameworks & Tools**  
- **⚙️ Backend:** Spring Boot, Spring Security with JWT, Spring Data JPA  
- **📡 Microservices:** Eureka, API Gateway, Feign Client  
- **💬 Messaging:** Kafka  
- **💻 Development:** Maven, IntelliJ IDEA, Postman  
- **📊 Monitoring:** Kafdrop UI  

---

## **✨ Key Features**  
- **🔴 Real-Time Notifications**: Users stay updated on post likes and connection requests instantly.  
- **🛡️ Secure Authentication**: JWT-based security ensures data integrity.  
- **🏗️ Microservices Architecture**: Independent, scalable services for enhanced performance.  
- **🌉 User-Friendly API Gateway**: Filters requests, validates JWT, and ensures seamless communication between services.  

---

## **🏛️ Architecture**  
### **Microservices Breakdown**  
1. **👤 User Service**:  
   Handles user-related operations: sign-up, login (JWT issuance), and user management.  
   - **📂 Database**: PostgreSQL  
   - **🔗 Endpoints**:  
     - `POST /signup`: Create a new user.  
     - `POST /login`: Authenticate user and issue JWT.  
     - `GET /getAllUser`: Retrieve all users.  

2. **🔗 Connection Service**:  
   Manages connections and requests using **Neo4j** and Kafka for notifications.  
   - **📢 Key Kafka Topics**:  
     - `request-sent-topic`  
     - `request-received-topic`  
     - `request-accepted-topic`  
     - `request-rejected-topic`  
   - **🔗 Endpoints**:  
     - `POST /createNewPerson`: Add a new person to the graph.  
     - `POST /send/{requestedUserId}`: Send a connection request.  
     - `POST /accept/{senderId}`: Accept a connection request.  
     - `POST /reject/{senderId}`: Reject a connection request.  
     - `GET /firstDegreeConnections/{userId}`: Retrieve first-degree connections.  

3. **📝 Post Service**:  
   Handles post creation, likes, and fetching posts. Collaborates with Notification Service for updates.  
   - **📂 Database**: PostgreSQL  
   - **🔗 Endpoints**:  
     - `POST /`: Create a post.  
     - `POST /{postId}`: Like a post.  
     - `DELETE /{postId}`: Unlike a post.  
     - `GET /users/{userId}/allPosts`: Fetch all posts by a user.  

4. **🔔 Notification Service**:  
   Listens to Kafka topics and sends notifications to users.  
   - **📢 Kafka Listeners**:  
     - `post-created-topic`  
     - `post-liked-topic`  
     - `request-sent-topic`  

### **🌉 API Gateway**  
Centralized entry point for all requests.  
- **🔒 Filters**: Verifies JWT, adds user ID to requests for downstream services.  
- **📜 Request Handling**: Each microservice has interceptors to manage thread-local variables for user-specific contexts.  

---
