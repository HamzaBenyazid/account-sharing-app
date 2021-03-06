def buildJar(){
    echo "building the jar"
    sh 'mvn clean package'
}
def buildImage(){
    echo "${IMAGE_TAG}"
    sh "docker build -t oubaydos/temp:${IMAGE_TAG} ."
}
def pushImage(){
    echo "pushing the docker image"
    withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]){
        sh "echo $PASSWORD | docker login -u $USERNAME --password-stdin"
        sh "docker push oubaydos/temp:$IMAGE_TAG"
    }
}
def commitVersion(){
    withCredentials([usernamePassword(credentialsId: 'githubWithToken', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]){
        sh "git config user.name 'jenkins'"
        sh "git config user.email 'jenkins@oubaydos.com'"
        sh "git remote set-url origin https://${PASSWORD}@github.com/HamzaBenyazid/account-sharing-app"
        sh "git add . "
        sh "git commit -m 'update version'"
        sh "git push origin HEAD:${BRANCH_NAME}"
    }
}
return this