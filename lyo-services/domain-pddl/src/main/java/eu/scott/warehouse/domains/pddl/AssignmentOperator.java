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

package eu.scott.warehouse.domains.pddl;

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

import eu.scott.warehouse.domains.pddl.PddlDomainConstants;


import eu.scott.warehouse.domains.pddl.PddlDomainConstants;

// Start of user code imports
// End of user code

// Start of user code preClassCode
// End of user code

// Start of user code classAnnotations
// End of user code
@OslcNamespace(PddlDomainConstants.ASSIGNMENTOPERATOR_NAMESPACE)
@OslcName(PddlDomainConstants.ASSIGNMENTOPERATOR_LOCALNAME)
@OslcResourceShape(title = "AssignmentOperator Resource Shape", describes = PddlDomainConstants.ASSIGNMENTOPERATOR_TYPE)
public class AssignmentOperator
    extends AbstractResource
    implements IAssignmentOperator
{
    // Start of user code attributeAnnotation:argument
    // End of user code
    private Boolean argument;
    // Start of user code attributeAnnotation:parameter
    // End of user code
    private Link parameter = new Link();
    
    // Start of user code classAttributes
    // End of user code
    // Start of user code classMethods
    // End of user code
    public AssignmentOperator()
           throws URISyntaxException
    {
        super();
    
        // Start of user code constructor1
        // End of user code
    }
    
    public AssignmentOperator(final URI about)
           throws URISyntaxException
    {
        super(about);
    
        // Start of user code constructor2
        // End of user code
    }
    
    
    public static ResourceShape createResourceShape() throws OslcCoreApplicationException, URISyntaxException {
        return ResourceShapeFactory.createResourceShape(OSLC4JUtils.getServletURI(),
        OslcConstants.PATH_RESOURCE_SHAPES,
        PddlDomainConstants.ASSIGNMENTOPERATOR_PATH,
        AssignmentOperator.class);
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
            result = result + "{a Local AssignmentOperator Resource} - update AssignmentOperator.toString() to present resource as desired.";
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
    
    
    // Start of user code getterAnnotation:argument
    // End of user code
    @OslcName("argument")
    @OslcPropertyDefinition(PddlDomainConstants.SCOTT_PDDL_2_1_SUBSET_SPEC_NAMSPACE + "argument")
    @OslcDescription("Assignment operator argument (fluent).")
    @OslcOccurs(Occurs.ExactlyOne)
    @OslcValueType(ValueType.Boolean)
    @OslcReadOnly(false)
    public Boolean isArgument()
    {
        // Start of user code getterInit:argument
        // End of user code
        return argument;
    }
    
    // Start of user code getterAnnotation:parameter
    // End of user code
    @OslcName("parameter")
    @OslcPropertyDefinition(PddlDomainConstants.SCOTT_PDDL_2_1_SUBSET_SPEC_NAMSPACE + "parameter")
    @OslcDescription("Condition (same as precondition).")
    @OslcOccurs(Occurs.ExactlyOne)
    @OslcValueType(ValueType.Resource)
    @OslcReadOnly(false)
    public Link getParameter()
    {
        // Start of user code getterInit:parameter
        // End of user code
        return parameter;
    }
    
    
    // Start of user code setterAnnotation:argument
    // End of user code
    public void setArgument(final Boolean argument )
    {
        // Start of user code setterInit:argument
        // End of user code
        this.argument = argument;
    
        // Start of user code setterFinalize:argument
        // End of user code
    }
    
    // Start of user code setterAnnotation:parameter
    // End of user code
    public void setParameter(final Link parameter )
    {
        // Start of user code setterInit:parameter
        // End of user code
        this.parameter = parameter;
    
        // Start of user code setterFinalize:parameter
        // End of user code
    }
    
    
    static public String argumentToHtmlForCreation (final HttpServletRequest httpServletRequest)
    {
        String s = "";
    
        // Start of user code "Init:argumentToHtmlForCreation(...)"
        // End of user code
    
        s = s + "<label for=\"argument\">argument: </LABEL>";
    
        // Start of user code "Mid:argumentToHtmlForCreation(...)"
        // End of user code
    
        s= s + "<input name=\"argument\" type=\"radio\" value=\"true\">True<input name=\"argument\" type=\"radio\" value=\"false\">False";
        // Start of user code "Finalize:argumentToHtmlForCreation(...)"
        // End of user code
    
        return s;
    }
    
    static public String parameterToHtmlForCreation (final HttpServletRequest httpServletRequest)
    {
        String s = "";
    
        // Start of user code "Init:parameterToHtmlForCreation(...)"
        // End of user code
    
        s = s + "<label for=\"parameter\">parameter: </LABEL>";
    
        // Start of user code "Mid:parameterToHtmlForCreation(...)"
        // End of user code
    
        // Start of user code "Finalize:parameterToHtmlForCreation(...)"
        // End of user code
    
        return s;
    }
    
    
    public String argumentToHtml()
    {
        String s = "";
    
        // Start of user code argumenttoHtml_mid
        // End of user code
    
        try {
            if (argument == null) {
                s = s + "<em>null</em>";
            }
            else {
                s = s + argument.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        // Start of user code argumenttoHtml_finalize
        // End of user code
    
        return s;
    }
    
    public String parameterToHtml()
    {
        String s = "";
    
        // Start of user code parametertoHtml_mid
        // End of user code
    
        try {
            if ((parameter == null) || (parameter.getValue() == null)) {
                s = s + "<em>null</em>";
            }
            else {
                s = s + parameter.getValue().toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        // Start of user code parametertoHtml_finalize
        // End of user code
    
        return s;
    }
    
    
}
