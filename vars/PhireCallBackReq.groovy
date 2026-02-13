import org.phire.*
import groovy.json.JsonSlurper

@NonCPS
def call(String strReqMsg) {
    println "in PhireCallBackReq.groovy - call function" + strReqMsg

        println strReqMsg
            def jsonSlurper = new JsonSlurper()
            def reqObj =  jsonSlurper.parseText(strReqMsg)
            assert reqObj instanceof Map
            println "req obj in call" +reqObj.toString()
            println "req obj - domain id" + reqObj.DTTL_PHI_MIGR_STAT_REQ.PHI_DOMAIN_ID
            println "req obj - cr num" + reqObj.DTTL_PHI_MIGR_STAT_REQ.PHI_CR_NUM
            println "req obj - ticket status" + reqObj.DTTL_PHI_MIGR_STAT_REQ.DTTL_TICKET_STATUS
            println "req obj - ticket id" + reqObj.DTTL_PHI_MIGR_STAT_REQ.DTTL_TICKET_ID
            println "req obj - migr type" + reqObj.DTTL_PHI_MIGR_STAT_REQ.PHI_MIGR_TYPE
            println "req obj - action type" + reqObj.DTTL_PHI_MIGR_STAT_REQ.DTTL_ACTION_TYPE
            println "req obj - action descr" + reqObj.DTTL_PHI_MIGR_STAT_REQ.PHI_ACTION_DESCR
    def mapCallbackActReq = new PhireCallbackActReq(reqObj.DTTL_PHI_MIGR_STAT_REQ.PHI_DOMAIN_ID, reqObj.DTTL_PHI_MIGR_STAT_REQ.PHI_CR_NUM, reqObj.DTTL_PHI_MIGR_STAT_REQ.DTTL_TICKET_STATUS, reqObj.DTTL_PHI_MIGR_STAT_REQ.DTTL_TICKET_ID, reqObj.DTTL_PHI_MIGR_STAT_REQ.PHI_MIGR_TYPE, reqObj.DTTL_PHI_MIGR_STAT_REQ.DTTL_ACTION_TYPE, reqObj.DTTL_PHI_MIGR_STAT_REQ.PHI_ACTION_DESCR)
            println "phireCallbackActReq - domain id" + mapCallbackActReq.PHI_CALLBACK_REQ.PHI_DOMAIN_ID
            println "phireCallbackActReq - cr num" + mapCallbackActReq.PHI_CALLBACK_REQ.PHI_CR_NUM
            println "phireCallbackActReq - ticket status" + mapCallbackActReq.PHI_CALLBACK_REQ.DTTL_TICKET_STATUS
            println "phireCallbackActReq - ticket id" + mapCallbackActReq.PHI_CALLBACK_REQ.DTTL_TICKET_ID
            println "phireCallbackActReq - migr type" + mapCallbackActReq.PHI_CALLBACK_REQ.PHI_MIGR_TYPE
            println "phireCallbackActReq - action type" + mapCallbackActReq.PHI_CALLBACK_REQ.DTTL_ACTION_TYPE
            println "phireCallbackActReq - action descr" + mapCallbackActReq.PHI_CALLBACK_REQ.PHI_ACTION_DESCR
            //println "phireCallbackActReq in call" + mapCallbackActReq
    //def phireCallbackActReq = new PhireCallbackActReq(strReqMsg)
     println "in PhireCallBackReq.groovy - B4 return" + mapCallbackActReq.PHI_CALLBACK_REQ
    return mapCallbackActReq
}