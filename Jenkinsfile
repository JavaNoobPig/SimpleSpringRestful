pipeline {
    agent any

    stages {
        stage('build') {
            steps {
                echo 'building application...'
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
    post {
    always{
      echo "always do after all stages"
    }
    success{
      echo "success do after all stages"
    }
    failure{
      echo "failure do after all stages"
    }
  }
}
