@Init()
void init(){
    echo '>>> sending slack notification for "BUILD STARTED"'
}

@AfterStep()
void afterStep(){
/*****************************
I need to somehow get the real build status here
since:
1. when the currnet step finishes the currentBuild.result is not updated
2. configuration is not being shared between libraries 
******************************/
    config.status = currentBuild.result
    if(currentBuild.result == null || currentBuild.result == 'SUCCESS'){
        config.status = 'SUCCESS'
    }

//the only way I found to pass some information across libraries was using the currentBuild object, I write a JSON into its description and read it here
    if(currentBuild.description?.trim()){
        def info = readJSON text: currentBuild.description
        if(config.status != 'SUCCESS'){
            config.error = info.message
            def finalized_message = "${hookContext.step}: ${config.error?.trim() ? config.error : 'go to build log for more info'}"
            currentBuild.description = finalized_message
            error finalized_message
        }
    }
}

@CleanUp()
void cleanup(){
    // in my real pipeline i'm generating a slack notification payload here, for this example i will just create the message        
    def message = [
        ":jenkins:${config.status == 'SUCCESS' ? ':white_check_mark:' : ':bangbang:'} ${env.JOB_BASE_NAME} #${env.BUILD_NUMBER} '${config.status}'",
        config.error?.trim() ? ": ${config.error}".toString() : ''
    ].join(' ')

    echo """slackSend(
        channel: ${config.slack.channel},
        botUser: true,
        tokenCredentialId: 'ts-custom-jenkins',
        teamDomain: 'transmitsecurity.slack.com',
        blocks: ${message}
    )"""

    println groovy.json.JsonOutput.prettyPrint(groovy.json.JsonOutput.toJson(pipelineConfig))
}
