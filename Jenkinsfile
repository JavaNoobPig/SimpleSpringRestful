pipeline {
  agent any
  tools {
    maven 'maven-3.8.4'
  }
  stages {
    stage('build jar') {
      steps {
        echo 'building application...'
        sh 'mvn package'
      }
    }
    stage('build image') {
      steps {
        echo 'building the docker image...'
        withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
          sh 'docker build -t javapig/demo-app:jma-2.0 .'
          sh "echo $PASS | docker login -u $USER --password-stdin "
          sh 'docker push javapig/demo-app:jma-2.0'
        }
      }
    }
    stage('deploy') {
      steps {
        echo 'deploying application...'
      }
    }
  }
}
