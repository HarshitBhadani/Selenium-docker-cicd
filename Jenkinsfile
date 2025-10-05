pipeline{
    
    agent any

    environment{
        NUMBER = 5
    }

    stages{
        stage('Build Jar'){
            steps{
                bat "mvn clean package -DskipTests"
            }
        }

        stage('Build Docker Image'){
            steps{
                bat "docker build -t harshitbhadani/flightreservationtest ./"
            }
        }

        stage('Push Docker Image'){
            steps{
                bat "docker push harshitbhadani/flightreservationtest2 "
            }
        }
    }

    post{
        success{
            echo "build Success"
        }
        always{
            echo "clean up"
        }
    }

}