node {
    def WORKSPACE = "/var/lib/jenkins/workspace/springboot-deploy"
    def dockerImageTag = "springboot-deploy${env.BUILD_NUMBER}"
    try{
        stage('Clone Repo') {
            // for display purposes
            // Get some code from a GitHub repository
            git url: 'https://github.com/Khietn/lab-spring-cicd.git',
                credentialsId: 'khietn',
                branch: 'lab-pipeline'
            sh 'mvn --version'
            sh 'mvn clean install -DskipsTest'
         }
        stage('Build docker') {
             //dockerImage = docker.build("springboot-deploy:${env.BUILD_NUMBER}") //Docker build with docker cloud
            sh "docker build -t springboot-deploy:${env.BUILD_NUMBER}"
        }
        stage('Deploy docker'){
              echo "Docker Image Tag Name: ${dockerImageTag}"
              sh "docker stop springboot-deploy || true && docker rm springboot-deploy || true"
              sh "docker run --name springboot-deploy -d -p 8081:8081 springboot-deploy:${env.BUILD_NUMBER}"
        }
    }catch(e){
        currentBuild.result = "FAILED"
        throw e
    }finally{
    
    }
}
