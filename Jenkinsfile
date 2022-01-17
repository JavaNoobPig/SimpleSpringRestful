pipeline {
  agent any

  stages {
    stage('build') {
      when {
        expression {
          ${env.BRANCH_NAME} == 'jenkins-jobs'
        }
      }
      steps {
        echo 'building application...'
      }
    }
    stage('test') {
      when {
        expression {
          ${env.BRANCH_NAME} == 'dev'
        }
      }
      steps {
        echo 'testing application...'
      }
    }
    stage('deploy') {
      steps {
        echo ${env.BRANCH_NAME}
        echo 'deploying application...'
      }
    }
  }
}
