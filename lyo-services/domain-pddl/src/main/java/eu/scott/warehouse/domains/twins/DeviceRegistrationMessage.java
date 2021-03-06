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
 *       Sam Padgett          - initial API and implementation
 *     Michael Fiedler      - adapted for OSLC4J
 *     Jad El-khoury        - initial implementation of code generator (422448)
 *     Matthieu Helleboid   - Support for multiple Service Providers.
 *     Anass Radouani       - Support for multiple Service Providers.
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

import org.eclipse.lyo.oslc4j.core.OSLC4JUtils;
import org.eclipse.lyo.oslc4j.core.exception.OslcCoreApplicationException;
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
import org.eclipse.lyo.oslc4j.core.model.ResourceShape;
import org.eclipse.lyo.oslc4j.core.model.ResourceShapeFactory;

import eu.scott.warehouse.domains.twins.TwinsDomainConstants;


import eu.scott.warehouse.domains.twins.TwinsDomainConstants;

// Start of user code imports
// End of user code

// Start of user code preClassCode
// End of user code

// Start of user code classAnnotations
// End of user code
@OslcNamespace(TwinsDomainConstants.DEVICEREGISTRATIONMESSAGE_NAMESPACE)
@OslcName(TwinsDomainConstants.DEVICEREGISTRATIONMESSAGE_LOCALNAME)
@OslcResourceShape(title = "DeviceRegistrationMessage Resource Shape", describes = TwinsDomainConstants.DEVICEREGISTRATIONMESSAGE_TYPE)
public class DeviceRegistrationMessage
    extends AbstractResource
    implements IDeviceRegistrationMessage
{
    // Start of user code attributeAnnotation:twinType
    // End of user code
    private String twinType;
    // Start of user code attributeAnnotation:twinId
    // End of user code
    private String twinId;
    // Start of user code attributeAnnotation:deregister
    // End of user code
    private Boolean deregister;
    
    // Start of user code classAttributes
    // End of user code
    // Start of user code classMethods
    // End of user code
    public DeviceRegistrationMessage()
           throws URISyntaxException
    {
        super();
    
        // Start of user code constructor1
        // End of user code
    }
    
    public DeviceRegistrationMessage(final URI about)
           throws URISyntaxException
    {
        super(about);
    
        // Start of user code constructor2
        // End of user code
    }
    
    
    public static ResourceShape createResourceShape() throws OslcCoreApplicationException, URISyntaxException {
        return ResourceShapeFactory.createResourceShape(OSLC4JUtils.getServletURI(),
        OslcConstants.PATH_RESOURCE_SHAPES,
        TwinsDomainConstants.DEVICEREGISTRATIONMESSAGE_PATH,
        DeviceRegistrationMessage.class);
    }

    @Override
    public String toString() {
        return "DeviceRegistrationMessage<"+getAbout()+">{" + "twinType='" + twinType + '\'' + ", twinId='" + twinId
            + '\'' + ", deregister=" + deregister + '}';
    }

    public String toString(boolean asLocalResource)
    {
        String result = "";
        // Start of user code toString_init
        // End of user code
    
        if (asLocalResource) {
            result = result + "{a Local DeviceRegistrationMessage Resource} - update DeviceRegistrationMessage.toString() to present resource as desired.";
            // Start of user code toString_bodyForLocalResource
            // End of user code
        }
        else {
            result = getAbout().toString();
        }
    
        // Start of user code toString_finalize
        // End of user code
    
        return result;
    }
    
    public String toHtml()
    {
        return toHtml(false);
    }
    
    public String toHtml(boolean asLocalResource)
    {
        String result = "";
        // Start of user code toHtml_init
        // End of user code
    
        if (asLocalResource) {
            result = toString(true);
            // Start of user code toHtml_bodyForLocalResource
            // End of user code
        }
        else {
            result = "<a href=\"" + getAbout() + "\" class=\"oslc-resource-link\">" + toString() + "</a>";
        }
    
        // Start of user code toHtml_finalize
        // End of user code
    
        return result;
    }
    
    
    // Start of user code getterAnnotation:twinType
    // End of user code
    @OslcName("twinType")
    @OslcPropertyDefinition(TwinsDomainConstants.TWINS_DOMAIN_NAMSPACE + "twinType")
    @OslcOccurs(Occurs.ExactlyOne)
    @OslcValueType(ValueType.String)
    @OslcReadOnly(false)
    public String getTwinType()
    {
        // Start of user code getterInit:twinType
        // End of user code
        return twinType;
    }
    
    // Start of user code getterAnnotation:twinId
    // End of user code
    @OslcName("twinId")
    @OslcPropertyDefinition(TwinsDomainConstants.TWINS_DOMAIN_NAMSPACE + "twinId")
    @OslcOccurs(Occurs.ExactlyOne)
    @OslcValueType(ValueType.String)
    @OslcReadOnly(false)
    public String getTwinId()
    {
        // Start of user code getterInit:twinId
        // End of user code
        return twinId;
    }
    
    // Start of user code getterAnnotation:deregister
    // End of user code
    @OslcName("deregister")
    @OslcPropertyDefinition(TwinsDomainConstants.TWINS_DOMAIN_NAMSPACE + "deregister")
    @OslcOccurs(Occurs.ExactlyOne)
    @OslcValueType(ValueType.Boolean)
    @OslcReadOnly(false)
    public Boolean isDeregister()
    {
        // Start of user code getterInit:deregister
        // End of user code
        return deregister;
    }
    
    
    // Start of user code setterAnnotation:twinType
    // End of user code
    public void setTwinType(final String twinType )
    {
        // Start of user code setterInit:twinType
        // End of user code
        this.twinType = twinType;
    
        // Start of user code setterFinalize:twinType
        // End of user code
    }
    
    // Start of user code setterAnnotation:twinId
    // End of user code
    public void setTwinId(final String twinId )
    {
        // Start of user code setterInit:twinId
        // End of user code
        this.twinId = twinId;
    
        // Start of user code setterFinalize:twinId
        // End of user code
    }
    
    // Start of user code setterAnnotation:deregister
    // End of user code
    public void setDeregister(final Boolean deregister )
    {
        // Start of user code setterInit:deregister
        // End of user code
        this.deregister = deregister;
    
        // Start of user code setterFinalize:deregister
        // End of user code
    }
    
    
    static public String twinTypeToHtmlForCreation (final HttpServletRequest httpServletRequest)
    {
        String s = "";
    
        // Start of user code "Init:twinTypeToHtmlForCreation(...)"
        // End of user code
    
        s = s + "<label for=\"twinType\">twinType: </LABEL>";
    
        // Start of user code "Mid:twinTypeToHtmlForCreation(...)"
        // End of user code
    
        s= s + "<input name=\"twinType\" type=\"text\" style=\"width: 400px\" id=\"twinType\" >";
        // Start of user code "Finalize:twinTypeToHtmlForCreation(...)"
        // End of user code
    
        return s;
    }
    
    static public String twinIdToHtmlForCreation (final HttpServletRequest httpServletRequest)
    {
        String s = "";
    
        // Start of user code "Init:twinIdToHtmlForCreation(...)"
        // End of user code
    
        s = s + "<label for=\"twinId\">twinId: </LABEL>";
    
        // Start of user code "Mid:twinIdToHtmlForCreation(...)"
        // End of user code
    
        s= s + "<input name=\"twinId\" type=\"text\" style=\"width: 400px\" id=\"twinId\" >";
        // Start of user code "Finalize:twinIdToHtmlForCreation(...)"
        // End of user code
    
        return s;
    }
    
    static public String deregisterToHtmlForCreation (final HttpServletRequest httpServletRequest)
    {
        String s = "";
    
        // Start of user code "Init:deregisterToHtmlForCreation(...)"
        // End of user code
    
        s = s + "<label for=\"deregister\">deregister: </LABEL>";
    
        // Start of user code "Mid:deregisterToHtmlForCreation(...)"
        // End of user code
    
        s= s + "<input name=\"deregister\" type=\"radio\" value=\"true\">True<input name=\"deregister\" type=\"radio\" value=\"false\">False";
        // Start of user code "Finalize:deregisterToHtmlForCreation(...)"
        // End of user code
    
        return s;
    }
    
    
    public String twinTypeToHtml()
    {
        String s = "";
    
        // Start of user code twinTypetoHtml_mid
        // End of user code
    
        try {
            if (twinType == null) {
                s = s + "<em>null</em>";
            }
            else {
                s = s + twinType.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        // Start of user code twinTypetoHtml_finalize
        // End of user code
    
        return s;
    }
    
    public String twinIdToHtml()
    {
        String s = "";
    
        // Start of user code twinIdtoHtml_mid
        // End of user code
    
        try {
            if (twinId == null) {
                s = s + "<em>null</em>";
            }
            else {
                s = s + twinId.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        // Start of user code twinIdtoHtml_finalize
        // End of user code
    
        return s;
    }
    
    public String deregisterToHtml()
    {
        String s = "";
    
        // Start of user code deregistertoHtml_mid
        // End of user code
    
        try {
            if (deregister == null) {
                s = s + "<em>null</em>";
            }
            else {
                s = s + deregister.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        // Start of user code deregistertoHtml_finalize
        // End of user code
    
        return s;
    }
    
    
}
