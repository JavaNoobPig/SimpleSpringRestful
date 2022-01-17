pipeline {
  agent any
  parameters {
    //string(name: 'VERSION', defaultValue: '', description:'version to deploy on prod')
    choice(name: 'VERSION', choices: ['1.1.0','1.2.0','1.3.0'], description: 'hello')
    booleanParam(name: 'executeTest', defaultValue: true, description: 'boolean value' )
  }
  stages {
    stage('build') {
      steps {
        echo 'building application...'
      }
    }
    stage('test') {
      when {
        expression {
          params.executeTest == true
        }
      }
      steps {
        echo 'testing application...'
      }
    }
    stage('deploy') {
      steps {
        echo 'deploying application...'
        echo "deploying version ${params.VERSION}"
      }
    }
  }
}
