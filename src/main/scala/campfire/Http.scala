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
  val credentials = new UsernamePasswordCredentials("APITOKEN:X")
  
  def get(endPoint:String): JValue = {
    val client = new HttpClient
    val method = new GetMethod("https://chaschats.campfirenow.com"+endPoint+".json")

    client.getState.setCredentials(AuthScope.ANY, credentials)
    client.getParams.setAuthenticationPreemptive(true)

    try {
      client.executeMethod(method)
      json.parse(method.getResponseBodyAsString)
    } finally {
      method.releaseConnection()
    }
  }

  def post(endPoint: String, data: JObject) = {
    val client = new HttpClient
    val method = new PostMethod("https://chaschats.campfirenow.com"+endPoint+".json")
    method.setRequestEntity(new StringRequestEntity(compact(render(data)), "application/json", null))

    client.getState.setCredentials(AuthScope.ANY, credentials)
    client.getParams.setAuthenticationPreemptive(true)

    try {
      client.executeMethod(method)
    } finally {
      method.releaseConnection()
    }
  }

  def stream(endPoint: String, processor: StreamProcessor) = {
    val client = new HttpClient
    val method = new GetMethod("http://streaming.campfirenow.com"+endPoint+".json")

    client.getState.setCredentials(AuthScope.ANY, credentials)
    client.getParams.setAuthenticationPreemptive(true)

    try {
      client.executeMethod(method)
      processor.process(method.getResponseBodyAsStream)
    } finally {
      method.releaseConnection()
    }
  }
}