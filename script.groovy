def buildJar(){
    echo 'building application...'
    sh 'mvn package'
}

def buildAndPushImage(){
    echo 'building the docker image...And...Push'
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS' , usernameVariable: 'USER')]){
        sh 'docker build -t javapig/demo-app:jma-2.0 .'
        sh "echo $PASS | docker login -u $USER --password-stdin "
        sh 'docker push javapig/demo-app:jma-2.0'
}

def deployApp(){
     echo 'deploying the application...'
}

return this
