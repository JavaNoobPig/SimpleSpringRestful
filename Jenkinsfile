pipeline {
  agent any

  stages {
    stage('build') {
      when {
        expression {
          BRANCH_NAME == 'jenkins-jobs'
        }
      }
      steps {
        echo 'building application...'
      }
    }
    stage('test') {
      when {
        expression {
          BRANCH_NAME == 'dev'
        }
      }
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
