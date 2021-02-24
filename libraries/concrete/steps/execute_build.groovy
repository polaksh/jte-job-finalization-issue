void call(){
    stage('Build'){
        try{
            //just to make this fail and reproduce my issue, this build will fail on an even BUILD_NUMBER
            if(env.BUILD_NUMBER.toInteger() % 2 == 0){
                error '!!! EVEN NUMBERS ARE THE DEVIL !!!'
            }
        }
        catch(Throwable t){
            currentBuild.result = "FAILURE"     
            currentBuild.description = groovy.json.JsonOutput.toJson([message: t.message])
        }
    }
}
