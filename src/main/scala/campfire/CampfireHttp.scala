package campfire

import org.apache.commons.httpclient._

object Campfire extends HTTPClient {
  override val credentials:UsernamePasswordCredentials = new UsernamePasswordCredentials("5ec22e80e2c7b31ad738a80c5c5cd405bb8a78dc:X")
}
