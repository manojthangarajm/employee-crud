pipeline {
    agent any

    environment {
        IMAGE_NAME = "employee-crud-app"
        CONTAINER_NAME = "employee-crud"
        APP_PORT = "8081"
    }

    stages {

        stage('Checkout') {
            steps {
                echo 'Checking out code...'
                checkout scm
            }
        }

        stage('Maven Build & Test') {
            steps {
                echo 'Building project with Maven...'
                sh 'mvn clean package'
            }
        }

        stage('Docker Build') {
            steps {
                echo 'Building Docker image...'
                sh 'docker build -t $IMAGE_NAME .'
            }
        }

        stage('Run Container') {
            steps {
                echo 'Running Docker container...'
                sh '''
                docker rm -f $CONTAINER_NAME || true
                docker run -d -p ${APP_PORT}:${APP_PORT} --name $CONTAINER_NAME $IMAGE_NAME
                '''
            }
        }
    }

    post {
        success {
            echo '✅ Pipeline completed successfully!'
        }
        failure {
            echo '❌ Pipeline failed!'
        }
    }
}