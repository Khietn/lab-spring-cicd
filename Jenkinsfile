// Rather than inline YAML, you could use: yaml: readTrusted('jenkins-pod.yaml')
// Or, to avoid YAML: containers: [containerTemplate(name: 'maven', image: 'maven:3.6.3-jdk-8', command: 'sleep', args: 'infinity')]
podTemplate(yaml: '''
apiVersion: v1
kind: Pod
spec:
  containers:
  - name: maven
    image: maven:3.8.3-openjdk-17
    command:
    - sleep
    args:
    - infinity
''') {
    node(POD_LABEL) {
      
      environment {
          DOCKER_HUB_CREDENTIALS = credentials('docker-hub')
          IMAGE_NAME = 'trada98/spring-boot'
          IMAGE_TAG = 'latest'  
      }
      
      stage('Clone Repo') {
            // for display purposes
            // Get some code from a GitHub repository
            git url: 'https://github.com/Khietn/lab-spring-cicd.git',
                credentialsId: 'khietn',
                branch: 'lab-k8s'
      }
      
      stage("Build Repo") {
        //Build on container
        container('maven') {
            sh 'mvn -B -ntp -Dmaven.test.failure.ignore verify'
        }
        archiveArtifacts '**/target/*.jar' 
      }

      stage('Push to Repository') {
            steps {
                withCredentials([DOCKER_HUB_CREDENTIALS]) {
                    sh "docker login -u ${DOCKER_HUB_CREDENTIALS_USR} -p ${DOCKER_HUB_CREDENTIALS_PSW}"
                }
                sh "docker push $IMAGE_NAME:$IMAGE_TAG"
            }
        } 
    }
}
