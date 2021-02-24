void call(){
    stage('Build Setup'){
        try{
            echo "build pre-requisites come here"
        }
        catch(Throwable t){
            currentBuild.result = "FAILURE"     
            currentBuild.description = groovy.json.JsonOutput.toJson([message: t.message])
        }
    }
}
