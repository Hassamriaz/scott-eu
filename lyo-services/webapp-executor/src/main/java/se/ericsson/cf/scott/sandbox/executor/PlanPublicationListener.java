package se.ericsson.cf.scott.sandbox.executor;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TBD
 *
 * @version $version-stub$
 * @since FIXME
 */
public class PlanPublicationListener implements IMqttMessageListener {
    private final static Logger log = LoggerFactory.getLogger(PlanPublicationListener.class);
    private final MqttClient mqttClient;

    public PlanPublicationListener(final MqttClient mqttClient) {
        this.mqttClient = mqttClient;
    }

    @Override
    public void messageArrived(final String topic, final MqttMessage message) throws Exception {
        log.error("PlanPublicationListener is not implemented yet");
    }
}
