pipeline {
    agent any

    tools {
        gradle 'Gradle_8'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/yared2/springboot-jwt-auth.git'
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
                echo '✅ Deployment step will go here in the next phase'
                sh '''
                	nohup java -jar build/libs/project-one-security-0.0.1-SNAPSHOT.jar > app.log 2>&1 &
                	sleep 10
                  '''
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
