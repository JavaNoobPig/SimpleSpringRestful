pipeline {
  agent {
    kubernetes {
      yaml '''
        apiVersion: v1
        kind: Pod
        spec:
          containers:
          - name: maven
            image: registry.redhat.io/openshift4/ose-jenkins-agent-maven:v4.10.0-202206270836.p0.gc5b7159.assembly.stream
            command:
            - cat
            tty: true
          imagePullSecrets:
          - name: registry-redhat-io-secret
        '''
    }
  }
  stages {
    stage('local check') {
      agent { label 'local' }
      steps {
          script {
          sh 'ls -l'
          }
      }
    }
    stage('Run maven') {
      steps {
        container('maven') {
          sh 'mvn -version'
          sh 'which oc'
          sh '''
          oc login --token=eyJhbGciOiJSUzI1NiIsImtpZCI6IlRxOWdGTHl5aWFCbVhrdWJjNkp5V3c4OG9ENWtQSWQ0d0dnY3NLR0pQOUkifQ.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJqayIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VjcmV0Lm5hbWUiOiJqa3NhLXRva2VuLTRycWRnIiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZXJ2aWNlLWFjY291bnQubmFtZSI6Imprc2EiLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC51aWQiOiJhM2FiMDdkOS05Y2NmLTRiZTEtOGI3Ny1lNWIzYjljNTA2YWEiLCJzdWIiOiJzeXN0ZW06c2VydmljZWFjY291bnQ6ams6amtzYSJ9.VQih-5P7j2ZyoH3I2tVn0tLc3xr1yohByIhqxVlIcEIDlQW987gbouWdUm2-bYY96mqN4UMw8R1AZNYSebMqrzV7ZCxnJHyRm-cN5rdcL4qcueshxJW8itZRmnPm6F-gGwB6NQSUbCp2lF6f7LbBtQmtF4MbbSZ6Dx-svUzAJ7LXEd2EeyHb-qj6jMTtnJJlaNx4wW11E31gVINAfRw1PEN_3MLXoOUAZYL09pLhDlTCXzO-qu6uQj14wr9Bcn52-kh0-Wy-xKSOI0xPueHUbveIYLyJxrgwX7PBiAAR9a3s6LM5xFZ9jzjcxGKFcqHU0bCM7u1qOBCzxleFMiy4MJHZ_UlynRuX0NFd2WKDyG3ZwZO6tA0LpJBT6unCWQrpKR-K0kkJM2nw5V7gKaK9-k8aDTu-iksQjo7ZGKdLGfD9CMVFFHUMIMu9iF8bikqHMgHMDs3CmXB-XRZfBB0L1S_-R91ADdAUQDZbNoNvmmgYpDNd7vq871WgNANGgvRSAGRFpoPYqcJemNTOKVZhCbVj-XBi1q7rLWPUfopmI8i7Q6wwvinmh2oY0kOWsTcRJJ4sWwhxrmkdrEc0nps0lf_65rU-kIUPb6Z1Fx5AyTKWvMyDRaP2w9DQqCrcsKLW-33SBkjo7-fqWuOJqoYiGxqJgRE1CY9fFCooDkD0QWo  --server=https://api.cluster-65wbs.65wbs.sandbox2884.opentlc.com:6443 --insecure-skip-tls-verify=true
          oc whoami
          '''
        }
      }
    }
    stage('Run oc') {
      agent { label 'local' }
      steps {
          script {
          echo 'Hello World...'
          sh "echo 'Hello World' > test.txt"
          withCredentials([string(credentialsId: 'sa-token', variable: 'SECRET')]) {
          echo "My secret text is '${SECRET}'"
          sh '''
          oc login --token=${SECRET}  --server=https://api.cluster-65wbs.65wbs.sandbox2884.opentlc.com:6443 --insecure-skip-tls-verify=true
          oc whoami
          '''
          }
          }
      }
    }
  }
}
