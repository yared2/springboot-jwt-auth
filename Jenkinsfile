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
stage('JWT Test') {
    steps {
        echo '🔐 Testing JWT authentication...'
        sh '''
            echo "⬇️ Installing jq..."
            apt-get update && apt-get install -y jq

            echo "➡️ Requesting JWT..."
            TOKEN=$(curl -s -X POST http://localhost:8082/auth/login \
              -H "Content-Type: application/json" \
              -d '{"username": "admin", "password": "admin123"}' | jq -r '.token')

            echo "🪪 Received token: $TOKEN"

            echo "➡️ Accessing protected endpoint..."
            RESPONSE=$(curl -s -o response.txt -w "%{http_code}" \
              -H "Authorization: Bearer $TOKEN" \
              http://localhost:8082/api/admin)

            if [ "$RESPONSE" = "200" ]; then
              echo "✅ Access successful"
              cat response.txt
            else
              echo "❌ Access failed with HTTP $RESPONSE"
              cat response.txt
              exit 1
            fi
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
