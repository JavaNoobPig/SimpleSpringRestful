pipeline {
  agent any
  tools {
    maven 'maven-3.8.4'
  }
  stages {
    stage('build') {
      steps {
        echo 'building application...'
        sh "mvn package"
      }
    }
    stage('test') {
      steps {
        echo 'testing application...'
      }
    }
    stage('deploy') {
      steps {
        echo 'deploying application...'
      }
    }
  }
}
