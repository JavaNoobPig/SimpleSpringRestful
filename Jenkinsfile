def branchName = getCurrentBranch()
pipeline {
  agent any

  stages {
    stage('build') {
      when {
        expression {
          branchName == 'jenkins-jobs'
        }
      }
      steps {
        echo 'building application...'
      }
    }
    stage('test') {
      when {
        expression {
          branchName == 'dev'
        }
      }
      steps {
        echo 'testing application...'
      }
    }
    stage('deploy') {
      steps {
        echo "${branchName}"
        echo 'deploying application...'
      }
    }
  }
}

def getCurrentBranch () {
    return sh (
        script: 'git rev-parse --abbrev-ref HEAD',
        returnStdout: true
    ).trim()
}
