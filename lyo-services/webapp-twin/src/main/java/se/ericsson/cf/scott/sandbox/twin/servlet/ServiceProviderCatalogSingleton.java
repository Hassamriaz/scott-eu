// Start of user code Copyright
/*******************************************************************************
 * Copyright (c) 2012 IBM Corporation and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompanies this distribution.
 *
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *
 *     Russell Boykin       - initial API and implementation
 *     Alberto Giammaria    - initial API and implementation
 *     Chris Peters         - initial API and implementation
 *     Gianluca Bernardini  - initial API and implementation
 *     Michael Fiedler      - adapted for Bugzilla service provider
 *     Jad El-khoury        - initial implementation of code generator (422448)
 *     Matthieu Helleboid   - initialize each service provider separately
 *     Anass Radouani       - initialize each service provider separately
 *
 * This file is generated by org.eclipse.lyo.oslc4j.codegenerator
 *******************************************************************************/
// End of user code

package se.ericsson.cf.scott.sandbox.twin.servlet;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import org.eclipse.lyo.oslc4j.core.OSLC4JUtils;
import org.eclipse.lyo.oslc4j.core.exception.OslcCoreApplicationException;
import org.eclipse.lyo.oslc4j.core.model.Publisher;
import org.eclipse.lyo.oslc4j.core.model.Service;
import org.eclipse.lyo.oslc4j.core.model.ServiceProvider;
import org.eclipse.lyo.oslc4j.core.model.ServiceProviderCatalog;
import se.ericsson.cf.scott.sandbox.twin.IndependentServiceProviderInfo;
import se.ericsson.cf.scott.sandbox.twin.TwinsServiceProviderInfo;

// Start of user code imports
// End of user code

/**
 * This is the OSLC service provider catalog for the adapter.  Service providers are
 * not registered with the catalog until a request comes in to access either the catalog or a
 * specific service provider.   This request could be from an external consumer or an internal
 * request triggered by a consumer accessing a change request.
 *
 * Information about the desired list of ServiceProviders is retrieved from the Manager.getServiceProviderInfos() method.
 * A ServiceProvider is created and registered for each entry in that list.
 *
 * The registered service providers are refreshed on each catalog or service provider collection
 * request.
 */
public class ServiceProviderCatalogSingleton
{
    private static final ServiceProviderCatalog serviceProviderCatalog;
    private static final SortedMap<String, ServiceProvider> serviceProviders = new TreeMap<String, ServiceProvider>();

    static {
        serviceProviderCatalog = new ServiceProviderCatalog();
        URI catalogUri = UriBuilder.fromUri(OSLC4JUtils.getServletURI()).path("/catalog/singleton").build();
        serviceProviderCatalog.setAbout(catalogUri);
        serviceProviderCatalog.setTitle("Service Provider Catalog");
        serviceProviderCatalog.setDescription("Service Provider Catalog");
    }

    private ServiceProviderCatalogSingleton()
    {
        super();
    }


    public static URI getUri()
    {
        return serviceProviderCatalog.getAbout();
    }

    public static ServiceProviderCatalog getServiceProviderCatalog(HttpServletRequest httpServletRequest)
    {
        initServiceProviders(httpServletRequest);
        return serviceProviderCatalog;
    }

    public static ServiceProvider [] getServiceProviders(HttpServletRequest httpServletRequest)
    {
        synchronized(serviceProviders)
        {
            initServiceProviders(httpServletRequest);
            return serviceProviders.values().toArray(new ServiceProvider[ serviceProviders.size()]);
        }
    }


    private static URI constructTwinsServiceProviderURI(final String twinKind, final String twinId)
    {
        String basePath = OSLC4JUtils.getServletURI();
        Map<String, Object> pathParameters = new HashMap<String, Object>();
        pathParameters.put("twinKind", twinKind);

        pathParameters.put("twinId", twinId);
        String instanceURI = "twins/{twinKind}/{twinId}";

        final UriBuilder builder = UriBuilder.fromUri(basePath);
        return builder.path(instanceURI).buildFromMap(pathParameters);
    }

    private static String twinsServiceProviderIdentifier(final String twinKind, final String twinId)
    {
        String identifier = "/" + twinKind+"/" + twinId;
        return identifier;
    }

    public static boolean containsTwinsServiceProvider(final String twinKind, final String twinId) {
        return serviceProviders.containsKey(twinsServiceProviderIdentifier(twinKind, twinId));
    }

    public static ServiceProvider getTwinsServiceProvider(HttpServletRequest httpServletRequest, final String twinKind, final String twinId)
    {
        ServiceProvider serviceProvider;

        synchronized(serviceProviders)
        {
            String identifier = twinsServiceProviderIdentifier(twinKind, twinId);
            serviceProvider = serviceProviders.get(identifier);

            //One retry refreshing the service providers
            if (serviceProvider == null)
            {
                getServiceProviders(httpServletRequest);
                serviceProvider = serviceProviders.get(identifier);
            }
        }

        if (serviceProvider != null)
        {
            return serviceProvider;
        }

        throw new WebApplicationException(Status.NOT_FOUND);
    }

        public static ServiceProvider createTwinsServiceProvider(final TwinsServiceProviderInfo serviceProviderInfo)
            throws OslcCoreApplicationException, URISyntaxException, IllegalArgumentException {
        String basePath = OSLC4JUtils.getServletURI();
        String identifier = twinsServiceProviderIdentifier(serviceProviderInfo.twinKind, serviceProviderInfo.twinId);
        if (containsTwinsServiceProvider(serviceProviderInfo.twinKind, serviceProviderInfo.twinId)) {
            throw new IllegalArgumentException(String.format("The SP '%s' was already registered", identifier));
        }

        String serviceProviderName = serviceProviderInfo.name;
        String title = String.format("Service Provider '%s'", serviceProviderName);
        String description = String.format("%s (id: %s; kind: %s)",
            "A Service Provider for Twins",
            identifier,
            "Twin SP");
        Publisher publisher = null;
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("twinKind", serviceProviderInfo.twinKind);

        parameterMap.put("twinId", serviceProviderInfo.twinId);
        return TwinsServiceProvidersFactory.createServiceProvider(basePath, title, description, publisher, parameterMap);
    }

    public static ServiceProvider registerTwinsServiceProvider(final HttpServletRequest httpServletRequest,
                                                          final ServiceProvider serviceProvider,
                                                          final String twinKind, final String twinId)
                                                throws URISyntaxException
    {
        synchronized(serviceProviders)
        {
            final URI serviceProviderURI = constructTwinsServiceProviderURI(twinKind, twinId);
            return registerTwinsServiceProviderNoSync(serviceProviderURI,
                                                 serviceProvider,
                                                 twinKind, twinId);
        }
    }

    /**
    * Register a service provider with the OSLC catalog
    *
    */
    private static ServiceProvider registerTwinsServiceProviderNoSync(final URI serviceProviderURI,
                                                                 final ServiceProvider serviceProvider
                                                                 , final String twinKind, final String twinId)
    {
        final SortedSet<URI> serviceProviderDomains = getServiceProviderDomains(serviceProvider);

        String identifier = twinsServiceProviderIdentifier(twinKind, twinId);
        serviceProvider.setAbout(serviceProviderURI);
        serviceProvider.setIdentifier(identifier);
        serviceProvider.setCreated(new Date());
        serviceProvider.setDetails(new URI[] {serviceProviderURI});

        serviceProviderCatalog.addServiceProvider(serviceProvider);
        serviceProviderCatalog.addDomains(serviceProviderDomains);

        serviceProviders.put(identifier, serviceProvider);

        return serviceProvider;
    }

    // This version is for self-registration and thus package-protected
    static ServiceProvider registerTwinsServiceProvider(final ServiceProvider serviceProvider, final String twinKind, final String twinId)
                                            throws URISyntaxException
    {
        synchronized(serviceProviders)
        {
            final URI serviceProviderURI = constructTwinsServiceProviderURI(twinKind, twinId);

            return registerTwinsServiceProviderNoSync(serviceProviderURI, serviceProvider, twinKind, twinId);
        }
    }

    public static void deregisterTwinsServiceProvider(final String twinKind, final String twinId)
    {
        synchronized(serviceProviders)
        {
            final ServiceProvider deregisteredServiceProvider =
                serviceProviders.remove(twinsServiceProviderIdentifier(twinKind, twinId));

            if (deregisteredServiceProvider != null)
            {
                final SortedSet<URI> remainingDomains = new TreeSet<URI>();

                for (final ServiceProvider remainingServiceProvider : serviceProviders.values())
                {
                    remainingDomains.addAll(getServiceProviderDomains(remainingServiceProvider));
                }

                final SortedSet<URI> removedServiceProviderDomains = getServiceProviderDomains(deregisteredServiceProvider);

                removedServiceProviderDomains.removeAll(remainingDomains);
                serviceProviderCatalog.removeDomains(removedServiceProviderDomains);
                serviceProviderCatalog.removeServiceProvider(deregisteredServiceProvider);
            }
            else
            {
                throw new WebApplicationException(Status.NOT_FOUND);
            }
        }
    }

    private static URI constructIndependentServiceProviderURI(final String serviceProviderId)
    {
        String basePath = OSLC4JUtils.getServletURI();
        Map<String, Object> pathParameters = new HashMap<String, Object>();
        pathParameters.put("serviceProviderId", serviceProviderId);
        String instanceURI = "independent/{serviceProviderId}";

        final UriBuilder builder = UriBuilder.fromUri(basePath);
        return builder.path(instanceURI).buildFromMap(pathParameters);
    }

    private static String independentServiceProviderIdentifier(final String serviceProviderId)
    {
        String identifier = "/" + serviceProviderId;
        return identifier;
    }

    public static boolean containsIndependentServiceProvider(final String serviceProviderId) {
        return serviceProviders.containsKey(independentServiceProviderIdentifier(serviceProviderId));
    }

    public static ServiceProvider getIndependentServiceProvider(HttpServletRequest httpServletRequest, final String serviceProviderId)
    {
        ServiceProvider serviceProvider;

        synchronized(serviceProviders)
        {
            String identifier = independentServiceProviderIdentifier(serviceProviderId);
            serviceProvider = serviceProviders.get(identifier);

            //One retry refreshing the service providers
            if (serviceProvider == null)
            {
                getServiceProviders(httpServletRequest);
                serviceProvider = serviceProviders.get(identifier);
            }
        }

        if (serviceProvider != null)
        {
            return serviceProvider;
        }

        throw new WebApplicationException(Status.NOT_FOUND);
    }

    public static ServiceProvider createIndependentServiceProvider(final IndependentServiceProviderInfo serviceProviderInfo) 
            throws OslcCoreApplicationException, URISyntaxException, IllegalArgumentException {
        String basePath = OSLC4JUtils.getServletURI();
        String identifier = independentServiceProviderIdentifier(serviceProviderInfo.serviceProviderId);
        if (containsIndependentServiceProvider(serviceProviderInfo.serviceProviderId)) {
            throw new IllegalArgumentException(String.format("The SP '%s' was already registered", identifier));
        }

        String serviceProviderName = serviceProviderInfo.name;
        String title = String.format("Service Provider '%s'", serviceProviderName);
        String description = String.format("%s (id: %s; kind: %s)",
            "Generic SP for SP-independent services",
            identifier,
            "Independent");
        Publisher publisher = null;
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("serviceProviderId", serviceProviderInfo.serviceProviderId);
        return IndependentServiceProvidersFactory.createServiceProvider(basePath, title, description, publisher, parameterMap);
    }

    public static ServiceProvider registerIndependentServiceProvider(final HttpServletRequest httpServletRequest,
                                                          final ServiceProvider serviceProvider,
                                                          final String serviceProviderId)
                                                throws URISyntaxException
    {
        synchronized(serviceProviders)
        {
            final URI serviceProviderURI = constructIndependentServiceProviderURI(serviceProviderId);
            return registerIndependentServiceProviderNoSync(serviceProviderURI,
                                                 serviceProvider,
                                                 serviceProviderId);
        }
    }

    /**
    * Register a service provider with the OSLC catalog
    *
    */
    private static ServiceProvider registerIndependentServiceProviderNoSync(final URI serviceProviderURI,
                                                                 final ServiceProvider serviceProvider
                                                                 , final String serviceProviderId)
    {
        final SortedSet<URI> serviceProviderDomains = getServiceProviderDomains(serviceProvider);

        String identifier = independentServiceProviderIdentifier(serviceProviderId);
        serviceProvider.setAbout(serviceProviderURI);
        serviceProvider.setIdentifier(identifier);
        serviceProvider.setCreated(new Date());
        serviceProvider.setDetails(new URI[] {serviceProviderURI});

        serviceProviderCatalog.addServiceProvider(serviceProvider);
        serviceProviderCatalog.addDomains(serviceProviderDomains);

        serviceProviders.put(identifier, serviceProvider);

        return serviceProvider;
    }

    // This version is for self-registration and thus package-protected
    static ServiceProvider registerIndependentServiceProvider(final ServiceProvider serviceProvider, final String serviceProviderId)
                                            throws URISyntaxException
    {
        synchronized(serviceProviders)
        {
            final URI serviceProviderURI = constructIndependentServiceProviderURI(serviceProviderId);

            return registerIndependentServiceProviderNoSync(serviceProviderURI, serviceProvider, serviceProviderId);
        }
    }

    public static void deregisterIndependentServiceProvider(final String serviceProviderId)
    {
        synchronized(serviceProviders)
        {
            final ServiceProvider deregisteredServiceProvider =
                serviceProviders.remove(independentServiceProviderIdentifier(serviceProviderId));

            if (deregisteredServiceProvider != null)
            {
                final SortedSet<URI> remainingDomains = new TreeSet<URI>();

                for (final ServiceProvider remainingServiceProvider : serviceProviders.values())
                {
                    remainingDomains.addAll(getServiceProviderDomains(remainingServiceProvider));
                }

                final SortedSet<URI> removedServiceProviderDomains = getServiceProviderDomains(deregisteredServiceProvider);

                removedServiceProviderDomains.removeAll(remainingDomains);
                serviceProviderCatalog.removeDomains(removedServiceProviderDomains);
                serviceProviderCatalog.removeServiceProvider(deregisteredServiceProvider);
            }
            else
            {
                throw new WebApplicationException(Status.NOT_FOUND);
            }
        }
    }

    private static SortedSet<URI> getServiceProviderDomains(final ServiceProvider serviceProvider)
    {
        final SortedSet<URI> domains = new TreeSet<URI>();

        if (serviceProvider!=null) {
            final Service [] services = serviceProvider.getServices();
            for (final Service service : services)
            {
                final URI domain = service.getDomain();

                domains.add(domain);
            }
        }
        return domains;
    }

    /**
    * Retrieve the set of initial ServiceProviders as returned from the Manager.getServiceProviderInfos() method, and construct a service provider for each.
    *
    * Each ServiceProvider ID is added to the parameter map which will be used during service provider
    * creation to create unique URI paths for each ServiceProvider. 
    *
    */
    protected static void initServiceProviders (HttpServletRequest httpServletRequest)
    {
        throw new IllegalStateException("This is method is being removed");
        /*try {
            // Start of user code initServiceProviders
            // End of user code

            TwinsServiceProviderInfo [] twinsServiceProviderInfos = TwinManager.getTwinsServiceProviderInfos(httpServletRequest);
            //Register each service provider
            for (TwinsServiceProviderInfo serviceProviderInfo : twinsServiceProviderInfos) {
                if (!containsTwinsServiceProvider(serviceProviderInfo.twinKind, serviceProviderInfo.twinId)) {
                    ServiceProvider aServiceProvider = createTwinsServiceProvider(serviceProviderInfo);
                    registerTwinsServiceProvider(aServiceProvider, serviceProviderInfo.twinKind, serviceProviderInfo.twinId);
                }
            }
            IndependentServiceProviderInfo [] independentServiceProviderInfos = TwinManager.getIndependentServiceProviderInfos(httpServletRequest);
            //Register each service provider
            for (IndependentServiceProviderInfo serviceProviderInfo : independentServiceProviderInfos) {
                if (!containsIndependentServiceProvider(serviceProviderInfo.serviceProviderId)) {
                    ServiceProvider aServiceProvider = createIndependentServiceProvider(serviceProviderInfo);
                    registerIndependentServiceProvider(aServiceProvider,    serviceProviderInfo.serviceProviderId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new WebApplicationException(e,Status.INTERNAL_SERVER_ERROR);
        }*/
    }
}

