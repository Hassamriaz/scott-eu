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

import eu.scott.warehouse.domains.pddl.PddlDomainConstants;
import eu.scott.warehouse.domains.pddl.PddlDomainConstants;
import eu.scott.warehouse.domains.pddl.IDomain;
import eu.scott.warehouse.domains.pddl.IPddlObject;

// Start of user code imports
// End of user code

@OslcNamespace(PddlDomainConstants.PROBLEM_NAMESPACE)
@OslcName(PddlDomainConstants.PROBLEM_LOCALNAME)
@OslcResourceShape(title = "Problem Resource Shape", describes = PddlDomainConstants.PROBLEM_TYPE)
public interface IProblem
{

    public void addInit(final Link init );
    public void addPddlObject(final Link pddlObject );

    @OslcName("domain")
    @OslcPropertyDefinition(PddlDomainConstants.SCOTT_PDDL_2_1_SUBSET_SPEC_NAMSPACE + "domain")
    @OslcDescription("Problem planning domain.")
    @OslcOccurs(Occurs.ExactlyOne)
    @OslcValueType(ValueType.Resource)
    @OslcRange({PddlDomainConstants.DOMAIN_TYPE})
    @OslcReadOnly(false)
    public Link getDomain();

    @OslcName("goal")
    @OslcPropertyDefinition(PddlDomainConstants.SCOTT_PDDL_2_1_SUBSET_SPEC_NAMSPACE + "goal")
    @OslcDescription("Problem goal.")
    @OslcOccurs(Occurs.ExactlyOne)
    @OslcValueType(ValueType.Resource)
    @OslcReadOnly(false)
    public Link getGoal();

    @OslcName("init")
    @OslcPropertyDefinition(PddlDomainConstants.SCOTT_PDDL_2_1_SUBSET_SPEC_NAMSPACE + "init")
    @OslcDescription("Problem init.")
    @OslcOccurs(Occurs.ZeroOrMany)
    @OslcValueType(ValueType.Resource)
    @OslcReadOnly(false)
    public HashSet<Link> getInit();

    @OslcName("maximize")
    @OslcPropertyDefinition(PddlDomainConstants.SCOTT_PDDL_2_1_SUBSET_SPEC_NAMSPACE + "maximize")
    @OslcDescription("Maximization metric.")
    @OslcOccurs(Occurs.ZeroOrOne)
    @OslcValueType(ValueType.Resource)
    @OslcReadOnly(false)
    public Link getMaximize();

    @OslcName("minimize")
    @OslcPropertyDefinition(PddlDomainConstants.SCOTT_PDDL_2_1_SUBSET_SPEC_NAMSPACE + "minimize")
    @OslcDescription("Minimization metric.")
    @OslcOccurs(Occurs.ZeroOrOne)
    @OslcValueType(ValueType.Resource)
    @OslcReadOnly(false)
    public Link getMinimize();

    @OslcName("pddlObject")
    @OslcPropertyDefinition(PddlDomainConstants.SCOTT_PDDL_2_1_SUBSET_SPEC_NAMSPACE + "pddlObject")
    @OslcDescription("Problem objects.")
    @OslcOccurs(Occurs.ZeroOrMany)
    @OslcValueType(ValueType.Resource)
    @OslcRange({PddlDomainConstants.OBJECT_TYPE})
    @OslcReadOnly(false)
    public HashSet<Link> getPddlObject();

    @OslcName("label")
    @OslcPropertyDefinition(PddlDomainConstants.SCOTT_PDDL_2_1_SUBSET_SPEC_NAMSPACE + "label")
    @OslcDescription("Parameter name.")
    @OslcOccurs(Occurs.ExactlyOne)
    @OslcValueType(ValueType.String)
    @OslcReadOnly(false)
    public String getLabel();


    public void setDomain(final Link domain );
    public void setGoal(final Link goal );
    public void setInit(final HashSet<Link> init );
    public void setMaximize(final Link maximize );
    public void setMinimize(final Link minimize );
    public void setPddlObject(final HashSet<Link> pddlObject );
    public void setLabel(final String label );
}

