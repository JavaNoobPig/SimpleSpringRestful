#!/usr/bin/env groovy

pipeline {
  agent any
  tools {
    maven 'maven-3.8.4'
  }
  stages {
    stage('increment version') {
      steps {
        script {
            echo 'incrementing app version...'
            sh 'mvn build-helper:parse-version versions:set \
                -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
                versions:commit'
            def version readMavenPom().getVersion()
            env.IMAGE_NAME = "$version-$BUILD_NUMBER"
        }
      }
    }
    stage('build Jar') {
      steps {
        script {
            echo 'building application...'
            sh 'mvn clean package'
        }
      }
    }
    stage('build image') {
      steps {
        script {
            echo 'building the docker image'
            echo "Current IMAGE_NAME $IMAGE_NAME"

        }
      }
    }
  }
}
