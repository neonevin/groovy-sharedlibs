import org.phire.*

def call(String strReqMsg) {
    def phireCallbackActReq = new PhireCallbackActReq(strReqMsg)
    return phireCallbackActReq
}