# lab-spring-cicd


Để chạy, cần config kubernetes plugin để tạo agent, thêm IP svc vô tunnel 



node {
    def remote = [:]
      remote.name = 'cloud_user'
      remote.host = '18.141.177.214'
      remote.user = 'cloud_user'
      remote.password = 'Khiet@123'
      remote.allowAnyHosts = true
      stage('Remote SSH') {
        sshCommand remote: remote, command: "ls -lrt"
      }
}

Cần SSH Pipeline Steps
