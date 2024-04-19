package videogamedb

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class MyFirstTest extends Simulation {

  //1. Http Config
  val httpProtocol = http.baseUrl("http://127.0.0.1:8093/")
    .acceptHeader("application/json")

  //2. Scenario Definition
  var scn = scenario("My First Scenario")
    .exec(
      exec(http("Get all games")
        .get("/hello").check(bodyString.saveAs("responseBody"))),
      exec {
          session => println(session("responseBody").as[String]); session
      }
    )

  //3. Load Scenario
  setUp(
    scn.inject(
      constantUsersPerSec(1).during(60))
  ).protocols(httpProtocol)
    .maxDuration(60)

}
