import groovy.transform.Field
import groovy.json.JsonBuilder
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
//import org.wslite.rest.*
//@Grab('com.github.groovy-wslite:groovy-wslite:1.1.3')
import org.wslite.rest.RESTClient
import org.wslite.http.auth.*

class crNextStep {
    def CR_TASK_NEXT_STEP_REQ =  [PHI_DOMAIN_ID: "", PHI_CR_TYPE: "", PHI_CR_NUM: "", DEL_JIRA_STATUS:"",  PHI_ASSIGN_TO:"", PHI_MIGR_TYPE:""]

    crNextStep(phi_domain_id, phi_cr_type, phi_cr_num, del_jira_status,phi_assign_to,phi_migr_type) {
        this.CR_TASK_NEXT_STEP_REQ.PHI_DOMAIN_ID=phi_domain_id
        this.CR_TASK_NEXT_STEP_REQ.PHI_CR_TYPE=phi_cr_type
        this.CR_TASK_NEXT_STEP_REQ.PHI_CR_NUM=phi_cr_num
        this.CR_TASK_NEXT_STEP_REQ.DEL_JIRA_STATUS=del_jira_status
        this.CR_TASK_NEXT_STEP_REQ.PHI_ASSIGN_TO=phi_assign_to
        this.CR_TASK_NEXT_STEP_REQ.PHI_MIGR_TYPE=phi_migr_type
    }
}

@NonCPS
def buildMessage() {
    def cr_next = new crNextStep('HR','MIGR','CR000014','TEST','DTTLNARESH','S' )
    def builder = new JsonBuilder(cr_next)

}

@NonCPS
def sendMessage(JsonBuilder jsonMsg,String userid, String password){
     println "in send message 1"

    def client = new RESTClient("http://140.238.207.38:8000/PSIGW/RESTListeningConnector/PSFT_HR/DEL_API_CR_NEXT_STEP.v1/")
    try{
    client.authorization = new HTTPBasicAuthorization(userid, password)
    }catch (Exception e){
        println "auth fail"
    }
    def jsonStr=jsonMsg.toString()
    println "in send message 2"
    def response = client.post() {
        type "application/json"  // String or ContentType
        charset "US-ASCII"

        // one of the following
        //json id:"525", department:"Finance"
        //json jsonMsg
        //json CR_TASK_NEXT_STEP_REQ
        //text(CR_TASK_NEXT_STEP_REQ)
        //text(groovy.json.JsonOutput.toJson(jsonMsg))
        text(jsonStr)
    }
        println "after text ()"
        println jsonMsg.toString()
    println "HTTP post complete"
    println response.url
    println response.request
    println response.json
    println response.text
    println "in send message"
}

def call(String userid, String password) {
//    def call() {
    println userid + password
    JsonBuilder builder=buildMessage()
   // println builder
    println ""
    println builder.toString()
    //println json(builder.toString())
    
   // JsonSlurper slurper= new JsonSlurper().parseText(builder.toString())
    //sendMessage(groovy.json.JsonOutput.toJson(builder))

/* def json = new groovy.json.JsonBuilder()
 json name: "Guillaume", age: 33
println 'print jsonoutput'
println JsonOutput.toJson(json)
*/
    println 'calling send message'

    sendMessage(builder,userid,password)
  /*  getGroovyVersion() */
}

public String getGroovyVersion() {
        try {
                return org.codehaus.groovy.runtime.InvokerHelper.version
        }
        catch (Throwable ignore) { }
        return GroovySystem.version
}

//println getGroovyVersion()
call('usr', 'pwd')
//call()

