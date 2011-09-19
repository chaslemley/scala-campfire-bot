package campfire

import org.apache.commons.httpclient._
import org.apache.commons.httpclient.auth.AuthScope
import org.apache.commons.httpclient.methods.GetMethod
import org.apache.commons.httpclient.methods.PostMethod
import org.apache.commons.httpclient.methods.StringRequestEntity

import net.liftweb.json
import net.liftweb.json.JValue
import net.liftweb.json.JsonAST.JObject
import net.liftweb.json._

object HTTP {
  val client = new HttpClient
  val credentials = new UsernamePasswordCredentials("API_TOKEN:X")
  
  client.getState.setCredentials(AuthScope.ANY, credentials)
  client.getParams.setAuthenticationPreemptive(true)

  def get(endPoint:String): JValue = {
    val method = new GetMethod("https://csinteractive.campfirenow.com"+endPoint+".json")
    
    try {
      client.executeMethod(method)
      json.parse(method.getResponseBodyAsString)
    } finally {
      method.releaseConnection()
    }
  }

  def post(endPoint: String, data: JObject) = {
    val method = new PostMethod("https://csinteractive.campfirenow.com"+endPoint+".json")
    method.setRequestEntity(new StringRequestEntity(compact(render(data)), "application/json", null))
    
    try {
      client.executeMethod(method)
    } finally {
      method.releaseConnection()
    }
  }
}