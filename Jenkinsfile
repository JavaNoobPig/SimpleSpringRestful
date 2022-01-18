pipeline {
  agent any
  tools {
    maven 'maven-3.8.4'
  }
  stages {
    stage('build jar') {
      steps {
        script {
          echo 'building application...'
          sh 'mvn package'
        }

      }
    }
    stage('build image') {
      steps {
        script {
          echo 'building the docker image...'
          withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
            sh 'docker build -t javapig/demo-app:jma-2.0 .'
            sh "echo $PASS | docker login -u $USER --password-stdin "
            sh 'docker push javapig/demo-app:jma-2.0'
          }
        }
      }
    }
    stage('deploy') {
      steps {
        script {
          echo 'deploying application...'
        }
      }
    }
  }
}
