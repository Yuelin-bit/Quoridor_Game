/*
 * (C) Copyright 2015-2018, by Alexey Kudinkin and Contributors.
 *
 * JGraphT : a free Java graph-theory library
 *
 * See the CONTRIBUTORS.md file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0, or the
 * GNU Lesser General Public License v2.1 or later
 * which is available at
 * http://www.gnu.org/licenses/old-licenses/lgpl-2.1-standalone.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR LGPL-2.1-or-later
 */
package org.jgrapht.alg.interfaces;

import java.util.*;

/**
 * Allows to derive <a href="https://en.wikipedia.org/wiki/Maximum_flow_problem">maximum-flow</a>
 * from the supplied <a href="https://en.wikipedia.org/wiki/Flow_network">flow network</a>
 *
 * @author Alexey Kudinkin
 * @author Joris Kinable
 *
 * @param <V> the graph vertex type
 * @param <E> the graph edge type
 *
 */
public interface MaximumFlowAlgorithm<V, E>
    extends
    FlowAlgorithm<V, E>
{

    /**
     * Sets current source to <tt>source</tt>, current sink to <tt>sink</tt>, then calculates
     * maximum flow from <tt>source</tt> to <tt>sink</tt>. Returns an object containing detailed
     * information about the flow.
     *
     * @param source source of the flow inside the network
     * @param sink sink of the flow inside the network
     *
     * @return maximum flow
     */
    MaximumFlow<E> getMaximumFlow(V source, V sink);

    /**
     * Sets current source to <tt>source</tt>, current sink to <tt>sink</tt>, then calculates
     * maximum flow from <tt>source</tt> to <tt>sink</tt>. Note, that <tt>source</tt> and
     * <tt>sink</tt> must be vertices of the <tt>
     * network</tt> passed to the constructor, and they must be different.
     *
     * @param source source vertex
     * @param sink sink vertex
     * @return the value of the maximum flow
     * @deprecated replaced by @link{getMaximumFlowValue}
     */
    @Deprecated
    default double calculateMaximumFlow(V source, V sink)
    {
        return getMaximumFlow(source, sink).getValue();
    }

    /**
     * Sets current source to <tt>source</tt>, current sink to <tt>sink</tt>, then calculates
     * maximum flow from <tt>source</tt> to <tt>sink</tt>. Note, that <tt>source</tt> and
     * <tt>sink</tt> must be vertices of the <tt>
     * network</tt> passed to the constructor, and they must be different.
     *
     * @param source source vertex
     * @param sink sink vertex
     * @return the value of the maximum flow
     */
    default double getMaximumFlowValue(V source, V sink)
    {
        return getMaximumFlow(source, sink).getValue();
    }

    /**
     * Returns maximum flow value, that was calculated during last <tt>
     * calculateMaximumFlow</tt> call.
     *
     * NOTE: this function currently has a default implementation to guarantee backwards
     * compatibility. This function should be enforced in the next version.
     *
     * @return maximum flow value
     * @deprecated method has been removed
     */
    @Deprecated
    default double getMaximumFlowValue()
    {
        throw new UnsupportedOperationException("Function not implemented");
    }

    /**
     * A maximum flow
     *
     * @param <E> the graph edge type
     */
    interface MaximumFlow<E>
        extends
        Flow<E>
    {
        /**
         * Returns value of the maximum-flow for the given network
         *
         * @return value of the maximum-flow
         */
        Double getValue();

        /**
         * Returns mapping from edge to flow value through this particular edge
         *
         * @return maximum flow
         * @deprecated Method renamed to getFlowMap
         */
        @Deprecated
        default Map<E, Double> getFlow()
        {
            return getFlowMap();
        }
    }

    /**
     * Default implementation of the maximum flow
     *
     * @param <E> the graph edge type
     */
    class MaximumFlowImpl<E>
        extends
        FlowImpl<E>
        implements
        MaximumFlow<E>
    {
        private Double value;

        /**
         * Create a new maximum flow
         * 
         * @param value the flow value
         * @param flow the flow map
         */
        public MaximumFlowImpl(Double value, Map<E, Double> flow)
        {
            super(flow);
            this.value = value;
        }

        @Override
        public Double getValue()
        {
            return value;
        }

        @Override
        public String toString()
        {
            return "Flow Value: " + value + "\nFlow map:\n" + getFlowMap();
        }
    }
}
