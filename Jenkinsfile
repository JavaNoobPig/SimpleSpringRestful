def gv

pipeline {
  agent any
  parameters {
    choice(name: 'VERSION', choices: ['1.1.0','1.2.0','1.3.0'], description: 'hello')
    booleanParam(name: 'executeTest', defaultValue: true, description: 'boolean value' )
  }
  stages {
    stage('init') {
      steps {
        script {
            gv = load "script.groovy"
        }
      }
    }
    stage('build') {
      steps {
        script {
            gv.buildApp
        }
      }
    }
    stage('test') {
      when {
        expression {
          params.executeTest == true
        }
      }
      steps {
        script {
            gv.testApp
        }
      }
    }
    stage('deploy') {
      steps {
        script {
            gv.deployApp
        }
      }
    }
  }
}
