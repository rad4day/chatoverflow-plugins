import org.codeoverflow.chatoverflow.api.plugin.{PluginImpl, PluginManager}

class simpletestPlugin(manager: PluginManager) extends PluginImpl(manager) {

  private val twitchChatInputReq = require.input.twitchChat("reqTwitch", "A twitch channel", false)
  private val nameToSayHelloToReq = require.parameter.string("reqHello", "Your name", false)
  loopInterval = 1000

  override def setup(): Unit = {
    println("Started successfully!")
    log("Hello!")
    log("Whats up?")

    twitchChatInputReq.get.registerMessageHandler(msg => println(msg))

    println(s"Hello ${nameToSayHelloToReq.get}!")

    try {
      println("\nTest #1: This should fail (no access policy in the plugin)")
      val output = scala.io.Source.fromURL("https://skate702.de")
      println(output != null)
    } catch {
      case e: Exception => println(s"No rights. Message: ${e.getMessage}")
    }

    twitchChatInputReq.get.requestURLWithoutRights()
    twitchChatInputReq.get.requestURLWithoutRightsUsingTheConnector()
    twitchChatInputReq.get.requestURLUsingStaticActorCode()
    twitchChatInputReq.get.requestURLUsingDynamicActorExecution()
    twitchChatInputReq.get.requestURLUsingOwnActor()
  }

  override def loop(): Unit = {
    //println("Loop!")
  }

  override def shutdown(): Unit = {

  }
}