pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                sh 'chmod +x mvnw'
                sh './mvnw clean package'
            }
        }

        stage('Docker Build') {
            steps {
                sh '/usr/local/bin/docker build -t employee-crud-app .'
            }
        }

        stage('Run Container') {
            steps {
                sh '''
                /usr/local/bin/docker rm -f employee-crud || true
                /usr/local/bin/docker run -d -p 8081:8081 --name employee-crud employee-crud-app
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