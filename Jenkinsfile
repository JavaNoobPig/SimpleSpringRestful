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
          def matcher = readFile('pom.xml') = ~'<version>(.+)</version>'
          def version = matcher[1][1]
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
          withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
            sh "docker build -t javapig/demo-app:$IMAGE_NAME ."
            sh "echo $PASS | docker login -u $USER --password-stdin "
            sh "docker push javapig/demo-app:$IMAGE_NAME"
          }
        }
      }
    }
  }
}