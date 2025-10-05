pipeline{
    
    agent {
    	label 'windows'
    }

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
                bat "docker build -t harshitbhadani/flightreservationtest:latest ./"
            }
        }

        stage('Push Docker Image'){
        	environment{
        		DOCKER_HUB = credentials('dockerhub-creds')
        	}
            steps{
            	bat 'docker login -u %DOCKER_HUB_USR% -p %DOCKER_HUB_PSW%'
                bat "docker push harshitbhadani/flightreservationtest:latest"
                bat "docker tag harshitbhadani/flightreservationtest:latest harshitbhadani/flightreservationtest:%env.BUILD_NUMBER%"
                bat "docker push harshitbhadani/flightreservationtest:%env.BUILD_NUMBER%"
            }
        }
    }

    post{
        
        always{
            bat "docker logout"
        }
    }

}