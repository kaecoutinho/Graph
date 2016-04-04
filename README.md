# Graph

**Graph**'s repository

Created by Kaê Angeli Coutinho

_MIT license_

## Introduction

**Graph** is a _Java_ library and project that implements a graph data structure and makes it possible to easily simulate its behavior and definition through its algorithms.

Some classic and essential algorithms are implemented within the project, such as checking if it's a tree, cyclic or a clique.

_NetBeans_ was used as the main IDE but as the source code is all written in _Java_, it's possible to generate a _jar_ file to use as library, copy the source files to a new project in another IDE or even compile through command-line interface.

## Usage

#### Building a graph structure

First things first, in order to build a graph we need some vertices, let's instantiate a few of them.

``` java
Vertex vertexA = new Vertex("A");
Vertex vertexB = new Vertex("B");
Vertex vertexC = new Vertex("C");
Vertex vertexD = new Vertex("D");
```

Now that we already have the vertices, it's time to instantiate a graph.

``` java
Graph graph = new Graph("G",'G',vertexA,vertexB,vertexC,vertexD);
```

We now have the graph "G" instance with all the previous instantiated vertices but they aren't linked yet. Time to define some edges between them.

``` java
vertexA.addAdjacentVertex(vertexB);
vertexA.addAdjacentVertex(vertexC);
vertexB.addAdjacentVertex(vertexC);
vertexC.addAdjacentVertex(vertexD);
```

There we go, we just created a fully functional graph, it's more than time to print its information and execute some _Graph Theory_ algorithms on it.

``` java
graph.printInfo();

Graph tempGraph;

tempGraph = graph.getSubraphByRemovingSet(vertexA,vertexD);
tempGraph.printInfo();

tempGraph = graph.getSetsSubgraph(vertexA,vertexD);
tempGraph.printInfo();

tempGraph = graph.getComplement();
tempGraph.printInfo();

tempGraph = graph.getCompleteGraph();
tempGraph.printInfo();
```

As you could see, it's extremely easy to learn the library and use it. Also, it may be useful for academic projects and purposes.

There's also a example file provided in the project folder called [_Test.java_](https://github.com/kaiky25/Graph/blob/master/Source%20Code/Graph/src/runtime/Test.java), check it out later on.

## Changelog

#### Version 1.0

<ul>
  <li>Raw Graph library</li>
  <li>First batch of <i>Graph Theory</i> algorithms</li>
</ul>
