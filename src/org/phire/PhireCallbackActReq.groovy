
/*phire pkg  */

package org.phire

import groovy.transform.Field
import groovy.json.JsonSlurper


class PhireCallbackActReq {
    def PHI_CALLBACK_REQ =  [
        PHI_DOMAIN_ID: "", 
        PHI_CR_NUM: "",
        DTTL_TICKET_STATUS:"", //Phire action status
        DTTL_TICKET_ID:"", //Jira ticket id
        PHI_MIGR_TYPE:"", //Phire migration type
        DTTL_ACTION_TYPE:"", //Phire action type
        PHI_ACTION_DESCR:"", //Phire action description response from phire
        ]

        
    PhireCallbackActReq(String phiDomainId, String phiCrNum, String ticketStatus, String ticketId, String phiMigrType, String actionType, String actionDescr) {
        this.PHI_CALLBACK_REQ.PHI_DOMAIN_ID = phiDomainId ?: ""
        this.PHI_CALLBACK_REQ.PHI_CR_NUM = phiCrNum ?: ""
        this.PHI_CALLBACK_REQ.DTTL_TICKET_STATUS = ticketStatus ?: ""
        this.PHI_CALLBACK_REQ.DTTL_TICKET_ID = ticketId ?: ""
        this.PHI_CALLBACK_REQ.PHI_MIGR_TYPE = phiMigrType ?: ""
        this.PHI_CALLBACK_REQ.DTTL_ACTION_TYPE = actionType ?: ""
        this.PHI_CALLBACK_REQ.PHI_ACTION_DESCR = actionDescr ?: ""
    }

    def PhireCallbackActReq()
    {
    } 

/*
    def PhireCallbackActReq(strReqMsg)
    {
        def jsonSlurper = new JsonSlurper()
        println "req constr" + strReqMsg
        def reqObj =  jsonSlurper.parseText(strReqMsg)
        assert reqObj instanceof Map
        println "req cnstr 2 " + reqObj.toString()
        reqObj.DTTL_PHI_MIGR_STAT_REQ.DTTL_TICKET_ID
        this.PhireCallbackActReq(reqObj.DTTL_PHI_MIGR_STAT_REQ.PHI_DOMAIN_ID, reqObj.DTTL_PHI_MIGR_STAT_REQ.PHI_CR_NUM, reqObj.DTTL_PHI_MIGR_STAT_REQ.DTTL_TICKET_STATUS, reqObj.DTTL_PHI_MIGR_STAT_REQ.DTTL_TICKET_ID, reqObj.DTTL_PHI_MIGR_STAT_REQ.PHI_MIGR_TYPE, reqObj.DTTL_PHI_MIGR_STAT_REQ.DTTL_ACTION_TYPE, reqObj.DTTL_PHI_MIGR_STAT_REQ.PHI_ACTION_DESCR)

    }   */
}

