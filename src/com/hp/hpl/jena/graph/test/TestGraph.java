/*
  (c) Copyright 2002, 2003 Hewlett-Packard Development Company, LP
  [See end of file]
  $Id: TestGraph.java,v 1.19 2003-09-22 12:16:28 chris-dollin Exp $
*/

package com.hp.hpl.jena.graph.test;

/**
    Tests that check GraphMem and WrappedGraph for correctness against the Graph
    and reifier test suites.
 
    @author kers
*/

import com.hp.hpl.jena.mem.*;
import com.hp.hpl.jena.shared.ReificationStyle;
import com.hp.hpl.jena.graph.*;
import com.hp.hpl.jena.graph.impl.*;

import junit.framework.*;

public class TestGraph extends GraphTestBase
    { 
	public TestGraph( String name )
		{ super( name ); }
        
    /**
        Answer a test suite that runs the Graph and Reifier tests on GraphMem and on
        WrappedGraphMem, the latter standing in for testing WrappedGraph.
     */
    public static TestSuite suite()
        { 
        TestSuite result = new TestSuite( TestGraph.class );
        result.addTest( suite( MetaTestGraph.class, GraphMem.class ) );
        result.addTest( suite( TestReifier.class, GraphMem.class ) );
        result.addTest( suite( MetaTestGraph.class, WrappedGraphMem.class ) );
        result.addTest( suite( TestReifier.class, WrappedGraphMem.class ) );
        return result;
        }
        
    public static TestSuite suite( Class classWithTests, Class graphClass )
        { return MetaTestGraph.suite( classWithTests, graphClass ); }
        
    /**
        Trivial [incomplete] test that a Wrapped graph pokes through to the underlying
        graph. Really want something using mock classes. Will think about it. 
    */
    public void testWrappedSame()
        {
        Graph m = new GraphMem();
        Graph w = new WrappedGraph( m );
        graphAdd( m, "a trumps b; c eats d" );
        assertIsomorphic( m, w );
        graphAdd( w, "i write this; you read that" );
        assertIsomorphic( w, m );
        }        
        
    /**
        Class to provide a constructor that produces a wrapper round a GraphMem.    
    	@author kers
    */
    public static class WrappedGraphMem extends WrappedGraph
        {
        public WrappedGraphMem( ReificationStyle style ) 
            { super( new GraphMem( style ) ); }  
        }
    }

/*
    (c) Copyright 2002, 2003 Hewlett-Packard Development Company, LP
    All rights reserved.

    Redistribution and use in source and binary forms, with or without
    modification, are permitted provided that the following conditions
    are met:

    1. Redistributions of source code must retain the above copyright
       notice, this list of conditions and the following disclaimer.

    2. Redistributions in binary form must reproduce the above copyright
       notice, this list of conditions and the following disclaimer in the
       documentation and/or other materials provided with the distribution.

    3. The name of the author may not be used to endorse or promote products
       derived from this software without specific prior written permission.

    THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
    IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
    OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
    IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
    INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
    NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
    DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
    THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
    (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
    THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
