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

package eu.scott.warehouse.domains.mission;

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

import eu.scott.warehouse.domains.mission.MissionDomainConstants;


import eu.scott.warehouse.domains.mission.MissionDomainConstants;
import eu.scott.warehouse.domains.mission.Goal;

// Start of user code imports
// End of user code

// Start of user code preClassCode
// End of user code

// Start of user code classAnnotations
// End of user code
@OslcNamespace(MissionDomainConstants.MISSION_NAMESPACE)
@OslcName(MissionDomainConstants.MISSION_LOCALNAME)
@OslcResourceShape(title = "Mission Resource Shape", describes = MissionDomainConstants.MISSION_TYPE)
public class Mission
    extends AbstractResource
    implements IMission
{
    // Start of user code attributeAnnotation:goal
    // End of user code
    private Goal goal = new Goal();
    // Start of user code attributeAnnotation:responseTimeout
    // End of user code
    private Double responseTimeout;
    // Start of user code attributeAnnotation:missionDeadline
    // End of user code
    private Date missionDeadline;
    
    // Start of user code classAttributes
    // End of user code
    // Start of user code classMethods
    // End of user code
    public Mission()
           throws URISyntaxException
    {
        super();
    
        // Start of user code constructor1
        // End of user code
    }
    
    public Mission(final URI about)
           throws URISyntaxException
    {
        super(about);
    
        // Start of user code constructor2
        // End of user code
    }
    
    
    public static ResourceShape createResourceShape() throws OslcCoreApplicationException, URISyntaxException {
        return ResourceShapeFactory.createResourceShape(OSLC4JUtils.getServletURI(),
        OslcConstants.PATH_RESOURCE_SHAPES,
        MissionDomainConstants.MISSION_PATH,
        Mission.class);
    }
    
    
    public String toString()
    {
        return toString(false);
    }
    
    public String toString(boolean asLocalResource)
    {
        String result = "";
        // Start of user code toString_init
        // End of user code
    
        if (asLocalResource) {
            result = result + "{a Local Mission Resource} - update Mission.toString() to present resource as desired.";
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
    
    
    // Start of user code getterAnnotation:goal
    // End of user code
    @OslcName("goal")
    @OslcPropertyDefinition(MissionDomainConstants.MISSIONONTOLOGY_NAMSPACE + "goal")
    @OslcOccurs(Occurs.ExactlyOne)
    @OslcValueType(ValueType.LocalResource)
    @OslcRange({MissionDomainConstants.GOAL_TYPE})
    @OslcReadOnly(false)
    public Goal getGoal()
    {
        // Start of user code getterInit:goal
        // End of user code
        return goal;
    }
    
    // Start of user code getterAnnotation:responseTimeout
    // End of user code
    @OslcName("responseTimeout")
    @OslcPropertyDefinition(MissionDomainConstants.MISSIONONTOLOGY_NAMSPACE + "responseTimeout")
    @OslcOccurs(Occurs.ExactlyOne)
    @OslcValueType(ValueType.Double)
    @OslcReadOnly(false)
    public Double getResponseTimeout()
    {
        // Start of user code getterInit:responseTimeout
        // End of user code
        return responseTimeout;
    }
    
    // Start of user code getterAnnotation:missionDeadline
    // End of user code
    @OslcName("missionDeadline")
    @OslcPropertyDefinition(MissionDomainConstants.MISSIONONTOLOGY_NAMSPACE + "missionDeadline")
    @OslcOccurs(Occurs.ExactlyOne)
    @OslcValueType(ValueType.DateTime)
    @OslcReadOnly(false)
    public Date getMissionDeadline()
    {
        // Start of user code getterInit:missionDeadline
        // End of user code
        return missionDeadline;
    }
    
    
    // Start of user code setterAnnotation:goal
    // End of user code
    public void setGoal(final Goal goal )
    {
        // Start of user code setterInit:goal
        // End of user code
        this.goal = goal;
    
        // Start of user code setterFinalize:goal
        // End of user code
    }
    
    // Start of user code setterAnnotation:responseTimeout
    // End of user code
    public void setResponseTimeout(final Double responseTimeout )
    {
        // Start of user code setterInit:responseTimeout
        // End of user code
        this.responseTimeout = responseTimeout;
    
        // Start of user code setterFinalize:responseTimeout
        // End of user code
    }
    
    // Start of user code setterAnnotation:missionDeadline
    // End of user code
    public void setMissionDeadline(final Date missionDeadline )
    {
        // Start of user code setterInit:missionDeadline
        // End of user code
        this.missionDeadline = missionDeadline;
    
        // Start of user code setterFinalize:missionDeadline
        // End of user code
    }
    
    
    static public String goalToHtmlForCreation (final HttpServletRequest httpServletRequest)
    {
        String s = "";
    
        // Start of user code "Init:goalToHtmlForCreation(...)"
        // End of user code
    
        s = s + "<label for=\"goal\">goal: </LABEL>";
    
        // Start of user code "Mid:goalToHtmlForCreation(...)"
        // End of user code
    
        // Start of user code "Finalize:goalToHtmlForCreation(...)"
        // End of user code
    
        return s;
    }
    
    static public String responseTimeoutToHtmlForCreation (final HttpServletRequest httpServletRequest)
    {
        String s = "";
    
        // Start of user code "Init:responseTimeoutToHtmlForCreation(...)"
        // End of user code
    
        s = s + "<label for=\"responseTimeout\">responseTimeout: </LABEL>";
    
        // Start of user code "Mid:responseTimeoutToHtmlForCreation(...)"
        // End of user code
    
        s= s + "<input name=\"responseTimeout\" type=\"text\" style=\"width: 400px\" id=\"responseTimeout\" >";
        // Start of user code "Finalize:responseTimeoutToHtmlForCreation(...)"
        // End of user code
    
        return s;
    }
    
    static public String missionDeadlineToHtmlForCreation (final HttpServletRequest httpServletRequest)
    {
        String s = "";
    
        // Start of user code "Init:missionDeadlineToHtmlForCreation(...)"
        // End of user code
    
        s = s + "<label for=\"missionDeadline\">missionDeadline: </LABEL>";
    
        // Start of user code "Mid:missionDeadlineToHtmlForCreation(...)"
        // End of user code
    
        s= s + "<input name=\"missionDeadline\" type=\"text\" style=\"width: 400px\" id=\"missionDeadline\" >";
        // Start of user code "Finalize:missionDeadlineToHtmlForCreation(...)"
        // End of user code
    
        return s;
    }
    
    
    public String goalToHtml()
    {
        String s = "";
    
        // Start of user code goaltoHtml_mid
        // End of user code
    
        try {
            if (goal == null) {
                s = s + "<em>null</em>";
            }
            else {
                s = s + goal.toHtml(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        // Start of user code goaltoHtml_finalize
        // End of user code
    
        return s;
    }
    
    public String responseTimeoutToHtml()
    {
        String s = "";
    
        // Start of user code responseTimeouttoHtml_mid
        // End of user code
    
        try {
            if (responseTimeout == null) {
                s = s + "<em>null</em>";
            }
            else {
                s = s + responseTimeout.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        // Start of user code responseTimeouttoHtml_finalize
        // End of user code
    
        return s;
    }
    
    public String missionDeadlineToHtml()
    {
        String s = "";
    
        // Start of user code missionDeadlinetoHtml_mid
        // End of user code
    
        try {
            if (missionDeadline == null) {
                s = s + "<em>null</em>";
            }
            else {
                s = s + missionDeadline.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        // Start of user code missionDeadlinetoHtml_finalize
        // End of user code
    
        return s;
    }
    
    
}
