import org.phire.*
import groovy.json.JsonSlurper

@NonCPS
def call(String strReqMsg) {
    println "in PhireCallBackReq.groovy - call function" + strReqMsg

    println strReqMsg
    def jsonSlurper = new JsonSlurper()
    def reqObj =  jsonSlurper.parseText(strReqMsg)
    assert reqObj instanceof Map
    def mapCallbackActReq = new PhireCallbackActReq(reqObj.DTTL_PHI_MIGR_STAT_REQ.PHI_DOMAIN_ID, reqObj.DTTL_PHI_MIGR_STAT_REQ.PHI_CR_NUM, reqObj.DTTL_PHI_MIGR_STAT_REQ.DTTL_TICKET_STATUS, reqObj.DTTL_PHI_MIGR_STAT_REQ.DTTL_TICKET_ID, reqObj.DTTL_PHI_MIGR_STAT_REQ.PHI_MIGR_TYPE, reqObj.DTTL_PHI_MIGR_STAT_REQ.DTTL_ACTION_TYPE, reqObj.DTTL_PHI_MIGR_STAT_REQ.PHI_ACTION_DESCR)
            //println "phireCallbackActReq in call" + mapCallbackActReq
    //def phireCallbackActReq = new PhireCallbackActReq(strReqMsg)
     //println "in PhireCallBackReq.groovy - B4 return" + mapCallbackActReq.PHI_CALLBACK_REQ
    return mapCallbackActReq
}