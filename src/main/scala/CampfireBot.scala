import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler
import org.apache.commons.httpclient.HttpClient
import org.apache.commons.httpclient.HttpException
import org.apache.commons.httpclient.HttpStatus
import org.apache.commons.httpclient.HttpURL
import org.apache.commons.httpclient.UsernamePasswordCredentials
import org.apache.commons.httpclient.auth.AuthScope
import org.apache.commons.httpclient.HttpMethod
import org.apache.commons.httpclient.methods.GetMethod
import org.apache.commons.httpclient.params.HttpMethodParams

object CampfireBot {
  def main(args:Array[String]) {
    val client = new HttpClient
    val method = new GetMethod("http://streaming.campfirenow.com/room/"+args(0)+"/live.json")
    val credentials = new UsernamePasswordCredentials(args(1)+":X")

    client.getState.setCredentials(this.getAuthScope(method), credentials)
    client.getParams.setAuthenticationPreemptive(true)

    try {
      client.executeMethod(method)
      StreamProcessor.process(method.getResponseBodyAsStream())
    } finally {
      method.releaseConnection()
    }
  }

  def getAuthScope(method: HttpMethod): AuthScope = {
    val url = new HttpURL(method.getURI.toString)
    new AuthScope(url.getHost, url.getPort)
  }
}