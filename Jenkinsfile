pipeline {
    agent any
    parameters {
        choice(name: 'BROWSER', choices: ['chrome','firefox','edge'], description: 'Primary browser')
        choice(name: 'ENV', choices: ['dev','qa','staging','prod'], description: 'Environment')
        booleanParam(name: 'CROSS_BROWSER', defaultValue: false, description: 'Run multiple browsers')
        string(name: 'BROWSERS', defaultValue: 'chrome,firefox', description: 'Comma-separated browsers for cross-browser')
        booleanParam(name: 'PARALLEL', defaultValue: true, description: 'Enable parallel execution')
        string(name: 'THREADS', defaultValue: '4', description: 'Threads for parallel execution')
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build & Test') {
            steps {
                script {
                    if (params.CROSS_BROWSER) {
                        // split browsers into a map for Jenkins parallel
                        def browserList = params.BROWSERS.split(",")
                        def parallelStages = [:]

                        browserList.each { b ->
                            parallelStages["Run on ${b.trim()}"] = {
                                bat """
                                    mvn clean test ^
                                    -Dbrowser=${b.trim()} ^
                                    -Denv=${params.ENV} ^
                                    -Dparallel=${params.PARALLEL} ^
                                    -Dthreads=${params.THREADS}
                                """
                            }
                        }
                        parallel parallelStages
                    } else {
                        // single browser
                        bat """
                            mvn clean test ^
                            -Dbrowser=${params.BROWSER} ^
                            -Denv=${params.ENV} ^
                            -Dparallel=${params.PARALLEL} ^
                            -Dthreads=${params.THREADS}
                        """
                    }
                }
            }
        }
        stage('Archive Reports') {
            steps {
                junit '**/target/surefire-reports/*.xml'
                archiveArtifacts artifacts: 'target/extent-report/**', allowEmptyArchive: true
            }
        }
    }
}
