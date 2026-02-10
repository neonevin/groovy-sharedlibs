import org.phire.*

def call(String strReqMsg) {
    println "in PhireCallBackReq.groovy - call function"
    def phireCallbackActReq = new PhireCallbackActReq(strReqMsg)
    return phireCallbackActReq
}