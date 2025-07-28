pipeline {
    agent any

    tools {
        gradle 'Gradle_8'
    }

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
                echo 'Deployment step goes here'
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
