pipeline {
  agent any
  environment{
     NEW_VERSION = '1.3.0'
  }
  stages {
    stage('build') {
      when {
        expression {
          NEW_VERSION == '1.3.0'
        }
      }
      steps {
        echo 'building application...'
      }
    }
    stage('test') {
      when {
        expression {
          NEW_VERSION == '1.3.3'
        }
      }
      steps {
        echo 'testing application...'
      }
    }
    stage('deploy') {
      steps {
        echo "${NEW_VERSION}"
        echo 'deploying application...'
      }
    }
  }
}
