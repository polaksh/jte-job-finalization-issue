void call(){
    echo "This is my clone step"
    pipelineConfig.foo = ["${env.BUILD_NUMBER}_foo"]
}
