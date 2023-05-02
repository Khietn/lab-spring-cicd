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
          
      stage('Clone Repo') {
            // for display purposes
            checkout([$class: 'GitSCM',
                      branches: [[name: 'lab-k8s']],
                      doGenerateSubmoduleConfigurations: false,
                      extensions: [],
                      submoduleCfg: [],
                      userRemoteConfigs: [[credentialsId: 'khietn', url: 'https://github.com/Khietn/lab-spring-cicd.git']]
                    ])
      }
      
      stage("Build Repo") {
        //Build on container
        container('maven') {
            sh 'mvn -B -ntp -Dmaven.test.failure.ignore verify'
        }
        archiveArtifacts '**/target/*.jar' 
      }

      stage("Build image") {
        sh 'docker build -t spring-boot:latest .'
      }
      /*stage('Push to Repository') {
        environment {
            DOCKER_HUB_CREDENTIALS = credentials('docker_hub')
            IMAGE_NAME = 'trada98/spring-boot'
            IMAGE_TAG = 'latest'  
        }
          try {          
            sh "docker login -u ${DOCKER_HUB_CREDENTIALS_USR} -p ${DOCKER_HUB_CREDENTIALS_PSW}"

            sh "docker push $IMAGE_NAME:$IMAGE_TAG"
          } catch (err) {
            error "Failed to push image: ${err}"
          }
       } */
    }
}
