/*
“Docker-in-Docker”: runs a Docker-based build where the Docker daemon and client are both defined in the pod.
This allows you to control the exact version of Docker used.
(For example, try DOCKER_BUILDKIT=1 to access advanced Dockerfile syntaxes.)
There is no interaction with the container system used by Kubernetes:
docker.sock does not need to be mounted as in dood.groovy.
May or may not work depending on cluster policy: https://kubernetes.io/docs/concepts/policy/pod-security-policy/
*/
podTemplate(yaml: '''
              apiVersion: v1
              kind: Pod
              spec:
                volumes:
                - name: docker-socket
                  emptyDir: {}
                containers:
                - name: maven
                  image: maven:3.8.3-openjdk-17
                  command:
                  - sleep
                  args:
                  - 99d
                - name: docker
                  image: docker:19.03.1
                  readinessProbe:
                    exec:
                      command: [sh, -c, "ls -S /var/run/docker.sock"]
                  command:
                  - sleep
                  args:
                  - 99d
                  volumeMounts:
                  - name: docker-socket
                    mountPath: /var/run
                - name: docker-daemon
                  image: docker:19.03.1-dind
                  securityContext:
                    privileged: true
                  volumeMounts:
                  - name: docker-socket
                    mountPath: /var/run
''') {
  node(POD_LABEL) {
//    //git, branch,
//    stage('Checkout SCM') {
//     checkout([$class: 'GitSCM',
//                       branches: [[name: '*/lab-k8s']],
//                       doGenerateSubmoduleConfigurations: false,
//                       userRemoteConfigs: [[credentialsId: 'khietn', url: 'https://github.com/Khietn/lab-spring-cicd.git']]
//                     ])   
//    }
    
//    //Container Maven
//    container('maven') {
//         sh 'mvn -B -ntp clean package -DskipTests'
//    }
//    //Container Docker
//    container('docker') {
     
//      //Build image
//      stage('Build image') {
//          sh 'docker version'
//          sh 'docker build -t trada98/spring-boot:latest .'
//      }
     
//      //Push image
//      stage('Push Docker image to Docker Hub') {
//        withCredentials([usernamePassword(credentialsId: 'docker-hub', usernameVariable: 'DOCKER_HUB_USER', passwordVariable: 'DOCKER_HUB_PASSWORD')]) {
//           try {
//              sh 'docker login -u $DOCKER_HUB_USER -p $DOCKER_HUB_PASSWORD'
//              sh 'docker push trada98/spring-boot:latest'
//            } catch (err) {
//              echo "Failed to push Docker image: ${err}"
//              currentBuild.result = 'FAILURE'
//            } finally {
//              sh 'docker logout'
//            }
//        }
//      }//End stage push image
     
//     } //End container docker
    
    sh 'kubectl version'
  }
}
 
