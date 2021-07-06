void call(){
    //just to make this fail and reproduce my issue, this build will fail on an even BUILD_NUMBER
    // if(env.BUILD_NUMBER.toInteger() % 2 == 0){
    // pipelineConfig.description = groovy.json.JsonOutput.toJson([message: '!!! EVEN NUMBERS ARE THE DEVIL !!!'])
    //     error '!!! EVEN NUMBERS ARE THE DEVIL !!!'
    // }

    pipelineConfig.description = [message: "${BUILD_NUMBER} --- foo"]
}
