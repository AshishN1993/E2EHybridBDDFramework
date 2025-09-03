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
def browsers = params.CROSS_BROWSER ? params.BROWSERS : params.BROWSER
def parallelFlag = params.PARALLEL ? 'true' : 'false'
sh "mvn clean test -Dbrowser=${browsers} -Denv=${params.ENV} -DcrossBrowser=${params.CROSS_BROWSER} -Dparallel=${parallelFlag} -Dsurefire.threadCount=${params.THREADS} -Dsurefire.parallel=${params.PARALLEL ? 'methods' : ''}"
}
}
}
stage('Archive Reports') {
steps {
junit '**/target/surefire-reports/*.xml' // optional
archiveArtifacts artifacts: 'target/extent-report/**', allowEmptyArchive: true
}
}
}
}