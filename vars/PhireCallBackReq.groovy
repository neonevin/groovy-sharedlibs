import org.phire.*

@NonCPS
def call(String strReqMsg) {
    println "in PhireCallBackReq.groovy - call function" + strReqMsg
    def phireCallbackActReq = new PhireCallbackActReq(strReqMsg)
    return phireCallbackActReq
}