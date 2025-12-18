pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out code...'
                checkout scm
            }
        }

        stage('Maven Build & Test') {
            steps {
                echo 'Building project with Maven Wrapper...'
                sh 'chmod +x mvnw'
                sh './mvnw clean package'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t employee-crud-app .'
            }
        }

        stage('Run Container') {
            steps {
                sh '''
                docker rm -f employee-crud || true
                docker run -d -p 8081:8081 --name employee-crud employee-crud-app
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