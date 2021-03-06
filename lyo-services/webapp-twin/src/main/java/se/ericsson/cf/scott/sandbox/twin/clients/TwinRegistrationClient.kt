package se.ericsson.cf.scott.sandbox.twin.clients

import eu.scott.warehouse.domains.twins.RegistrationMessage
import org.eclipse.lyo.client.oslc.OslcClient
import org.eclipse.lyo.oslc4j.core.model.OslcMediaType
import java.net.URI

/**
 * TBD
 *
 * @version $version-stub$
 * @since   FIXME
 */
class TwinRegistrationClient(private val client: OslcClient, private val registrationCFUri: String) {
    fun registerTwin(type: String, id: String) {
        val registrationMessage = RegistrationMessage()
        registrationMessage.twinType = type
        registrationMessage.trsUri = trsURI(id)
        registrationMessage.trsMqttTopic = trsMqttTopic(id)
        val createResource = client.createResource(registrationCFUri, registrationMessage,
            OslcMediaType.TEXT_TURTLE)
    }

    fun trsMqttTopic(id: String) = "scott.warehouse.todo"

    private fun trsURI(id: String): URI? {
        return URI.create("urn:x:todo:"+id)
//        TODO("not implemented")
    }
}
