void call(){
    echo "This is my clone step"
    currentBuild.result = "FAILURE"     
    currentBuild.description = groovy.json.JsonOutput.toJson([message: t.message])
}
