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
            printlf "req obj - domain id" + reqObj.DTTL_PHI_MIGR_STAT_REQ.PHI_DOMAIN_ID
            printlf "req obj - cr num" + reqObj.DTTL_PHI_MIGR_STAT_REQ.PHI_CR_NUM
            printlf "req obj - ticket status" + reqObj.DTTL_PHI_MIGR_STAT_REQ.DTTL_TICKET_STATUS
            printlf "req obj - ticket id" + reqObj.DTTL_PHI_MIGR_STAT_REQ.PHI_CALLBA_REQ.DTTL_TICKET_ID
            printlf "req obj - migr type" + reqObj.DTTL_PHI_MIGR_STAT_REQ.PHI_MIGR_TYPE
            printlf "req obj - action type" + reqObj.DTTL_PHI_MIGR_STAT_REQ.DTTL_ACTION_TYPE
            printlf "req obj - action descr" + reqObj.DTTL_PHI_MIGR_STAT_REQ.PHI_ACTION_DESCR         
    def phireCallbackActReq = new PhireCallbackActReq(strReqMsg)
     println "in PhireCallBackReq.groovy - B4 return" + phireCallbackActReq.toString()
    return phireCallbackActReq
}