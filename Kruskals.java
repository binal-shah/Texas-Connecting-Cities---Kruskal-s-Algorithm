package com.company;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Binal Shah
 */
import java.io.*;
import java.util.*;

public class Kruskals
{
    public static class Edge implements Comparable<Edge>
    {
        int dist; //distance
        String u , v;

        Edge(String s1 , String s2,int d)
        {
            this.u = s1;
            this.v = s2;
            this.dist = d;
        }
        public int compareTo(Edge e)
        {
            if (this.dist < e.dist)
                return -1;
            else if (this.dist > e.dist)
                return 1;
            else
                return 0;
        }
    }

    public static void kruskals()
    {

        BufferedReader fileReader = null;

        ArrayList<Edge> eList = new ArrayList<>();
        ArrayList<String> num_vertices = new ArrayList<>();

        try
        {
            String line = "";
            fileReader = new BufferedReader(new FileReader("assn9_data.csv"));

            while ((line = fileReader.readLine()) != null)
            {
                String[] a = line.split(",");
                num_vertices.add(a[0]);
                eList.add( new Edge(a[0],a[1],Integer.parseInt(a[2])));

                for (int i=3 ; i<a.length ; i++)
                {
                    eList.add(new Edge(a[0],a[i],Integer.parseInt(a[i+1])));
                    i++ ;
                }
            }
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
        //implementation of pseudocode
        int total = 0;
        int edgeAccepted = 0 ;
        DisjSets ds = new DisjSets(num_vertices.size());
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        for (Edge e : eList)
        {
            pq.add(e);
        }

        while (edgeAccepted < num_vertices.size()-1 )
        {
            Edge	e = pq.poll(); //get minimum edge=(u,v)
            int uset=ds.find(num_vertices.indexOf(new String(e.u)));
            int vset=ds.find(num_vertices.indexOf(new String(e.v)));
            if (uset != vset) //if not same set (not yet connected)
            {
                //accept the edge
                edgeAccepted++ ;
                ds.union(uset,vset); //connect them
                System.out.println(e.u + " --> " + e.v );
                total = total + e.dist ;
                System.out.println("Distance = " + e.dist);
            }
        }
        System.out.println();
        System.out.println("Kruskal's Minimum Spanning Tree, sum of all the distances of accepted edges:  = " + total);
    }
    public static void main(String args[])
    {
        Kruskals kruskals = new Kruskals();
        System.out.println("Kruskal's Minimum Spanning Tree Accepted edges with the distance between them: ");

        System.out.println();
        kruskals.kruskals();
    }
}

