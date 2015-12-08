/*
 * COSC-5302 AOS, 2015 Spring /Project
 * 
 * Author: Shaomin (Samuel) Zhang
 * 
 * Email : smicn@foxmail.com
 * */
package com.lamar.aos.swn;
 
import java.io.*;
import java.util.*;

/** 
 * Samuel notes at 15:00 Apr 20, 2015, quoted from:
 *
 * http://rosettacode.org/wiki/Dijkstra%27s_algorithm 
 * 
 * For Dijkstra Shortest Path Algorithm.
 **/
/**
 * Samuel notes at 22:00 Apr 30, 2015:
 * 
 * this class is deprecated, since we did not benefit from Dijkstra's.
 **/
class Graph {
   /** mapping of vertex names to Vertex objects, built from a set of Edges */
   protected Map<String, Vertex> graph; 
 
   /** One edge of the graph (only used by Graph constructor) */
   public static class Edge {
      public final String v1, v2;
      public final int dist;
      public Edge(String v1, String v2, int dist) {
         this.v1 = v1;
         this.v2 = v2;
         this.dist = dist;
      }
   }
 
   /** One vertex of the graph, complete with mappings to neighbouring vertices */
   public static class Vertex implements Comparable<Vertex> {
      public final String name;
      public int dist = Integer.MAX_VALUE; // MAX_VALUE assumed to be infinity
      public Vertex previous = null;
      public final Map<Vertex, Integer> neighbours = new HashMap<Vertex, Integer>();
 
      public Vertex(String name) {
         this.name = name;
      }
 
      private void printPath() {
         if (this == this.previous) {
            System.out.printf("%s", this.name);
         } else if (this.previous == null) {
            System.out.printf("%s(unreached)", this.name);
         } else {
            this.previous.printPath();
            System.out.printf(" -> %s(%d)", this.name, this.dist);
         }
      }
      
      private int calcHop() {
    	  if (this == this.previous) {
              return 0;
           } else if (this.previous == null) {
        	   DBG.e("error: " + this.name + " unreached!");
              return 1000 * 1000;
           } else {
              return 1 + this.previous.calcHop();
           }
      }
 
      public int compareTo(Vertex other) {
         //return Integer.compare(dist, other.dist);
         return (dist - other.dist);
      }
   }
 
   /** Constructs with null input **/
   public Graph() {
	   graph = null;
   }
   
   /** Builds a graph from a set of edges */
   protected void build(Edge[] edges) {
      graph = new HashMap<String, Vertex>(edges.length);
 
      //one pass to find all vertices
      for (Edge e : edges) {
         if (!graph.containsKey(e.v1)) graph.put(e.v1, new Vertex(e.v1));
         if (!graph.containsKey(e.v2)) graph.put(e.v2, new Vertex(e.v2));
      }
 
      //another pass to set neighbouring vertices
      for (Edge e : edges) {
         graph.get(e.v1).neighbours.put(graph.get(e.v2), e.dist);
         //graph.get(e.v2).neighbours.put(graph.get(e.v1), e.dist); // also do this for an undirected graph
      }
   }
 
   /** Runs dijkstra using a specified source vertex */ 
   protected void dijkstra(String startName) {
      if (!graph.containsKey(startName)) {
         System.err.printf("Graph doesn't contain start vertex \"%s\"\n", startName);
         return;
      }
      final Vertex source = graph.get(startName);
      NavigableSet<Vertex> q = new TreeSet<Vertex>();
 
      // set-up vertices
      for (Vertex v : graph.values()) {
         v.previous = v == source ? source : null;
         v.dist = v == source ? 0 : Integer.MAX_VALUE;
         q.add(v);
      }
 
      dijkstra(q);
   }
 
   /** Implementation of dijkstra's algorithm using a binary heap. */
   protected void dijkstra(final NavigableSet<Vertex> q) {      
      Vertex u, v;
      while (!q.isEmpty()) {
 
         u = q.pollFirst(); // vertex with shortest distance (first iteration will return source)
         if (u.dist == Integer.MAX_VALUE) break; // we can ignore u (and any other remaining vertices) since they are unreachable
 
         //look at distances to each neighbour
         for (Map.Entry<Vertex, Integer> a : u.neighbours.entrySet()) {
            v = a.getKey(); //the neighbour in this iteration
 
            final int alternateDist = u.dist + a.getValue();
            if (alternateDist < v.dist) { // shorter path to neighbour found
               q.remove(v);
               v.dist = alternateDist;
               v.previous = u;
               q.add(v);
            } 
         }
      }
   }
 
   /** Prints a path from the source to the specified vertex */
   protected void printPath(String endName) {
      if (!graph.containsKey(endName)) {
         System.err.printf("Graph doesn't contain end vertex \"%s\"\n", endName);
         return;
      }
 
      graph.get(endName).printPath();
      System.out.println();
   }
   
   /** Prints the path from the source to every vertex (output order is not guaranteed) */
   protected void printAllPaths() {
      for (Vertex v : graph.values()) {
         v.printPath();
         System.out.println();
      }
   }

   protected int calcDiameter(String endName) {
      if (!graph.containsKey(endName)) {
         return -1;
      }
	  return graph.get(endName).calcHop();
   }

   /**
	 * Samuel notes at 22:00 Apr 30, 2015:
	 * 
	 * Test results for Dijkstra's are not as good as expected.
	 **/
   public static void testDijkstra() {
	   final Graph.Edge[] GRAPH = {
	      new Graph.Edge("a", "b", 7),
	      new Graph.Edge("b", "a", 7),
	      new Graph.Edge("a", "c", 9),
	      new Graph.Edge("a", "f", 14),
	      new Graph.Edge("b", "c", 10),
	      new Graph.Edge("b", "d", 15),
	      new Graph.Edge("c", "d", 11),
	      new Graph.Edge("c", "f", 2),
	      new Graph.Edge("d", "e", 6),
	      new Graph.Edge("e", "f", 9),
	   };
	   String START = "a";
	   String END = "e";

	   Graph g = new Graph();
	   g.build(GRAPH);
	   g.dijkstra(START);
	   g.printPath(END);
	   g.printAllPaths();
	   
	   System.out.println("a->a: " + g.calcDiameter("a"));
	   System.out.println("a->b: " + g.calcDiameter("b"));
	   System.out.println("a->c: " + g.calcDiameter("c"));
	   System.out.println("a->d: " + g.calcDiameter("d"));
	   System.out.println("a->e: " + g.calcDiameter("e"));
	   System.out.println("a->f: " + g.calcDiameter("f"));
	}
}
