pipeline {
    agent any  // 모든 에이전트에서 실행

    stages {
        stage('Checkout') {
            steps {
                // Git에서 main 브랜치를 체크아웃
                git branch: 'main', url: 'https://github.com/JIMINJI1/nbcamp2.git'
            }
        }

        stage('Set Permissions') {
            steps {
                // Gradle Wrapper에 실행 권한 부여
                sh 'chmod +x gradlew'
            }
        }

        stage('Build and Test') {
            steps {
                // Gradle 빌드 실행 (테스트 제외)
                sh '''
                ./gradlew clean build --info --stacktrace
                '''
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Docker 이미지 빌드 (latest 태그)
                    def image = docker.build('usermin123/schedule')
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                // Docker Hub 자격증명을 사용하여 로그인 및 푸시
                withCredentials([usernamePassword(credentialsId: 'dockerhub-jenkins', usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_PASSWORD')]) {
                    script {
                        // Docker Hub에 로그인하고 이미지를 푸시
                        docker.withRegistry('https://index.docker.io/v1/', 'dockerhub-jenkins') {
                             sh '''
                                 docker login -u $DOCKERHUB_USERNAME -p $DOCKERHUB_PASSWORD  // Docker Hub 로그인
                                 docker push usermin123/schedule:latest  // 이미지 푸시
                                '''
                        }
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                echo "Deploy is not yet implemented"
            }
        }
    }

    post {
        success {
            // 파이프라인 성공 시 출력
            echo 'Pipeline succeeded!'
        }
        failure {
            // 파이프라인 실패 시 출력
            echo 'Pipeline failed!!'
        }
    }
}
