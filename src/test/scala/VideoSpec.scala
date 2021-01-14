import org.matthicks.media4s.video.VideoUtil
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import java.io.File

class VideoSpec extends AnyWordSpec with Matchers {
  "VideoUtil" should {
    val trailer480p = new File(".\\content\\video\\movie.mov")

    "gather the correct information for trailer_480p.mov" in {
      val info = VideoUtil.info(trailer480p)
      info.duration should be(30.571)
    }
  }
}