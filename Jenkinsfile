podTemplate(containers: [
  containerTemplate(name: 'maven', image: 'maven:3.8.3-openjdk-17', command: 'sleep', args: '99d')
  ], volumes: [
  persistentVolumeClaim(mountPath: '/root/.m2/repository', claimName: 'maven-repo', readOnly: false)
  ]) {

  node(POD_LABEL) {
    stage('Build a Maven project') {
     checkout([$class: 'GitSCM',
                      branches: [[name: 'lab-k8s']],
                      doGenerateSubmoduleConfigurations: false,
                      extensions: [],
                      submoduleCfg: [],
                      userRemoteConfigs: [[credentialsId: 'khietn', url: 'https://github.com/Khietn/lab-spring-cicd.git']]
                    ])
      container('maven') {
        sh 'mvn -B -ntp clean package -DskipTests'
      }
    }
  }
}
