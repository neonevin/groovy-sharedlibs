import org.phire.*
import groovy.json.JsonSlurper

@NonCPS
def call(String strReqMsg) {
    println "in PhireCallBackReq.groovy - call function" + strReqMsg

        println strReqMsg
            def jsonSlurper = new JsonSlurper()
            def reqObj =  jsonSlurper.parseText(strReqMsg)
            assert reqObj instanceof Map
            println "req obj" +reqObj.toString()
    def phireCallbackActReq = new PhireCallbackActReq(strReqMsg)
     println "in PhireCallBackReq.groovy - B4 return" + phireCallbackActReq.toString()
    return phireCallbackActReq
}