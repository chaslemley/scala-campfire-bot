package campfire

import org.apache.commons.httpclient._
import org.apache.commons.httpclient.auth.AuthScope
import org.apache.commons.httpclient.methods.GetMethod
import org.apache.commons.httpclient.methods.PostMethod
import org.apache.commons.httpclient.methods.StringRequestEntity

import net.liftweb.json
import net.liftweb.json._
import net.liftweb.json.JsonDSL._
import net.liftweb.json.JValue

trait RemoteObject {
  implicit val formats = net.liftweb.json.DefaultFormats
  val client = new HttpClient
  client.getParams.setAuthenticationPreemptive(true)
  val credentials = new UsernamePasswordCredentials("197404a4747257a9f56668d19cefe9a2a794c93c:X")

  def fetchData(endPoint:String): JValue = {
    val method = new GetMethod("http://csinteractive.campfirenow.com"+endPoint+".json")
    client.getState.setCredentials(this.getAuthScope(method), credentials)

    try {
      client.executeMethod(method)
      json.parse(method.getResponseBodyAsString)
    }  catch {
     case e: Exception => throw new IllegalArgumentException("Only values between 1 and 10")
    } finally {
      method.releaseConnection()
    }
  }

  def postData(endPoint:String) = {
    val method = new PostMethod("http://csinteractive.campfirenow.com"+endPoint+".json")
    method.setFollowRedirects(true)
    val message =
      ("message" ->
        ("type" -> "TextMessage") ~
        ("body" -> "hello"))

    println(compact(render(message)))

    method.setRequestEntity(new StringRequestEntity(compact(render(message)).toString, "application/json", null))
    client.getState.setCredentials(this.getAuthScope(method), credentials)

    try {
      val returnCode = client.executeMethod(method)
      println(returnCode)
    } catch {
     case e: Exception => println(e.getMessage)
    } finally {
      method.releaseConnection()
    }
  }

  def getAuthScope(method:HttpMethod): AuthScope = {
    val url = new HttpURL(method.getURI.toString)
    new AuthScope(url.getHost, 443) // SSL port 443 !
  }
}