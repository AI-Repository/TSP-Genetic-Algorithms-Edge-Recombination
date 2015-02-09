# TSP-Genetic-Algorithms
This project was develop with the objective of solving the Travelling salesman problem as a **Genetic Algorithm**. 

**Edge Recombination** was introduced as a specialized operator for TSP. 
The motivation behind this operator is that it should preserve the adjacency between permutation elements, since the cost of a tour in a TSP is directly related to the set of adjacency relationships that exist s between permutation elements. 
 
 The **PATH CLASS** is responsable for this operator:

![UML Project](https://cloud.githubusercontent.com/assets/6472330/6099654/0f9692e0-aff6-11e4-8a77-455e0239f2b4.PNG)

The **input** program is a sequence of lines. The first line have the number of cities.
Next is a sequence of N lines, each with 3 numbers: a city ID (an integer), and its coordinates, X and Y, both real numbers, which represent the location of the city:

6 
 1 565.0 575.0 
 2 25.0 185.0 
 3 345.0 750.0 
 4 945.0 685.0 
 5 845.0 655.0 
 6 880.0 660.0 
