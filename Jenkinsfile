#!/usr/bin/env groovy

pipeline {
  agent any
  tools {
    maven 'maven'
  }
  stages {
    stage('increment version') {
      steps {
        script {
          echo 'incrementing app version...'
          sh 'mvn build-helper:parse-version versions:set \
                -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
                versions:commit'
          def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
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
    stage('deploy') {
      steps {
        script {
          echo 'deploy docker image to EC2....'
        }
      }
    }
    stage('commit version update') {
      steps {
        script {
          withCredentials([usernamePassword(credentialsId: 'GitRepoCredentials', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
            sh 'git config --global user.email "jenkins@example.com"'
            sh 'git config --global user.name "jenkins"'

            sh 'git status'
            sh 'git branch'
            sh 'git config --list'

            sh "git remote set-url origin https://${USER}:${PASS}@github.com/JavaNoobPig/SimpleSpringRestful.git"
            sh 'git add .'
            sh 'git commit -m "ci: version bump"'
            sh 'git push origin HEAD:jenkins-jobs-version-ctl'
          }
        }
      }
    }
  }
}
