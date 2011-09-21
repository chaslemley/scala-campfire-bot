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

class HTTPClient {
  val credentials:UsernamePasswordCredentials = null
  
  def get(url:String): JValue = {
    val client = new HttpClient
    val method = new GetMethod(url)

    if (credentials != null) {
      client.getState.setCredentials(AuthScope.ANY, credentials)
      client.getParams.setAuthenticationPreemptive(true)
    }

    try {
      client.executeMethod(method)
      json.parse(method.getResponseBodyAsString)
    } finally {
      method.releaseConnection()
    }
  }

  def post(url: String, data: JObject) = {
    val client = new HttpClient
    val method = new PostMethod(url)
    method.setRequestEntity(new StringRequestEntity(compact(render(data)), "application/json", null))

    if (credentials != null) {
      client.getState.setCredentials(AuthScope.ANY, credentials)
      client.getParams.setAuthenticationPreemptive(true)
    }

    try {
      client.executeMethod(method)
    } finally {
      method.releaseConnection()
    }
  }

  val backOff = BackOff(2000, 128000)
  final def stream(url: String, processor: StreamProcessor): Unit = {
    try {
      val client = new HttpClient
      val method = new GetMethod(url)

      if (credentials != null) {
        client.getState.setCredentials(AuthScope.ANY, credentials)
        client.getParams.setAuthenticationPreemptive(true)
      }

      try {
        client.executeMethod(method)
        backOff.reset
        processor.process(method.getResponseBodyAsStream)
      } finally {
        method.releaseConnection()
      }
      backOff.backOff
      stream(url, processor)
    }
  }
}

case class BackOff(var origBackOffTime: Long, capBackOffAt: Long) {
  var backOffTime = origBackOffTime

  def backOff = {
    Thread.sleep(backOffTime)
    backOffTime *= 2
    if(backOffTime > capBackOffAt) backOffTime = capBackOffAt
  }

  def reset() = { backOffTime = origBackOffTime }
}