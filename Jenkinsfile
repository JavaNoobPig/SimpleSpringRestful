pipeline {
  agent any
  environment{
     NEW_VERSION = '1.3.0'
     //SERVER_CREDENTIALS = credentials('server-credenials')
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
        //echo "build with ${SERVER_CREDENTIALS}"
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
        echo 'deploying application...'
        echo "${NEW_VERSION}"
        withCredentials([usernamePassword(credentialsId: 'server-credenials', 
                                          usernameVariable: 'USER', 
                                          passwordVariable: 'PWD')]) {
          echo "some script ${USER} ${PWD}"
        }
        
      }
    }
  }
}
