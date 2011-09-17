import java.io.InputStream
import java.io.InputStreamReader
import java.io.BufferedReader

object StreamProcessor {
  def process(is: InputStream) {
    val reader: BufferedReader = new BufferedReader(new InputStreamReader(is, "UTF-8"))
    var line = reader.readLine()

    while (line != null) {
      println(line)
      // dispatch to any registered processors
      line = reader.readLine()
    }

    is.close
  }
}