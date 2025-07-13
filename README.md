# QR Code Generator

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![AWS SDK](https://img.shields.io/badge/Amazon%20S3-FF9900?style=for-the-badge&logo=amazons3&logoColor=white)
![Google ZXing](https://img.shields.io/badge/Google_Zxing-4285F4?style=for-the-badge&logo=google-zxing&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2CA5E0?style=for-the-badge&logo=docker&logoColor=white)
![Maven](https://img.shields.io/badge/apache_maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)

A Spring Boot application that generates QR codes and stores them in AWS S3. This project uses Google's ZXing library for QR code generation and AWS S3 for storage.

## Table of Contents

- [How to Use](#how-to-use)
  - [Prerequisites](#prerequisites)
  - [Environment Variables](#environment-variables)
  - [Running the Application](#running-the-application)
    - [Local Development](#local-development)
    - [Docker Deployment](#docker-deployment)
  - [AWS S3 Configuration](#aws-s3-configuration)
- [Application Flow](#application-flow)
- [API Endpoints](#api-endpoints)
- [License](#license)

## How to Use

This section provides comprehensive instructions for setting up and running the QR Code Generator application.

### Prerequisites

- Java 21 JDK
- Maven
- Docker
- AWS Account with S3 access
- AWS CLI configured with appropriate credentials

### Environment Variables

Create a `.env` file in the project root with the following variables:

```env
AWS_ACCESS_KEY_ID=your_access_key
AWS_SECRET_ACCESS_KEY=your_secret_key
AWS_REGION=your_region
AWS_BUCKET_NAME=your_bucket_name
```

### Running the Application

#### Local Development

1. Create the `.env` file as described above
2. Build the project:
   ```bash
   mvn clean package
   ```
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

#### Docker Deployment

1. Build the Docker image:
   ```bash
   docker build -t qrcode-generator:X.X . 
   ```
> Remember to replace the version and image name if you want

2. Run the container:
   ```bash
   docker run --env-file .env -p 8080:8080 qrcode-generator:X.X 
   ```

> Remember to replace the .env file path to the path of your environment file that you created.

### AWS S3 Configuration

1. Create an S3 bucket in your AWS account
2. Update the `AWS_BUCKET_NAME` in your `.env` file or Docker run command
3. Ensure your AWS credentials have appropriate permissions to access the S3 bucket

## Application Flow

> **Note**: This diagram was built on [Mermaid Online Editor](https://www.mermaidchart.com/app/projects/d95a6b85-4fcc-442f-827b-da6d7a456612/diagrams/f54eab59-6c0f-42d4-86ab-1afa9cee30ce/version/v0.1/edit).

<img src="./.github/diagram.svg"/>

## API Endpoints

### POST /qrcode
Generate a QR code from the provided text and store it in AWS S3. The QR code will be generated as a PNG image with dimensions of 200x200 pixels.

**Parameters**

| Name | Required | Type | Description |
|------|----------|------|-------------|
| `text` | required | string | The text content to be encoded in the QR code. This can be any string value that you want to convert into a QR code. |

**Response**

```json
{
    "url": "https://your-bucket.s3.your-region.amazonaws.com/random-uuid"
}
```

**Error Response**

If an error occurs during QR code generation or S3 upload, the API will return a 500 Internal Server Error.

**Example Usage**

```bash
curl -X POST http://localhost:8080/qrcode \
     -H "Content-Type: application/json" \
     -d '{"text": "https://example.com"}'
```

## License

This project is licensed under the MIT License - see the LICENSE file for details. 