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
 *	   Sam Padgett	       - initial API and implementation
 *     Michael Fiedler     - adapted for OSLC4J
 *     Jad El-khoury        - initial implementation of code generator (https://bugs.eclipse.org/bugs/show_bug.cgi?id=422448)
 *
 * This file is generated by org.eclipse.lyo.oslc4j.codegenerator
 *******************************************************************************/
// End of user code

package eu.scott.warehouse.domains.twins;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import javax.ws.rs.core.UriBuilder;

import org.eclipse.lyo.oslc4j.core.annotation.OslcAllowedValue;
import org.eclipse.lyo.oslc4j.core.annotation.OslcDescription;
import org.eclipse.lyo.oslc4j.core.annotation.OslcMemberProperty;
import org.eclipse.lyo.oslc4j.core.annotation.OslcName;
import org.eclipse.lyo.oslc4j.core.annotation.OslcNamespace;
import org.eclipse.lyo.oslc4j.core.annotation.OslcOccurs;
import org.eclipse.lyo.oslc4j.core.annotation.OslcPropertyDefinition;
import org.eclipse.lyo.oslc4j.core.annotation.OslcRange;
import org.eclipse.lyo.oslc4j.core.annotation.OslcReadOnly;
import org.eclipse.lyo.oslc4j.core.annotation.OslcRepresentation;
import org.eclipse.lyo.oslc4j.core.annotation.OslcResourceShape;
import org.eclipse.lyo.oslc4j.core.annotation.OslcTitle;
import org.eclipse.lyo.oslc4j.core.annotation.OslcValueType;
import org.eclipse.lyo.oslc4j.core.model.AbstractResource;
import org.eclipse.lyo.oslc4j.core.model.Link;
import org.eclipse.lyo.oslc4j.core.model.Occurs;
import org.eclipse.lyo.oslc4j.core.model.OslcConstants;
import org.eclipse.lyo.oslc4j.core.model.Representation;
import org.eclipse.lyo.oslc4j.core.model.ValueType;

import eu.scott.warehouse.domains.twins.TwinsDomainConstants;
import eu.scott.warehouse.domains.RdfsDomainConstants;
import eu.scott.warehouse.domains.twins.TwinsDomainConstants;

// Start of user code imports
// End of user code

@OslcNamespace(TwinsDomainConstants.REGISTRATIONMESSAGE_NAMESPACE)
@OslcName(TwinsDomainConstants.REGISTRATIONMESSAGE_LOCALNAME)
@OslcResourceShape(title = "RegistrationMessage Resource Shape", describes = TwinsDomainConstants.REGISTRATIONMESSAGE_TYPE)
public interface IRegistrationMessage
{


    @OslcName("twinType")
    @OslcPropertyDefinition(TwinsDomainConstants.TWINS_DOMAIN_NAMSPACE + "twinType")
    @OslcOccurs(Occurs.ExactlyOne)
    @OslcValueType(ValueType.String)
    @OslcReadOnly(false)
    public String getTwinType();

    @OslcName("deregister")
    @OslcPropertyDefinition(TwinsDomainConstants.TWINS_DOMAIN_NAMSPACE + "deregister")
    @OslcOccurs(Occurs.ExactlyOne)
    @OslcValueType(ValueType.Boolean)
    @OslcReadOnly(false)
    public Boolean isDeregister();

    @OslcName("trsUri")
    @OslcPropertyDefinition(TwinsDomainConstants.TWINS_DOMAIN_NAMSPACE + "trsUri")
    @OslcOccurs(Occurs.ExactlyOne)
    @OslcReadOnly(false)
    public URI getTrsUri();

    @OslcName("trsMqttTopic")
    @OslcPropertyDefinition(TwinsDomainConstants.TWINS_DOMAIN_NAMSPACE + "trsMqttTopic")
    @OslcOccurs(Occurs.ExactlyOne)
    @OslcValueType(ValueType.String)
    @OslcReadOnly(false)
    public String getTrsMqttTopic();

    @OslcName("label")
    @OslcPropertyDefinition(RdfsDomainConstants.RDFS_NAMSPACE + "label")
    @OslcDescription("Parameter name.")
    @OslcOccurs(Occurs.ExactlyOne)
    @OslcValueType(ValueType.String)
    @OslcReadOnly(false)
    public String getLabel();


    public void setTwinType(final String twinType );
    public void setDeregister(final Boolean deregister );
    public void setTrsUri(final URI trsUri );
    public void setTrsMqttTopic(final String trsMqttTopic );
    public void setLabel(final String label );
}

