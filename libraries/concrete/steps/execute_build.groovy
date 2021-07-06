void call(){
    //just to make this fail and reproduce my issue, this build will fail on an even BUILD_NUMBER
    // if(env.BUILD_NUMBER.toInteger() % 2 == 0){
    // pipelineConfig.description = groovy.json.JsonOutput.toJson([message: '!!! EVEN NUMBERS ARE THE DEVIL !!!'])
    //     error '!!! EVEN NUMBERS ARE THE DEVIL !!!'
    // }
    def lst = ['a','b','c','d','e','f']
    pipelineConfig.bar = lst.collect{i-> "${env.JOB_NAME}_${i}_${env.BUILD_NUMBER}".toString()}
}
