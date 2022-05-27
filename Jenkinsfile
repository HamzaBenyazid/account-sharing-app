def gv
def version
pipeline{
    agent any
  tools {
    maven 'maven'
  }
    stages{
        stage("init"){
            steps{
                script{
                    gv = load "script.groovy"
                    echo "loaded script.groovy"
                }
            }
        }
        stage("increment version"){
            steps{
                script{
                    echo "incrementing project version"
                    sh 'mvn build-helper:parse-version versions:set -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} versions:commit'
                    version = readMavenPom().getVersion()
                    env.IMAGE_TAG = "$version-$BUILD_NUMBER"
                    echo "the new version is : ${version}"
                }
            }
        }
        stage("build docker-image"){
            steps{
              script{
                // test are included here
                  echo "building the docker image ${IMAGE_TAG}"
                  gv.buildImage()
              }
            }

        }
         stage("push docker-image"){
            steps{
              script{
                  gv.pushImage()
              }
            }
        }
// //         stage("deploy"){
// //             // docker login is already done in ec2
// //             steps{
// //                 script{
// //                     def dockerCommand = "docker ps -aq | xargs -I {} docker rm -f {} && docker run -p 3000:8080 -d oubaydos/temp:${IMAGE_TAG}"
// //                     sshagent(['ec2-deployment-instance']) {
// //                         sh "ssh -o StrictHostKeyChecking=no ec2-user@54.173.119.64 ${dockerCommand}"
// //                     }
// //                 }
// //             }
// //
// //         }
        stage("commiting new version"){
            steps{
                script{
                    gv.commitVersion()
                }
            }
        }
    }
}