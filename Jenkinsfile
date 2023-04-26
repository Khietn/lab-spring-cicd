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
        //Clone source
        git branch: 'lab-k8s', credentialsId: 'khietn', url: 'https://github.com/Khietn/lab-spring-cicd.git'
        //Build on container
        container('maven') {
            sh 'mvn -B -ntp -Dmaven.test.failure.ignore verify'
        }
        archiveArtifacts '**/target/*.jar'
    }
}
