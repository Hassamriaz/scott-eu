// Start of user code Copyright
/*******************************************************************************
 * Copyright (c) 2011, 2012 IBM Corporation and others.
 *
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  and Eclipse Distribution License v. 1.0 which accompanies this distribution.
 *  
 *  The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 *  and the Eclipse Distribution License is available at
 *  http://www.eclipse.org/org/documents/edl-v10.php.
 *  
 *  Contributors:
 *  
 *	   Sam Padgett	       - initial API and implementation
 *     Michael Fiedler     - adapted for OSLC4J
 *     Jad El-khoury        - initial implementation of code generator (https://bugs.eclipse.org/bugs/show_bug.cgi?id=422448)
 *     Matthieu Helleboid   - Support for multiple Service Providers.
 *     Anass Radouani       - Support for multiple Service Providers.
 *
 * This file is generated by org.eclipse.lyo.oslc4j.codegenerator
 *******************************************************************************/
// End of user code

package se.ericsson.cf.scott.sandbox.executor;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;
import eu.scott.warehouse.lib.MqttClientBuilder;
import eu.scott.warehouse.lib.MqttTopics;
import eu.scott.warehouse.lib.TrsMqttGateway;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletContextEvent;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Start of user code imports
// End of user code

// Start of user code pre_class_code
// End of user code

public class PlanExecutorManager {

    // Start of user code class_attributes
    private final static Logger log = LoggerFactory.getLogger(PlanExecutorManager.class);
    private static String adaptorId = "exec-" + UUID.randomUUID();
    private static String mqttBroker;
    private static TrsMqttGateway mqttGateway;
    private static ServletContext servletContext;
    private static IQueue<Object> dummyQueue;
    private static HazelcastInstance hc;
    // End of user code
    
    
    // Start of user code class_methods
    public static String p(final String s) {
        final String fqdn = pFQDN(s);
        final String parameter = servletContext.getInitParameter(fqdn);
        log.trace("{}={"
                          + "}", fqdn, parameter);
        return parameter;
    }

    public static String pFQDN(final String key) {
        final String base = PlanExecutorManager.class.getPackage().getName().toString();
        log.trace("Using '{}' as a parameter base", base);
        return base.trim() + "." + key.trim();
    }
    // End of user code

    public static void contextInitializeServletListener(final ServletContextEvent servletContextEvent)
    {
        
        // Start of user code contextInitializeServletListener
        log.info("Plan Executor {} is starting", adaptorId);
        servletContext = servletContextEvent.getServletContext();
        if (log.isTraceEnabled()) {
            dumpInitParams(servletContext);
            dumpEnvVars();
        }
        mqttBroker = p("trs.mqtt.broker");

        hc = HazelcastFactory.INSTANCE.instanceFromDefaultXmlConfig("executor");
        log.debug("New instance of Hazelcast has been constructed");
        assert hc != null;
        new Thread(new PlanQueueRunnable(hc)).start();

        try {
            log.debug("Starting MQTT broker initialisation");
            mqttGateway = new MqttClientBuilder().withBroker(mqttBroker)
                                                 .withId(adaptorId)
                                                 .withRegistration(
                                                         new ExecutorAckRegistrationAgent(adaptorId,
                                                                                          MqttTopics.WHC_PLANS
                                                         ))
                                                 .build();
        } catch (MqttException | IllegalArgumentException e) {
            log.error("Failed to initialise the MQTT client for OSLC TRS support");
            log.trace("Failed to initialise the MQTT client for OSLC TRS support", e);
        }

        dummyQueue = hc.getQueue("dummy");
//        final MqttClient mqttClient = MqttManager.initMqttClient(mqttBroker, adaptorId);
        // End of user code
    }

    private static void dumpEnvVars() {
        final Map<String, String> getenv = System.getenv();
        final StringBuilder builder = new StringBuilder();
        getenv.forEach((k, v) -> builder.append(String.format("%s='%s';\n", k, v)));
        log.debug("The environment variables of the adaptor:\n{}", builder);
    }

    // TODO Andrew@2018-08-03: move to the common helper class
    private static void dumpInitParams(ServletContext c) {
        log.trace("Context initialisation parameters:");
        final List<String> parameterNames = Collections.list(c.getInitParameterNames());
        for (String p : parameterNames) {
            log.trace("\t{}='{}'", p, c.getInitParameter(p));
        }
        log.trace("---");
    }

    public static void contextDestroyServletListener(ServletContextEvent servletContextEvent) 
    {
        
        // Start of user code contextDestroyed
        log.info("Plan Executor is shutting down");
        try {
            mqttGateway.disconnect();
        } catch (MqttException e) {
            log.error("The MQTT gateway could not shut down cleanly");
            log.debug("The MQTT gateway could not shut down cleanly", e);
        }
        hc.shutdown();
        // End of user code
    }

    public static ServiceProviderInfo[] getServiceProviderInfos(HttpServletRequest httpServletRequest)
            throws InterruptedException {
        ServiceProviderInfo[] serviceProviderInfos = {};
        
        // Start of user code "ServiceProviderInfo[] getServiceProviderInfos(...)"
//        throw new UnsupportedOperationException("The REST I/F is not implemented yet");
        log.warn("The REST I/F is not implemented yet");
        final HCData hcData = new HCData(httpServletRequest.getRequestURI(),
                                    UUID.randomUUID().toString(),
                                    Collections.list(httpServletRequest.getHeaderNames())
                                               .stream()
                                               .map(httpServletRequest::getHeader)
                                               .toArray(String[]::new)
        );
        log.debug("Testing the dummy queue with {}", hcData.getUuid());
        dummyQueue.put(hcData);

        // End of user code
        return serviceProviderInfos;
    }

    private static class PlanQueueRunnable implements Runnable {
        private final IQueue<Object> hcQueue;

        PlanQueueRunnable(final HazelcastInstance hc) {
            hcQueue = hc.getQueue("dummy");
        }

        @Override
        public void run() {
            log.debug("Starting a thread to process Hazelcast queue items");
            try {
                //noinspection InfiniteLoopStatement
                while (true) {
                    final Object object = hcQueue.take();
                    log.info("New object to process: {}", object);
                }
            } catch (InterruptedException e) {
                log.warn("Queue processing was interrupted, shutting down the bg thread");
            }
        }
    }
}
