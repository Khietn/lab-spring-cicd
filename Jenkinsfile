pipeline {
    agent {
        docker {
            image 'maven:3.8.3-jdk-11'
            args '-v /root/.m2:/root/.m2'
        }
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Dockerize') {
            agent {
                docker {
                    image 'openjdk:11-jre-slim'
                    args '-p 8080:8080'
                }
            }
            steps {
                sh 'cp target/my-spring-app.jar /app.jar'
                sh 'docker build -t my-spring-app .'
            }
        }
        stage('Deploy') {
            agent {
                docker {
                    image 'openjdk:11-jre-slim'
                    args '-p 8080:8080'
                }
            }
            steps {
                sh 'docker run -d --name my-spring-app my-spring-app'
            }
        }
    }
}
