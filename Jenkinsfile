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
                  image: maven:3.8.1-jdk-8
                  command:
                  - sleep
                  args:
                  - 99d
                  volumeMounts:
                  - name: maven-repo
                    mountPath: /root/.m2/repository
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
   checkout([$class: 'GitSCM',
                      branches: [[name: '*/lab-k8s']],
                      doGenerateSubmoduleConfigurations: false,
                      userRemoteConfigs: [[credentialsId: 'khietn', url: 'https://github.com/Khietn/lab-spring-cicd.git']]
                    ])
    container('maven') {
        sh 'mvn -B -ntp clean package -DskipTests'
    }
//     container('docker') {
//       sh 'docker version'
//       sh 'docker build -t spring-boot:latest .'
//     }
  }
}
