# AI Answering Application Platform D
## Project Overview
The AI Answering Application Platform project aims to develop a comprehensive system that combines advanced AI technologies with a user-friendly interface to provide efficient and accurate answers to questions.
## Project Objectives
1. Users can create quiz applications by uploading questions and custom scoring rules for others to search and use.
2. By leveraging AI to power the platform, an AI-intelligent quiz application platform can be developed. Users only need to set the topic, and AI can quickly generate questions and analyze user answers, greatly reducing the cost of creating quiz applications and increasing the diversity of responses.
## Technical Stack Selection
### Backend
#### Java Spring Boot development framework
    - Storage Layer: MySQL database + Redis cache + Tencent Cloud COS object storage
    - General AI capabilities based on the ChatGLM large model
    - RxJava reactive framework + thread pool isolation in practice
    - Server-Sent Events (SSE) for server-side pushing
    - ShardingSphere for database sharding
    - Various design patterns
    - Multi-faceted project optimization: performance, stability, cost optimization, product optimization, etc.

### Frontend
#### Web Page Development
    - Vue 3
    - Vue-CLI scaffolding
    - Axios request library
    - Arco Design component library
    - Frontend engineering: ESLint + Prettier + TypeScript
    - Rich text editor
    - QRCode.js for QR code genera 
    - QRCode.js for QR code generation
    - Pinia for state management
    - OpenAPI for frontend code generation
