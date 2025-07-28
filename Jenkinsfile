pipeline {
    agent any

    tools {
        gradle 'Gradle_8'  // Must match the name in Jenkins tool config
    }

    environment {
        // Add any env vars later (e.g., JWT secret, DB credentials)
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/yared2/springboot-jwt-auth.git'
            }
        }

        stage('Build') {
            steps {
                sh './gradlew clean build'
            }
        }

        stage('Test') {
            steps {
                sh './gradlew test'
            }
        }

        stage('Package') {
            steps {
                sh './gradlew bootJar'
                archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploy logic goes here (e.g., copy JAR to server, Docker run, etc.)'
            }
        }
    }

    post {
        success {
            echo '✅ Build succeeded!'
        }
        failure {
            echo '❌ Build failed!'
        }
    }
}
