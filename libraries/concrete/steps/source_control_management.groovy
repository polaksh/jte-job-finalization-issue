void call(){
    stage('SCM'){
        try{
            echo "This is my clone step"
        }
        catch(Throwable t){
            currentBuild.result = "FAILURE"     
            currentBuild.description = groovy.json.JsonOutput.toJson([message: t.message])
        }
    }
}
