def buildJar(){
    echo "building the jar"
    sh 'mvn clean package'
}
def buildImage(){
    echo "${IMAGE_TAG}" //${IMAGE_TAG}
    sh "docker build -t oubaydos/temp:tmm ."
}
def pushImage(){
    echo "pushing the docker image"
    withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]){
        sh "echo $PASSWORD | docker login -u $USERNAME --password-stdin"
        sh "docker push oubaydos/temp:$IMAGE_TAG"
    }
}
def commitVersion(){
    withCredentials([usernamePassword(credentialsId: 'gitlab', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]){
        sh "git config user.name 'jenkins'"
        sh "git config user.email 'jenkins@oubaydos.com'"
        sh "git remote set-url origin https://${USERNAME}:${PASSWORD}@github.com/Coaching-Website-Team/Coaching-Website"
        sh "git add . "
        sh "git commit -m 'update version'"
        sh "git push origin HEAD:master"
    }
}
return this